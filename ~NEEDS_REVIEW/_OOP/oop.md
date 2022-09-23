# O.O.P. 


###### Encapsulation
- Hide variables, use getters and setters, for the purpose of protection.
- Hide unnecessary details in our classes and provide a clear and simple interface for working with them.

###### Inheritance
- Inherit functionality of another class, write less code.
- Improves code readability and enable the reuse of functionality.

###### Abstraction
- Hide implementation, for the purpose of simplification.
- Deal with objects considering their important characteristics and ignore all other details.

###### Polymorphism
- Work in the same manner with different objects, which define a specific implementation of some abstract behavior.


# OOP  relationships 


###### Inheritance
Inheritance is “IS-A” type of relationship. 
Example: Dog inherits from Animal.


###### Association
Association is a “has-a” type relationship.
Example: doctor and a patient, they can exist independently.


###### Aggregation
Composition is a loose "part-of" relationship.
One objects is the parent, and the other the child
If the parent object is destroyed, the child objects would NOT cease to exist
Example: relationship between library and book,
books will remain so even when the library is no more


###### Composition
Composition is a strict "part-of" relationship.
Example: “an engine is part of a car”, “a heart is part of a body”.
If the parent object is destroyed, the child objects would be destroyed also.
