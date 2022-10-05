const setKey = (obj, keyPath, value) => {
  const isObject = obj.constructor === Object;
  let currentKey = keyPath.split('.')[0];
  let nextObj = obj[currentKey];
  const isNextObject = nextObj === undefined ? false : nextObj.constructor === Object;
  let pathArray = keyPath.split('.');
  pathArray.shift();
  let nextKeyStringPath = pathArray.join('.');
  let nextKey = keyPath.split('.')[1];

  if(nextKey === undefined){
    // base case
    obj[currentKey] = value;
    return;
  }

  if(isObject && isNextObject){
    // recurse
    setKey(nextObj, nextKeyStringPath, value);
  }else{
    // recurse (key is undefined)
    obj[currentKey] = {};
    nextObj = obj[currentKey];
    setKey(nextObj, nextKeyStringPath, value);
  }
};

const setKeyIfExists = (obj, keyPath, value) => {
  const isObject = obj.constructor === Object;
  let currentKey = keyPath.split('.')[0];
  let nextObj = obj[currentKey];
  const isNextObject = nextObj === undefined ? false : nextObj.constructor === Object;
  let pathArray = keyPath.split('.');
  pathArray.shift();
  let nextKeyStringPath = pathArray.join('.');
  let nextKey = keyPath.split('.')[1];
  
  if(nextKey === undefined){
    // base case

    if(obj[currentKey] === undefined){
      console.log('KEY NOT FOUND: ' + currentKey);
      return;
    }
    
    obj[currentKey] = value;
    return;
  }

  if(isObject && isNextObject){
    // recurse
    setKeyIfExists(nextObj, nextKeyStringPath, value);
  }else{
    console.log('KEY NOT FOUND: ' + currentKey);
  }
};
