# ES6


```es6

//
// ES6 === ES2015
//


const foo = () => {
    console.log('aaa')
}

foo()


let x = 123
console.log(x)


let obj = {name: 'brian', age: 29}
let obj2 = {...obj, address: 'foobar 123'} 

console.log(obj2)


let arr = ['a', 'b']
arr.map( (item) => {
    console.log(item)
})


const pi = 3.141;
// pi = 12; // unknown: "pi" is read-only





let x2, y2;
({name: x2, age: y2} = { name: 'Erich', age: 27 });

//=====================================================


class Person {
  constructor(firstname, lname) {
    this._firstname = firstname;
    this._surname = lname;
  }
  get firstname() {
    return this._firstname;
  }
  getFullName() {
    // woohoooo template strings!!
    return `${this._firstname} ${this._surname}`;
  }
}
let aPerson = new Person('Brian', 'Spinos');
console.log(aPerson.getFullName());
console.log(aPerson.firstname);
```




