--Damian Gałka - unix grep in haskell
module GrepHS where

--Main grep invoker
grepHs :: [String] -> IO()
grepHs ["h"]= usageGrep
grepHs ["help"]= usageGrep
grepHs list = grepGetParts list

--Resolve options for grep
grepGetParts :: [String] -> IO()
grepGetParts (p:"file":o:os) = grepHsF p o
grepGetParts (p:"file":o) = grepHsF p "/"
grepGetParts (p:o:rest) = grepHsS p o rest
grepGetParts _ = usageGrep

--Grep check existing flags
isFlag :: String -> Bool
isFlag [] = False
isFlag (x:xs)
                | x == '-' = True
                |otherwise = False

--Grep from file
grepHsF :: String -> String -> IO() 
grepHsF pat opt = do
                putStr $"Wprowadz nazwę pliku: "
                inSearchFile <- getLine
                contents <- readFile inSearchFile
                let fContent = lines contents
                putStr (packResult (handlePCO pat opt fContent))

--Grep from stream
grepHsS :: String -> String -> [String] -> IO()
grepHsS pat opt cont = putStr(
                         packResult(
                                handlePCO pat opt cont))

--Grep handle pattern content and options
handlePCO :: String -> String -> [String] -> [String]
handlePCO pat _ [] = []
handlePCO []  _ cont = cont
handlePCO pat opt cont = grepMatch pat opt cont False False

--Find matches in content
grepMatch :: String -> String -> [String] ->Bool ->Bool -> [String]
grepMatch pat [] cont uCase uMatch
        | uMatch = filter (phraseNotExistIn pat uCase) cont
        | otherwise = filter (phraseExistIn pat uCase) cont
         
grepMatch pat (o:os) cont uCase uMatch
        | o == 'i' = grepMatch pat os cont True uMatch
        | o == 'v' = grepMatch pat os cont uCase True
        | otherwise = grepMatch pat os cont uCase uMatch


--apply options for pattern phrase
aPO :: String -> String -> String
aPO pat [] = pat
aPO pat opt 
        | (head opt) == 'i' = aPO pat (tail opt)
        | (head opt) == 'v' = aPO ("~"++pat) (tail opt)
        | otherwise = aPO pat (tail opt)     

--apply options for pattern content 
aCO :: [String] -> String -> [String]
aCO cont [] = cont
aCO cont opt
        | (head opt) == 'i' = aCO cont (tail opt)
        | otherwise = aCO cont (tail opt)

--Grep way uf usage
usageGrep :: IO()
usageGrep = putStr $unlines([ 
                      "Usage grepHs:",
                      "For stdin [pattern,-{flags,} conten]",
                      "For file [pattern,'file',-{flags,}]",
                      "-{flags}:",
                      "-i : ignoring character case",
                      "-v : invert match"])

--Packing results
packResult :: [String] -> String
packResult [] = "Nie znaleziono frazy"
packResult x = unlines x

--Additionals functions
--array of substrings base array
subStrings :: [a] -> [[a]]
subStrings xs = xs : case xs of
                        [] -> []
                        _:xs' -> subStrings xs'

--checking if phrase is prefix of line
phraseIsPrefOf :: [Char] -> Bool ->[Char] -> Bool
phraseIsPrefOf [] _ _ = True
phraseIsPrefOf _ _ [] = False
phraseIsPrefOf (x:xs) uCase (y:ys) = (cmpChars x y uCase) && phraseIsPrefOf xs uCase ys

--checking if line contains phr
phraseExistIn :: [Char]->Bool->[Char]->Bool
phraseExistIn phr uCase line = any (phraseIsPrefOf phr uCase) (subStrings line)

--checking if line not contains phr
phraseNotExistIn :: [Char]->Bool->[Char]->Bool
phraseNotExistIn phr uCase line = not (phraseExistIn phr uCase line)

charLowCase :: Char -> Char
charLowCase c 
        | (fromEnum 'A') <= (fromEnum c) && 
        (fromEnum c) <= (fromEnum 'Z') = toEnum (32+fromEnum c)
        | c == 'Ą' = 'ą'
        | c == 'Ę' = 'ę'
        | c == 'Ó' = 'ó'
        | c == 'Ś' = 'ś'
        | c == 'Ł' = 'ł'
        | c == 'Ż' = 'ż'
        | c == 'Ź' = 'ź'
        | c == 'Ć' = 'ć'
        | c == 'Ń' = 'ń'
        | otherwise = c
        
        
cmpChars :: Char->Char->Bool->Bool
cmpChars x y uCase = x == y || (uCase && (charLowCase x) == (charLowCase y))
                       
