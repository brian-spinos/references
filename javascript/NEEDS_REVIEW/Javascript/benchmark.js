var b = (fn) => {
  const REPS = 10000;
const results = [];
  for(var i = 0; i < REPS; i++) {
    console.log(i)
    const start = +new Date();
    fn()
    const end = +new Date();
    results[i] = end - start;
  }

  average = 0;

  results.forEach((j) => {
    average += j;
  });

  const here = average / REPS;

  console.log('here', here);

  return here;
};



var plus = b(() => +new Date());


var gettime = b(() => (new Date()).getTime());

console.log('plus', plus);
console.log('gettime', gettime);

console.log('plus is less than gettime: ', plus < gettime);
