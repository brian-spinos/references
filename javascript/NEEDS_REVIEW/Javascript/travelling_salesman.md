# Travelling Salesman problem


```javascript
var MAX_ITERATIONS = 1000; // 50M iterations

var PATH = [0,1,2,3,4,5,6,7,8,9];

var MATRIX = [
    [12,53,84,54,84,90,67,27,95,11],
    [82,65,26,15,73,87,67,95,67,46],
    [65,33,11,73,22,84,34,62,16,16],
    [12,53,56,54,84,98,67,27,11,76],
    [11,65,26,15,11,87,67,54,67,55],
    [65,33,66,73,22,84,34,83,16,16],
    [12,20,84,54,84,73,67,27,95,76],
    [82,65,26,15,73,87,67,95,11,26],
    [65,33,66,73,22,84,34,83,16,16],
    [29,53,11,54,11,98,67,27,26,76]
];


function getRandomIndex(){
    Math.floor(Math.random() * 10);  
}


/**
 * Shuffles array in place.
 * @param {Array} a items An array containing the items.
 */
function shuffle(a) {
    var j, x, i;
    for (i = a.length - 1; i > 0; i--) {
        j = Math.floor(Math.random() * (i + 1));
        x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    return a;
}


function calculateRandomPath(){
    var randPath = shuffle(PATH);
    var dist = 0;

    for(var i = 0; i < (PATH.length - 1); i++){
        var currentPoint = randPath[i];
        var nextPoint = randPath[i + 1];

        dist += (MATRIX[currentPoint][nextPoint]);
        
        
    }

    var result = {
        dist: dist,
        currentPath: randPath
    };
    
    // console.log("result: ", result);
    
    return result;
}


function multiCalc(){
    var minDist = 99 * 10;
    var minPath = null;
    var minHistoryDist = [];
    var iterationNumForMin = 0;

    var maxDist = 0;
    var maxPath = null;
    var maxHistoryDist = [];
    var iterationNumForMax = 0;


    for(var i = 0; i < MAX_ITERATIONS; i++){
        var result = calculateRandomPath();
        // console.log("result: ", result);

        if(result.dist < minDist){
            minDist = result.dist;
            minPath = result.currentPath.slice(); // copy by value, a must, so we dont get the last returned value always
            minHistoryDist.push(result.dist);
            iterationNumForMin = i;
            // console.log("min: ",minPath, minDist);
        }

        if(result.dist > maxDist){
            maxDist = result.dist;
            maxPath = result.currentPath.slice(); // copy by value, a must, so we dont get the last returned value always
            maxHistoryDist.push(result.dist);
            iterationNumForMax = i;
            // console.log("max: ",maxPath, maxDist);
        }
        
        // console.log("========");
        // console.log("minB: ",minPath, minDist);
        // console.log("maxB: ",maxPath, maxDist);
    }
    
    return {
        minDist: minDist,
        minPath: minPath,
        minHistoryDist: minHistoryDist,
        iterationNumForMin: iterationNumForMin,

        maxDist: maxDist,
        maxPath: maxPath,
        maxHistoryDist: maxHistoryDist,
        iterationNumForMax: iterationNumForMax,
    };
}


multiCalc();



/*
OUTPUT EXAMPLE:

{ minDist: 239,
  minPath: [ 5, 1, 6, 0, 9, 8, 4, 3, 7, 2 ],
  minHistoryDist: [ 393, 322, 308, 298, 277, 263, 259, 239 ],
  iterationNumForMin: 383,
  maxDist: 698,
  maxPath: [ 5, 3, 9, 6, 8, 7, 4, 1, 0, 2 ],
  maxHistoryDist: [ 393, 453, 510, 537, 554, 580, 588, 635, 698 ],
  iterationNumForMax: 51 }


*/


```
