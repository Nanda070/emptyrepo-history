--проверяет, имеет ли список длину хотя бы n

atLeastNLength :: Int -> [a] -> Bool
atLeastNLength n []
  | n <= 0 = True
  | n > 0 = False
atLeastNLength n (_:xs)
  | n <= 0 = True
  | n > 0 = atLeastNLength (n-1) xs
  
--по крайней мереNLength 3 [1,2]
--3 > 0 = atLeastNLength 3-1 xs (условие защиты образца 2 2)
--по крайней мереNLength 2 [2]
--2 > 0 = atLeastNLength 2-1 xs (условие защиты образца 2 2)
--по крайней мереNLength 1 []
--Ложь (шаблон 1, условие защиты 2)
  
--по крайней мереNLength 3 [1,2,3]
--по крайней мереNLength 2 [2,3]
--по крайней мереNLength 1 [3]
--atLeastNLength 0 [] (условие защиты шаблона 1 1)

atLeastNLengthImproved :: Int -> [a] -> Bool
atLeastNLengthImproved n [] = n <= 0
atLeastNLengthImproved n (_:xs)
  | n<=0 = True
  | otherwise = atLeastNLengthImproved (n-1) xs



--переписывание функции take создает другой список из первых n элементов

take' :: Int -> [a] -> [a]
take' n _ | n <= 0 = []
take' _ [] = []
take' n (x:xs) = x : take' (n-1) xs

--возьми' 2 [1,2,3]
-- 1: возьми' 1 [2,3]
-- 2: возьми' 0 [3]
--   [1,2]



--противоположность drop take перечисляет те, которые не решены
drop' :: Int -> [a] -> [a]
drop' n ls | n <= 0 = ls
drop' _ [] = []
drop' n (x:xs) = drop' (n-1) xs

--реверс переворачивает список

--первое решение: примитивная рекурсия
{-
reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]
-}

--второе решение: последний курсив
reverse' :: [a] -> [a]
reverse' ls = rev ls [] where
  rev [] acc = acc
  rev (x:xs) acc = rev xs (x : acc)