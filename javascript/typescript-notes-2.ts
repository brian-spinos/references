//================================================================================ Omit

interface Person {
  name: string;
  age: number;
  address: string;
  email: string;
}

// Create a new type that omits the 'age' and 'address' properties from Person
type PersonWithoutAgeAndAddress = Omit<Person, "age" | "address">;

const person: PersonWithoutAgeAndAddress = {
  name: "Alice",
  email: "alice@example.com"
};



//================================================================================ Record

type Person = {
  name: string;
  age: number;
};

// Using Record to create a type where the keys are string literals and the values are of type Person
type People = Record<string, Person>;

const people: People = {
  alice: { name: "Alice", age: 25 },
  bob: { name: "Bob", age: 30 },
};


//=====


type Grades = "math" | "science" | "history";

// Using Record to create a type where the keys are specific subjects and the values are numbers
type StudentGrades = Record<Grades, number>;

const grades: StudentGrades = {
  math: 90,
  science: 85,
  history: 88,
};

//===== nested records 

type CityTemperature = Record<string, number>;
type CountryWeather = Record<string, CityTemperature>;

const weather: CountryWeather = {
  USA: {
    NewYork: 25,
    LosAngeles: 30,
  },
  Canada: {
    Toronto: 20,
    Vancouver: 22,
  },
};


//================================================================================ Union and Intersection Types

type A = { x: number };
type B = { y: string };
type C = A & B; // Intersection: { x: number, y: string }
type D = A | B; // Union: { x: number } | { y: string }

//================================================================================ keyof

interface Person {
  name: string;
  age: number;
  email: string;
}

type PersonKey = keyof Person;

// PersonKey is now "name" | "age" | "email"

//

function getProperty<T, K extends keyof T>(obj: T, key: K): T[K] {
  return obj[key];
}

let person: Person = {
  name: "Alice",
  age: 30,
  email: "alice@example.com",
};

let nameValue = getProperty(person, "name"); // nameValue is of type string
let ageValue = getProperty(person, "age"); // ageValue is of type number
let emailValue = getProperty(person, "email"); // emailValue is of type string

//

type PersonRecord = Record<keyof Person, any>;

// Example usage
const personData: PersonRecord = {
    name: "John",
    age: 30,
    email: "john@example.com",
    // Additional properties would also be allowed as long as they match keyof Person
    // job: "Developer", // This would cause an error because 'job' is not in Person
};

//================================================================================ typeof

let x = 10;
let y: typeof x; // y is now of type number


// Example with a more complex type
let obj = { name: "Alice", age: 30 };
let person: typeof obj; // person is now { name: string, age: number }


//

interface Person {
  name: string;
  age: number;
}

let john: Person = { name: "John", age: 30 };
let nameType: typeof john.name; // nameType is now string
let ageType: typeof john.age; // ageType is now number


//

const obj = { a: 1, b: "hello" };

type ObjType = typeof obj;
// ObjType is now { a: number, b: string }

interface ObjInterface extends ObjType {
    c: boolean;
}
// ObjInterface is now { a: number, b: string, c: boolean }

//================================================================================ instanceof

class Foo {}

if (person instanceof Foo) {
  //...
}

//================================================================================ overriding a type

// Type Assertions

let someValue: unknown = "this is a string";
let strLength: number = (someValue as string).length;
let strLength2: number = (<string>someValue).length;

//================================================================================ generics 


function identity<T>(arg: T): T {
  return arg;
}

const num = identity<number>(42);
const str = identity<string>("hello");


//

function foo<T, U>(arg: T, foo: U): T {
  return arg;
}

const num3 = foo<number, boolean>(42, true);
const str3 = foo<string, boolean>("hello", false);  

const num4 = foo(42, true);
const str4 = foo("hello", false);


//================================================================================ Partial / Readonly
// Advanced Types and Utility Types

type Person = { name: string; age: number };
type PartialPerson = Partial<Person>; // { name?: string; age?: number }
type ReadonlyPerson = Readonly<Person>; // { readonly name: string; readonly age: number }


//


interface PartialPerson {
  name?: string;
  age?: number;
}

const requiredPerson: Required<PartialPerson> = { name: 'Alice', age: 25 };
// requiredPerson must have both 'name' and 'age'



//


interface Point {
  x: number;
  y: number;
}

const readOnlyPoint: Readonly<Point> = { x: 10, y: 20 };
// readOnlyPoint.x = 5; // Error: Cannot assign to 'x' because it is a read-only property.


//


type Fruit = 'apple' | 'banana' | 'orange';
type FruitPrices = Record<Fruit, number>;

const fruitPrices: FruitPrices = {
    apple: 1.25,
    banana: 0.75,
    orange: 1.00,
};



//



interface Car {
  make: string;
  model: string;
  year: number;
}

const carInfo: Pick<Car, 'make' | 'model'> = {
  make: 'Toyota',
  model: 'Camry',
};
// carInfo can only have 'make' and 'model' properties from Car interface



//




interface User {
  id: number;
  name: string;
  email: string;
}

const userWithoutId: Omit<User, 'id'> = {
  name: 'Alice',
  email: 'alice@example.com',
};
// userWithoutId does not have the 'id' property



//



type Animal = 'dog' | 'cat' | 'bird';
type DomesticAnimal = 'dog' | 'cat';

const wildAnimal: Exclude<Animal, DomesticAnimal> = 'bird';
// wildAnimal can only be 'bird' because it excludes 'dog' and 'cat'



//


type Animal = 'dog' | 'cat' | 'bird';
type DomesticAnimal = 'dog' | 'cat';

const domesticAnimal: Extract<Animal, DomesticAnimal> = 'dog';
// domesticAnimal can only be 'dog' or 'cat' because it extracts 'dog' and 'cat'


//



type StringOrUndefined = string | undefined;
type StringNonNull = NonNullable<StringOrUndefined>;

const str: StringNonNull = 'Hello';
// str can only be a string and cannot be undefined or null



//


function greet(name: string): string {
  return `Hello, ${name}!`;
}

type GreetReturnType = ReturnType<typeof greet>;
// GreetReturnType is 'string', which is the return type of the greet function


//










//================================================================================ interface

interface Person {
  name: string;
}

let p: Person = { name: "Alice" }; // can p have more fields and be valid ?




//=====




// if (p instanceof Person) {}  // <--------- this is invalid




//===== interface guards - use `instanceof` for classes

interface Person {
  name: string;
  age: number;
}

interface Student {
  name: string;
  age: number;
  school: string;
}

function printInfo(individual: Person | Student): void {
  // Type guard using the 'in' operator
  if ('school' in individual) {
    // TypeScript now knows 'individual' is a Student
    console.log(`Student ${individual.name}, Age: ${individual.age}, School: ${individual.school}`);
  } else {
    // TypeScript now knows 'individual' is a Person
    console.log(`Person ${individual.name}, Age: ${individual.age}`);
  }
}

//=====

function isStudent(individual: Person | Student): individual is Student {
  return (individual as Student).school !== undefined;
}

function isStudent(individual: Person | Student): individual is Student {
  return 'school' in individual;
}

//================================================================================ type

// remember 'T.O. ~ type equals object' :)
type Point = {
  x: number;
  y: number;
};

let p: Point = { x: 10, y: 20 };

//================================================================================ enum

// remember 'E.O. ~ enum equals object, no equal' :)
enum Color {
  Red = "RED",
  Green = "GREEN",
  Blue = "BLUE",
}

let color: Color = Color.Green;


//================================================================================ Guards 

function padLeft(value: string, padding: string | number) {
  if (typeof padding === "number") {
    return Array(padding + 1).join(" ") + value;
  }
  if (typeof padding === "string") {
    return padding + value;
  }
  throw new Error(`Expected string or number, got '${typeof padding}'.`);
}
//================================================================================ Error

try {
  // Code that might throw an error
  throw new Error("Something went wrong");
} catch (error) {
  // Here, 'error' is implicitly of type 'any' if not explicitly typed

  // You can explicitly specify the type of error being caught
  if (error instanceof Error) {
      // 'error' is of type 'Error' here
      console.error("Caught an error:", error.message);
  } else {
      // This block executes if the error is not an instance of 'Error'
      console.error("Caught an unknown error:", error);
  }
}


//================================================================================ namespace

namespace MyNamespace {
  export interface SomeInterface {
      // Interface definition
  }

  export function someFunction() {
      // Function implementation
  }
}

let obj: MyNamespace.SomeInterface;
MyNamespace.someFunction();

//================================================================================
