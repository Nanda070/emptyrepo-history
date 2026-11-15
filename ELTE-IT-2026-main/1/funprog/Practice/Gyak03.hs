 --Сопоставление с образцом

not' True = False
not' False = True

--образец может быть:
--переменная: х, у
-- шутник: _ (подходит любой тип, но зрелые использовать нельзя)
-- образцы для конкретного типа: True

or' :: Bool -> Bool -> Bool
--или' Истина Истина = Истина
--или' Истина Ложь = Истина
--или' Ложь Истина = Истина
--или' Ложь Ложь = Ложь

--или' _ Истина = Истина
--или' Истина _ = Истина
--или' _ _ = Ложь

or' False False = False
or' _ _ = True


and' :: Bool -> Bool -> Bool
--и' Истина Истина = Истина
--и 'Истина Ложь = Ложь
--и' Ложь Истина = Ложь
--и' Ложь Ложь = Ложь

and' True True = True
and' _ _ = False

whatNumber :: Integer -> String
whatNumber 1 = "Egy"
whatNumber 2 = "Kettő"
whatNumber 3 = "Három"
whatNumber x = "Nem 1 és 3 közötti."

--функция, которая заменяет разрывы строк пробелами
replaceNewLine :: Char -> Char
replaceNewLine '\n' = ' '
replaceNewLine x = x

--кортеж: заказано n
--первый из первой заказанной пары
--второй из организованной пары

first :: (p1,p2) -> p1
first pair = fst pair

first' :: (p1,p2) -> p1
first' (a, b) = a

swap :: (p1, p2) -> (p2, p1)
swap (a, b) = (b, a)

triplicate :: a -> (a, a, a)
triplicate n = (n, n, n)

isEven :: Integral a => a -> (a, Bool)
isEven n = (n, even(n))

addPair :: Num a => (a, a) -> (a, a) -> (a, a)
addPair (n, m) (x, y) = (n+x, m+y)

divAndMod :: Integral a => a -> a -> (a, a)
divAndMod n m = (n `mod` m, n `div`m)

distFromOrigo :: Floating a => (a, a) -> a
distFromOrigo (n, m) = sqrt(n*n+m*m)

distance :: Floating a => (a, a) -> (a, a) -> a
distance (n, m) (x, y) =  sqrt((n-x)*(n-x)+(m-y)*(m-y))

--списки

pelda1 :: Num a => [a]
pelda1 = [1, 2, 3, 4 ,5]
-- списки однородны, могут содержать только один тип

pelda2 :: [Bool]
pelda2 = [True, False, True, True]

pelda3 :: [Char]
pelda3 = ['a', 'b', 'c']
-- то же самое, что и строка «abc»

pelda4 :: [(Integer, Bool)]
pelda4 = [(2, True), (1, False)]

exerciseList1 :: [(Integer, Integer)]
exerciseList1 = [(4,3), (0,2)]

exerciseList2 :: Integral a => [(a, Char)]
exerciseList2 = [(4, 'x'), (7, 'f')]

exerciseList3 :: (Fractional a, Integral b) => [(a, a, b)]
exerciseList3 = [(2.5, 4.2, 1),(2.325, 3.421, 5)]

exerciseList4 :: Num a => [[a]]
exerciseList4 = [[2,4],[3,5,6],[0]]

-- Оператор ++ объединяет два списка

pelda5 = [1,2] ++ [3,4]

-- .. выражения

pelda6 = [1..10]
-- конец интервала также включен

pelda7 = [9,8..0]

pelda8 = ['a'..'z']

exerciseList5 = [10,9..(-10)]

exerciseList6 = [10,9.. -10]
