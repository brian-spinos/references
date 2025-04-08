// Typescript

//==================================================================== videos

// https://www.youtube.com/watch?v=VGu1vDAWNTg&list=PL4cUxeGkcC9gNhFQgS4edYLqP7LkZcFMN

//====================================================================

// superset of javascript
// compiled to javascript

// objective: type safety through static checking

// typescript is a development tool, because you will ship javascript

// typescriptlang.org playground

// $ npm install -g typescript
// $ npm install typescript --save-dev
// $ npx tsc

//  foo.ts file

// $ tsc foo.ts # will convert it to JS !!!

// The compiler can be configured using a tsconfig.json file.
// create the file: $ npx tsc --init
// You can learn more at https://aka.ms/tsconfig.json

//==================================================================== Functions

const fn1 = (a: number, b: number): number => {
  return a + b;
};

fn1(1, 2); // 3
let res1: number = fn1(1, 2);

//===

// function of type: () => void
type MyFn = () => void;
let myFunction1: MyFn = () => {
  console.log("hello");
};

//=== function types

type MyFunc1 = (a: number, b: number) => number;

let myFunc3: MyFunc1 = (a, b) => {
  return a + b;
};

//==================================================================== Generics (Functions)

let func1 = <T, U>(a: T, b: U): T | U => {
  if (typeof a === "number") return a;
  return b;
};

let res: number | string = func1<string, number>("1", 2);
console.log(res); // 2

//===

let func3 = <T>(a: T, b: T): T => {
  // ...
  return b;
};
func3<number>(10, 20);

//===

let func2 = <T, U>(a: T, b: U): U => {
  console.log(a);
  return b;
};

func2<{ key1: number }, { key2: number }>({ key1: 1 }, { key2: 2 });

//===

// makes things reusable

interface Person8 {
  name: string;
  age: number;
}

// K is restricted to the fields in Person8
const func4 = <K extends keyof Person8>(key: K) => {
  return key;
};

func4("name");
func4("age");
// func4("email"); // Error

//===

interface Person82 {
  name: string;
  age: number;
}

const func5 = <P extends Person82>(person: P) => {
  return person;
};

func5({ name: "b", age: 30, foo: 123 });

//==================================================================== Generics (Classes)

let myGenericArray: Array<number> = [1, 2, 3];

//===

class Foo10<T, U> {
  private f1: T;
  private f2: U;

  constructor(f1: T, f2: U) {
    this.f1 = f1;
    this.f2 = f2;
  }

  public getF1() {
    return this.f1;
  }

  public getF2() {
    return this.f2;
  }
}

let foo10: Foo10<string, number> = new Foo10("brian", 30);
foo10.getF1(); // 'brian'
foo10.getF2(); // 30

//==================================================================== Generics (Interfaces)

interface Pair<K, V> {
  key: K;
  value: V;
}

const stringNumberPair: Pair<string, number> = {
  key: "age",
  value: 30,
};

const booleanStringPair: Pair<boolean, string> = {
  key: true,
  value: "active",
};

//==================================================================== Primitive Types

// MNEMONIC: BB NU SSN O?

let t1: bigint = 1000000n; // only in ES2020 and above
let t2: boolean = true;
let t3: null = null;
let t4: undefined = undefined;
let t5: symbol = Symbol("id");
let t6: string = "hello";
let t7: number = 123; // 3.14 - is valid too
let t8: object = { name: "brian", age: 30 };

//==================================================================== Non primitive Types
// MNEMONIC FROM-SAD

let t13: Function = () => {};
let t14: RegExp = /hello/i;

let t14b: { name: string } = { name: "brian" }; // Object
t14b.name = "ana";

let t15: Map<string, string> = new Map();

let t16: Set<string> = new Set();
let t9: Array<number> = [1, 2, 3];
let t10: Date = new Date("Jan 1, 2025");

//===

let t11: unknown = "aaa";
t11 = 456; // can be reassigned with another type
let t12: any = "can be anything";
t12 = 100; // can be reassigned with another type

// TODO: never -- I do have this somewhere in here...

//==================================================================== typeof (for primitives)

typeof 1000000n; // 'bigint'
typeof true; // 'boolean'
typeof null; // 'object' <----------------------
typeof undefined; // 'undefined'
typeof "hello"; // 'string'
typeof Symbol("id"); // 'symbol'
typeof 123; // 'number'
typeof { name: "brian" }; // 'object'

typeof function () {}; // 'function'
typeof /abc/; // 'object' <----------------------
typeof [1, 2, 3]; // 'object' <----------------------
typeof new Date("Jan 1, 2025"); // 'object' <----------------------

//==================================================================== instanceof (for classes, Date,Array,Object,String)

class FooParent {}
class Foo extends FooParent {}

let foop = new FooParent();
let foo = new Foo();

foop instanceof FooParent; // true
foo instanceof Foo; // true
foo instanceof FooParent; // true

//---

let f = function () {}; // let f = () => {}
let reg = /abc/g;
let obj = { name: "brian" };
let m = new Map();
let s = new Set();
let arr = [1, 2, 3];
let date = new Date("Jan 1, 2025");

let str1: any = "hello"; // cant use instanceof with primitive string, so use any ?
let str2 = new String("hello");

f instanceof Function; // true
reg instanceof RegExp; // true
obj instanceof Object; // true
m instanceof Map; // true
s instanceof Set; // true
arr instanceof Array; // true
date instanceof Date; // true

str1 instanceof String; // false <-------- because its a primitive
str2 instanceof String; // true

//==================================================================== Tuples

const myTuple: [string, number] = ["brian", 30];

//=== named-tuple
const myTuple2: [name: string, age: number] = ["brian", 30];

//========================================================= Arrays

let arr1: string[] = ["a", "b", "c"];
let arr2: number[] = [1, 2, 3];
let arr3: boolean[] = [true, false];

//===

let arr4: string[] = [];
arr4.push("aaa");

let arr5: Array<string> = [];
arr5.push("aaa");

let arr6: string[][] = [["a"], ["b"], ["c"]];

//===

let neverArray = []; // array of type 'never'
// neverArray.push("aaa"); // not assignable

//==================================================================== Classes

class User3 {
  private name: string; // private / public / protected
  private age: number;

  public country: string;

  foo123: string = "BAR-123"; // public (default)

  // protected -- within class and subclasses !!!

  // only set on initialization !!!
  // it can be set in the constructor !!!
  readonly birthDate: string;

  // you can add private/public before the variables 'name' and 'age'
  // - and you dont need to declare these variables before the constructor
  public constructor(name: string, age: number) {
    this.name = name;
    this.age = age;
    this.country = "USA";
  }

  //--- public

  public getName(): string {
    return this.name;
  }

  public setName(name: string): void {
    this.name = name;
  }

  //--- private

  private doSomething() {
    return 123;
  }

  //--- static

  static getUserCount(): number {
    return 1000;
  }

  static readonly FOO: number = 2000; // dont use `const` for clas fields, use `readonly`
}

let u30: User3 = new User3("brian", 30);

// u30.name; // no access

u30.setName("ana");
u30.getName(); // 'ana'

u30.country; // 'USA
u30.foo123; // 'BAR-123'

// u30.doSomething(); // 123 - will run, but code editor will complain

User3.getUserCount(); // 1000
User3.FOO; // 2000

//==================================================================== Inheritance

class User5 {
  public name = "brian";
  public sayHello(): string {
    return "hello";
  }
}

class Student5 extends User5 {}

let u50 = new Student5();
u50.sayHello(); // 'hello'

//==================================================================== Abstract Class

abstract class User4 {
  abstract sayHello(): string;
}

class Student4 extends User4 {
  sayHello(): string {
    return "hello";
  }
}

let u40 = new Student4();
u40.sayHello(); // 'hello'

//==================================================================== Enums

// - enums are not encouraged, lots of bugs

// For most cases, union types or constant objects are better than enums.
//       - see `as const`
//       - see `union types`: type Status = "Pending" | "Approved" | "Rejected";

enum MyEnum {
  ONE = 1,
  TWO = 2,
  THREE = "three",
}

// Not sure if there are issues with this implementation
enum Color {
  RED = "red",
  GREEN = "green",
  BLUE = "blue",
}

console.log(Color.RED); // 'red'

let myEnum: Color = Color.RED; // 'red'

//==================================================================== Interfaces

interface User1 {
  name: string;
  age: number;
  isAdmin?: boolean; // optional
  readonly ssn: number; // readonly
  sendEmail: (from: string, to: string) => boolean;
  [k: string]: any; // to allow any extra keys
}

// I can re-open the interface !!!
interface User1 {
  anotherField: number;
}

let u1: User1 = {
  name: "brian",
  age: 30,
  // isAdmin: true, // optional
  ssn: 1111111111,
  sendEmail: (from: string, to: string) => {
    console.log(`sending email from: ${from}, to: ${to}`);
    return true;
  },
  country: "USA", // extra field example
  anotherField: 123,
};

// u1.ssn = 2222222222; // ERROR

let success: boolean = u1.sendEmail("a@a.com", "b@b.com");

//==================================================================== interfaces extends

interface User10 {
  name: string;
}

interface Person10 extends User10 {
  age: number;
}

let u6: Person10 = {
  name: "brian",
  age: 30,
};

//==================================================================== Classes and interfaces

interface IUser {
  name: string;
  sendEmail: (email: string) => void;
}

class AppUser implements IUser {
  name: string;

  constructor(name: string) {
    this.name = name;
  }

  public sendEmail(email: string) {
    console.log(`email sent to ${email}`);
  }
}

let u7: AppUser = new AppUser("brian");
u7.sendEmail("foo@foo.com"); // "email sent to foo@foo.com"

//==================================================================== Types (Type Alias)

type Point = { x: number; y: number };
let myPoint: Point = { x: 123, y: 456 };

//===

type MyType1 = {
  name: string;
  age: number;
  isAdmin?: boolean; // optional
  readonly id: number;
  myFunc: (a: number, b: number) => number;
  [k: string]: any; // allow extra keys
};

let myType1: MyType1 = {
  name: "ana",
  age: 30,
  isAdmin: false, // optional
  id: 123,
  myFunc: (a, b) => {
    return a + b;
  },
  country: "USA", // extra keys
};

// myType1.id = 456; // Error

// myType1.myFunc(1,2) // 3

//=============== Union types ( `|` )

type Status1 = "Y" | "N";
let status1: Status1 = "N";

type MyUnionType1 = string | number;
let myUnion: MyUnionType1 = "abc";
myUnion = 123;

//=============== Intersection Types ( `&` )

type User52 = { isUser: boolean };
type Admin = { isAdmin: boolean };

type UserAndAdmin = User52 & Admin;

let myUser: UserAndAdmin = { isUser: true, isAdmin: true };

//=============== Literal Types

type FooId = "foo123";
type FavNum = 42;

let myFooId: FooId = "foo123";
let myFavNum: FavNum = 42;

let myLiteral: "foo" = "foo";
let myLiteral2: 42 = 42;

// Literal types allow you to specify the exact value a variable can hold.
type Direction = "up" | "down" | "left" | "right";
let myDirection: Direction = "right";

//=============== Type Aliases for Functions

// You can use type to define the signature of a function.
type AddFunction = (a: number, b: number) => number;
const add: AddFunction = (a, b) => a + b;

//=============== Index Signatures

// TODO

//=============== Mapped Types

// TODO

//==================================================================== Union/Intersection Types for interfaces ( `|` and `&` )

// - you can combine multiple interfaces

interface UserName1 {
  name: string;
}

interface UserAge1 {
  age: number;
}

interface UserExtra1 {
  [k: string]: any;
}

let u2: UserName1 & UserAge1 & UserExtra1 = {
  name: "brian",
  age: 30,
};

let u3: UserName1 | UserAge1;
u3 = { name: "brian", age: 30 };
u3 = { name: "brian" };
u3 = { age: 30 };

let u4: string | number;
u4 = "hello";
u4 = 123;

//==================================================================== Type Guards (just IF statements, or helper function)

// Type of Guards

// typeof – for primitive types (string, number, boolean, etc.).
// instanceof – for classes.
// User-Defined Guards – like isUser.
// `in` operator – to check if a property exists in an object.
// color is "red" - for unions

type Foo7 = number | string;

const fn2 = (arg: Foo7): Foo7 => {
  if (typeof arg === "string") {
    return arg.toUpperCase();
  } else {
    return arg * 2; // number
  }
};

//---

interface User6 {
  type: "user";
  username: string;
}

interface Person6 {
  type: "person";
  firstName: string;
}

const fn3 = (u: User6 | Person6): string => {
  if (u.type === "user") {
    return u.username;
  } else {
    return u.firstName;
  }
};

//==================================================================== Utility Types

// My MNEMONIC: AP 3R PO, NEE LUCU

interface User7 {
  f1: string;
  f2: string;
  f3: number;
  f4: number;
  f5: boolean;
  f6: boolean;
}

// Awaited<T> - gets the resolved type of a Promise
let u111: Awaited<Promise<number>> = 100;
// let u111: Awaited<typeof myPromise> = 100;

// Partial<T> - all fields are optional
let u100: Partial<User7> = { f1: "a", f2: "b" };

// Required<T> - all fields are required
let u101: Required<User7> = {
  f1: "a",
  f2: "b",
  f3: 10,
  f4: 20,
  f5: true,
  f6: true,
};

// Readonly<T> - all fields are readonly
let u102: Readonly<User7> = {
  f1: "a",
  f2: "b",
  f3: 10,
  f4: 20,
  f5: true,
  f6: true,
};
// u102.f1 = 'aa' // ERROR

// Record<K, V> - key/value pair
let u105: Record<string, User7> = {
  "user-105A": { f1: "a", f2: "b", f3: 10, f4: 20, f5: true, f6: true },
  "user-105B": { f1: "x", f2: "z", f3: 30, f4: 40, f5: false, f6: false },
};

// Pick<T, 'f1' | 'f2'> - pick some fields
let u103: Pick<User7, "f1" | "f2"> = { f1: "a", f2: "b" };

// Omit<T, 'f1' | 'f2'> - omit some fields
let u104: Omit<User7, "f1" | "f2"> = { f3: 10, f4: 20, f5: true, f6: true };

//---

// NonNullable<T> - removes null and undefined from T
let u108: NonNullable<string | null | undefined> = "valid"; // Cannot be null or undefined
// let u108: NonNullable<User7> = {...}; // Cannot be null or undefined

// Exclude<T, U> - exclude specific types from T
// Removes 'string' from the union type
let u106: Exclude<string | number | boolean, string> = 42; // Can be number or boolean, but not string

// Extract<T, U> - extract specific types from T
// Extracts only 'string' from the union type
let u107: Extract<string | number | boolean, string> = "hello";

// Uppercase<T> - converts string literal types to uppercase
let u116: Uppercase<"hello"> = "HELLO";

// Lowercase<T> - converts string literal types to lowercase
let u117: Lowercase<"HELLO"> = "hello";

// Capitalize<T> - capitalizes the first letter of a string literal type
let u118: Capitalize<"helloThere"> = "HelloThere";

// Uncapitalize<T> - makes the first letter of a string lowercase
let u119: Uncapitalize<"HelloThere"> = "helloThere";

//
//
//

// ReturnType<T> - gets the return type of a function
function myFunc4() {
  return { name: "TypeScript" };
}
let u109: ReturnType<typeof myFunc4> = { name: "TS" };

// InstanceType<T> - gets the instance type of a constructor function
class ExampleClass {
  prop = 123;
}
let u110: InstanceType<typeof ExampleClass> = new ExampleClass();

// Parameters<T> - gets the parameters of a function as a tuple
function myFunc5(a: number, b: string) {}
type T115 = Parameters<typeof myFunc5>; // [number, string]

// ConstructorParameters<T> - gets the constructor parameter types as a tuple
type T114 = ConstructorParameters<typeof ExampleClass>; // [] (empty tuple since no constructor params)

// ThisParameterType<T> - extracts the type of 'this' from a function
function thisExample(this: { val: string }) {}
type T112 = ThisParameterType<typeof thisExample>; // { val: string }

// OmitThisParameter<T> - removes 'this' from a function type
let u113: OmitThisParameter<typeof thisExample> = () => {}; // Now 'this' is omitted

//
//
//

/* =============utility types
  Partial<T>
  Required<T>
  Readonly<T>
  Pick<T, K>
  Omit<T, K>
  Record<K, T>
  Exclude<T, U>
  Extract<T, U>
  NonNullable<T>
  ReturnType<T>
  InstanceType<T>
  Awaited<T>
  ThisParameterType<T>
  OmitThisParameter<T>
  ConstructorParameters<T>
  Parameters<T>
  Uppercase<T>
  Lowercase<T>
  Capitalize<T>
  Uncapitalize<T>
*/

//==================================================================== unknown (needs to be checked, before using it)

// better than 'any', but needs to be checked

let u: unknown = true; // could be anything, and we can re-assign it
u = { foo: "bar" };
u = 123;
u = "aaaa";

if (typeof u === "object" && u !== null && "foo" in u) {
  u.foo = "new value"; // safe after type check
}
if (typeof u === "number") {
  console.log(u + 10); // safe after type check
}
if (typeof u === "string") {
  console.log(u.length); // safe after type check
}

//========================================================= never

// 'never' represents a value that never occurs

// like errors or infinite loops, or branches that should never execute

type Shape = "circle" | "square";

function getArea(shape: Shape): number {
  switch (shape) {
    case "circle":
      return Math.PI * 10 * 10;
    case "square":
      return 10 * 10;
    default:
      // TypeScript ensures that all cases are handled

      // GOTCHA: assign to `never` var and throw var as error
      const _exhaustiveCheck: never = shape;
      throw new Error(`Unhandled case: ${_exhaustiveCheck}`);
  }
}

// - `never` can be assigned as a return type for functions that 'never complete'
//    like throwing errors, or infinite loop
function throwError(message: string): never {
  throw new Error(message);
}

// throwError("This is an error!"); // This function never returns a value.

function infiniteLoop(): never {
  while (true) {
    console.log("This loop will never end.");
  }
}

//===

function exampleOfNever(x: string | number) {
  if (typeof x === "string") {
    //...
  } else if (typeof x === "number") {
    //...
  } else {
    console.log(typeof x); // "never" <-------------------- because it will never happen
  }
}

//==================================================================== keyof

// keyof - makes a list of keys from the object, so we can use it as a type
//            - in a scenario where we need typing for the different valid keys in an object
//            - usage: keyof <some-type>

interface User2 {
  name: string;
  age: number;
  isAdmin: boolean;
  country: string;
}

type User2Keys = keyof User2; // 'name' | 'age' | 'isAdmin' | 'country' -- it is a 'union' type

let k2: User2Keys;
k2 = "name";
k2 = "age";
k2 = "isAdmin";
k2 = "country";
// k2 = "aaa"; // ERROR

//--- <T, K extends keyof T>

let u5 = {
  name: "brian",
  age: 30,
  isAdmin: true,
  country: "USA",
};

// only keys from object
const getVal5 = <T, K extends keyof T>(obj: T, key: K): T[K] => {
  return obj[key];
};

getVal5<User2, User2Keys>(u5, "name"); // 'brian'
// getVal5<User2, User2Keys>(u5, "foo"); // ERROR key 'foo'

// TODO there is more... ???

//==================================================================== (is)

// user-defined type guard
function isNumber(value: unknown): value is number {
  return typeof value === "number";
}

function processValue(value: unknown) {
  if (isNumber(value)) {
    console.log(value.toFixed(2)); // ✅ TypeScript knows `value` is a number
  } else {
    console.log("Not a number");
  }
}

processValue(42); // "42.00"
processValue("hello"); // "Not a number"

//==================================================================== as (Type Assertions)
// --> tell the compaler to treat a variable as some other type :)
// --> it works best when the type is : `unknown`, but could be `any`

let x2: unknown = "hello";
let len: number = (x2 as string).length;
console.log(len);

//==================================================================== `as const` (simple)

const colors2 = ["red", "green", "blue"]; // Type: string[]

const colors3 = ["red", "green", "blue"] as const;
// Type: readonly ["red", "green", "blue"]

//==================================================================== `as const` (simple)

let config2 = {
  url: "https://foo.com",
  ttl: 60,
  other: {
    foo: 123,
    bar: 456,
  },
} as const;

const func8 = (a: typeof config2.url, b: typeof config2.other.foo) => {
  console.log({ a, b });
};

func8("https://foo.com", 123);

//==================================================================== `as const`
// --> (Good for configuration objects !!!)

// as const makes all fields as readonly , and the values as literals !!!

// as const can be better than an ENUM, FYI (enums have known bugs)

const Routes = {
  home: "/",
  admin: "/admin",
  users: "/users",
}; // <--------------------

const visit = (route: "/" | "/admin" | "/users") => {};
// visit(Routes.users); // Argument is of type 'string' <--------------------

//--- using `as const`

const Routes2 = {
  home: "/",
  admin: "/admin",
  users: "/users",
} as const; // <-------------------- 'as const' fixes it

const visit2 = (route: "/" | "/admin" | "/users") => {};

visit2(Routes2.users); // WORKS !!! <-------------------- the type is now '/users' and not 'string'

//--- creating the type for the object:

const Routes3 = {
  home: "/",
  admin: "/admin",
  users: "/users",
} as const; // <-------------------- 'as const' fixes it

type RouteKeys3 = keyof typeof Routes3;
type Route3 = (typeof Routes3)[RouteKeys3];

const visit3 = (route: Route3) => {};

visit3(Routes3.users); // WORKS !!! <-------------------- the type is now '/users' and not 'string'

//========================================================= as const (again)

// TODO: need to refactor all the `as const` sections of these notes !!!

const myValue = "hello" as const;
// TypeScript infers the type of `myValue` as "hello" (a string literal type)
// Without `as const`, it would be inferred as `string`

const colors = ["red", "green", "blue"] as const;
// Type: readonly ["red", "green", "blue"]

// Without `as const`, the type would be:
// string[] (a mutable array of strings)

const config = {
  apiUrl: "https://api.example.com",
  retries: 3,
} as const;
// Type: {
//   readonly apiUrl: "https://api.example.com";
//   readonly retries: 3;
// }

// Without `as const`, the type would be:
// {
//   apiUrl: string;
//   retries: number;
// }

//==================================================================== Boxing/Unboxing

// --> AVOID THEM, unpredictable behavior
//    (even thought it is done behind the scene, to allow method calling)

let x: number = 10; // primitive
let y: Number = new Number(10); // boxed version
let z: number = y.valueOf(); // unboxing

//==================================================================== ( <my-var> is <my-type> ) need to check

// TODO: is this a duplicate section ???

interface User {
  id: number;
  name: string;
}

function isUser(obj: any): obj is User {
  return (
    typeof obj === "object" &&
    obj !== null &&
    typeof obj.id === "number" &&
    typeof obj.name === "string"
  );
}

const data: any = { id: 1, name: "Brian" };

if (isUser(data)) {
  // Inside this block, TypeScript knows that `data` is a `User`
  console.log(data.name.toUpperCase()); // ✅ No TypeScript error
} else {
  console.log("Not a User");
}

// Outside the if block, `data` is still `any`

//========================================================= Method overload

interface User8 {
  name: string;
  email: string;
}

const isUser8 = (u: unknown): u is User8 => {
  if (typeof u === "object" && u !== null && "name" in u) {
    return true;
  }
  return false;
};

class Foo8 {
  sendEmail(obj: User8): void;
  sendEmail(name: string, email: string): void;
  sendEmail(arg1: unknown, arg2?: unknown): void {
    // second argument is optional

    if (isUser8(arg1)) {
      console.log({ aaa: 111, arg1 });
      return;
    }
    if (!arg2) {
      console.log({ obj: arg1 });
      return;
    }

    console.log({ name: arg1, email: arg2 });
  }
}

const f2: Foo8 = new Foo8();
f2.sendEmail({ name: "brian", email: "foo@foo" });
f2.sendEmail("brian", "foo@foo");

//===

class Student2 {
  foo(a: string): string;
  foo(a: number): number;
  foo(a: unknown): number | string {
    if (typeof a === "string") {
      return a;
    }

    if (typeof a === "number") {
      return a;
    }

    throw new Error("Invalid argument"); // Optional: Explicit error for unexpected cases
  }
}

let s2 = new Student2();
console.log(s2.foo("aaa"));

//==================================================================== non-null assertion operators (!).

let x3: { name: string } | null = null;
console.log(x3!.name);

let var8: string | null = "Hello";
let length8: number = var8!.length; // Assert that 'var8' is not null

//==================================================================== infer

// TODO

// used to extract types from other types

// infer allows us to go inside a type an pull out another type  !!!!!

// REMEMBER: `infer X`

//=== extract type from function

const func7 = (a: number, b: string): boolean => {
  return true;
};

type MyType<T> = T extends (a: infer A, b: infer B) => infer C ? A : never;

let res7: MyType<typeof func7> = 42;

//=== extract type from promise

let myPromise: Promise<number> = new Promise((res, rej) => res(123));

type ReturnTypeOfPromise<T> = T extends Promise<infer U> ? U : never;

let res8: ReturnTypeOfPromise<typeof myPromise> = 456;

//=== extract type from Array ?

type FromArray<T> = T extends (infer A)[] ? A : never; // string[] --> (infer A)[]
let arr7: Array<string> = ["a", "b"];
let x7: FromArray<typeof arr7> = "abc";

//=== extract type from generic ???

// TODO: not sure this makes sense

//==================================================================== extends II

interface User60 {
  name: string;
  age: number;
}

const func6 = <U extends User60, K extends keyof U>(u: U, k: K) => {
  console.log(u);
};

interface Admin60 {
  name: string;
  age: number;
  aaa: number;
}

// no extra fields
func6({ name: "aaa", age: 120 }, "age");

// with extra fields :)
func6({ name: "aaa", age: 120, aaa: 3 }, "aaa");
func6<Admin60, keyof Admin60>({ name: "aaa", age: 120, aaa: 3 }, "aaa");

//==================================================================== Conditional Types

// - If T extends U, the result is X, otherwise, it's Y.
// Pseudo-code:
// T extends U ? X : Y

type IsString<T> = T extends string ? "Yes" : "No";

type A = IsString<string>; // "Yes"
type B = IsString<number>; // "No"

// TODO: there is more...

//==================================================================== NEW FEATURES?

/*
- Accessors : get set 
- Type Guards -- use 'typeof', 'in', and the 'instanceof' operator. or IF, FUNCTIONS to check a variables type
- Union type
- Utility types
- keyof
- typeof ?
- Type Guards
- generic interfaces
- type alias
- Function Overloading
- Intersection Types : type Foo = Type1 & Type2
- type inference - let num = 10;  // TypeScript infers 'num' to be 'number'
- generic constraints -  <T extends string | number>
- Type Assertions : `as` !!!
- boxing/unboxing
- abstract classes
- literal types
- Conditional Types
- 
-
- decorators
- type annotations
- template literal types
- indexed access - use an interface as a hash of key to type
-
-
- TypeScript Mixins
- duck-typing
- mapped types
- triple slash directives
- Ambients
- Dynamic Import: Supports loading modules dynamically using import().
- Destructuring with Types: Using type annotations with destructuring. -- just normal destructuring {x,y}
- Indexed Type Queries
*/

//==================================================================== need to fix

interface MyInterface1 {
  field1: number;
  field2: string;
  field3: (a: number, b: number) => number;
  field4: <T>(a: T, b: T) => T; // interface with generic function
}

let var7: unknown = { a: 1 } as { a: number };

type RemoveFirst<T extends any[]> = T extends [infer First, ...infer Rest]
  ? Rest
  : never;

//
//

export {};

// INFER : create a type within a conditional type

// conditional types
type Result = true extends boolean ? 1 : 0;
