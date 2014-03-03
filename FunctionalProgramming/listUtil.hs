dl x = if x == [] then 0 else 1 + dl(tail x)

dl2 [] = 0
dl2 (x:xs) = 1 + (dl2 xs)
