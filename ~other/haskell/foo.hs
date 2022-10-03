

-- data Blah a b c = Blah a b c 



-- ====================================================================================

-- instance Functor Blah where  
--   fmap f (Blah a b c) = (Blah a b (f c))

-- ====================================================================================
-- data Car a b c = Car { company :: a  
--                      , model :: b  
--                      , year :: c   
--                      } deriving (Show)  

-- ====================================================================================

-- data Tree a = EmptyTree | Node a (Tree a) (Tree a) deriving (Show, Read, Eq)  

-- instance Functor Tree where  
--     fmap f EmptyTree = EmptyTree  
--     fmap f (Node x leftsub rightsub) = Node (f x) (fmap f leftsub) (fmap f rightsub)  

-- ====================================================================================

-- Nice! Now how about Either a b? Can this be made 
-- a functor? The Functor typeclass wants a type 
-- constructor that takes only one type parameter 
-- but Either takes two. Hmmm! I know, we'll 
-- partially apply Either by feeding it only one 
-- parameter so that it has one free parameter. Here's 
-- how Either a is a functor in the standard libraries:

data Eithera a b = Lefta a | Righta b  

instance Functor (Eithera a) where  
    fmap f (Righta x) = Righta (f x)  
    fmap f (Lefta x) = Lefta x  


-- ====================================================================================

data Barry t k p = Barry { yabba :: p, dabba :: t k }   deriving (Show)  

instance Functor (Barry a b) where  
    fmap f (Barry {yabba = x, dabba = y}) = Barry {yabba = f x, dabba = y}  

-- ====================================================================================
data Shape = Circle Float Float Float | Rectangle Float Float Float Float deriving (Show)  
-- ghci> Circle 10 20 5  
-- Circle 10.0 20.0 5.0
--
-- ghci> let p = ( Circle 10 20 5  )
-- ghci> p
-- Circle 10.0 20.0 5.0



-- ====================================================================================

data Cat = Cat {
    catName :: String,
    catAge :: Int
} deriving (Show)  

-- ====================================================================================
data Dog = Dog {
    dogName :: String,
    dogAge :: Int
} deriving (Show)  
-- ghci>  let dog1 = (Dog{dogName="pluto", dogAge=5})
-- ghci>  let dog2 = (Dog{dogName="pluto2", dogAge=6})
-- ghci> dog1
-- ghci> dog2
-- ghci> dogName dog2 -- "pluto2"

-- ====================================================================================

data Person = Person { firstName :: String  
                     , lastName :: String  
                     , age :: Int  
                     , height :: Float  
                     , phoneNumber :: String  
                     , flavor :: String  
                     } deriving (Show)  
-- ghci>  let pp = ( Person {firstName="brian", lastName="spinos", age=30, height=5.7, phoneNumber="9998887766", flavor="vanilla" }  )
-- ghci> pp
-- Person {firstName = "brian", lastName = "spinos", age = 30, height = 5.7, phoneNumber = "9998887766", flavor = "vanilla"}


-- ====================================================================================
-- type constructor 'MyBox'
-- with 1 type parameter 'a'
data MyBox a = MyBox a deriving (Show)

instance Functor MyBox where  
    fmap f (MyBox x) = MyBox (f x)

-- ### FUNCTION ###
-- addMe :: Int -> Int -> Int -- declaration not necessary
-- addMe x y = x + y -- you can use any letters
-- result1 = print(addMe 1 3)
-- result2 = print(1 `addMe` 3) -- this also works
changeStr :: String -> String
changeStr a = a ++ "!!!"

-- let mb = (MyBox "cat") -- in the console, or in the main function ?
-- fmap changeStr mb // MyBox "cat!!!"

-- ====================================================================================


data Car = Car {company :: String, model :: String, year :: Int} deriving (Show)  
-- ghci> Car {company="Ford", model="Mustang", year=1967}  
-- Car {company = "Ford", model = "Mustang", year = 1967}  
--
-- ghci> let cc = ( Car {company="Ford", model="Mustang", year=1967}  )
-- ghci> cc
-- Car {company = "Ford", model = "Mustang", year = 1967}

-- ====================================================================================

main = do
    print "hello world"
