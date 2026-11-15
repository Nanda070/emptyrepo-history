import select
import socket
import sys
import queue


class ChatServer:
    def __init__(self, addr='localhost', port=10001, timeout=1):
        self.server, self.message_queues = self.setupServer(addr, port)
        # Сокеты, из которых мы ожидаем чтения
        self.inputs = [self.server]
        # Сокеты, в которые мы ожидаем писать
        self.outputs = []
        self.username = {}
        # Подождите, пока хотя бы один из сокетов будет готов к обработке.
        self.timeout = timeout

    def setupServer(self, addr, port):
        # Создать сокет TCP/IP
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.setblocking(0)
        server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        # Привязать сокет к порту
        server_address = (addr, port)
        server.bind(server_address)

        # Слушайте входящие соединения
        server.listen(5)

        # Очереди исходящих сообщений ([имя пользователя]:...)
        message_queues = queue.Queue()
        return server, message_queues

    def handleNewConnection(self, sock):
        # «Читаемый» серверный сокет готов принять соединение.
        connection, client_address = sock.accept()
        connection.setblocking(0)  # или Connection.settimeout(1.0)
        name = connection.recv(20)
        name = name.strip().decode('utf-8')
        print('new connection from ' + str(client_address) + " with username " + name)
        self.username[connection] = name
        self.message_queues.put("[" + self.username[connection] + "] is LOGIN")

        self.inputs.append(connection)
        self.outputs.append(connection)

    def handleSocketError(self, err, sock, writable):
        errorcode = err.args[0]
        if errorcode == 10054:  # WSAECONNRESET (это может произойти, когда клиент1 что-то отправляет, клиент2 печатает, поэтому сообщение не может прийти, но затем клиент2 внезапно завершает работу во время набора текста)
            print('The connection was forcibly closed by ' + str(sock.getpeername()))
            # Прекратить прослушивание входных данных о соединении
            if sock in self.outputs:
                self.outputs.remove(sock)
            self.inputs.remove(sock)
            writable.remove(sock)
            self.message_queues.put("[" + self.username[sock] + "] is LOGOUT")
        else:
            raise  # Повторное исключение исключения

    def handleDataFromClient(self, sock, writable):
        try:
            data = sock.recv(1024)
        except socket.error as err:
            self.handleSocketError(err, sock, writable)
        else:
            data = data.strip()
            if data:
                # Читаемый клиентский сокет содержит данные
                print('received "%s" from %s' % (data, sock.getpeername()))
                self.message_queues.put("[" + self.username[sock] + "]: " + data.decode('utf-8'))
            else:
                # Интерпретировать пустой результат как закрытое соединение
                print('closing ' + str(sock.getpeername()) + ' after reading no data')
                # Прекратить прослушивание входных данных о соединении
                if sock in self.outputs:
                    self.outputs.remove(sock)
                self.inputs.remove(sock)
                writable.remove(sock)
                sock.close()
                self.message_queues.put("[" + self.username[sock] + "] is LOGOUT")

    def handleInputs(self, readable, writable):
        for sock in readable:
            if sock is self.server:
                self.handleNewConnection(sock)
            else:
                self.handleDataFromClient(sock, writable)

    def handleOutputs(self, writable):
        while not self.message_queues.empty():
            try:
                next_msg = self.message_queues.get_nowait()
                print(next_msg)
            except queue.Empty:
                break
            else:
                for sock in writable:
                    print('sending "%s" to %s' % (next_msg, sock.getpeername()))
                    sock.sendall(next_msg.encode())

    def handleExceptionalCondition(self, exceptional):
        for sock in exceptional:
            print('handling exceptional condition for ' + str(sock.getpeername()))
            # Прекратить прослушивание входных данных о соединении
            self.inputs.remove(sock)
            if sock in self.outputs:
                self.outputs.remove(sock)
            sock.close()

    def handleConnections(self):
        while self.inputs:
            try:
                readable, writable, exceptional = select.select(self.inputs, self.outputs, self.inputs, self.timeout)

                if not (readable or writable or exceptional):
                    # время вышло, займитесь другой работой здесь
                    continue

                self.handleInputs(readable, writable)
                self.handleOutputs(writable)
                self.handleExceptionalCondition(exceptional)
            except KeyboardInterrupt:
                print("Close the system")
                for c in self.inputs:
                    c.close()
                self.inputs = []


chatServer = ChatServer()
chatServer.handleConnections()