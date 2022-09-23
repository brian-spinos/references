```javascript


function * study(){

  console.log('start...');

  let language = yield;
  console.log("studying: " + language);

  let time = yield;
  console.log("for: " + time);

  let finishedMessage = yield;
  console.log("finished: " + finishedMessage);


  console.log('end...');
}

var s = study();
s.next() // this will start the process off!
s.next('Haskell')
s.next('3 hours')
s.next('horay! I am done!')

// start...
// studying: Haskell
// for: 3 hours
// finished: horay! I am done!
// end...

//=================
console.log('\n\n\n\n')

function * dude(){
  yield 123
  yield 456

  let language = yield;
  console.log("studying: " + language);

  yield 'LAST_VALUE'


}

let x = dude()

x.next().value // 123
x.next().value // 456
x.next()
x.next('ruby').value



```
