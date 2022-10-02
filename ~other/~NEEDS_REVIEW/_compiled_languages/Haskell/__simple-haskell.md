```haskell


-- data type
data Person = Person {
  name :: String,
  age :: Int
} deriving (Show)


-- Typeclass
class Worker u where
    wakeup :: u -> String
    sleep :: u -> String


instance Worker Person where
    wakeup (Person name age) = "working..." 
    sleep (Person name age) = "sleeping..." 
   

main = do
  let brian = Person {name="brian", age=29}
  print $ show brian
  print $ wakeup brian
  print $ sleep brian
  

-- "Person {name = \"brian\", age = 29}"
-- "working..."
-- "sleeping..."
```
