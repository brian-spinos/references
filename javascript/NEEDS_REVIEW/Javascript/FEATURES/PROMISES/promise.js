// Promise
var promise = new Promise(function(resolve, reject){
    resolve(123);
    //reject(reason);
});


promise
.then(function (data) {
    console.log(data);
})
.catch(function (error) {
    console.log(error.message);
});
