s :: Integer -> Integer
s (n) = sum([x | x <- [1..n], mod n x == 0])
