# Closures in Ruby


```ruby


#
# We have access to @counter inside MyScope class
#
class MyScope
  def initialize
    @counter = 0
  end
    
  def getClosure
    myClosure =  Proc.new do
      @counter += 1
    end
    
    return myClosure
  end
end

#
# We DONT have access to @counter outside MyScope class
# But with a closure, we Do have access to @counter
#

puts "@counter: #{@counter}" # it is empty, of course

puts "==="

closureA = MyScope.new.getClosure()
puts closureA.call # 1
puts closureA.call # 2
puts closureA.call # 3

puts "==="

closureB = MyScope.new.getClosure()
puts closureB.call # 1
puts closureB.call # 2
puts closureB.call # 3


=begin Output:

@counter: 
===
1
2
3
===
1
2
3

=end
```
