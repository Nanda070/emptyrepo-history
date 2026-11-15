import struct
import select
import socket

unpacker = struct.Struct('d c d')


def unpack_expression(data):
    operand1, operator, operand2 = unpacker.unpack(data)

    output = f'{operand1} {operator.decode()} {operand2} = '

    match operator.decode():
        case '+':
            output += str(operand1 + operand2)
        case '-':
            output += str(operand1 - operand2)
        case '*':
            output += str(operand1 * operand2)
        case '/':
            output += str(operand1 / operand2)
    return output


class CalcTCPSelectServer:
    def __init__(self, address='localhost', port=10001, timeout=1):
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.setblocking(False)
        server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        server_socket.bind((address, port))

        server_socket.listen(5)

        self.server = server_socket

        # Сокеты, из которых мы ожидаем чтения
        self.inputs = [self.server]
        # Подождите, пока хотя бы один из сокетов будет готов к обработке.
        self.timeout = timeout

    def handle_new_connection(self, sock):
        # «Читаемый» серверный сокет готов принять соединение.
        connection, client_address = sock.accept()
        connection.setblocking(0)  # или Connection.settimeout(1.0)
        self.inputs.append(connection)

    def handle_client_data(self, sock):
        data = sock.recv(1024)
        data = data.strip()
        if data:
            unpacked = unpack_expression(data)
            print(unpacked)
            sock.sendall(unpacked.encode())
        else:
            # Интерпретировать пустой результат как закрытое соединение
            print('closing ' + str(sock.getpeername()) + ' after reading no data')
            # Прекратить прослушивание входных данных о соединении
            self.inputs.remove(sock)
            sock.close()

    def handle_inputs(self, readable):
        for sock in readable:
            if sock is self.server:
                self.handle_new_connection(sock)
            else:
                self.handle_client_data(sock)

    def handle_exception(self, exceptions):
        for sock in exceptions:
            print('handling exceptional condition for ' + str(sock.getpeername()))
            # Прекратить прослушивание входных данных о соединении
            self.inputs.remove(sock)
            sock.close()

    def handle_connections(self):
        while self.inputs:
            try:
                readable, writable, exceptions = select.select(self.inputs, [], self.inputs, self.timeout)

                if not (readable or writable or exceptions):
                    # время вышло, займитесь другой работой здесь
                    continue

                self.handle_inputs(readable)
                self.handle_exception(exceptions)
            except KeyboardInterrupt:
                print("Close the system")
                for c in self.inputs:
                    c.close()
                self.inputs = []


simpleTCPSelectServer = CalcTCPSelectServer()
simpleTCPSelectServer.handle_connections()
