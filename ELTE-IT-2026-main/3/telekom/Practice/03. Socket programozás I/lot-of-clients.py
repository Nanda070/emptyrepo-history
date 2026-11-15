import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
    server.bind(('localhost', 10000))
    server.listen(1)
    s1 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s3 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    s1.connect(('localhost', 10000))
    # поскольку размер очереди равен 1, сервер может принять только один запрос на соединение
    s2.connect(('localhost', 10000))
    s3.connect(('localhost', 10000))
