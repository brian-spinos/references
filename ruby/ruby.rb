
# $ ruby ./foo.rb


# Docs:
# https://www.tutorialspoint.com/ruby/index.htm



#===========================================================================
# hash

h = Hash.new
h = {}
h = Hash["a" => 100, "b" => 200]
h = {name: "brian", age: 30}

h["name"] #=> nil 
h[:name] #=> "brian" 

h.each do |key,value| 
    puts key 
    puts value 
end

h.each_key do |key| 
    puts key 
end

h.clear # clears all keys/values
h.keys # array of keys
h.empty?
h.inspect
h.to_s
h.length
h.size
h.values # array of values only (no keys)
h.delete(:name) #=> "brian"  (returns value before deleting)


#===========================================================================
# array

names = Array.new
names = Array.new(20)
names = Array.new(4, "mac") # all 4 values are "mac"
names = Array(0..9)

names.size
names.length
n = names.at(6)

#===========================================================================
# modules

module Foo 
    Foo::MY_CONSTANT = 123

    MY_CONSTANT2 = 456

    def Foo.some_method(x)
        return x * 2
    end
    
    def some_module_method(x)
        return x * 100
    end
end


require 'foo.rb' # .rb is optional
class Bar 
    include Foo 

    def m1(x)
        puts Foo::MY_CONSTANT
        puts Foo.some_method(3) # 6
        return x * 10
    end
end


b = Bar.new
b.m1(2)
b.some_module_method(9)
Bar::MY_CONSTANT2 # 456

x = Foo.some_method(2)
y = Foo::MY_CONSTANT # 123

#===========================================================================
# Datetime
#===========================================================================
#  exceptions
#===========================================================================
# class

class SayHello
  def self.from_the_class
    "Hello, from a class method"
  end

  def from_an_instance
    "Hello, from an instance method"
  end
end

#===========================================================================





















#
