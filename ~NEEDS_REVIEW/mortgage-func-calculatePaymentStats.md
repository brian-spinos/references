```js
/**
 * Calculate stats about payments 
 */
const getPaymentsStats = (apr, principal, years, monthlyPayment) => {
  const months = years * 12;
  let accumulatedInterest = 0;
  let accumulatedPayment = 0;
  let balance = principal;

  // Loop through months
  for(let i = 1; i <= months; i++){

    // calculate interest for monthly payment:
    let interest = balance * ((apr/100)/12);

    // from the principal, remove (monthlyPayment - interest)
    balance -= (monthlyPayment - interest);

    if(balance > 0){
      accumulatedInterest += interest;
      accumulatedPayment += monthlyPayment;

      let paymentObj = {
        paymentNumber: i,
        principalBalance: balance,
        accumulatedInterestPayed: accumulatedInterest,
        accumulatedPrincipalPayed: accumulatedPayment - accumulatedInterest,
        accumulatedPrincipalAndInterestPayed: accumulatedPayment,
        interestPayedThisMonth: interest,
        principalPayedThisMonth: monthlyPayment - interest,
      };

      console.log(paymentObj);
    }
  };

  return {
    accumulatedInterestPayed: accumulatedInterest,
    accumulatedPrincipalAndInterestPayed: accumulatedPayment
  };
};

// const res1 = getPaymentsStats(5,380000,30,2000);
// const res2 = getPaymentsStats(5,380000,15,2000);
// const diff = res1.accumulatedInterestPayed - res2.accumulatedInterestPayed;
// console.log('\n\ndiff', diff.toFixed(2));

getPaymentsStats(5,100000,30,536.82);

/* OUTPUT:

{
  accumulatedInterestPayed: 93256.55076586806,
  accumulatedPrincipalAndInterestPayed: 193255.2000000016
}

*/

```
