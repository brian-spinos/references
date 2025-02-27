// https://www.youtube.com/watch?v=Zi-Q0t4gMC8

// types:

/**

//mnemonic:
BB NU SSN O

bigint
boolean
null
undefined
string
symbol
number
object



 */

const uniqueKey = Symbol(); // unique and imutable identifier, (used for object keys ?)

let s10 = Symbol();
let s20 = Symbol();
s10 === s20; // false

let s30 = Symbol("id");
let s40 = Symbol("id");
s30 === s40; // false

const s1 = Symbol("s1");
const s2 = Symbol("s2");
s1 === s2; // false

const id = Symbol("id");

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

//
// typeof
//

typeof undefined; // 'undefined'
typeof 0; // 'number'
typeof 10n; // 'bigint'
typeof "aaa"; // 'string'
typeof Symbol(); // 'symbol'
typeof null; // 'object' ____________________> yeah...
typeof true; // 'boolean'
typeof { a: 1 }; // 'object'

typeof []; // 'object' <-----------------------

//
// loops
//

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

//
// break/continue
//

for (let i = 0; i < 10; i++) {
  if (i === 9) break; // break out of loop
  if (i === 2) continue; // skips 2
}

//
// string API
//

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

"hello".length; // 5

/\d+/.test("aaa 123 456 aaa"); // true - it has digits

//
// array API
//

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

[1, 2, 3, [4, 5, 6]].flat(); // [ 1, 2, 3, 4, 5, 6 ] - just one level

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

new Array(10).fill(0).map((elem, index) => index); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
Array.from({ length: 10 }, (el, i) => i); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

[1, 2, 3].toString(); // "1,2,3"

//
// Object API
//

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

//
// Promises
//

let p5 = Promise.resolve(10); // resolve emediatly with value X
let p6 = Promise.reject("err"); // rejects emediatly with reson X

// wait for all promises - fails if any fail
Promise.all([p1, p2, p3]).then((x) => {
  console.log(x); // array of results
});
// .catch(e => {})
// .finally(_ => {})

// Promise.allSettled([p1,p2]) // array of {status, value|reason}
// Promise.any([p1,p2]) // returns first promise to resolve!, error if all fail
// Promise.race([p1,p2]) // who ever finished first, we get the result, if either success of failure

//

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

//
// Async/Await
//

const asyncFn = async () => 123;

asyncFn().then((x) => console.log(x)); // 123

let val = await asyncFn(); // 123

//
// fetch
//

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

//
// DOM JS API
//

document.getElementById("foo");
document.getElementsByClassName("bar");

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

el.style.color = "blue";

el.classList.remove("active");
el.classList.add("foo");
el.classList.contains("active"); // boolean
el.classList.toggle("active"); // toggle on/off

el.style.maxHeight = "100px";

el.querySelector(".bar"); // find .bar within the el ?
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

//
// Accordeon - https://www.youtube.com/watch?v=Q3ipHIy-YG0
//

// diplay: block // none
// max-height: 1000px; // 0

// .accordeon-content.active

//
// CSP - content security policy
//

/**





//
// Javascript Event loop - https://www.youtube.com/watch?v=eiC58R16hb8
//
 
JS_ENGINE
  HEAP
  CALL STACK

WEB_APIS
  setTimeout
  setInterval
  Promises

  fetch 
  geolocation
  indexedDB
  console.log
  webstorage
  sessionStorage
  File 
  Performance 
  HTML DOM 
  URL 


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

//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
//==================================================
