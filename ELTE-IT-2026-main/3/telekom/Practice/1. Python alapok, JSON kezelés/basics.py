# строго динамически типизированный интерпретируемый язык
# интерпретатор можно запустить из командной строки с помощью py/python (windows) python 3 (unix).
# язык не использует; и {} (конец строки — это новая строка, а блоки определяются размером отступа)

# переменные, запись
a = 42  # объявление переменной
b = 32

print(type(a))  # получить тип

c = 'valami'  # может быть строкой с ' и ", также существует многострочная строка """
d = """abv
sdasds
sdadsd"""
print(c)
print("d: %s" % d)  # форматированный вывод (c-подобный спецификатор)
print(f"a/b = {a/b}")  # форматированный вывод (fstring)
# print(a + c) не будет работать, поскольку система типов сильная.
print(str(a) + c)  # функция преобразования str (есть и для других типов)


# математические операторы
# доступны обычные математические операции
print(a+b)  # вывод: функция печати
print(a*b)
print(a/b)
print(a**2)  # дополнительно: возведение в степень
print(10 % 2)
a += 1  # нет оператора ++/--

round(3.88, 1)  # округление

import math  # модуль импорта
math.ceil(3.14)  # верхнее целое число
math.floor(3.2)  # нижнее целое число


# строковые операции
print('alma'.upper())
print('alma'.lower())
print("LO" in "HELLO")  # оператор in возвращает содержимое
print(len('alma'))  # длина строки

# списки
# гетерогенные структуры данных
players = [12, 31, 27, '48', 54]

print(players[0])       # Индексируем с 0
print(players[-1])      # его также можно индексировать сзади
print(players[2:4])     # вы также можете указать интервал (закрыто слева, открыто справа)
print(players[:-1])     # начало интервала — это первый элемент по умолчанию
print(players[1:])      # конец интервала — это последний элемент по умолчанию
print(len(players))     # длина списка
players.append(89)      # добавить в список
del players[2]          # удаление по индексу
players[2] = 48
print(sorted(players))  # список сортировки (только если однородный)
print(42 in players)    # тест содержания


# упорядоченные кортежи
# гетерогенная неизменяемая структура данных
playerst1 = (12, 31, 27, '48', 54)
playerst2 = tuple(players)  # преобразование из другой структуры данных

# наборы
mylist = [8, 3, 2, 3, 2, 4, 6, 8, 2]
myset1 = set(mylist)  # преобразование из другой структуры данных
myset2 = {8, 3, 2, 3, 2, 4, 6, 8, 2}
# набор операций
myset1.intersection(myset2)
myset1.union(myset2)
myset1.difference(myset2)


# словарь
team = {
    91: "Ayers, Robert",
    13: "Beckham Jr,",
    3: "Brown, Josh",
    54: "Casillas, Jonathan",
    21: "Collins, Landon"}
print(len(team))  # длина
print(team[3])  # получение элемента по заданному ключу
print(team.keys())  # получение набора ключей (можно преобразовать в список с помощью функции list())
print(team.values())  # получить значения
print(91 in team)  # тест содержания

# филиалы
if 100 in team:
    print('100: Yes')
elif 76 in team:
    print('76: Yes')
else:
    print('Neither')


# цикл
# для каждого
for i in players:
    print(i)

# подражая
for i in range(1, 10): # интервал диапазона
    print(i)

for i in range(2, 2, 10): # шаг можно указать
    print(i)

j = 3
while j < 5:
    j = j+1

# упорядоченные n также могут быть переменными цикла
for (k, v) in team.items():
    print(f"Playername: {v}, #: {к}")

# функции


def is_even(num):
    if (num % 2) == 0:
        return True
    else:
        return False


# может быть несколько возвращаемых значений из-за кортежей

def multiple_return_values(x):
    return x**2, x**3, x**4


print(multiple_return_values(2))
a, b, c = multiple_return_values(2)  # расширение кортежа
print(a, b, c)


# функциональные элементы
# генераторы
myGenList = [i**2 for i in range(1, 10)]
print(myGenList)

myGenDict = {i: i**2 for i in range(1, 5)}
print(myGenDict)

myGenTuple = (i for i in range(1, 4))
print(tuple(myGenTuple))

# карта и лямбда
temperatures = (36.5, 37, 37.5, 38, 39)
F = map(lambda x: (float(9)/5)*x + 32, temperatures)
print(list(F))

nums = [2, 32, 32, 32, 3, 123, 21, 321, 312, 32, 31, 23, 2, 32, 13, 23]
even_nums = filter(lambda x: not is_even(x),nums)
print(list(even_nums))


# управление файлами («r» — чтение, «w» — запись, «a» — добавление)
# при выходе из блока with файл закрывается
with open('teszt.txt', 'r') as file:
    # f.readline()
    # f.read()
    fdict = {}
    for i in file:
        line = i.split()  # Split разбивает строку на список по одному символу по умолчанию: пробел
        fdict[line[0]] = line[1]

    for k in fdict.keys():
        print(fdict[k])


# стандартный ввод
x = int(input("Szám: "))  # str всегда читается


# параметры командной строки
import sys
print(sys.argv[0])  # сценарий неве
#print(sys.argv[1]) # первый параметр ...


# занятия


class Hallgato:
    nev = ' '
    ZHpont = 0

    def __init__(self, _name, _point):  # конструктор
        self.nev = _name  # я == это
        self.ZHpont = _point

    def __str__(self):  # __str__ = toString
        return self.nev+"("+str(self.ZHpont)+")"


p = Hallgato("Ford", 20)
print(p)
print(p.nev)
p.nev = "Fort"
print(p.nev)

# основная функция может быть определена


def main():
    print("Ez a main")


if __name__ == "__main__":
    print ("Ez akkor fog lefutni ha scriptkent hivod meg, ha beimportálod másba nem!")
    main()

# JSON (нотация объекта Javascript)

import json

data = {
    "president": {
    "name": "Zaphod Beeblebrox",
    "species": "Betelgeusian"
    }
}

with open("data_file.json", "w") as write_file:
    json.dump(data, write_file)  # JSON-файл

json_string = json.dumps(data)  # преобразовать json в строку


# сканирование

# из файла
with open("data_file.json", "r") as read_file:
    data = json.load(read_file)
    print( data["president"]["name"] )

#из строки
json_string = """
{
    "researcher": {
        "name": "Ford Prefect",
        "species": "Betelgeusian",
        "relatives": [
            {
                "name": "Zaphod Beeblebrox",
                "species": "Betelgeusian"
            }
        ]
    }
}
"""
data = json.loads(json_string)
for rel in data["researcher"]["relatives"]:
    print('Name: %s (%s)' % ( rel["name"], rel["species"] ) )