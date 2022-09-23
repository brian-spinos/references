# Play the lottery!


```javascript
var maxLoteryNumber = 13983816; // possibilities, 6 numbers, 1-49
var pickedLoteryNumber = Math.floor(Math.random() * maxLoteryNumber);  // 9980811;
console.log("LOTERY NUMBER IS: " + pickedLoteryNumber);
var MAX_TRIES = 1000000; // 1M


function playLotery(){
    var myNumber = Math.floor(Math.random() * maxLoteryNumber); 
    
    var didWin = false;

    if(myNumber === pickedLoteryNumber){
        // console.log("WINNER!!!");
        didWin = true;
    }else{
        // console.log("LOOSER...");
        didWin = false;
    }
    
    // console.log("didWin: " + didWin);
    
    return didWin;
}


var didWin = false;
for(var i = 0; i < MAX_TRIES; i++){
    didWin = playLotery();
    
    if(didWin === true){
      console.log("WINNER!!!");
      break;
    }
    
    
}

console.log("didWin: " + didWin);
```
