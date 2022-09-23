/**
 * Some 3rd party function that returns a promise...
 */
let fetchData = () => {
    let p = new Promise((resolve) => {
        setTimeout(() => {
            resolve(123);
        }, 10_000);
    });

  return p;
}

/**
 * your application code
 */
let fetchDataWrapper = async () => {
    const res = await fetchData(); // JS will wait for the promise in this line to complete
    // console.log(res); // 123
    return res;
}


/**
 * calling fetchDataWrapper() - WITH `await`
 */
let x = await fetchDataWrapper(); // JS will wait for the promise in this line to complete
console.log('x', x); // 123


/**
 * calling fetchDataWrapper() - WITHOUT `await`
 */
let y = fetchDataWrapper(); // with no await, this function will return a promise reference
console.log('y', y); // Promise {<pending>}
y // Promise {<fulfilled>: 123}
