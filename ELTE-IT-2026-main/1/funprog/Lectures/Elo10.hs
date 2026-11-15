zip' :: [a] -> [b] -> [(a,b)]
--zip' (x:xs) (y:ys) = (,) x y : zip' xs ys -- все функции такие же, как (x,y)
--zip' _ _ = []

zip' = zipWith' (,)
-- zip в его простейшей форме
-- :t zipWith' (,) --> [a] -> [b] -> [(a,b)], поэтому это можно упростить следующим образом

zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' f (x:xs) (y:ys) = f x y : zipWith' f xs ys
zipWith' _ _ _ = []
-- zipWith — это общий случай zip

zip'' :: [a] -> [b] -> [(a,b)]
zip'' xs ys = zipWith' (,) xs ys

iterate' :: (a -> a) -> a -> [a]
--не могу тренироваться с этим типом
--пл. итерация (*2) 1
iterate' f x = x : iterate' f (f x)

takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' _ [] = []
takeWhile' p (x:xs)
  | p x = x : takeWhile' p xs
  | otherwise = []

dropWhile' :: (a -> Bool) -> [a] -> [a]
dropWhile' _ [] = []
dropWhile' p (x:xs)
  | p x = dropWhile' p xs
  | otherwise = (x:xs)

{-
flip (a -> b -> c) -> b -> a -> c
flip' f y x = f x y
-}

-- (`элем` [1..10])
-- перевернуть элемент [1..10]

-- :t перевернуть элемент --> Eq a => [a] -> a -> Bool
-- :t elem --> Eq a => a -> [a] -> Bool

-- применение нескольких функций к значению
--карта ($1) [четная, нечетная]

foldR :: (a -> b -> b) -> b -> [a] -> b
foldR _ e [] = e
foldR f e (x:xs) = x `f` (foldR f e xs)

sum'' ls = foldR (+) 0 ls
prod'' ls = foldR (*) 1 ls

-- сумма'' [4,3,7]
-- сгибR (+) 0 [4,3,7] =
-- 4 + (foldR (+) 0 [3,7]) =
-- 4 + 3 + (foldR (+) 0 [7]) =
-- 4 + 3 + 7 (foldR (+) 0 []) =
-- 4 + (3 + (7 + 0)) = 14

reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]

reverse'' :: [a] -> [a]
reverse'' ls = rev ls [] where
  rev [] acc = acc
  rev (x:xs) acc = rev xs (x:acc)
  
--реверс'' [2,3,1]
--ред. [2,3,1] []
--ред. [3,1] [2]
--ред. [1] [3,2]
--реверс [] [1,3,2]
-- [1,3,2]

sum' :: [Int] -> Int
sum' ls = sh ls 0 where
  sh [] acc = acc
  sh (x:xs) acc = sh xs (acc + x)


-- сложитьL

foldL :: (b -> a -> b) -> b -> [a] -> b
foldL f e [] = e
foldL f e (x:xs) = foldL f (f e x) xs

sumF :: [Int] -> Int
sumF ls = foldL (+) 0 ls

reverseF :: [a] -> [a]
reverseF ls = foldL (flip (:)) [] ls

