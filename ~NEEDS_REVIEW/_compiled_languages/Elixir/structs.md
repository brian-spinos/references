# Structs

```elixir
defmodule User do
  
  defstruct name: nil, age: nil
  
  def create(name, age) do
    %User{name: name, age: age}
  end
  
  def sayHi(user) do
    "Hello, my name is #{user.name}!"
  end
  
  def changeName(user, name) do
    struct(user, name: name)
  end
end

b = User.create("brian", 29)
IO.puts b.name # brian
IO.puts User.sayHi(b) # Hello, my name is brian!


b = User.changeName(b, "erich")
IO.puts b.name # erich
IO.puts User.sayHi(b) # Hello, my name is erich!
```
