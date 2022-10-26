

////============================================================================= promise success

let promise2 = new Promise((res,rej) => {
    // rej("this is an error");
    res({aaa:123});
});



try{
    let data = await promise2;
    console.log(`DATA_IS: ${JSON.stringify(data)}`);  // executed <<<< DATA_IS: {"aaa":123}
}catch(error){
    console.log(`ERROR_IS: ${error}`);
}

////============================================================================= promise fail

let promise = new Promise((res,rej) => {
    rej("this is an error");
    // res({aaa:123});
});



try{
    let data = await promise;
}catch(error){
    console.log(`ERROR_IS: ${error}`);  //  ERROR_IS: this is an error
}




////============================================================================= compose promises


let getFirstPromise = async (data) => {
    console.log('data', data);
    let promise = new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve('FIRST_VAL')
        }, 1000);
        //reject('FIRST_VAL')
    });

    return promise;
};

let getSecondPromise = async (data) => {
    console.log('data', data);
    let promise = new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(data + ' SECOND_VAL')
        }, 1000);
        //reject('SECOND_VAL')
    });

    return promise;
};

let getThirdPromise = async (data) => {
    console.log('data', data);
    let promise = new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(data + ' THIRD_VAL')
        }, 1000);
        //reject('THIRD_VAL')
    });

    return promise;
};

let handleFinalData = async (data) => {
    console.log('data', data);
};


let handleError = (error) => {
    console.log('error', error);
};


getFirstPromise('START')
    .then(x => getSecondPromise(x)) // same as .then(getSecondPromise)
    .then(x => getThirdPromise(x)) // same as .then(getSecondPromise)
    .then(x => handleFinalData(x)) // same as .then(getSecondPromise)
    .catch(handleError);







////=============================================================================