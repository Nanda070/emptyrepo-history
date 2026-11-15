--Задание/план урока для упражнения 9

---------------------
--Мои типы данных...
---------------------


--Введение нового типа:
--Конструктор типа данных = Конструктор данных1 | Конструктор данных2 | ...

--пл.:
--данные Bool = Ложь | Истинный


-- Новый тип объявления:
data SimpleColor = Red | Green | Blue


-- Сопоставление с образцом
favoriteColor :: SimpleColor -> Bool
favoriteColor Blue = True
favoriteColor _ = False


-- С параметром типа
data Point = Point Float Float deriving Show
-- Конструктор типа != Конструктор данных
-- ... но поскольку они находятся в другом пространстве имен, мы можем называть их одинаково

distance :: Point -> Point -> Float
distance (Point x1 y1) (Point x2 y2) = sqrt $ (x1 - x2)^2 + (y1 - y2)^2


-- Напишите функции getX и getY, которые возвращают координаты x и y точки (тип данных Point, определенный выше).
getX :: Point -> Float
getX (Point x _) = x

getY :: Point -> Float
getY (Point _ y) = y


-- Давайте перепишем функцию расстояния, чтобы использовать функции getX и getY вместо шаблона, соответствующего конструктору данных Point!
distance' :: Point -> Point -> Float 
distance' (point1) (point2) = sqrt $ (getX point1 - getX point2)^2 + (getY point1 - getY point2)^2


-- Тип данных shape имеет два конструктора данных: Circle и Rect.
-- параметры: x y радиус x1 y1 x2 y2
data Shape = Circle Float Float Float | Rect Float Float Float Float deriving Show
-- верхний левый нижний правый

area :: Shape -> Float
area (Circle _ _ r) = r^2 * pi
area (Rect x1 y1 x2 y2) = (abs $ x2 - x1) * (abs $ y2 - y1)


-- Давайте перепишем тип данных Shape так, чтобы конструктор данных Circle получал центр круга как точку, а Rect получал верхний левый и нижний правый угол прямоугольника как точку!
-- Давайте также преобразуем функцию площади, вычисляющую площадь!
data Shape' = Circle' Point Float | Rect' Point Point
area' :: Shape' -> Float
area' (Circle' _ r) = r^2 * pi
area' (Rect' p1 p2) = (abs $ (getX p2) - (getX p1)) * (abs $ (getY p2) - (getY p1))


-- Давайте напишем функцию, которая перемещает точку на плоскости!
displace :: Point -> Point -> Point
displace (Point x1 y1) (Point x2 y2) = Point (x1+x2) (y1+y2)

displace' :: Point -> Point -> Point
displace' (p1) (p2) = Point ((getX p1) + (getX p2)) ((getY p1)+(getY p2))


-- Давайте напишем функцию, которая перемещает фигуру (ожидает точку в качестве параметра).
displaceShape :: Shape -> Point -> Shape
displaceShape (Circle u v r) (Point x y) = Circle (u+x) (v+y) r
displaceShape (Rect x1 y1 x2 y2) (Point a b) = Rect (x1+a) (y1+b) (x2+a) (y2+b)

displaceShape' :: Shape' -> Point -> Shape'
displaceShape' (Circle' p r) (v) = Circle' (displace' p v) r
displaceShape' (Rect' p1 p2) (v) = Rect' (displace' p1 v) (displace' p2 v)


-- (предыдущее задание)
-- Определите функцию, которая определяет, будет ли погода приятной, по паре единица измерения-температура. (Цельсия: 20–30, Кельвина: 293–303, Фаренгейта: 68–86)
--niceWeather :: (Ord a, Num a) => ([Char], a) -> Bool
--niceWeather ("Цельсий", n)
-- | n >= 20 && n<= 30 = Истина
--niceWeather ("Кельвин", n)
-- | n >= 293 && n<= 303 = Истина
--niceWeather ("Фаренгейт", n)
-- | n >= 68 && n<= 86 = Истина
--niceWeather _ = Ложь

-- Давайте решим предыдущую проблему, представив температуру с помощью собственного типа данных!

data Temperature = Celsius Float | Fahrenheit Float | Kelvin Float
niceWeather :: Temperature -> Bool
niceWeather (Fahrenheit t) = (t >= 68 && t<= 86)
niceWeather (Kelvin t) = (t >= 293 && t<= 303)
niceWeather (Celsius t) = (t >= 20 && t<= 30)