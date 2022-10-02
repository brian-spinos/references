data Shape = Circle Float Float Float

-- data Person = Person String Int deriving (Show) 

-- like a java class, with default constructor
data Person = Person {
  firstName :: String, 
  age :: Int  
} deriving (Show) -- deriving is like an interface

-- like a java class with constructor
data Car make model y = Car {
  make :: make,
  model :: model,
  year :: y 
} deriving (Show) -- deriving is like an interface 


main = do
  print "hello"
  let guy = Person "Buddy" 23
  print guy
  let car = Car "toyota" "corolla" 2019
  print car
  -- let stang = Car {company="Ford", model="Mustang", year=1967}  
