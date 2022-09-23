# Elixir

- http://tryelixir.online/ (Don't use this one)

- http://elixirplayground.com/


```elixir

# Keyword list
[{:a, 1}, {:b, 2}] === [a: 1, b: 2] # true



IO.puts "Hello world"

IO.puts("Hello"); IO.puts("World!")


IO.puts(:brian) # atoms

# true false

# This is a comment in Elixir

{a, b, c} = {:hello, "world", 42}
IO.inspect {a, b, c}


# Lists are actually stored as linked lists, so insertions, deletions are very fast in lists.
# Tuples on the other hand, are stored in contiguous memory block, which make accessing them faster but adds an # additional cost on insertions and deletions.


name = "Spinos"
IO.puts name


my_list = [123, "ABC", :an_atom, true]
IO.inspect my_list

my_tuple = {123, "ABC", :an_atom, true}
IO.inspect my_tuple


my_number = 123
IO.puts my_number


# You can create lists of key value tuples
my_list2 = [name: "Brian", age: 29, address: "123 foobar st"]
IO.inspect my_list2

my_map = %{"name" => "Brian", "age" => 29, "address" => "123 foobar st"}
IO.inspect my_map
IO.puts my_map["name"]


my_map2 = %{name: "Brian", age: 29, address: "123 foobar st"}
IO.inspect my_map2
IO.puts my_map2[:name]
IO.puts my_map2.address




get_sum = fn (x, y) -> x + y end
IO.puts "5 + 5 = #{get_sum.(5,5)}"


#========================= functions
defmodule MY_MODULE do
  def foo(x, y) do
    x + y
  end
end
IO.puts MY_MODULE.foo(2, 3)
#========================= 


# lambdas
sum = fn (a, b) -> a + b end
IO.puts(sum.(1, 5))


# Loops
for n <- 1..3 do
  IO.puts "Hello ##{n}"
end

IO.puts "=========================="
#=============================== Structs
defmodule User do
  defstruct name: nil, age: nil, address: nil
end

defmodule AnotherModule do # we cannot call `%User{}` in the same contexts it was defined.
  def test_user do
    brian = %User{name: "spinos", age: 29, address: "foobar st"}
    IO.puts(brian.name)
    IO.puts(brian.age)
    IO.puts(brian.address)
    
    # update
    brian = %{brian | name: "Brian Spinos"}
    IO.puts(brian.name)
    IO.puts(brian.age)
    IO.puts(brian.address)
  end
end


AnotherModule.test_user()




#=============================== `use` keyword
defmodule MyModuleA do  
  defmacro __using__(_) do
    quote do
      def foo(s) do
        IO.puts(s <> "!!!")
      end
    end
  end
end  

defmodule MyModuleB do  
  use MyModuleA
end

MyModuleB.foo("Brian") # Brian!!!


#===============================================

defmodule Brian do
  
  # public function
  def foo(a, b) do
    r = a + b
    bar(r)
  end
  
  # private function
  defp bar(msg) do
    IO.puts "private msg: #{msg}"
  end
  
  # recursion
  def rec([head | tail]) do
    IO.puts head * 10
    rec(tail)
  end
  
  def rec([]) do
    IO.puts "DONE!"
  end
  

  #
  # functions for piping
  #

  def bazA(x) do
    x <> "A"
  end
  
  def bazB(x) do
    x <> "B"
  end
  
  def bazC(x) do
    x <> "C"
  end
end

Brian.foo(5, 5) 


# Piping
"Elixir rocks" |> String.upcase() |> String.split()

# Piping
IO.puts "BRIAN: " 
|> Brian.bazA() 
|> Brian.bazB()
|> Brian.bazC() # BRIAN: ABC


# Recursion
Brian.rec([1,2,3,4,5,6])


# Pattern matching
[name, age] = ["Brian", 29]
IO.puts name
IO.puts age


# Case statement
case {1, 2, 3} do
  {4, 5, 6} ->
    "This clause won't match"
  {1, x, 3} ->
    "This clause will match and bind x to 2 in this clause"
  _ ->
    "This clause would match any value"
end



# lambda with pattern matching
my_lambda = fn
  {x} -> IO.puts "1 arg: #{x}"
  {x, y}  -> IO.puts "2 arg: #{x}, #{y}"
  :error -> IO.puts "ERROR"
end
   
my_lambda.({"one"})
my_lambda.({"one", "two"})
my_lambda.(:error)

#=========================================


# variables are imutable

foo = 123

_foo = 456.45 # float

is_integer(foo)
is_float(foo)
is_atom(foo)
is_tuple(foo)
String.length("Brian")

x = true and true # and, or
y = not true



if foo do # if, unless
  # stuff
else
  # stuff
end  

# condition (produce output on the first match only):
age = 18

cond do
  age >= 14 -> IO.puts "Can wait"
  age >= 16 -> IO.puts "Can drive"
  age >= 18 -> IO.puts "Can vote" # <==
  true -> IO.puts "default option!"
end


# case
user = "Rick"
case user do
  "Brian" -> IO.puts "This is Brian"
  "Erich" -> IO.puts "This is Erich"
  "Rick" -> IO.puts "This is Rick"
  _ -> IO.puts "Default"
end



# ternary
n = 12
result = if n > 10, do: "10+", else: "10-"


# tuple
result2 = {123, 45.67, :foobar}
result3 = Tuple.append(result2, 78)


# list
result4 = [1,2,3]

Enum.each [4,5,6], fn(item) ->
  IO.puts item
end


# maps
result5 = %{"foo" => "bar", "foo2" => "bar2"}
result6 = %{foo: "bar", foo2: "bar2"}
IO.puts result5["foo"]
IO.puts result6[:foo]
IO.puts result6.foo


# lambda
add = &(&1 + &2)
IO.puts add.(1,2)



defmodule ABC do
  def func2(x \\ 1, y \\ 1) do 
    x + y
  end
end
  
IO.puts ABC.func2 10 # 11



# nil



# list comprehension
result7 = for n <- [1,2,3], do: n * 2
IO.puts result7



```
