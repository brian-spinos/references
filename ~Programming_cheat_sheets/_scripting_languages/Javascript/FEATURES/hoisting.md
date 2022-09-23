# Hoisting

- Hoisting is JavaScript's default behavior of moving all declarations to the top of the current scope (to the top of the current script or the current function).


```javascript
foo(); // "hello"

// function declarations are hoisted
// variable declataions are hoisted
// function assignments/initializations are NOT hoisted
// variable assignments/initializations are NOT hoisted
function foo(){
	console.log("hello");
}
```

```javascript
bar(); // Uncaught TypeError: bar is not a function

// function declarations are hoisted
// variable declataions are hoisted
// function assignments/initializations are NOT hoisted
// variable assignments/initializations are NOT hoisted
var bar = function(){
	console.log("Howdy!");
}
```

```javascript
console.log(name); // undefined

// function declarations are hoisted
// variable declataions are hoisted
// function assignments/initializations are NOT hoisted
// variable assignments/initializations are NOT hoisted
var name = "brian";
```

