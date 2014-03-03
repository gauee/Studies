--Maybe
--data Moze a = Dokladnie a | Blad

--instance Monad Moze where
  --      return x = Dokladnie x            --a -> Moze a
  --      (Dokladnie x >>= f ) = f x       --bind  Moze a -> (a -> Moze b)->Moze b
  --      (Blad >>= _ ) = Blad
        
        
--Funkcja pierwiaste i odwrotnosc
f::Double -> Maybe Double               --pierwiastek kw
f x  
      | x >= 0 = Just (sqrt x)        --dla odwrotnosci x!=0 = Just(1/x)
      | otherwise = Nothing

g::Double -> Maybe Double
g x 
        | x /= 0 = Just(1/x)
        | otherwise = Nothing
                
--Konstrukcje z do
h::Double -> Maybe Double
h2::Double -> Maybe Double
h3::Double -> Maybe Double
--h(x) = 1/(x^(1/4)
h x = f x >>= f >>= g 
h2 x = return x >>= f >>= f >>= g 
--bezpunktowo fliped bind =<<
--flip( >>= )(f >>= f >>= g)(return x)
--h3 = (g =<< (f =<< (f x)))
--h3 = g =<<((=<<)f (f x))
--h3 = ((=<<) g)(((=<<)f)(f x))
h3 = ((=<<)g).((=<<)f).f


--Zadanie z bazą
LookupName::PeselDb->Integer->Maybe String
LookupAddress::AdressDb->String->Maybe String

Search::PeselDb->AdressDb->Integer->Maybe String
SearchDo::PeselDb->AdressDb->Integer->Maybe String

Search db1 db2 pesel = (LookupName db1 pesel) >>= (LookupAddress db2)

SearchDo db1 db2 pesel = do
                                x <- LookupName db1 pesel
                                LookupAddress db2 x                     --zwrocenie wartości
                                -- y<-LookupAdress db2 x
                                -- return y
                                
                                
--dwa rodzaje bledu powazny (Nothing) blad mniej powazny (Warning)
data Result a = Ok(a) | Warn(a,String) | Error

status (Ok _) = True
status (Warn(_,_)) = False
valueOf (Ok x) = x
valueOf (Warn(x,_)) = x
msgWarn (Warn(_,s)) = s

instance Monad Result where
        return x = Ok(x)
        (Error >>= _) = Error
        (Ok x >>= f) = f x
        (Warn(x,s) >>= f) = f =
               if f x = Error then Error
               else if status(f x) then Warn(valueOf(f x),s)
               else Warn(valueOf(f x),s++msgWarn(f x))


--customSequnce
putStr:: String -> IO ()
putChar::Char -> IO ()
[putChar 'a', putStr "la",putChar ' ',putStr "ma kota"]

seq::(Monad m) => [m a] -> m [a]
-- f >> g = f >>=(\_ -> g);
--[a,b,c,d] ----> a>>b>>c>>d
seq = foldl(>>)(return[])
--sequence jak implementuje definicja w PRELUDE













