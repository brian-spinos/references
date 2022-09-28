# S.O.L.I.D.

###### S - Single responsiblity principle 
- A class should have one and only one reason to change, meaning that a class should have only one job.
- dont try to do everything in a class, make the class do one thing, and do it well

###### O - Open closed principle
- Objects or entities should be open for extension, but closed for modification.
- Dont make modifications that can break the API


###### L - Liskov substitution principle
- Every subclass/derived class should be substitutable for their base/parent class.

###### I - Interface segregation principle
- A client should never be forced to implement an interface that it doesn't use or clients shouldn't be forced to depend on methods they do not use.
- You should break up your interfaces into smaller ones

###### D - Dependency Inversion Principle
- Entities must depend on abstractions not on concretions. It states that the high level module must not depend on the low level module, but they should depend on abstractions.
- Use an interface to plugin low-evel code! so it can be swappable in the future!
