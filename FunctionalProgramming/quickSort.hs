qs [] = []
qs (x:xs) = qs [ y | y <-xs, y<=x] ++ [x] ++ [y | y<-xs,y>x]
