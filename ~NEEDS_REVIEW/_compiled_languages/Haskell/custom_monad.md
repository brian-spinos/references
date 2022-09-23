# Custom Monad


```haskell
-- :t (>>=) 
-- (>>=) :: Monad m => m a -> (a -> m b) -> m b

-- functions only take 1 parameter

-- :t return
-- return :: Monad m => a -> m a

-- like a hash?
-- data Foo a =  Foo (a, Int)

-- class Monad m where
--     (>>=)  :: m a -> (a -> m b) -> m b -- infix
--     return :: a -> m a

-- ===========

-- Otherwise you can't do the Applicative instance.
import Control.Applicative 
import Control.Monad (liftM, ap)


-- Foo Type constructor
data Foo a = Bar a | Baz  deriving (Show) 


-- Foo Functor
instance Functor Foo where
  fmap = liftM


-- Foo Applicative
instance Applicative Foo where
  pure  = return
  (<*>) = ap


-- Foo Monad
instance Monad Foo where  
    Baz >>= f = Baz   
    Bar x >>= f  = f x 
    return x = Bar x  
    fail _ = Baz  
    

main = do
    print(Bar 5 >>= (\ x -> Bar (x* 10) ) ) -- Bar 50
    
    
    
    
    
```
