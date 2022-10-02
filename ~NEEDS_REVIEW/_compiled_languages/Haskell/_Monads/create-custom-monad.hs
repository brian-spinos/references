-- ================================ Custom Monad definition

-- Otherwise you can't do the Applicative instance.
import Control.Applicative

-- Custom type declaration
data MyBox a = MyBox a

-- MyBox functor
instance Functor MyBox where  
    fmap f (MyBox x) = MyBox (f x)

-- MyBox Applicative 
instance Applicative MyBox where  
    pure = MyBox 
    (MyBox f) <*> x = f <$> x

-- MyBox Monad
instance Monad MyBox where  
    return x = MyBox x
    MyBox x >>= f  = f x 
    
-- ================================ Helpers

-- Helper for printing Monads
myMonadPrinter :: (Show a) => MyBox a -> IO()
myMonadPrinter (MyBox x) = print x

-- Simple function (appends -foo2)
foo2 :: String -> String
foo2 x = do
    x ++ "-foo2"

-- Simple lambda function (appends -foo)    
-- with a Monad you can use functions and lambdas
foo = (\x -> x ++ "-foo")

-- ================================ Usage
    
-- (MyBox as a functor)
-- Use a function with a wrapped value
m1 = foo <$> (MyBox "brian")

-- (MyBox as an Applicative)
-- Use a wrapped function with a wrapped value
m2 = (MyBox foo) <*> (MyBox "erich")

-- (MyBox as a Monad)  
-- Use a wrapped value with a lambda chain
myInitialMonad1 = MyBox "aaa"  
func1 = (\x -> MyBox (x ++ "-bbb"))
func2 = (\x -> MyBox (x ++ "-ccc"))
func3 = (\x -> MyBox (x ++ "-ddd"))
m3 = myInitialMonad1
    >>= func1
    >>= func2
    >>= func3
    
-- (MyBox as a Monad)  
-- using "do" notation
myInitialMonad2 = MyBox "aaa"    
m4 = do
    v1 <- myInitialMonad2
    v2 <- func1 v1
    v3 <- func2 v2
    v4 <- func3 v3
    -- Here we have access to v1,v2,v3,v4
    MyBox (v4 ++ "-2")

main = do
    putStrLn "Custom Monad example:"
    myMonadPrinter m1 -- "brian-foo"
    myMonadPrinter m2 -- "erich-foo"
    myMonadPrinter m3 -- "aaa-bbb-ccc-ddd"
    myMonadPrinter m4 -- "aaa-bbb-ccc-ddd-2"


{-- OUTPUT:

Custom Monad example:
"brian-foo"
"erich-foo"
"aaa-bbb-ccc-ddd"
"aaa-bbb-ccc-ddd-2"

--}
