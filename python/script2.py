

#
# String
#

name = "brian"
print(name)

print(''.join(['a','b','c'])) # 'abc'

#
# List
#

# my_list = ['a','b','c']
my_list = []
my_list.append('a')
my_list.append("b")

print(my_list)



#
# Set
#

my_set = set() # empty set
my_set = {10,20,30,30}
# my_set = {} # incorrect
my_set.add(100)
my_set.add(200)
my_set.remove(200)
# my_set.remove(10000) # error


print(my_set)


#
# Tuple
#


my_tuple = ("aaa-tuple", 'bbb', 111, 222)
print(my_tuple)




#
# Dictionary
#

my_dict = {
    "name": "brian",
    "age": 30,
    "foo": True
}

print(my_dict)


#
# Class
#


class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def greet(self, name):
        return f"hello {name}"

brian = Person("bri", 30)
print(brian.name)
print(brian.age)
print(f"{brian.name} is {brian.age}")
print(brian.greet("ana"))


try:
    risky_operation()
except Exception as e:
    print(f"err: {e}") # err: name 'risky_operation' is not defined
finally:
    print("finally")


print("======================")

#
# dictionaries
#

dict = {"address": "123 foobar"}
dict2 = {"foo": "bar"}

dict["name"] = "brian"
dict.update({"age": 30})
dict.update({**dict2})

del dict["foo"]
dict.pop("name")


for k, v in dict.items():
    print(f"k:{k}, v:{v}")

print(dict)
print(dict.get("foo", "EMPTY"))

dict.clear()


print("name" in dict) # False





print("======================================")
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


