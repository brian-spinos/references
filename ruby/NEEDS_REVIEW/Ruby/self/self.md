# self in ruby


```ruby
class User 
    
    puts "================"
    puts "1. self is class: #{self}" # class
    puts "2. self is class: #{User}" # class
    
    def foo
        puts "================"
        puts "3. self is instance: #{self}" # instance
        puts "4. self is class: #{User}" # class
    end
    
    def self.bar
        puts "================"
        puts "5. self is class: #{self}" # class
        puts "6. self is class: #{User}" # class
    end
end 

brian = User.new
brian.foo
User.bar

=begin Output:

================
1. self is class: User
2. self is class: User
================
3. self is instance: #<User:0x0055a7922d24d8>
4. self is class: User
================
5. self is class: User
6. self is class: User

=end
```
