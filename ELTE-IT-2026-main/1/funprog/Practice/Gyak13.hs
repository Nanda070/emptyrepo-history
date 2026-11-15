import Data.List

--План задания/урока к упражнению 12

-- foldr_ (+) 0 [1,2,3] == 1 + (2 + (3 + (0)))
foldr_ :: (a -> b -> b) -> b -> [a] -> b
foldr_ f acc []     = acc 
foldr_ f acc (x:xs) = x `f` (foldr_ f acc xs)

-- foldl_ (+) 0 [1,2,3] == (((0) + 1) + 2) + 3
foldl_ :: (b -> a -> b) -> b -> [a] -> b
foldl_ f acc []     = acc 
foldl_ f acc (x:xs) = foldl_ f (f acc x) xs

--Определите сумму с помощью Foldr/foldl!
sum' :: Num a => [a] -> a
sum' ls = foldr (+) 0 ls

--Определите или с помощью Foldr/foldl!
or' :: [Bool] -> Bool
or' ls = foldr (||) False ls

--Определите и с помощью Foldr/foldl!
and' :: [Bool] -> Bool
and' ls = foldr (&&) True ls

--Определите concat с помощью Foldr/foldl!
concat' :: [[a]] -> [a]
concat' xs = foldr (++) [] xs

--Определите длину с помощью командыfoldr/foldl!
length' :: [a] -> Int
length' = foldr (\_ acc -> acc + 1) 0

--Определите функции head и Last, используя Foldr и Foldl! Если список пуст, результатом должна быть ошибка. Что с чем можно сделать?
last' :: [a] -> acc
last' xs = foldr (\_ acc -> acc) (error "empty list") xs



--Определите максимум с помощью Foldr/foldl!
maximum' :: Ord a => [a] -> a
maximum' (x:xs) = foldr (max) x xs

--Определите карту с помощью Foldr! (А с фолдом?)
map' :: (a -> b) -> [a] -> [b]
map' f xs = foldr (\x acc -> f x : acc) [] xs

--Определите сортировку вставкой с помощью Foldr! (Используйте вставку, чтобы вставить элемент в отсортированный список)
--isort :: Ord a => [a] -> [a]


-- Определите оператор (++) с помощью Foldr!
--(+++) :: [а] -> [а] -> [а]

-- Определите функцию подсчета с помощью Foldr, которая подсчитывает, сколько элементов с заданным свойством найдено в одном списке!
--count :: (a -> Bool) -> [a] -> Int

reverse' :: [a] -> [a]
reverse' xs = rev [] xs where
    rev :: [a] -> [a] -> [a]
    rev done [] = done
    rev done (x:xs) = rev (x:done) xs


-- Определите реверс, используя Foldr или Foldl!
--verseWithFoldl :: [a] -> [a]

--Определите элемент с помощью Foldr!
--elem' :: Eq a => a -> [a] -> Bool

-- Определите функцию all с помощью Foldr!
--all'' :: Eq a => [a] -> Bool