//
// Need to cleanup
//

// npm install -g typescript

const foo = (x: string, callback: Function) : number => {
    console.log(x)
    return 123;
}

foo('aaa', () => {})


interface Person {
    name: string;
    age: number;
  }

  type Person2 = {
    name: string;
    age: number;
  };

// number, string, boolean, null, undefined, void, any, arrays, tuples, enums, objects, functions, 
// union types, intersection types, type assertions, generics, literal types
let message: string = "Hello";
let fruits: string[] = ["apple", "banana", "orange"];
let person: { name: string, age: number } = { name: "Alice", age: 30 };


// callbacks

function foo2(myFunc: (x: string) => number) {
  console.log(123)
}

let _myFunc = (x: string) => 123

foo2(_myFunc)


//
/// templates:
//


function foo3<T>(cb: (data: T) => T) {
    console.log(123)
 }

 

 const foo4 =      <T>           (x: T)                          : number =>     {
    return 123;
 }

 const foo5 =       <T>           (cb: (data: T) => T)           : number => {
    return 123;
};



const x = <T>() => {}
 

let myFunc2 = (data: number) => {return data}
foo3(myFunc2) // when I call a function with the template, I have to tell it the type I am using
foo3<number>(myFunc2) // optionally, when I call a function with the template, I have to tell it the type I am using



//
// nested objects 

// Interface representing the structure of a nested object
interface NestedObject {
    name: string;
    age: number;
    address: {
        street: string;
        city: string;
        postalCode: number;
    };
}

// Example nested object
const person2: NestedObject = {
    name: "Alice",
    age: 30,
    address: {
        street: "123 Main St",
        city: "Anytown",
        postalCode: 12345
    }
};


//
//
//

interface User {
    name: string,
    info: {
        foo: string | number,
        bar: string | number,
    },
    cb: (value: number) => number,
    ids: number[]
    
}

const processUser = (user: User) => {}

let user : User = {
    name: 'brian',
    info: {
        foo: 1,
        bar: 2,
    },
    cb: (x: number) => x * 2,
    ids: [111,222,333]
};

processUser(user);

//
//
//
