import array

# https://www.programiz.com/python-programming/array

arr = array.array('i', [0] * 5)
arr[1] = 11
arr[2] = 22
arr[3] = 33
arr += array.array('i', [0] * 5)
# arr.extend([0]*5)
arr[9] = 999
print(arr)


li = [1,2,3]
li += [0] * 3
li[5] = 555
print(li)


'''OUTPUT
array('i', [0, 11, 22, 33, 0, 0, 0, 0, 0, 999])
[1, 2, 3, 0, 0, 555]
'''

x = set()
y = deque()


print("person details {}, {}".format(name, age))


if __name__ == "__main__":
    print("this file is being run from commandline")




# instance variables need to use:
self.foo

# instance fields are created inside the constructor
def __init__(self):
    self.foo = 123
    self.bar = 456


# print the firs string block in the class
print(obj.__doc__)



@classmethod
def classmethod(cls):
    print('class method called', cls)

@staticmethod
def staticmethod():
    print('static method called')



# integer to string:
str(123)
print("aaa" + str(123))

int("5")
str(123)

# concat string:
"aaa" + "bbb"

# instantiation:
brian = Person("Brian Spinos", 28)
brian.sayHello()



print "Total Employee %d" % Employee.empCount
print "Name : ", self.name,  ", Salary: ", self.salary


import math

math.ceil(1.4)

print('hello', 'there') #=> 'hello there'




# array:
arr = [1,2,3,4,5]
print arr
print len(arr) # arr length



# same as java substring
foo = "brian"
print(foo[0:3]) # bri
print(foo[3:5]) # an
print(foo[:3]) # bri
print(foo[3:]) # an



# same as java substring
arr = [1,2,3,4,5]
print(arr[1:4]) # [2, 3, 4]
print(arr[2:]) # [3, 4, 5]
print(arr[:3]) # [1, 2, 3]



if 1 < 3 or 4 == 4:
    print('yeah')
elif 2 < 6:
    print('wha?')
else:
    print('no')




dict = {
  "name": "brian",
  "age": 32,
  456: "four five six"
}

print dict["name"] # brian
print dict["age"] # 32
print dict[456] # four five six




# no method overloading



# check if key is present in dictionary
key in dict == True



# private method:
def __foo(self):
    return 123

# increment/decrement:
a += 1
a -= 1




# arrays: LISTS

foo = []
foo.append(1)
foo.append(2)
foo.append(3)
foo.append(4)
foo[-1] # last item

foo = foo[:-1] # delete last



# initialize list to 10 items:
myList = [None] * 10

# append 10 None's to array:
myList += [None] * 10


# private field in class:
self.__foo


#------------------------------ PYTHON 3

# int division (no decimal)
7//3

print("abc"), # does not work
print("abc", end="") # for no new line ???

# u can use method:
def print(self, foo):
    print(foo)

x = 123
y = "abc"
print(f"hello {x} and {y}")
# hello 123 and abc
