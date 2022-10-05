// Closures

let createClosure = () => {

  // Data that will be available to the closure instance
  let a = 0;
  let b = 1000;
  
  let myclosure = () => {
    a++; // we can modify the data 
    b++;
    let result = a + " " +  b;
    console.log(result);
    return result;
  }

  return myclosure;
}

let myclosure1 = createClosure();
myclosure1(); // "1 1001"
myclosure1(); // "2 1002"
myclosure1(); // "3 1003"


let myclosure2 = createClosure();
myclosure2(); // "1 1001"
myclosure2(); // "2 1002"
myclosure2(); // "3 1003"

// Each closure has a diff set of data, they dont share the same data 
