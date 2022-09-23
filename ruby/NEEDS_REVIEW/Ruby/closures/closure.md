# Closures in Ruby

```ruby
def counter
    n = 0
    
    # returning a closure
    return Proc.new { n += 1 }
end

# The variable `a` is a closure, 
# and it has access to the scope in which it was created,
# that is: the body of the `counter` function, 
# that includes the variable `n`.
a = counter
a.call # 1
a.call # 2

# `b` is another example of a closure.
b = counter
b.call # 1
b.call # 2
```
