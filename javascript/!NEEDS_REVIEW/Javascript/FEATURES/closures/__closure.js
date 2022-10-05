//
// Closures
// (Like creating functions with state!)
//

// Like a function factory, a closure factory!
let createClosure = () => {

  // Initial state
  let STATE = 0;

  // Creating the actual closure
  let myClosure =  () => {
    STATE += 1;
    console.log(STATE);
  }

  return myClosure;
};

//
//
//

let addA = createClosure();
let addB = createClosure();

//
//
//

addA(); // 1
addA(); // 2
addA(); // 3

addB(); // 1
addB(); // 2
addB(); // 3
