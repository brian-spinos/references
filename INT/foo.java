// https://www.tutorialspoint.com/java/index.htm


// WORA - write once run anywhere


// JDK > JRE > JVM

// java heap - stores objects ?
// Stack: Stores method calls, local variables, and references.

// Java is statically typed

// $ javac MyFirstJavaProgram.java
// $ java MyFirstJavaProgram


// download java SDK to a folder aa/bb/cc/jdk, then set the PATH=$JAVA_HOME/bin:$PATH
// JAVA_HOME - set the JDK folder
// java -version and javac -version

// TODO;
/**
 *  - type casting
 *  - method overloading & overriding
 *  - OOP
 *  - abstraction 
 *  - access modifiers
 *  - Inheritance, Encapsulation, Polymorphism, abstraction -- OOP , and:  association, aggregation, and composition.
 *  - overriding
 *  - dynamic/static binding
 *  - Instance Initializer Block
 *  - collections : List, Queue, Map, SortedMap, Set, SortedSet, Iterators, comparators 
 *  - files ?
 *  - threads ? - you create a class that 'extends Thread' or 'implements Runnable' // t.join() <--- waits for it to complete work!
 *  - JIT
 *  - ...
 */


// one public class per file, but can have multiple non-public classes


package com.example.myapp; //  directory structure ./src/com/example/myapp

import com.example.myapp.MyClass; // import com.example.myapp.*; (import all)

public class Foo {
    /* comment */
    // comment
    /**
     * comment
     */
    public static void main(String[] args){
        System.out.println("Hello");
    }
}


enum FOO_ENUM {
    BAR,
    BAZ,
    BUZ
}

FOO_ENUM fooenum = FOO_ENUM.BAR;

// 8 primitive data types in JAVA, need to correct
byte bbb = -128; // 127 - 1 byte (size)
short mySHort = 32767; // -32k, 32k // 2 bytes (size)
int age = 123; // 4 bytes 2B positive,negative  --------- int x = 5;  // Binary: 00000000 00000000 00000000 00000101
long age2 = 9000000000000000000L; // 8 bytes  - 9Quintilion + -

float flow = 3.14f; // 4 bytes // has rounding errors, dont use currency
double pi = 3.152155235; // 8 bytes // has rounding errors, dont use currency

boolean x = true; // 1-bit (in Java, it could be 1 byte)
char firstLetter = 'A'; // 2 bytes, 65k values



package com.brianspinos;
class User {
    public String name;

    public static final String APP_NAME = "aaaa"; // ???? correct ?

    public User(String name){
        this.name = name;
    }

}


User u = new User("brian");


// concatenation:
"foo " + bar + " baz"


// type casting , Widening type casting , Narrowing type casting // example: int to double

// Type mismatch: cannot convert from double to int
      int num1 = 5004;
      double num2 = 2.5;
      int sum = num1 + num2;

//
      int num = 5004;
      double doubleNum = (double) num;
      int convertedInt = (int) doubleNum;


// unicode, (2 bytes)
// \u0000 and the highest value is represented by \uFFFF.
char unicodeChar = '\u0041'; // 'A'
char unicodeChar2 = 'A';

char letter1 = 'A';
char letter2 = (char) (letter1 + 32); // 'a'



//============================================================ Math


// + - * / % ++ --

// == != > < >= <=

//============================================================ bitwise operations


a & b = 0000 1100
a | b = 0011 1101
a ^ b = 0011 0001  // XOR
~a  = 1100 0011 // bitwise complement operator - flips all bits
        int x = 5;  // Binary: 00000000 00000000 00000000 00000101
        int result = ~x; // Binary: 11111111 11111111 11111111 11111010

<< (left shift)
>> (right shift)  // preservers the posotive/negative sign
>>> (zero fill right shift) // DOES NOT preservers the posotive/negative sign

//============================================================ logical operators 

// && || !



//============================================================ operators  


// =, -= += *= /=  %= <<= >>= &= ^= |= 



//  ternary operator
int b = (a == 1) ? 20 : 30;

// instanceof
boolean result = name instanceof String; // passes the IS-A check

// generic type to precise type
Vehicle a = new Car();
boolean result =  a instanceof Car; // true


//============================================================ Javadoc - (documentation comment)

/**
 * Hello - this will be understood by the `Javadoc` tool and can be used to create HTML-based docs
 * @param foo
 * @param bar
 * @throws IllegalArgumentException if divisor is zero
 */





//============================================================ user input

import java.util.Scanner;

Scanner obj = new Scanner(System.in);

System.out.print("Enter the first number: ");
int num1 = obj.nextInt(); 
// .nextLine() // "hello world"




//============================================================ DateTime (not recommended?)

Date date = new Date();
System.out.println(date.toString());
// Thu Jan 02 12:34:56 PST 2025


      Date dNow = new Date( );
      SimpleDateFormat ft = 
      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

      System.out.println("Current Date: " + ft.format(dNow));

//============================================================ java.time (recommended)

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

LocalDateTime now = LocalDateTime.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
System.out.println(now.format(formatter));

//============================================================ sleep 

Thread.sleep(3000); // 3 seconds



//============================================================ loops

// if, else if, else 


if(foo){
    //
} else if(bar){
    //
}else{
    //
}



switch(foo){
    case 'AAA': {
        //
        break;
    }

    case 'BBB': {
        //
        break;
    }

    default: { // optional
        //
    }
}



//============================================================ for loop


// break; to break out of a loop

// continue; to jump to next iteration


for(int i=0;i < 10; i++){
    //
}



int[] arr = {0,1,2,3};
for(int i=0;i < arr.length; i++){
    //
}

import java.util.Arrays;
import java.util.List;

List<Integer> arr = Arrays.asList(10,20,30,40,50); // List<String>
for(Integer item : arr) {
   // Statements
}



while(foo){
    //
}


do {
    // exec at least once!
    // x++; // something needs to change :) 
}while(foo);


//============================================================ abstract classes

// - If a class is declared abstract, it cannot be instantiated. - you need a child class inheriting from it

public abstract class Employee {
    private String name;

    public Employee(String name) {
      this.name = name;
   }

   public double computePay() {
     return 300000.0;
   }

   public abstract double computePay(); // <--- abstract method
}

//============================================================ OOP Abstraction      (abstract classes & interfaces)

// think of methods without a body: like abstract-classes and interfaces -- meaning a child class has to implement it

//    - hide implementation details - example: Radio
//        - by using abstract-classes and interfaces 



// - as in abstract methods, that dont have a body, that is the abstract part of it !!!
//       - so a child class has to implement it!


// - in interfaces, the methods are also abstract, as they dont have a body 




//============================================================ OOP Polymorphism (2 types)

/*
 *   - compile-time polymorphism (static) 
 *       - in same class, same method name, but diff signatures, think adding int/double - (method overloading)
 * 
 *                  - obj.add(myInt, myInt)
 *                  - obj.add(myLong, myLong)
 *                  - obj.add(myDouble, myDouble)
 * 
 * 
 *   - run-time polymorphism (Dynamic) 
 *       - multiple child classes overrides parent method, example: Dogs, Cats are Animals - (Method overriding)
 *       - and intead of a parent class, you could use an interface: Animal
 * 
 * 
 * 
 *              - child class overrides parent method implementation
 *              - Animal animal = new Dog();
 *              - animal.talk() // "ruff"
 * 
 *              
 *              - classes implement method from interface:
 *              - class Car implements Vehicle        {      start(){}        }
 *              - class Motorcycle implements Vehicle {      start(){}        }
 * 
 */


// - parametric polymorphism 
//    - using generics - allows operation on diff types, without changing the code 
class Box<T>{}
public <T> void print(T item){}




public interface Vegetarian{}
public class Animal{}
public class Deer extends Animal implements Vegetarian{}

//
// because of polymorphism
//
// A Deer IS-A Animal
// A Deer IS-A Vegetarian
// A Deer IS-A Deer
// A Deer IS-A Object
//
// legal declarations: because of polymorphism
// polymorphism allows objects to be treated as intances of their parent/interface types <<<<<<<<<<<
Deer d = new Deer();
Animal a = d;
Vegetarian v = d;
Object o = d;

// -- SEE:  virtual method invocation, key feature of polymorphism (because of many forms: one method, many implementations)
//             -  basically calling child @Override method from parent reference 
Animal myDog = new Dog();
myDog.sound();  // Calls the overridden method in Dog

// -- SEE also: Dynamic/late binding, same as above ??? - at run-time JVM checks if method is overriden, and calls based on object actual type, not reference type

//============================================================ OOP Inheritance


// inheritance: reuse functionality/properties of existing classes 
class Super {
   ...
}
class Sub extends Super {
   ...
}

super.variable // access parent stuff 
super.method();
this.variable // access child stuff
this.method();


//============================================================ OOP Encapsulation (data hiding)
// encapsulation:
//    - encapsulate attributes/methods together, using getters/setters to access private data, in a single unit
//    - 
//    - 



//============================================================ OOP continued

obj.age = 3; // possible, but use getters/setters


// Constants : use `final` --> public static final String name = "Tommy";


class Dog {
   public final String name = "Tommy"; // read-only
   // dog.name = "Tommy2";  // Error while modifying name
}


package com.tutorialspoint;
class Student {
   int age
   Student() {
      this(20); // the keyword `this` can also call the contructor !!! <-- "explicit constructor invocation" using `this(...)` or `super(...)`
   }
   
   Student(int age) {
      this.age = age;	
   }
}


//============================================================ OOP static methods
public class Foo {
  static void func() {
    //
  }
}

Foo.func();


//============================================================ function called before object is destroyed:
protected void finalize( ) {
   // object to terminate cleanly
}


//============================================================ var-args
public static void printMax( double ...numbers) {
    //
}


//============================================================ inheritance 

// Animal > Mammal > Dog --> dog is a Mammal, dog is an Animal (instanceof)


//============================================================ Interfaces 

// An interface is implicitly abstract
// Methods in an interface are implicitly public.

//  tagging interface - EMPTY interface, for taagging purposes


// interfaces can extend other interfaces !!!
public interface Football extends Sports {...}



// Along with abstract methods, an interface may also contain 
// - constants
// - default methods
// - static methods
// - and nested types.
// - static final fields

//  Method bodies exist only for default methods and static methods.

public interface Animal {
    // Any number of final, static fields
    // Any number of abstract method declarations
    public void eat();
    public void travel();
}
public interface Mammal {}

public class Dog extends Animal, Mammal {}

// `instanceof` works same as a class, so the parent could be a class or in interface

//============================================================ (Aggregation) HAS-A -- an object has a field that is another object!
public class Vehicle{}
public class Speed{}

public class Van extends Vehicle {
   private Speed sp;
} 

// Van/Vehicle and Speed can exist independently 

// Speed is added to Van/Vehicle by a setter, and these two objects are created independently

//============================================================ (Composition) HAS-A

// an object owns another object, and the owned object cannot exist separately 
// - onwed object is in a private field in the parent object
// - and created inside a constructor


// example: Car owns Engine

class Car {
    private Engine engine; // Composition: Car owns Engine

    public Car(String engineType) {
        this.engine = new Engine(engineType); // Car creates the Engine
    }
}



//============================================================ instance initializer blocks

// - usefull if class has multiple constructors and they all have some initializing logic in common

public class Example {
    private int value;

    { // executed after super constructor and before child constructor
        // Common initialization
        value = 42;
        System.out.println("Initializer block executed");
    }

    private static int staticCounter;

    static {
        // static initializer block, to init static variables, static setup logic. - executed when class is loaded into memory, no object has yet been created
        staticCounter = 100;
    }

    public Example() {
        System.out.println("Default constructor");
    }

    public Example(String param) {
        System.out.println("Constructor with parameter: " + param);
    }
}


//============================================================ ArrayList

List<String> items = new ArrayList<>();
        items.add("Item1");
        items.add("Item2");



//============================================================  virtual method invocation


//============================================================  Static binding

// Polymorphism Doesn't Apply to Static Methods
Animal animal = new Dog();
animal.sound();  // Outputs: "Animal makes a sound" // NOT Dog... // it is resolved at compile-time


//  Static binding is also known as compile-time binding or early binding.
//  Method overloading is the example of Static binding.


//============================================================ packages

// to avoid naming conflicts 

// 2 types: 
// - 1. build in: java.lang, java.io (example)
// - 2. user-defined


//============================================================ CLASSPATH


//============================================================ Inner classes 
// - a class defined in another class

// parent is the "outer class"


class Foo {
    // fields

    public class Bar { // can be a private field, or static field too, inside a method too...
        // fields, methods
    }
    // methods
}

// Instantiating the outer class
Outer_Demo outer = new Outer_Demo();
// Instantiating the inner class
Outer_Demo.Inner_Demo inner = outer.new Inner_Demo();


// from an abstract class or an interface:
AnonymousInner inner = new AnonymousInner() {
    public void mymethod() {
        //...
    }
};
inner.mymethod();	


//
//
//

class Foo {
    static class Bar {
        public void baz(){
            System.out.println("baz called");
        }
    }
    
    public class Bar2 {
        public void baz2(){
            System.out.println("baz2 called");
        }
    }
}
 public class Main {
    public static void main(String[] args){
        Foo.Bar bar = new Foo.Bar();
        bar.baz();
        
        Foo foo = new Foo();
        Foo.Bar2 bar2 = foo.new Bar2();
        bar2.baz2();
    }
}

// MORE STUFF.... https://www.tutorialspoint.com/java/java_innerclasses.htm


//============================================================ Java Anonymous Class - from abstract class or interface, or from a normal class

interface Foo {
    public void bar();
}

abstract class Foo {
    public abstract void bar();
}

class Foo {
   public void bar() {
      System.out.println("Turbo Engine");
   }
}


 public class Main {
    public static void main(String[] args){
        Foo foo = new Foo(){
            public void bar(){
                System.out.println("baz called");
            }
        };
        
        foo.bar(); // baz called
    }
}



//============================================================ wrapper classes.

// - they wrap primitive types such as byte, int, long, double, etc.

java.lang.Byte 
java.lang.Character 
java.lang.Boolean 


java.lang.Short 
java.lang.Integer 
java.lang.Long 

java.lang.Double 
java.lang.Float 


// BOXING: Converting primitive data types into object is called boxing
 Integer x = 5;

 // UNBOXING:
 x = x + 10; 


//============================================================ Enum

 enum Mobile {
   Samsung,
   Nokia,
   Motorola
}

 Mobile.Samsung // "Samsung"


//
//
//

// Add data to each member of the enum:
enum WEEKDAY {
	// create values of enum
	MONDAY("Day 1"),
	TUESDAY("Day 2"),
	WEDNESDAY("Day 3"),
	THRUSDAY("Day 4"),
	FRIDAY("Day 5"),
	SATURDAY("Day 6"),
	SUNDAY("Day 7");
	
	private final String description;
	// private construtor to set default value
	private WEEKDAY(String description) {        // no public constructor allowed
		this.description = description;
	}
	// getter method to get the description
	public String getDescription () {
		return this.description;
	}
}
public class Tester {
   public static void main(String[] args) { 
	   System.out.println(WEEKDAY.MONDAY.getDescription()); // "Day 1"
	   System.out.println(WEEKDAY.MONDAY); // "MONDAY"
   }
}


//============================================================ Number class
Number intNumber = Integer.valueOf(42);
        Number doubleNumber = Double.valueOf(3.14);
        Number floatNumber = Float.valueOf(2.71f);

intNumber.doubleValue();
floatNumber.doubleValue();


//============================================================ Boolean class
 
Boolean bool1 = new Boolean(true);
Boolean.parseBoolean("true");
bool1.booleanValue();

Boolean.TRUE
Boolean.FALSE

       Boolean bool = true;
       System.out.println(bool || false); // true

//============================================================ Character class

Character ch = 'a';
Character ch = new Character('a');


//============================================================ Array


double[] myList; // prefered
double myList[];  // from c/c++


int[] arr = new int[10];

int[] arr = {1,2,3,4,5};


for(int i=0;i<arr.length;i++){
    System.out.println(arr[i]);
}

for(int elem : arr){
     System.out.println(elem);
}


// pass arary to method (inline)
foo(new int[]{3, 1, 2, 6, 4, 2});


//
//
//


java.util.Arrays


Arrays.sort(arr); // ???


//============================================================ Math

import java.lang.Math;

Math.ceil(10.5)
Math.floor(10.5)
Math.abs(-10.5)
Math.max(10,20)
Math.min(10,20)

Math.random() // double




//============================================================ Java File Class


//...


//============================================================ Exceptions 

//...

 
//============================================================ ArrayList

//...

//============================================================ HashMap

//...

//============================================================ List.of(...), Map.of(...) 

//...

//============================================================

        @FunctionalInterface
        interface MathOperation {
            int operate(int a, int b);
        }

        

        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> b != 0 ? a / b : 0;

        // Test the operations
        int x = 10;
        int y = 5;

        System.out.println("Addition: " + addition.operate(x, y));         // 15
        System.out.println("Subtraction: " + subtraction.operate(x, y));   // 5
        System.out.println("Multiplication: " + multiplication.operate(x, y)); // 50
        System.out.println("Division: " + division.operate(x, y));         // 2

//============================================================


// continue in this page, from the start: 
https://www.tutorialspoint.com/java/java_generics.htm
