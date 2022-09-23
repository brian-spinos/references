# FAQ

- Do closures in JavaScript make a copy of the parent scope variables or just refer to them?

```javascript
// It's a reference, and this is easy to prove:

var arr = ['Keven Spacey', 'Dracula', 'Franklyn D. Roosevelt'];
 
function outer(obj) {
  return function inner() { console.log(obj) };
}
 
var inner = outer(arr); 
inner(); // ['Keven Spacey', 'Dracula', 'Franklyn D. Roosevelt'];
 
arr.push('Jean-Luc Picard');
 
inner(); // ['Keven Spacey', 'Dracula', 'Franklyn D. Roosevelt', 'Jean-Luc Picard']
```



- What is a closure?
- http://javascriptissexy.com/understand-javascript-closures-with-ease/
```
A closure is an inner function that has access to the outer (enclosing) function’s variables—scope chain. The closure has three scope chains: it has access to its own scope (variables defined between its curly brackets), it has access to the outer function’s variables, and it has access to the global variables.
```
