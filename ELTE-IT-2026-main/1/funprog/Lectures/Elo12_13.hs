-- <<<<<. и $ >>>>>

evenFrequency :: [Int] -> Int
evenFrequency ls = length (filter even ls)

-- . оператор композиции функций (f . g) x = f (g x)
-- имеет наименьший приоритет, поэтому, например. длина . для фильтра даже ls сначала
-- фильтр выполняется
-- лучший ассоциативный

evenFrequency' :: [Int] -> Int
evenFrequency' ls = (length . filter even) ls

-- $ функция-оператор приложения
-- приоритет для пропуска самых высоких и удобных круглых скобок
-- лучший ассоциативный

evenFrequency'' :: [Int] -> Int
evenFrequency'' ls = length $ filter even ls

-- ######

dropSpaces :: String -> String
dropSpaces "" = ""
dropSpaces str = dropWhile (== ' ') str

dropSpaces2a :: String -> String
dropSpaces2a "" = ""
dropSpaces2a str = reverse (dropSpaces (reverse (dropSpaces str)))

dropSpaces2b :: String -> String
dropSpaces2b "" = ""
dropSpaces2b str = reverse $ dropSpaces $ reverse $ dropSpaces str

dropSpaces2c :: String -> String
dropSpaces2c "" = ""
dropSpaces2c str = reverse . dropSpaces . reverse .dropSpaces $ str

-- ######

composition :: [a -> a] -> (a -> a)
composition [] e = e
composition (f:fs) e = f $ composition fs e

composition' :: [a -> a] -> (a -> a)
composition' fs = foldr (.) (\x -> x) fs

-- <<<<< ТИПЫ ДАННЫХ >>>>>

--имена типов всегда начинаются с заглавной буквы

-- ##### тип синонима ######

--введите ключевое слово

--введите String = [Char]

type Hour   = Int
type Minute = Int
type Second = Int

type Time = (Hour,Minute,Second)

-- полезно для читабельности

type PredicateOnInt = (Int -> Bool)
-- даже :: PredicateOnInt

type Tuple a b = (a,b)
--fst :: (Кортеж a b) -> a

-- переменные типа b, если они существуют, тип называется параметрическим типом

type TupleStrict a = (a,a)
--fst :: (Кортеж a) -> a

-- при необходимости можно использовать для ограничения типа

-- ##### алгебраические типы данных #####

-- ключевое слово данных

--данные Bool = Ложь | Истинный

-- тип перечисления (конструкторы — это сами значения [постоянные функции])
data Day = Mon | Tue | Wed | Thu | Fri | Sat | Sun deriving (Show)

-- этот тип ничего не знает в такой форме
-- мы должны «научить» этому, существует только сопоставление с образцом
-- мы можем запросить автоматическое создание экземпляров определенных классов типов с помощью ключевого слова deriving

-- вручную:

instance Eq Day where
  (==) Mon Mon = True
  (==) Tue Tue = True --и т. д. и т. д.
  
-- Тип объединения (функции-конструкторы P2::Int -> Int -> Point)
-- совершенно новые типы
data Point = P2 Int Int | P3 Int Int Int deriving (Show)

xCord :: Point -> Int
xCord (P2 x _) = x
xCord (P3 x _ _) = x

data Shape = Circle Double Point| Rect Double Double| Mult [Point] deriving (Show)

area :: Shape -> Double
area (Rect x y) = x * y
area (Circle x _) = x*x*3.14

-- в некоторых случаях мы хотим заставить работать только тот тип, который нам подходит
data D = D Int deriving Show
-- более уместно писать истории с одним конструктором с помощью newtype

-- ##### новый тип #####
newtype N = N Int deriving Show

testD :: D -> Bool
testD (D _) = True

testN :: N -> Bool
testN (N _) = True
-- нет конструктора, нет необходимости его оценивать
-- testD неопределенный -> Hiba, testN неопределенный -> True


data Lazy = L Int deriving Show
data Strict = S !Int deriving Show

-- ! обозначает жадную оценку

testL :: Lazy -> Bool
testL (L _) = False
-- testL не определено -> Ложь
-- ленивая оценка даже не пытается вычислить выражение
-- потому что значение не нужно

testS :: Strict -> Bool
testS (S _) = False
-- testS не определено -> Хиба
-- с другой стороны, в случае жадного вычисления S оценивается как неопределенное
-- что приводит к ошибке

-- ##### СПИСОК #####
-- kosnstructors[] — пустой список, (:) — голова и другие

data List a = Nil | a :> (List a) --получение Показать
-- [] (:) а [а]
-- тип списка рекурсивный
-- указание инфиксного конструктора

infixr 5 :>
-- мы явно заявляем, что операция является инфиксной правоассоциативной и ее сила равна 5

instance Show a => Show (List a) where
  show ls = "<" ++ showContent ls ++ ">" where
    showContent Nil = ""
    showContent (x :> Nil) = show x
    showContent (x :> xs) = show x ++ "," ++ showContent xs

toList :: [a] -> List a
toList [] = Nil
toList (x:xs) = x :> toList xs

-- однако в этом случае все функции должны быть определены в нашем списке
-- что нам делать, если нам не хочется?

-- ##### Складной #####

instance Foldable List where
  foldr f e Nil = e
  foldr f e (x :> xs) = f x (foldr f e xs)
