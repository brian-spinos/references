/**
 * Race Condition
 */
class RaceConditionTest {
  main(){
    let BOX = 0;

    function rand(n){
      return Math.ceil(Math.random() * n);
    }
    
    let IDS = ['A', 'B']
    
    function updateDB(){
      let ID =  IDS.pop();

      // fetch data
      setTimeout(() => {
        let temp = BOX;
        console.log(`------------------- R_${ID} (copy) BOX: ${temp}`)
        
        // change data
        setTimeout(() => {
          temp += 1;
          // console.log(`------------------- R_${ID}.CHANGE BOX: ${temp}`)
          
          // update data
          setTimeout(() => {
            console.log(`------------------- R_${ID} (update) BOX: ${BOX} -> ${temp}`)
            BOX = temp;
          }, rand(0));

        }, rand(1000));

      }, rand(1000));
    }
  
    updateDB()
    updateDB();
    
    setTimeout(() => {
      console.log(`------- BOX: ${BOX}`)
    }, 6000);
  }
}

new RaceConditionTest().main()


// new RaceConditionTest().main()
//
//------------------- R_B (copy) BOX: 0
//------------------- R_A (copy) BOX: 0
//------------------- R_B (update) BOX: 0 -> 1
//------------------- R_A (update) BOX: 1 -> 1
//------- BOX: 1
// new RaceConditionTest().main()
//
//------------------- R_B (copy) BOX: 0
//------------------- R_B (update) BOX: 0 -> 1
//------------------- R_A (copy) BOX: 1
//------------------- R_A (update) BOX: 1 -> 2
//------- BOX: 2
