import Data.List (findIndex, find, break)
import Data.Maybe (fromJust, isJust, isNothing)

--Задание/план урока по упражнению 10

-------------------
--Введите синонимы--
-------------------

--Пример:

data Point = Point (Float, Float) -- Новый тип
    deriving Show

origo :: Point -- =! (Плавать, Плавать)
origo = Point (0, 0)   

type IntPoint = (Int, Int) -- Не новый тип!
-- просто синоним другого типа, например. строковый синоним массива символов

intOrigo :: IntPoint -- == (Целое, Целое)
intOrigo = (0, 0)

newtype Point2 = P (Int, Int) deriving Show
-- единый конструктор данных и данные поля данных

origo2 :: Point2
origo2 = P (0, 0)

----------

type Neptun = String
type Name = String
type Student = (Neptun, Name)

students :: [Student]
students = [("DEF456", "Toth Tibor"), ("ABC123", "Kovacs Kata"), ("GHI789", "Nagy Elemer")]

type AssignmentResult = (Student, Int) --((String, String), Int) более прозрачен.
results :: [AssignmentResult]
results = zip students [30, 40 ..]

grade :: AssignmentResult -> (Student, Int)
grade (student, score) 
    | score >= 50 = (student, 5)
    | score >= 40 = (student, 4)
    | score >= 30 = (student, 3)
    | score >= 20 = (student, 2)
    | otherwise  = (student, 1)

----------    
type Matrix = [[Float]]

zeroMat :: Int -> Matrix    
zeroMat n = [[0 | i <- [1..n]] | j <- [1..n]]

            
----------------  
-- Может быть, напечатайте...
----------------

-- данные Возможно a = Ничего | Просто
-- получение (Показать, уравнение)

safeDiv :: Float -> Float -> Maybe Float 
safeDiv _ 0 = Nothing
safeDiv a b = Just $ a / b

--Давайте определим функцию принципа безопасности!
safeHead :: [a] -> Maybe a
safeHead [] = Nothing
safeHead (x:xs) = Just x

--Давайте определим функцию безопасного хвоста!
safeTail :: [a] -> Maybe [a]
safeTail [] = Nothing
safeTail [x] = Just [x]
safeTail (_:xs) = safeTail xs

-- найти, найти из Data.List

-- Учитывая два значения `Может быть`. Сложите их вместе, если они оба содержат значение, в противном случае результатом должно быть «Ничего»!
addMaybe :: Num a => Maybe a -> Maybe a -> Maybe a
addMaybe (Just x) (Just y) = Just $ x + y 
addMaybe _ _ = Nothing

--------------
-- Обязанности--
--------------

--Дан список, содержащий значения `Maybe a`. Посчитайте, сколько «Ничего» в списке!

-- countNothings [] == 0
-- countNothings [Всего 3, Всего 5] == 0
-- countNothings [Просто «Хаскелл», Просто «Очистить»] == 0
-- countNothings [Просто «Хаскелл», Просто «Чистый», Ничего] == 1
-- countNothings [Ничего, Просто «Хаскелл», Просто «Чистый», Ничего] == 2
-- countNothings [Ничего, Просто "Haskell", Ничего, Просто "Чистый", Ничего] == 3
-- countNothings [Ничего, Просто Ничего, Ничего, Просто Ничего, Ничего] == 3

countNothings :: [Maybe a] -> Int
countNothings [] = 0
countNothings (x:xs) = isNothing x + countNothings xs where
  isNothing (Nothing) = 1
  isNothing _ = 0
  
countNothings' :: [Maybe a] -> Int
countNothings' xs = sum([1 | Nothing <-xs])

-- Добавьте элемент, заключенный в «Может быть», в начало списка! Если элементом является «Ничего», результатом должен быть исходный список!

-- Пл.:
-- addBefore (всего 1) [2,3,4] == [1,2,3,4]
-- addBefore (просто «E») «LTE» == «ELTE»
-- addBefore Nothing [True, False] == [True, False]

addBefore :: Maybe a -> [a] -> [a]
addBefore (Nothing) xs = xs
addBefore (Just x) xs = x : xs

-- Введите функцию, которая применяет данную функцию к значениям в `Maybe`s! Если функция должна использоваться в ``Nothing'', то вернитесь с ``Nothing''!

--пл.:
--ap2Maybe (*) (Всего 3) (Всего 2) == Всего 6
--ap2Maybe (++) (Просто [0..4]) (Просто [5..10]) == Всего [0..10]
--ap2Maybe div (всего 6) Ничего == Ничего
ap2Maybe :: (a -> b -> c) -> Maybe a -> Maybe b -> Maybe c
ap2Maybe _ _ Nothing = Nothing
ap2Maybe _ Nothing _ = Nothing 
ap2Maybe f (Just x) (Just y) = Just $ f x y


--Определите тип данных под названием SignedInt для представления целых чисел со знаком. Тип данных должен иметь три конструктора: «Минус», «Ноль» и «Плюс». По крайней мере, классы типов «Eq» и «Show» должны быть включены в предложение «deriving»!
-- Некоторые примеры:

-- Минус 5 :: SignedInt -- означает (-5)
-- Ноль :: SignedInt -- означает 0
-- Плюс 4 :: SignedInt — означает 4

data SignedInt = Minus Int | Zero | Plus Int deriving (Eq, Show)


--Давайте реализуем добавление к типу данных SignedInt, определенному выше! (В качестве помощи стоит сначала определить функции fromSigned и toSigned)

fromSigned :: SignedInt -> Int
fromSigned (Minus x) = negate $ abs x
fromSigned (Zero) = 0
fromSigned (Plus x) = abs x

toSigned :: Int -> SignedInt
toSigned x
  | x < 0 = Minus $ abs x
  | x == 0 = Zero
  | otherwise = Plus x

plus :: SignedInt -> SignedInt -> SignedInt
plus x y = toSigned $ (fromSigned x) + (fromSigned y)

--Минус 6 `плюс` Плюс 6 == Ноль
--Плюс 7 `плюс` Минус 2 == Плюс 5
--Минус 5 `плюс` Ноль == Минус 5
--Минус 8 `плюс` Плюс 4 == Минус 4


--Список значений типа SignedInt в заданном (закрытом) интервале!

interval :: (SignedInt, SignedInt) -> [SignedInt]
interval (x,y) = [toSigned z | z <- [(fromSigned x)..(fromSigned y)]]


--интервал (Плюс 2, Плюс 5) == [Плюс 2, Плюс 3, Плюс 4, Плюс 5]
--интервал (Минус 2, Плюс 2) == [Минус 2, Минус 1, Ноль, Плюс 1, Плюс 2]
--интервал (Плюс 2, Плюс 2) == [Плюс 2]
--интервал (Плюс 2, Минус 1) == []

