rkw :: (Float, Float, Float) -> (Float, Float)
rkw (a,b,c) = 
       if delta < 0 then (0,0)
       else ((-b-sqrt(delta))/2*a,(-b+sqrt(delta))/2*a)
       
       where delta=b*b-4*a*c
