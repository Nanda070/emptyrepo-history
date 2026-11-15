import socket
import select
import sys

class ProxyServer:
  def __init__(self, web_address, addr='localhost', port=10001, timeout=1):
    self.web_address = web_address
    self.port = port
    self.proxy_server = self.setupServer(addr)
    self.proxy_client = self.setupClient()
    # Сокеты, из которых мы ожидаем чтения
    self.inputs = [ self.proxy_server ]
    # Подождите, пока хотя бы один из сокетов будет готов к обработке.
    self.timeout=timeout
    self.cache = {}

  def setupServer(self, addr):
    # Создать сокет TCP/IP
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setblocking(0)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    
    # Привязать сокет к порту
    server_address = (addr, self.port)
    server.bind(server_address)
    
    # Слушайте входящие соединения
    server.listen(5)
    return server

  def setupClient(self):
    proxy_client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    proxy_client.connect(self.web_address)
    return proxy_client

  def handleNewConnection(self, sock):
    # «Читаемый» серверный сокет готов принять соединение.
    connection, client_address = sock.accept()
    print(client_address)
    connection.setblocking(0)	# или Connection.settimeout(1.0)
    self.inputs.append(connection)

  def forwardRequest(self, connection, data):
    data = data.decode('utf-8')
# распечатать (данные)
    data = data.replace('localhost:'+str(self.port),self.web_address[0]).encode()
# печать(data.decode('utf-8'))
    self.proxy_client.sendall(data)
    dataFromServer = self.proxy_client.recv(65000)
    connection.sendall(dataFromServer)

  def handleDataFromClient(self, sock):
    data = sock.recv(65000)
    if data:            
        self.forwardRequest(sock, data)
    # Прекратить прослушивание входных данных о соединении
    self.inputs.remove(sock)
    sock.close()

  def handleInputs(self, readable):
    for sock in readable:
        if sock is self.proxy_server:
            self.handleNewConnection(sock)
        else:
            self.handleDataFromClient(sock)

  def handleConnections(self):
    while self.inputs:
      try:
        readable, writable, exceptional = select.select(self.inputs, [], self.inputs, self.timeout)
    
        if not (readable or writable or exceptional):
            continue
    
        self.handleInputs(readable)
      except KeyboardInterrupt:
        print("Close the system")
        for c in self.inputs:
            c.close()
        self.inputs = []
        self.proxy_client.close()

web_address = (sys.argv[1],80)
proxyServer = ProxyServer(web_address, port=int(sys.argv[2]))
proxyServer.handleConnections()