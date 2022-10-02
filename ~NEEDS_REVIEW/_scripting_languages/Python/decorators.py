# Decorator:

def myDecorator(func):
    def myWrapper(name):
        print("\nDecorated!")
        func(name)
    return myWrapper

@myDecorator
def foo(name):
    print("foo: " + name)

foo("A")
foo("B")


""" OUTPUT:
Decorated!
foo: A

Decorated!
foo: B
"""
