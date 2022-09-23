# Closures


###### Closures with procs
```ruby
create_closure = Proc.new do
  puts "Creating closure"
  count = 0
  next Proc.new do # the `next` keywork is the `return` for procs!
    count += 1 
    puts count
  end 
end


closure1 = create_closure.call
closure1.call # 1
closure1.call # 2
closure1.call # 3

closure2 = create_closure.call
closure2.call # 1
closure2.call # 2
closure2.call # 3
```

###### Closures with functions
```ruby
def create_closure2
  puts "Creating closure"
  counter = 0
  
  return Proc.new do
    counter += 1 
    puts counter
  end
end 

closure3 = create_closure2
closure3.call # 1
closure3.call # 2
closure3.call # 3

closure4 = create_closure2
closure4.call # 1
closure4.call # 2
closure4.call # 3
```
