# Logarithm functions


```javascript
function checkLevelsWithCount(n){
    var x = n;
    var levels = 0;

    while(x > 1){
        levels++;

        x /= 2;
    }

    return levels;
}

function checkItemsCountWithLevel(levels){
    var itemsCount = 1;
    var currentLevelItemsCount = 1;

    for (var i = 0; i < (levels - 1); i++){
        currentLevelItemsCount *= 2
        itemsCount += currentLevelItemsCount;
    }

    return itemsCount;
}

checkItemsCountWithLevel(3); // 7
checkLevelsWithCount(7); // 3

checkItemsCountWithLevel(50) // 1125899906842623
checkLevelsWithCount(1125899906842623) // 50
```
