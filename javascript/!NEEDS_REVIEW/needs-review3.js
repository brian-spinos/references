
//===================================================================================
let num = Number("123")

//===================================================================================
https://www.sitepoint.com/es6-enhanced-object-literals/

// Object literal function shorthand
let obj = { 
    myFunc(a, b){
        console.log(`a: ${a}, b: ${b}`);
    },
    sum(a, b)  { return a + b; },
};

obj.myFunc(1, 2); // a: 1, b: 2

//===================================================================================

// function with void return:
const foo = (state, action) => void state.push(action.payload);

//=================================================================================== Proxy (Immer uses this ??? seems so)
https://www.javascripttutorial.net/es6/javascript-proxy/#:~:text=A%20JavaScript%20Proxy%20is%20an,%2C%20and%20function%20invocations%2C%20etc.

const user = {
    firstName: 'John',
    lastName: 'Doe',
    email: 'john.doe@example.com',
}

const handler = {
    get(target, property) {
        console.log(`Property ${property} has been read.`);
        return target[property];
    }
}

const proxyUser = new Proxy(user, handler);
console.log(proxyUser.firstName);
console.log(proxyUser.lastName);

// Property firstName has been read.
// John
// Property lastName has been read.
// Doe

// ...MORE...

//===================================================================================
foo?.bar() // ???

//=================================================================================== deconstruction, with alias
const { userId, contacts: contactList } = user;

//=================================================================================== arrays
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/findLast

array.at(-1); // get last item 
array[array.length-1] // same as above
array1.every(x => x > 10) // checks that every element is above 10
array.fill(null) // fill existing array will all null values
words.filter(word => word.length > 6);
array1.find(element => element > 10); // find first greater than 10 , returns undefined if non found
array1.findIndex((element) => element > 10) // find index, return -1 if not found
array1.findLast((element) => element > 10); // find last element greater than 10, return undefined if non
arr.find((todo) => todo.id === 123)
const arr3 = arr.slice() // copy array

//=================================================================================== log with color
console.log(`%c ${foo}`, 'background: #222; color: #bada55');

//=================================================================================== loop
for (const [key, value] of Object.entries(object1)) {
    console.log(`${key}: ${value}`);
  }

  Object.entries(obj).forEach(([key, value], index) => {  // <---- use this
    console.log(`${key} ${value}`); // "a 5", "b 7", "c 9"
  });

//=================================================================================== ??

nullOrUndefined ?? "aaa" // returns right side

//===================================================================================

[null, undefined, '', 0].includes('aaa') === false

//===================================================================================

const foo2 = JSON.parse(JSON.stringify(_foo)); // copy object

//===================================================================================