# Haskell functions

```haskell
-- The function declaration below is saying: 
-- 'this functions takes an int and returns an int' (the return is the last data type)
addTwo :: Int -> Int -- declaration is optional

addTwo x = x + 2

-- There should have a main function, called main!
main = do
    print (addTwo 3) -- 5
```

###### Combining functions

```haskell
addTwo :: Int -> Int
addTwo x = x + 2

crazyCalculation :: Int -> Int -> Int
crazyCalculation x y = addTwo x + addTwo y

main = do
    print (crazyCalculation 3 3) -- 10
```

###### Using the 'do' syntax

```haskell

printOneAndTwo = do
  print 1
  print 2

main = do
  printOneAndTwo
```
