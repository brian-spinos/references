//
// Handling async errors
//


const getFooData = () => 
  Promise.resolve({foo:'foo-123'});

const getBarData = () => 
  Promise.resolve({bar:'bar-456'});

const getFooDataError = () => 
  Promise.reject('SOME_ERROR_FOO');

const getBarDataError = () => 
  Promise.reject('SOME_ERROR_BAR');

//
// =====
//

const getAllData = async () => {

  let fooData;
  let barData;

  try{
    fooData = await getFooDataError();
  } catch(error){
    fooData = error;
  }

  try{
    barData = await getBarDataError();
  } catch(error){
    barData = error;
  }
  

  console.log('fooData', fooData); // fooData SOME_ERROR_FOO
  console.log('barData', barData); // barData SOME_ERROR_BAR
}

getAllData();
