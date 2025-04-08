// https://www.youtube.com/watch?v=Zi-Q0t4gMC8

// TODO: clean DOM API stuff
// TODO: clean DOM API stuff
// TODO: clean DOM API stuff

//================================================ primitive types: (7)

/**

//mnemonic:
BB NU SSN O?

bigint
boolean
null
undefined
string
symbol
number
 */

let myBigInt1 = BigInt(1234567890);
let myBigInt2 = BigInt("1234567890");
let myBigInt3 = 1234567890n;

let math1 =
  BigInt(1000000000000000000000000000000) +
  BigInt(1000000000000000000000000000000); // 2000000000000000039769249677312n // ?????

console.log(1000000000000000n + 1n); // 1000000000000001n // works

//================================================ non primitive types: (7)

// MNEMONIC:
// FROM-SAD

// Function;
// RegExp;
// Object;
// Map;
// Set;
// Array;
// Date;

// examples:

let func5 = new Function("a", "b", "{let x = a; return x + b}");
func5(1, 2); // 3

const regex5 = new RegExp("\\d+"); // Matches digits
const regexLiteral5 = /\d+/; // Preferred way

const obj5 = new Object(); // Creates an empty object

const map5 = new Map([
  ["name", "brian"],
  ["age", 30],
]);

const set5 = new Set([1, 2, 3, 3]); // 1,2,3

const arr5 = new Array(10).fill(0);

const date5 = new Date();
// .getMilliseconds()
// .getSeconds()
// .getMinutes()
// .getHours()
// .getDay() // 0 sunday
// .getMonth() // 0 jan
// .getFullYeay()

//================================================ Symbol

const uniqueKey = Symbol(); // unique and imutable identifier, (used for object keys ?)

let s10 = Symbol();
let s20 = Symbol();
s10 === s20; // false

// still unique, but with label
let s30 = Symbol("id");
let s40 = Symbol("id");
s30 === s40; // false

const s1 = Symbol("s1");
const s2 = Symbol("s2");
s1 === s2; // false

const id = Symbol("id");
id.description; // "id"  // <------- .description field

const user = {
  name: "brian",
  [id]: 123,
};

user[id]; // 123

Object.keys(user); // ["name"] // symbols are not listed

Object.getOwnPropertySymbols(user); // [Symbol(id)]

// shared symbols:
const globalSymbol1 = Symbol.for("shared");
const globalSymbol2 = Symbol.for("shared");

globalSymbol1 === globalSymbol2; // true

//================================================ template literals

let name2 = "Brian";
let greeting = `Hello, ${name2}!`;

//================================================ destructuring

let [x3, y3] = [10, 20];
let { name, age } = { name: "brian", age: 30 };

// advanced cases - rename variable, rest
const obj3 = { a: 1, b: 2, c: 3 };
const { a: alpha, b, ...rest } = obj3;

//================================================ default parameters
function greet(name = "Guest") {}
const greet2 = (name = "Guest") => {};

//================================================ .call() .apply() .bind()

// - used for `function` to set the values of `this`
let fn = function (arg1, arg2) {
  console.log("hello " + this.name);
};
let o2 = { name: "brian" };
fn.call(o2, "a", "b");
fn.apply(o2, ["a", "b"]);
let fn2 = fn.bind(o2);
fn2();

/// how the `this` is bound normally:

function foo(x) {
  console.log(`hey ${this.fname}`, x);
}

let obj2 = {
  fname: "brian",
  foo: foo,
};

obj2.foo(11);

//================================================ typeof

typeof 10n; // 'bigint'
typeof true; // 'boolean'
typeof null; // 'object' ____________________> yeah...
typeof undefined; // 'undefined'
typeof "aaa"; // 'string'
typeof Symbol(); // 'symbol'
typeof 0; // 'number'

typeof { a: 1 }; // 'object'
typeof []; // 'object' <-----------------------

//================================================ instanceof (class/constructor) -- checks the prototype chain

x = new Date("jan 10 2025");
x instanceof Date; // true

x4 = new Object();
x4 instanceof Object; // true

x5 = new Array();
x5 instanceof Array; // true

class Foo {}

let f1 = new Foo();
f1 instanceof Foo; // true

function Person() {}
let p7 = new Person();
p7 instanceof Person; // true

//================================================ Set API

// MNEMONIC: ACDEFH KSV

// add , delete, has, clear, size |  keys values entries forEach

let set1 = new Set([1, 2, 3, 4, 5]);
set1.add(10);

set1.delete(10);
set1.has(10); // true
set1.clear();
set1.size;

//================================================ Map API

// MNEMONIC: CDEFGH KSSV

// get/set , delete, has, clear, size |  keys values entries forEach

let map1 = new Map([
  ["name", "brian"],
  ["age", 30],
]);
map1.set("a", "b");
map1.get("a");

map1.delete("a");
map1.has("a"); // true
map1.clear();
map1.size;

//================================================ generator functions

/**
 *
 *  allows 'pausable' function, with multiple code execution and returns
 *
 *
 */
function* myGenerator() {
  console.log(`before A`);
  yield "A";

  console.log(`before B`);
  yield "B";

  console.log(`before C`);
  yield "C";

  console.log(`END C`);
}

let g = myGenerator();

g.next();
// before A
//   {value: 'A', done: false}
g.next();
// before B
//   {value: 'B', done: false}
g.next();
// before C
//   {value: 'C', done: false}
g.next();
// END C
//   {value: undefined, done: true}

//================================================ loops

for (let i = 0; i < 10; i++) {
  // i
}

let i = 0;
while (i < 10) {
  // i
  i++;
}

// let i = 0;
do {
  // i
  i++;
} while (i < 10);

let obj = {
  name: "brian",
  age: 30,
};

for (let k in obj) {
  // obj[k]
}

let arr = [10, 20, 30];
for (let item of arr) {
  // item
}

// runs a functions for each item
arr.forEach((item) => {
  // item
});

//================================================ break/continue

for (let i = 0; i < 10; i++) {
  if (i === 9) break; // break out of loop
  if (i === 2) continue; // skips 2
}

//================================================ String API

String(123); // "123"

let s = "hello world!";

s.trim();
s.trimStart();
s.trimEnd();

s.includes("world"); // true

s.startsWith("he"); // true
s.endsWith("d!"); // true

s.replace("hello", "hi"); // first arg can be regex: /(\d+)/
s.replaceAll("l", "L"); // ES2021

"aaa,bbb".split(","); // ["aaa", "bbb"]

"a-b-c-d".split("-", 2); // ["a", "b"] // limit the items

s.toLowerCase();
s.toUpperCase();

"abc".padStart(6, "*"); // '***abc' // if 100: will will the left side of string with '*', and total length is 100
"abc".padEnd(6, "*"); // 'abc***'

"ha".repeat(3); // 'hahaha'

// slice(startIndex)
// slice(startIndex, endExclusive)
"hello".slice(1, 4); // "ell"  // ACCEPTS NEGATIVE INDEX !!!
"hello".substring(1, 4); // "ell" // does ""NOT"" accept negative index

"hello world".indexOf("world"); // 6 - index of the start of the arg

"hello world world".lastIndexOf("world"); // 12

"hello 123 456".match(/\d+/); // ["123"] -- need to use `[0]` at the end though
"hello 123 456".match(/\d+/g); // [ '123', '456' ] // or null if not found

let str = "123 456".matchAll(/\d+/g); // ES2020+
[...str].map((m) => m[0]); // [ '123', '456' ]

let x = "abc def b".search("bc"); // arg can be regex also: /\d+/  - returns only first match index
console.log(x); // 1 - returns first index, or -1

(123).toString(); // "123" - needs parethensis !!!

"abc".at(1); // 'b' (ES2022 feature). -- (-1) for last char
"abc".charAt(1); // 'b' -- no neg index

"a".charCodeAt(0); // 97

"hello".length; // 5

/\d+/.test("aaa 123 456 aaa"); // true - it has digits

"abc".concat("def"); // "abcdef"

//================================================ array API

Array.of(1, 2, 3); // ["1","2","3"]

Array.from("abc"); // ['a','b','c']

[1, 2].concat([3, 4]); // [1,2,3,4]

let arr2 = [1, 2, 3];
arr2.push(4); // returns new length
arr2.pop(); // returns popped item

arr2.shift(); // removes first item - and returns it
arr2.unshift(1); // adds item to front of array - returns new length

// splice :
// arr.splice(start,deleteCount, ...other)
let x2 = [10, 20, 30, 40, 50, 60];
let ret = x2.splice(2, 2, 300, 400); // -- returns item that was deleted
ret; // [ 30, 40 ]
x2; // [10,20,300,400,50,60]

// slice - like slicing a cake
// slice (copy array)
let newArr = [1, 2, 3].slice(); // copy  -- returns a new array

// slice to get sub-array
// .slice(start, stopExclusive)
[1, 2, 3, 4].slice(1, 3); // [2,3]   ----     no negative indexes like -1, use arr.length

arr2.forEach((x) => console.log(x));

arr2.map((x) => x * 2);

arr2.filter((x) => x % 2); // creates new array with elements that pass the test

arr2.reduce((acum, curr) => {
  return acum + curr;
}, 0); // 0 is initial value

// reduceRight - same as reduce, but starts from right side of array

[1, 2, 3].every((i) => i > 2); // false - does EVERY item pass ?

[1, 2, 3].some((i) => i > 2); // true - do SOME items pass ?

[10, 20, 30, 40].find((i) => item > 20); // 30 - returns the first item that passes

// .findLast(fn)

[10, 20, 30, 40].findIndex((i) => i > 20); // 2 - find the index of the first item that passes

// .findLastIndex(fn)

[3, 2, 1].sort(); // [1,2,3] - sorts the array in place and returns the sorted array
[3, 2, 1].sort((a, b) => a - b);

[1, 2, 3].reverse(); // [3,2,1] - reverses the array in place and returns the reversed array

[1, 2, 3].join("-"); // "1-2-3"

[1, 2, 3].indexOf(2); // 1 - returns first index of item

[1, 2, 3, 2].lastIndexOf(2); // 3 - returns last index of item

[1, 2, 3].includes(2); // true

[1, 2, 3, [4, 5, 6]].flat(); // [ 1, 2, 3, 4, 5, 6 ] - just one level -- .flat(2) -- 2 levels

// flatMap - use it when map returns a nested array, but you want a 1D array
["hello", "world"].flatMap((x) => x.split("")); // ['h','e','l','l','o','w','o','r','l','d']
["hello", "world"].map((x) => x.split("")); // [ [ 'h', 'e', 'l', 'l', 'o' ], [ 'w', 'o', 'r', 'l', 'd' ] ]

// .copyWithin()
let arr4 = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 30, 40];
// arr4.copyWithin(startIndexToCopyTo, startIndexToCopyFrom, endIndexToCopyFromExclusive)
arr4.copyWithin(0, 10, 12); // [30, 40, 1, 1, 1, 1, 1, 1, 1, 1, 30, 40]

[0, 0, 0, 0, 0, 0, 0, 0, 10, 20].copyWithin(0, 8, 10);
//  [10, 20, 0, 0, 0, 0, 0, 0, 10, 20]

// fill(fillWith, start,endExclusive)
[1, 2, 3, 4, 5].fill(0, 1, 4); // [1,0,0,0,5]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0].fill(5, 3, 6); // [0, 0, 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0]

[...Array(10).keys()].map((x) => x); //  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
new Array(10).fill(0).map((elem, index) => index); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
Array.from({ length: 10 }, (el, i) => i); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

[1, 2, 3].toString(); // "1,2,3"

// TODO:
// flatMap(), findLast(), and reduceRight()

//================================================ Object API

let p = {
  name: "brian",
  age: 30,
};

Object.keys(p); // ['name', 'age']
Object.values(p); // ['brian', 30]
Object.entries(p); // [['name','brian'], ['age', 30]]

Object.is(NaN, NaN); // true

for (let [k, v] of Object.entries(p)) {
  console.log(k, v);
}

Object.entries(p).forEach(([k, v]) => {
  console.log(k, v);
});

for (let k in p) {
  console.log(k, p[k]);
}

JSON.stringify(p); // '{"name":"Brian","age":30}'

let jsonStr = '{"name":"brian", "age": 30}';
JSON.parse(jsonStr); // converts string to object

//================================================ Promises

let p5 = Promise.resolve(10); // resolve emediatly with value X
let p6 = Promise.reject("err"); // rejects emediatly with reson X

// wait for all promises - fails if 1+ fails
Promise.all([p1, p2, p3]).then((x) => {
  console.log(x); // array of results
});
// .catch(e => {})
// .finally(_ => {})

// Promise.all([p1,p2,p3]) // waits for all to succeed, else fails
// Promise.allSettled([p1,p2]) // array of {status, value|reason}
// Promise.any([p1,p2]) // returns first promise to resolve!, error if all fail
// Promise.race([p1,p2]) // who ever finished first, we get the result, if either success of failure

//===

/*
    Promise Methods:

    1. Promise.all([p1, p2, p3])  
       - Waits for all promises to resolve.
       - If any promise rejects, the entire result is rejected.
       - Requires all to succeed.

    2. Promise.allSettled([p1, p2, p3])  
       - Waits for all promises to settle (either resolve or reject).
       - Returns an array of objects with { status: "fulfilled"/"rejected", value/reason }.
       - Useful when you need results of all promises, regardless of failure.

    3. Promise.any([p1, p2, p3])  
       - Waits for the first promise to resolve.
       - If all promises reject, it throws an AggregateError.
       - Requires at least one success.

    4. Promise.race([p1, p2, p3])  
       - Waits for the first promise to settle (either resolve or reject).
       - Returns the result of the first settled promise.
       - Requires at least one to settle.

*/

//===

let prom = new Promise((res, rej) => {
  isSuccess = false;

  if (isSuccess) {
    setTimeout(() => {
      res("SUCCESS");
    }, 300);
  } else {
    setTimeout(() => {
      // rej("ERR-1")
      rej(new Error("ERR-1"));
    });
  }
});

let af = async () => {
  try {
    console.log(await prom);
  } catch (e) {
    console.log(e); // e.message
  }
};

af();

//

prom
  .then((x) => {
    console.log(x);
  })
  .catch((e) => {
    console.log(e);
  })
  .finally((y) => {
    console.log(y); // undefined
  });

//================================================ Async/Await
// -- `await` blocks only the surrounding async function, not the main thread !!!

const asyncFn = async () => 123;

asyncFn().then((x) => console.log(x)); // 123

let val = await asyncFn(); // 123

//================================================ fetch API

let url = "https://jsonplaceholder.typicode.com/users";
let config = {
  method: "POST", // GET
  headers: {
    "Content-Type": "application/json",
  },
  body: JSON.stringify(obj),
};
let res = await fetch(url, config);
let json = await res.json();
console.log(json);

//

let url2 = "https://jsonplaceholder.typicode.com/users";
let config2 = {
  method: "GET",
};
let res2 = await fetch(url2, config2);
let json2 = await res.json();
console.log(json2);

//================================================ Classes

class FooBar {
  // ----------------------- GOTCHA: private fields need to be declared, or there will be an error !!!
  // name = 'John'
  // age = 20

  constructor(name, age) {
    this.name = name;
    this.age = age;
  }

  getName() {
    return this.name;
  }

  setName(name) {
    this.name = name;
  }

  // getters and setters:
  // ----------------------- GOTCHA: the field needs to be diff from the function name !!!
  get name2() {
    return this.name;
  }

  // ----------------------- GOTCHA: the field needs to be diff from the function name !!!
  set name2(name) {
    this.name = name;
  }

  // static

  static getBar() {
    return "bar123";
  }
}

let fb = new FooBar("brian", 30);
fb.getName("ana");
fb.name = "isabella";
fb.name; // isabella
FooBar.getBar(); // bar123

class Dog extends Animal {} // methods in Dog overwrite the ones in parent class !!!

//================================================ DOM JS API

document.getElementById("foo");
document.getElementsByClassName("bar"); // Elementsssssss

document.querySelector(".foo"); // first
document.querySelectorAll(".foo"); // "...All()"

el = document.createElement("div");
ptag = document.createElement("p");

el.innerHTML = "<p>aaa</p>"; // TODO: ???

ptag.textContent = "aaa";

let link = document.querySelector("a");
link.setAttribute("id", "123");
link.getAttribute("id");
link.removeAttribute("id");
link.hasAttribute("id"); // boolean ???

el.style.color = "blue";

el.classList.remove("active");
el.classList.add("foo");
el.classList.contains("active"); // boolean
el.classList.toggle("active"); // toggle on/off
el.classList.replace("oldClass", "newClass");

el.style.maxHeight = "100px";

el.querySelector(".bar"); // find .bar within the el !!!
document.querySelector(".foo");
let foos = document.querySelectorAll(".foo");

foos.forEach((el) => {
  el.addEventListener("click", () => {
    //...
    el.parentElement;
  });
});

el.parentElement;
el.children;
el.nextElementSibling;
el.previousElementSibling;

//================================================ Accordeon - https://www.youtube.com/watch?v=Q3ipHIy-YG0

// diplay: block // none
// max-height: 1000px; // 0

// .accordeon-content.active

//
// CSP - content security policy
//

/**





//================================================ Javascript Event loop 
// - https://www.youtube.com/watch?v=eiC58R16hb8
 
JS_ENGINE
  HEAP - where objects are stored
  CALL STACK - where functions execute 

WEB_APIS -- handles the timing (delays, for setTimeout, setInterval) -- later callback is pushed to TQ
  setTimeout
  setInterval
  Promises

  fetch 
  geolocation
  indexedDB
  console.log
  webstorage (localStorage, sessionStorage)
  File API
  Performance API
  HTML DOM 
  URL API


EVENT_LOOP  
    TASK_Q  
    MICRO_TASK_Q

 */

//================================================== localStorage - persists across sessions

// - refresh does not delete it

localStorage.setItem("foo", "bar");
localStorage.getItem("foo"); // 'bar'
localStorage.clear();
localStorage.length; // 0

localStorage.setItem("foo2", "bar2");
localStorage.removeItem("foo2");

//================================================== sessionStorage - single session // same API as localStorage

// - PER BROWSER TAB !!!
// - refresh does not delete it

sessionStorage.setItem("foo", "bar");
sessionStorage.getItem("foo"); // 'bar'
sessionStorage.clear();
sessionStorage.length; // 0

sessionStorage.setItem("foo2", "bar2");
sessionStorage.removeItem("foo2");

//================================================== Regex

"foo foo foo".match(/foo/g);
//  ['foo', 'foo', 'foo']

let regex = /brian/;
let res3 = regex.test("hello brian spinos");
console.log(res3); // true

/[a-z]/.test("hello brian spinos"); // true

/*


/brian/gmi -- g is for multiple results, m is for multi-line,   i - case insensitive

[a-ZA-Z]

[0-9]

(cat|dog)


/./ -- represent any character


/(brian){3,5}/  -- between 3 and 5
/(brian){3,}/   -- 3 or more
/(brian){3}/    -- exactly 3


*/

/(foo)+/.test(""); // - 1 or more
/(foo)*/.test(""); // - zero or more
/(foo)?/.test(""); // - zero or 1

/*

\d - digit
\w - word
\s -space 

// negative versions:

\D
\W
\S


/^brian$/.test("brian")


*/

// Lookahead and Lookbehind
// These are used to assert if a match is possible based on what's around the matched characters.

// Positive Lookahead X(?=   )
"1a 2b 3c".match(/\d(?=\D)/g); //  ['1', '2', '3']

// Negative Lookahead X(?!   )
"1-BAR 2-FOO 3-BAR".match(/\d(?!-FOO)/g); //  ['1', '3']

// Positive Lookbehind (?<=   )X
"a1 b2 c3".match(/(?<=\D)\d/g); //  ['1', '2', '3']

// Negative Lookbehind (?<!   )X
"BAR-1 FOO-2 BAR-3".match(/(?<!FOO-)\d/g); //  ['1', '3']

//================================================== Prototypal inheritance

// Prototypal inheritance

// function constructor
function Person(name) {
  this.name = name;
}

// [prototype] > [p8] // prototype chain
let p8 = new Person("brian");

Person.prototype.foo = function () {
  console.log("hello " + this.name);
};

p8.foo();

Person.prototype === p8.__proto__; // true

//================================================== debounce/throttle

const debounce = (callback, timeout) => {
  let timeoutId;

  const fn2 = (...args) => {
    // start over, so cancel pre existing timeoutIds
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => callback(...args), timeout);
  };

  return fn2;
};

const throttle = (callback, timeout) => {
  let callTime = 0;

  const fn2 = (...args) => {
    let currentTime = Date.now();
    if (currentTime > callTime + timeout) {
      callTime = currentTime;
      callback(...args); // no waiting here
    }
  };

  return fn2;
};

const f1b = debounce(() => {
  console.log("hello");
}, 300);

const f2b = throttle(() => {
  console.log("hello");
}, 300);

//================================================== Modules

// file1.js
export const hello = "Hello";

// file2.js
import { hello } from "./file1.js";
console.log(hello);

/////

// file3.js
export default myValue = 123;

// file4.js
import MyVal from "./file3.js";
console.log(MyVal);

//================================================== Arrow functions

// - they dont bind their own `this`

let obj4 = {
  name: "Alice",
  greet: () => console.log(`Hello ${this.name}`), // `this` is not bound to `obj4`
};
obj4.greet(); // undefined

//================================================== Error Handling

try {
  let result = riskyFunction();
} catch (error) {
  console.error(error);
} finally {
  console.log("Clean up");
}

//================================================== spread operator

const arr6 = [1, 2, 3];
const newArr6 = [...arr6, 4, 5]; // [1, 2, 3, 4, 5]

//==================================================
