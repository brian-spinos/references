# Custom Typeclass

```haskell
-- data User = Brian | Erich | Rick

data User = User {
    name :: String,
    address :: String,
    age :: Int
}

brian = User {name = "Brian Spinos", address = "123 Foobar st", age = 28}



class MyTypeClass u where
    foo :: u -> String -> Bool
    bar :: u -> String -> Bool

instance MyTypeClass User where
    foo (User name address age) "Brian" = True 
    foo (User name address age) "Erich" = True 
    foo (User name address age) "Rick" = True 
    foo (User name address age) _ = False 

    bar (User name address age) "Brian" = True 
    bar (User name address age) "Erich" = True 
    bar (User name address age) "Rick" = True 
    bar (User name address age) _ = False 
    
  
main = do
    print "Hello"
    print(foo brian "Brian")

```
