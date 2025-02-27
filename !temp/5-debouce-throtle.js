const debounce = (fn1, timeout) => {
  let timeoutId;

  const fn2 = (...args) => {
    // start over, so cancel pre existing timeoutIds
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => fn1(...args), timeout);
  };

  return fn2;
};

const throttle = (fn1, timeout) => {
  let callTime = 0;

  const fn2 = (...args) => {
    let currentTime = Date.now();
    if (callTime + timeout < currentTime) {
      callTime = currentTime;
      fn1(...args); // no waiting here
    }
  };

  return fn2;
};

const f1 = debounce(() => {
  console.log("hello");
}, 300);

const f2 = throttle(() => {
  console.log("hello");
}, 300);
