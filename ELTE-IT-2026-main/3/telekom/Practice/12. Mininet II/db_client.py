import socket
import sys

server_addr = sys.argv[1]
server_port = int(sys.argv[2])

client_addr = sys.argv[3]
client_port = int(sys.argv[4])

# Например. (на хосте h3): python3 db_client.py 172.16.100.1 20000 192.168.100.1 10001

connection = socket.socket()
connection.connect((server_addr, server_port))

while True:
  try:
    query = input('query: ')
    connection.sendall(query.encode())
    result = connection.recv(1024)
    result = result.strip().decode()
    print(result)
  except KeyboardInterrupt:
    print("Close the system")
    connection.close()
    sys.exit()

# Ожидаемые результаты:
# запрос: SELECT * FROM DOGS WHERE возраст >= 5
# («Бейли», 5)
# («Коко», 10)
# запрос: ВЫБРАТЬ * ИЗ СОБАК, ГДЕ возраст < 5
# («Туз», 1)
# запрос: Закройте систему