#  $ python3 ./script.py 


print("hello")

x = "aaa"
y = 5
z = 1.2
is_foo = True # False

print(type(x)) # <class 'int'>


# list 

foo = ["aa", "bb"]
foo[1] # "bb"
foo.append("date")
print(foo)
foo.extend(["cc", "dd"])
foo.insert(0, "zz")
foo.remove("bb")
foo.pop() # removed, returns last elem
foo.pop(0) # removes, returns item at index 0
foo.clear()
foo.index("aa") # returns index of "aa"
foo.count()
foo.sort()
foo.reverse()
foo.copy() # returns shallow copy



# strings

name = "brian"

name.capitalize()
name.lower()
name.upper()
name.strip() # remove space around str
name.split() # array from words
''.join(['a', 'b'])
name.replace("a", "aaa")
name.find("r") # returns index

name.startswith("br")
name.endswith("ian")
"hello {}".format("world")

ord('A') # 65
chr(65) # 'A'

for char in text:
    print(f"char is {char}")



# tuple (immutable)
coords = (4,5)

print(coords)
print(coords[0]) # 4



# dictionaries 
person = {
    "name": "brian",
    "age": 30
}

person["name"]
person["city"] = "New York"
print(person)

dict = {}
dict["f1"] = "v1"
dict.update({"f2":"v2"})

dict2 = {"f3": "v3"}
dict.update(dict2)

del dict["f1"]
dict.pop("f2")

print(dict.get("f1", "EMPTY"))
print(dict.get("f3", "EMPTY"))

for k,v in dict.items():
    print(f"f: {k}, v: {v}")

dict.clear()



# conditionals


if foo >= 1:
    print("a")
else:
    print("b")


# loops

for i in range(5):
    print(i)


count = 0
while count < 5:
    print(count)
    count += 1


# functions 

def foo(x):
    return f"hello {x}"


# classes 

class Dog:
    def __init__(self, name, age):
        self.name = name 
        self.age = age 
    
    def bark(self):
        return f"{self.name} says woof"

my_dog = Dog("Buddy", 3)
my_dog.bark()


# sets 

fruits = {"aa", "bb"}
print(fruits)
fruits.add("date")
fruits.remove("bb")


# error handling

try:
    risky_operation()
except Exception as e:
    print(f"An error occurred: {e}")
finally:
    print("bb")


# math

import math 
math.sqrt(16) # 4

math.floor(x)
math.ceil(x)


