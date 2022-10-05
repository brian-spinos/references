```js
function * pauseableFunc(){
  // 1st pf.next() call will start executing from this line

  console.log("log-1");
  yield "val-from-func-1"
  
  // 2nd pf.next() call will start executing from this line

  console.log("log-2");
  yield "val-from-func-2"
  
  // 3rd pf.next() call will start executing from this line

  console.log("log-3");
  let val1FromCaller = yield "***-1";

  // 4th pf.next("AAA") call will start executing from this line

  console.log("log-4");
  console.log("val1FromCaller: " + val1FromCaller); // "AAA"
  let val2FromCaller = yield "***-2";
  

  // 5th pf.next("BBB") call will start executing from this line
  //     - returns {value: undefined, done: false} ==> undefined, because there was nothing after the "yield" ???

  console.log("log-5");
  console.log("val2FromCaller: " + val2FromCaller); // "BBB"
  yield "val-from-func-3"

  // 6th pf.next() call will start executing from this line

  console.log("log-6");
  yield "val-from-func-4"
  
  // 7th pf.next() call will start executing from this line

  console.log("log-7");

  // 8th pf.next() call will start executing from this line
  
}


let pf = pauseableFunc();

