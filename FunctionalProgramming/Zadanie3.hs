--compreff::(Eq a)=>[a]->[a]->Int
compreff::(Eq a)=>[a]->[a]->[a]
compreff [] _ = [0]
compreff _ [] = [0]
compreff a b = foldl (\a b -> if last a == 1 then (if fst b == snd b then 1:a else 0:a) else 0:a) [1] (zip a b) 
--compreff a b = fold (+) 0 foldl (\a b -> if fst b == snd b then a=1 else a=0 ) 0 (zip a b)
