isEven :: Int -> Bool
isEven n = mod n 2 == 0

isOdd :: Int -> Bool
isOdd n = mod n 2 /= 0

isOddBetter :: Int -> Bool
isOddBetter n = not (isEven n)

add :: Int -> Int -> Int
add a b = a + b

seven1 :: Int -> Int
seven1 = inc(inc(double(double(inc 1))))

isDivisibleBy :: Int -> Int -> Bool
isDivisibleBy n k = n `mod` k == 0

isDivBy4 :: Int -> Bool
isDivBy4 n = isDivisibleBy n 4 

isDivBy100 :: Int -> Bool
isDivBy100 n = isDivisibleBy n 100

isDivBy400 :: Int -> Bool
isDivBy400 n = isDivisibleBy n 400

isLeapYear :: Int -> Bool
isLeapYear n = isDivBy400(n) || isDivBy4(n) && not(isDivBy100(n))

-- и операторы более высокого уровня

sumSqrt :: Integral a => a -> a -> Double
sumSqrt a b = sqrt (fromIntegral (a + b))

-----------------
--Полиморфизм--
-----------------

--тип выражения можно узнать в GHCi с помощью команды :t <выражение>
--Например, :t (+) вернет (+) :: Num a => a -> a -> a
--a — параметр типа
--Num — класс типов (общее имя для числовых типов)
--Все типы, принадлежащие этому классу типов, поддерживают (+, -, *)
--Внутри Num дочерним классом типов является Integral (Int, Integer)
--Здесь уже работают функции mod, div
--Другой дочерний класс типов внутри Num — Fractional (Double, Float)
--Здесь работает оператор деления /

f1 :: Num a => a -> a
f1 x = 2*x+1

f2 :: Integral p1 => p1 -> Bool
f2 x = 3*x `div` 3 == 0

f3 :: Fractional a => a -> a 
f3 x = (x+100) / 7

--Eq(==) — класс типов, значения которых можно сравнивать на равенство
--Ord(<) — класс типов, значения которых можно упорядочивать/сравнивать по величине (является дочерним для Eq)

decreasing :: Ord a => a -> a -> a -> Bool
decreasing x y z = x >= y && y >= z

equal3 :: Eq a => a -> a -> a -> Bool
equal3 x y z = x == z && y == z

spike :: Eq a => a -> a -> a -> bool
spike x y z == z && y > x

--еще более общая функция

firstParam :: p1 -> p2 -> p1
firstParam a b = a