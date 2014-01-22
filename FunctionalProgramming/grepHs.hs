-- Damian Gałka - unix grep in haskell
-- Help
-- Program can grep lines from file or Strings pass in command array
-- Basic command example
-- grepHs [] - which will return man of program
-- grepHs should contains at least two element array. First of only it should contains pattern to search and second list of contents or "file" string in param array
-- to grep phrase "dam" from file imiona.txt you should type
-- grepHs ["dam","file"] and press Enter, after it you will see info:
-- "Wprowadz nazwę pliku:" and pleas insert path to your file and press enter
-- The result will contains matched lines with patter or "Nie znaleziono frazy"
-- to grep phrase in array of content please type e.g.:
-- grepHs ["sa","sasa","sss","aaaa","aasa"]
-- this mean that we will searching string which contains "sa"
-- To file and content grep we can add flags, after "file" when we use file grep and after pattern when we using content
-- is possible to enable options:
-- -i -> ignoring letter case
-- -v -> invert matching
-- -c -> counting amount of result
-- -w -> matching only whole words
-- the flags can be concatenate and then dont depends on order, but before first flag must be sign '-'
--
-- Examples of file imiona.txt
-- grepHs ["Dam","file"]
-- grepHs ["Dam","file","-i"]
-- grepHs ["a","file","-iv"]
-- grepHs ["a","file","-vc"]
-- grepHs ["a","file","-cvi"]
-- grepHs ["damian","file","-wi"]
-- grepHs ["August","file","-wi"]
-- grepHs ["August","file","-i"]

module GrepHS where

--Main grep invoker
grepHs :: [String] -> IO()
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
grepHsS pat opt cont
        | isFlag opt = putStr(
                         packResult(
                                handlePCO pat opt cont))
        | otherwise = putStr(
                         packResult(
                                handlePCO pat "" ([opt]++cont)))
        

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

--Grep find not matching to pattern
findNotMatches:: String->Bool->Bool->[String]->[String]
findNotMatches pat uCase oWord cont = filter (phraseNotExistIn pat uCase oWord) cont

--Grep way uf usage
usageGrep :: IO()
usageGrep = putStr $unlines([ 
                      "Usage grepHs:",
                      "For stdin [pattern,-{flags,} content]",
                      "For file [pattern,'file',-{flags}]",
                      "-{flags}:",
                      "-i : ignoring character case",
                      "-v : invert match",
                      "-c : count of matching lines",
                      "-w : match only whole words",
                      "flags can be concatenate e.g. -iv (ignore and invert)"])

-- checking if should count amount of result
cResult :: Bool ->[String]->[String]
cResult needCount rslt
                | needCount = [(show (length rslt))]
                | otherwise = rslt

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
                       
