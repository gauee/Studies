sequence :: Monad m -> [m a] -> m[a]

sequence [] = return []
sequence (c:cs) = do
                   x <- c
                   xs <- sequence cs
                   return (x:xs)

... -> m()                   
sequence_  = foldr(>>)(return ())




ERLANG
-module(test).
-export([pomnoz/1]).

pomnoz(Z) -> 2*x.
      
