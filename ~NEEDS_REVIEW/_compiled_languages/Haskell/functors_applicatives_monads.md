# Functors, Applicatives and Monads


```haskell
-- ================================================================
-- Otherwise you can't do the Applicative instance.
import Control.Applicative 

-- ================================================================ Custom types

-- Custom type declaration
data User = User {
    name :: String,
    address :: String,
    age :: Int
}


-- Custom type initialization
brian = User {name = "Brian Spinos", address = "123 Foobar st", age = 28}


-- Function that operates on a custom type
getUserName :: User -> String
getUserName (User name address age) = name


-- Function that operates on a custom type (with "IF" statement)
canUserDrive :: User -> Bool
canUserDrive (User name address age)
    | age  >= 18 = True
    | age  < 18 = False


-- Simple getter function, we dont really need this, since Haskell creates these already!
getUserAddress :: User -> String
getUserAddress user = address user


-- Case statement
isUserBrian :: User -> String
isUserBrian user = case (name user) of
    "Brian Spinos" -> "Yup"
    "Erich Spinos" -> "Nope"
    _ -> "Other Guy..."
    
    
-- ============================ Typeclass (interface)

-- Declaration of a typeclass:
class Student u where  
    isGoodStudent :: u -> Bool 
    
    
-- Usage of a typeclass:
instance Student User where
    isGoodStudent user = case (name user) of
        "Brian Spinos" -> True
        "Erich Spinos" -> False
        _ -> False
    
    
-- -- Usage of a typeclass:
-- instance Student User where  
--     isGoodStudent (User name address age) = True

    
-- ================================================= Functor
-- Functor is like a container for data, and how a function can access the data!


-- class Functor f where  
--     fmap :: (a -> b) -> f a -> f b  


-- Custom type declaration
data MyBox a = MyBox a


-- MyBox functor
instance Functor MyBox where  
    fmap f (MyBox x) = MyBox (f x)


-- Usage
myBoxFunc :: String -> String
myBoxFunc x = do
    x ++ " with myBoxFunc"
    
result = myBoxFunc <$> (MyBox "inbox") -- (MyBox "inbox with myBoxFunc")
printMyBox (MyBox x) = print x 
-- printMyBox(result) -- "inbox with myBoxFunc"

    
-- ================================================= Applicative
-- Applicative is like a container for data, and how a "WRAPPED FUNCTION" can access the data
-- the "MyBox" needs to be a Functor for this to work!


-- class (Functor f) => Applicative f where  
--     pure :: a -> f a  
--     (<*>) :: f (a -> b) -> f a -> f b  
    
    
-- instance Applicative Maybe where  
--     pure = Just  
--     Nothing <*> _ = Nothing  
--     (Just f) <*> something = fmap f something 


-- MyBox Applicative 
instance Applicative MyBox where  
    pure = MyBox 
    (MyBox f) <*> x = f <$> x


-- Usage
myBoxWrappedFunc :: String -> String
myBoxWrappedFunc x = do
    x ++ " with myBoxWrappedFunc"
    
result2 = (MyBox myBoxWrappedFunc) <*> (MyBox "inbox") -- (MyBox "inbox with myBoxWrappedFunc")
-- printMyBox (MyBox x) = print x -- already defined!
-- printMyBox(result2) -- "inbox with myBoxWrappedFunc"


-- ================================================= Monad

-- class Monad m where  
--     return :: a -> m a  
--     (>>=) :: m a -> (a -> m b) -> m b  
--     (>>) :: m a -> m b -> m b  
--     x >> y = x >>= \_ -> y  
--     fail :: String -> m a  
--     fail msg = error msg  


-- instance Monad Maybe where  
--     return x = Just x  
--     Nothing >>= f = Nothing  
--     Just x >>= f  = f x  
--     fail _ = Nothing  

-- MyBox Monad
instance Monad MyBox where  
    return x = MyBox x
    MyBox x >>= f  = f x 

-- Usage
myBoxFunc2 :: String -> String
myBoxFunc2 x = do
    x ++ " with myBoxFunc2"
    
result3 = (MyBox "inbox") >>= (\ x -> MyBox (x ++  " with Monad") )  -- (MyBox "inbox with Monad")
-- printMyBox (MyBox x) = print x -- already defined!
-- printMyBox(result3) -- "inbox with Monad"

-- This also works!
-- result3 = (MyBox "inbox") 
--     >>= (\ x -> MyBox (x ++  " with Monad") ) 
--     >>= (\ x -> MyBox (x ++  " with Monad2") )

-- This also works!  (Monads with the 'do' notation)
foo :: MyBox String  
foo = do  
    x <- MyBox "A" 
    y <- MyBox "B"  
    z <- MyBox "C"  
    MyBox (x ++ y ++ z)  

-- printMyBox(foo) -- "ABC"
    
-- =================================================
    
main = do
    print "Hello"
    
    print(name brian) -- "Brian Spinos"
    print(address brian) -- "123 Foobar st"
    print(age brian) -- 28
    
    print(getUserName brian) -- same as: print $ getUserName brian
    print(canUserDrive brian)
    
    print(getUserAddress brian) -- "123 Foobar st"
    
    print(isUserBrian brian) -- "Yup"
    
    print(isGoodStudent brian) -- True
    
    printMyBox(result) -- "inbox with myBoxFunc"
    printMyBox(result2) -- "inbox with myBoxWrappedFunc"
    printMyBox(result3) -- "inbox with Monad"
    printMyBox(foo) -- "ABC"
    
```
