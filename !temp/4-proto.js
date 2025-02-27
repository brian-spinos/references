// Prototypal inheritance

// function constructor
function Person(name) {
  this.name = name;
}

// [prototype] > [p] // prototype chain
let p = new Person("brian");

Person.prototype.foo = function () {
  console.log("hello " + this.name);
};

p.foo();

Person.prototype === p.__proto__; // true
