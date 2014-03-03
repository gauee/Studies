diffsums :: [[Int]] -> [[Int]]
diffsums [[]] = [[]]
diffsums (x:xs) = (foldr (+) 0 x) : [] : diffsums xs

--Sumowanie podlist do jednej listy
--foldl (\a b -> (foldr (+) 0 b ): a) [] [[1],[1,2,3],[3,3]]
