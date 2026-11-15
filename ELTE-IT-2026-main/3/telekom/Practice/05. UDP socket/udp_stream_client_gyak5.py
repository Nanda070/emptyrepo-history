import socket
import cv2
import numpy as np
import sys

if(len(sys.argv) != 3):
    print("Usage : {} hostname port".format(sys.argv[0]))
    print("e.g.   {} 192.168.0.39 1080".format(sys.argv[0]))
    sys.exit(-1)

# Следующая функция создает окно, идентификатор которого будет «Изображение» (это будет заполнитель для изображений, полученных по сети):
cv2.namedWindow("Image")

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.settimeout(1.0)
host = sys.argv[1]
port = int(sys.argv[2])
server_address = (host, port)

while(True):
    try:
        sent = sock.sendto("get".encode('utf-8'), server_address)

        data, server = sock.recvfrom(65507)
        print("Fragment size : {}".format(len(data)))
        if len(data) == 4:
            # Это сообщение об ошибке, отправленное сервером.
            if(data == b'FAIL'):
                continue
        # Данные поступают из сети с типом байтов, функция frombuffer ниже преобразует их в массив numpy, элементы которого типа uint8 будут байтами данных:
        array = np.frombuffer(data, dtype=np.dtype('uint8'))
        # цветное изображение в массиве байтов считывается в переменную img с помощью следующей функции:
        img = cv2.imdecode(array, 1)
        # Следующая функция отображает изображение img в окне «Изображение»:
        cv2.imshow("Image", img)
    except socket.timeout:
        pass
    # Следующее ждет 1 миллисекунду; необходимо, чтобы изображения отображались корректно:
    k = cv2.waitKey(1)
