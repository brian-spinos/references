# Closures

- The class/object equivalent


```javascript
function getClosure(){
    var password = 10;

    var myClosure = function(newPassword){
        console.log('Old password:', password);
        password += newPassword;
        console.log('New password:', password);
    }

    return myClosure;
}


var closureA = getClosure();
closureA(5); // 15
closureA(5); // 20
closureA(5); // 25
closureA(5); // 30


var closureB = getClosure();
closureB(5); // 15
closureB(5); // 20
closureB(5); // 25
closureB(5); // 30
```
