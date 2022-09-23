function * StartProcess(firstValue){

  console.log("Welcome, lets calculate your age!");
  console.log(`firstValue: ${firstValue}`);

  let yearBorn = yield "what year were you born in?" // return value to caller

  console.log(`Got: yearBorn: ${yearBorn}`);

  let currentYear = yield "what year is it now?" // return value to caller

  console.log(`currentYear: ${currentYear}`);

  console.log(`you are ${currentYear - yearBorn} yo`);
}


let foo = StartProcess("Hello");


//---------------------------

foo.next("hi") // this argument is ignored :(
// Welcome, lets calculate your age!
//firstValue: Hello
//{value: 'what year were you born in?', done: false}
foo.next(1988)
//Got: yearBorn: 1988
//{value: 'what year is it now?', done: false}
foo.next(2022)
//currentYear: 2022
//you are 34 yo
//{value: undefined, done: true}

