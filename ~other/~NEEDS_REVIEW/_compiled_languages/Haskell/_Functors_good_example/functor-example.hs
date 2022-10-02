-- ============================================
-- Examples of Functors with custom data types!

-- ============================================
{-

-- `a` is the same type, not the same value, ok?  1, 2, 3 is ok,   "1", 2, "three" is not ok
data Tree a = EmptyTree | Node a (Tree a) (Tree a) deriving (Show, Read, Eq)  


instance Functor Tree where  
    fmap f EmptyTree = EmptyTree  
    fmap f (Node x leftsub rightsub) = Node (f x) (fmap f leftsub) (fmap f rightsub)  




data Either a b = Left a | Right b  

instance Functor (Either a) where  
    fmap f (Right x) = Right (f x)  
    fmap f (Left x) = Left x 


-}


-- ============================================
-- Type constructor
data Car a b c = Car { company :: a  
                     , model :: b  
                     , year :: c   
                     } deriving (Show)  
-- tellCar (Car {company = c, model = m, year = y}) = "This " ++ c ++ " " ++ m ++ " was made in " ++ show y  

-- Caveat, you need to leave out the last parameter!
instance Functor (Car a b) where  
  fmap f (Car a b c) = (Car a b (f c))

-- ============================================
-- a and b represent different data types
-- a and a does not necessarilly mean the same exact value,
--         but the same type!
data Spinos b a = Brian a | Erich a deriving (Show)

instance Functor (Spinos b) where  
  fmap f (Erich a) = Erich (f a)  
  fmap f (Brian a) = Brian (f a)

-- ============================================
data Tree a = EmptyTree | Node a (Tree a) (Tree a) deriving (Show, Read, Eq)  

instance Functor Tree where  
    fmap f EmptyTree = EmptyTree  
    fmap f (Node x leftsub rightsub) = Node (f x) (fmap f leftsub) (fmap f rightsub)

-- ============================================
data Either2 a b = Left2 a | Right2 b  


instance Functor (Either2 a) where  
  fmap f (Right2 x) = Right2 (f x)  
  fmap f (Left2 x) = Left2 x 

-- ============================================

-- ============================================
main = do
  let x = "aaa"
  let y = "bbb"
  let z = (Car "toyota" "corolla" "2015")
  print x
  print y
  print z
  let xx =  (
              Node 1
                (Node 2 EmptyTree EmptyTree)
                (Node 3 EmptyTree EmptyTree)
            )
  let xx2 = fmap (*100) xx
  print xx
  print xx2
  let xx3 = fmap (++"qqq") (Brian "www")
  print xx3 -- Brian "wwwqqq"

  let xx4 = fmap (++"555") (Car "toyota" "corolla" "2015")
  print xx4 -- Car {company = "toyota", model = "corolla", year = "2015555"}
