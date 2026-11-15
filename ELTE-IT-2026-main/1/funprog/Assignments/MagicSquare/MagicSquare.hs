import Data.Maybe
import Data.List


{-
  ###
 #   #
 #   #
 #   #
  ###
-}

type Matrix = [[Int]]

--«распаковывает» матрицу в список для более удобного управления начиная с 1 (2.) и упрощения расчета стоимости (3.)
unpackMatrix :: Matrix -> [Int]
unpackMatrix [[]] = []
unpackMatrix [] = []
unpackMatrix (x:xs) = x ++ unpackMatrix xs

{-
    #
   ##
  # #
    #
	#
	#
-}
isSameSize :: Matrix -> Matrix -> Bool
isSameSize [] [] = True
isSameSize [_] [] = False
isSameSize [] [_] = False
isSameSize (x:xs) (y:ys)
  | rowSizeMatch x y = isSameSize xs ys
  | otherwise = False
  where
  rowSizeMatch :: [Int] -> [Int] -> Bool
  rowSizeMatch [] [] = True
  rowSizeMatch (x:xs) (y:ys) = rowSizeMatch xs ys
  rowSizeMatch _ _ = False

{-
  ###
 #   #
    #
   #
  #
 #####
-}
magicSquare :: Matrix -> Maybe Int
magicSquare [[x]] = Just x
magicSquare x
  | (isFrom1toN x) && (rowSumMatch x) && (rowSumMatch $ transpose x) && (sum $ getMainDiagonal 0 x) == (sum $ getSideDiagonal (getSquareMatrixSize x) x) = Just $ sum $ getMainDiagonal 0 x
  | otherwise = Nothing
  where
  
  --Определяет размер квадратной матрицы
  getSquareMatrixSize :: Matrix -> Int
  getSquareMatrixSize [] = 0
  getSquareMatrixSize (x:xs) = 1 + getSquareMatrixSize xs
  
  --Проверяет, содержит ли матрица цифры от 1 до ее размера.
  isFrom1toN :: Matrix -> Bool
  isFrom1toN [] = False
  isFrom1toN x
    | (sort $ unpackMatrix x) == [1..(getSquareMatrixSize x)^2] = True
    | otherwise                                                 = False

  --Проверяет, одинакова ли сумма строк
  rowSumMatch :: Matrix -> Bool
  rowSumMatch [] = True
  rowSumMatch [x,y] = (sum x ==  sum y)
  rowSumMatch (x:y:xs) = (sum x == sum y) && rowSumMatch (y:xs)

  --Вычисляет сумму элементов главной диагонали.
  getMainDiagonal :: Int -> Matrix -> [Int]
  getMainDiagonal _ [] = []
  getMainDiagonal n (x:xs) = (head $ drop n x) : getMainDiagonal (n+1) xs

  --Вычисляет сумму элементов на боковой диагонали
  getSideDiagonal :: Int -> Matrix -> [Int]
  getSideDiagonal _ [] = []
  getSideDiagonal n (x:xs) = (head $ drop (n-1) x) : getSideDiagonal (n-1) xs

{-
 ####
     #
     #
 ####
     #
     #
 ####
-}
minimalCost :: Matrix -> Maybe Int
minimalCost [[a,b,c],[d,e,f],[g,h,i]] = safeMinimum $ getCost (unpackMatrix [[2,9,4],[7,5,3],[6,1,8]]) $ map (unpackMatrix) $ getTransformations [[a,b,c],[d,e,f],[g,h,i]] where

  --функция стоимости
  cost :: [Int] -> [Int] -> Int
  cost [] _ = 0
  cost _ [] = 0
  cost (x:xs) (y:ys) = abs (x-y) + (cost xs ys)
 
  --«матричные преобразования»
  transformMatrix :: Int -> Int -> Matrix -> Matrix

  --просто снимаю
  transformMatrix 0 0   x = x
  transformMatrix 0 90  x = map (reverse) $ transpose x
  transformMatrix 0 180 x = map (reverse) $ transpose $ transformMatrix 0 90 x
  transformMatrix 0 270 x = transpose $ map (reverse) x

  --вращение и транспозиция
  transformMatrix 1 0   x = transpose x
  transformMatrix 1 90  x = transpose $ transformMatrix 0 90  x
  transformMatrix 1 180 x = transpose $ transformMatrix 0 180 x
  transformMatrix 1 270 x = transpose $ transformMatrix 0 270 x
  
  --производство трансформаций
  getTransformations :: Matrix -> [Matrix]
  getTransformations x = [transformMatrix t r x | t <- [0..1], r <- [0,90..270]]  
  
  --расчет затрат
  getCost :: [Int] -> [[Int]] -> [Int]
  getCost x ys = [cost x y | y <- ys]
  
  --безопасная версия минимальной функции
  safeMinimum :: [Int] -> Maybe Int
  safeMinimum [] = Nothing
  safeMinimum xs = Just $ minimum xs
  
minimalCost _ = Nothing