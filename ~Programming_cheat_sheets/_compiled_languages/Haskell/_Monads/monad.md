# Monad

```haskell


-- Otherwise you can't do the Applicative instance.
import Control.Applicative


-- Simple function
foo :: String -> String
foo x = do
    x ++ "!!!"
    
-- Helper for printing Monads
print2 :: (Show a) => MyBox a -> IO()
print2 (MyBox x) = print x
    

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

-- (MyBox as a functor) Use a function with a wrapped value
result1 = foo <$> (MyBox "Brian")

-- (MyBox as an Applicative) Use a wrapped function with a wrapped value
result2 = (MyBox foo) <*> (MyBox "Erich")

-- (MyBox as a Monad)  Use a wrapped value with a lambda (it can be chainable)
myLambda1 = (\ x -> MyBox (x ++  " aaa"))
myLambda2 = (\ x -> MyBox (x ++  " bbb"))
myLambda3 = (\ x -> MyBox (x ++  " ccc"))
result3 = (MyBox "Rick") 
    >>= myLambda1
    >>= myLambda2
    >>= myLambda3
    

-- Another Monad syntax
result4 = do
    x <- MyBox "A" 
    y <- MyBox "B"  
    z <- MyBox "C"  
    MyBox (x ++ y ++ z)


main = do
    print2(result1) -- "Brian!!!"
    print2(result2) -- "Erich!!!"
    print2(result3) -- "Rick aaa bbb ccc"
    print2(result4) -- "ABC"
    
    
```
