sc :: (Integer,Integer) -> Integer
sc (0,0) = 1
sc (n,0) = 0
sc (0,n) = 0
sc (n+1,k) = k*sc(n,k)+sc(n,k-1)  

