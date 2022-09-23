

```javascript
async function f() {
  return 1; // will be wrapped in a promise!
}
```

```
The word “async” before a function means one simple thing: a function always returns a promise. 
If the code has return <non-promise> in it, then JavaScript automatically wraps it into a resolved 
promise with that value.
```

```
https://javascript.info/async-await
```


```js
async function f() {
  return 1;
}

f().then(alert); // 1
```


- `await` works only inside async functions


```js
async function f() {

  let promise = new Promise((resolve, reject) => {
    setTimeout(() => resolve("done!"), 1000)
  });
  
  // wait till the promise resolves (*)
  // So any code after this line will need to wait,
  // even `console.log('hello');` will need to wait
  let result = await promise; 
  
  console.log('hello');
  
  alert(result); // "done!"
}

f();
```



###### Another example for `await`


```javascript
function getUsers() {
  return new Promise(resolve => {
    setTimeout(() => {
      var users = ['Brian', 'Erich', 'Rick'];
      resolve(users);
    }, 2000);
  });
}

async function displayUsers() {
  var users = await getUsers();
  console.log(users); // this line only executes after 2 seconds
}

// Displays users after 2 seconds
displayUsers(); //  ["Brian", "Erich", "Rick"]
````

