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
grepGetParts (p:"file":o) = grepHsF p "-"
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
handlePCO pat opt cont 
        | isFlag opt = grepMatch pat opt cont False False False False
        | otherwise = grepMatch pat [] cont False False False False

--Find matches in content
grepMatch::String->String->[String]->Bool->Bool->Bool->Bool->[String]
grepMatch pat [] cont uCase uMatch rNum oWord
        | uMatch = cResult rNum (findNotMatches pat uCase oWord cont)
        | otherwise = cResult rNum (findMatches pat uCase oWord cont)
         
grepMatch pat (o:os) cont uCase uMatch rNum oWord
        | o == 'i' = grepMatch pat os cont True uMatch rNum oWord
        | o == 'v' = grepMatch pat os cont uCase True rNum oWord
        | o == 'c' = grepMatch pat os cont uCase uMatch True oWord
        | o == 'w' = grepMatch pat os cont uCase uMatch rNum True
        | otherwise = grepMatch pat os cont uCase uMatch rNum oWord

--Grep find matching to pattern
findMatches:: String->Bool->Bool->[String]->[String]
findMatches pat uCase oWord cont = filter (phraseExistIn pat uCase oWord) cont

--Grep find matching to pattern
findNotMatches:: String->Bool->Bool->[String]->[String]
findNotMatches pat uCase oWord cont = filter (phraseNotExistIn pat uCase oWord) cont

--
cResult :: Bool ->[String]->[String]
cResult needCount rslt
                | needCount = [(show (length rslt))]
                | otherwise = rslt


--Grep way uf usage
usageGrep :: IO()
usageGrep = putStr $unlines([ 
                      "Usage grepHs:",
                      "For stdin [pattern,-{flags,} conten]",
                      "For file [pattern,'file',-{flags,}]",
                      "-{flags}:",
                      "-i : ignoring character case",
                      "-v : invert match",
                      "-c : count of matching lines",
                      "-w : match only whole words",
                      "flags can be concatenate e.g. -iv (ignore and invert)"])

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
phraseIsPrefOf :: [Char] -> Bool->Bool->[Char] -> Bool
phraseIsPrefOf [] _ True [] = True
phraseIsPrefOf [] _ True _ = False
phraseIsPrefOf _ _ True [] = False
phraseIsPrefOf [] _ _ _ = True
phraseIsPrefOf [] _ _ _ = True
phraseIsPrefOf _ _ _ [] = False
phraseIsPrefOf (x:xs) uCase oWord (y:ys) = (cmpChars x y uCase) &&    phraseIsPrefOf xs uCase oWord ys

--checking if line contains phr
phraseExistIn :: [Char]->Bool->Bool->[Char]->Bool
phraseExistIn phr uCase oWord line
        | oWord = any (phraseIsPrefOf phr uCase oWord) (words line)
        | otherwise = any (phraseIsPrefOf phr uCase oWord) (subStrings line)

--checking if line not contains phr
phraseNotExistIn :: [Char]->Bool->Bool->[Char]->Bool
phraseNotExistIn phr uCase oWord line = not (phraseExistIn phr uCase oWord line)

--method convert character to lower case if is in upper
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
        

--method to compare two chars with possible to ignore case
cmpChars :: Char->Char->Bool->Bool
cmpChars x y uCase = x == y || 
                (uCase && (charLowCase x) == (charLowCase y))
                       
