--Damian Gałka

-- First func 
is3 :: Int -> Maybe Int
is3 a 
   | is3Exist (calcIs3 a) = Just (head (calcIs3 a))
   | otherwise = Nothing

is3Exist :: [Int] -> Bool
is3Exist (x:xs) = True
is3Exist _ = False
        
calcIs3 :: Int -> [Int]
calcIs3 n = [x | x<-[0..n],x*x*x == n]++[x | x<-[n..0], x*x*x == n]

-- Second func
c :: Maybe Int -> Int
c Nothing = 0
c (Just a) = 2*a+1


-- Third func 2*(is3 n)+1
fun :: Int -> Int 
fun n 
        | is3Exist (calcIs3 n) = elimMaybe( (Just (is3 n)) >>= \x -> (Just (c x)))
        | otherwise = 0
       
       
calc2 :: Int -> Int
calc2 n
        | n==0 = 0
        | otherwise = (2*n)
       
fun2 :: Maybe Int -> Maybe Int
fun2 Nothing = Nothing
fun2 n = n     
 
elimMaybe :: Maybe Int -> Int
elimMaybe (Just a) = a
elimMaybe Nothing = 0

