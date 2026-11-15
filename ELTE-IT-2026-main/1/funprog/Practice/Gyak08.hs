--Задание/план урока для упражнения 8

--Повторить

--Определите функцию, которая создает список, содержащий суммы пар чисел, из списка, содержащего пары чисел.
sumPairs ::  Num  a => [(a,a)] -> [a]
sumPairs [] = []
sumPairs ((a,b):xs) = (a+b) : sumPairs xs 

-- Давайте подсчитаем количество вхождений элемента с заданным свойством в список!
count :: (a -> Bool) -> [a] -> Int
count p [] = 0
count p (x:xs)
  | p x= 1 + count p xs
  | otherwise= count p xs

count' :: (a -> Bool) -> [a] -> Int
count' p ls = sum[1 | x <- ls,p x]

-- Давайте переопределим функцию takeWhile!
takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' p [] = []
takeWhile' p (x:xs)
  |p x= x:takeWhile' p xs
  |otherwise=[]

    
-- Давайте определим функцию dropSpaces, которая удаляет пробелы из начала текста!
dropSpaces' :: String -> String
dropSpaces' "" = ""
dropSpaces' (c:ss)
  | c==' ' = dropSpaces' ss
  | otherwise = (c:ss)
  
dropSpaces'' :: String -> String
dropSpaces'' s = dropWhile (==' ') s


-- Приводится идентификатор языка (например, hu-HU или en-US), который состоит из двух частей: языка и региона. Разделите идентификатор языка на язык и регион! Мы предполагаем, что ввод правильно отформатирован. Каждая часть состоит из двух персонажей.


-- Учитывая текст, разделите его на список текстов тире!
-- отдельный "Hello-world-hello" == ["Привет","мир","привет"]
--отдельный :: Строка -> [Строка]



-- Давайте переопределим функцию zipWith!
zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' _ [] _ = []
zipWith' _ _ [] = []
zipWith' f (x:xs) (y:ys) = f x y : zipWith f xs ys

-- Давайте переопределим функцию zip, используя zipWith!

zip' :: [a] -> [b] -> [(a,b)]
zip' _ [] = []
zip' [] _ = []
zip' xs ys = zipWith (,) xs ys

-- Давайте переопределим функцию итерации!
iterate' :: (a -> a) -> a -> [a]
iterate' f x = x : iterate' f (f x)

-- Давайте определим функцию, которая генерирует следующую бесконечную последовательность: [1,-1,1,-1,1,-1 .. ]
--(Совет: используйте ранее определенную функцию итерации!)
alternating :: [Int]
alternating = iterate (*(-1)) 1

           
-- Давайте определим функцию, которая генерирует следующую бесконечную последовательность: [1,-2,3,-4,5,-6,7,-8,9,-10 .. ]
alternatingMultiple :: [Int]
alternatingMultiple = zipWith (*) [1..] alternating


-- Оператор приложения-функции ($)
-- http://lambda.inf.elte.hu/Higherorder.xml#feladat-a-doll%C3%A1r-oper%C3%A1tor
-- $ применяет функцию
-- инфикс
-- можно использовать вместо круглых скобок

calcExample = abs ((+6) (negate (sqrt 100)))
calcExample' = abs $ (+6) $ negate $ sqrt 100

-- Оператор композиции функций (.)
-- объединение функций, таких как математика, вместо тумана, просто круг.

calcExample'' = (abs . (+6) . negate . sqrt) 100

-- Определите функцию, которая преобразует каждый элемент списка целых чисел в отрицательное число!
toNegative :: [Int] -> [Int]
toNegative [] = []
toNegative (x:xs) = (negate . abs) x : toNegative xs 


trim :: String -> String
trim s = reverse $ dropWhile (==' ') $ reverse $ dropWhile (==' ') s

trim' :: String -> String
trim' s = (reverse . dropWhile (==' ') . reverse . dropWhile (==' ')) s