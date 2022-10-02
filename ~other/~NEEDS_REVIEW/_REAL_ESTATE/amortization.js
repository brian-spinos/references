/**
 * Amortization function
 * https://codepen.io/joeymack47/pen/fHwvd?editors=1111
 */
const amort = (balance, interestRate, terms) => {
    interestRate = interestRate / 100; // in decimal

    //Calculate the per month interest rate
    var monthlyRate = interestRate/12;
    
    // ***Calculate the payment***
    var payment = balance * (monthlyRate/(1-Math.pow(
        1+monthlyRate, -terms)));
        

    console.log('Loan amount', balance.toFixed(2));
    console.log('Interest rate', (interestRate*100).toFixed(2));
    console.log('Number of months', terms);
    console.log('Monthly payment', payment.toFixed(2));
    console.log('Total paid', (payment * terms).toFixed(2));

    let xxx = [];
    
    /**
     * Loop that calculates the monthly Loan amortization amounts then adds 
     * them to the return string 
     */
    for (var count = 0; count < terms; ++count) { 
        //in-loop interest amount holder
        var interest = 0;
        
        //in-loop monthly principal amount holder
        var monthlyPrincipal = 0;
        
        //calc the in-loop interest amount and display
        interest = balance * monthlyRate;
        
        //calc the in-loop monthly principal and display
        monthlyPrincipal = payment - interest;
        
        xxx.push({
            month: count + 1,
            balance: balance.toFixed(2),
            interest: interest.toFixed(2),
            monthlyPrincipal: monthlyPrincipal.toFixed(2),
            monthlyPayment: payment.toFixed(2),
        });   
        
        //update the balance for each loop iteration
        balance = balance - monthlyPrincipal;    
    }
    
    return xxx;
}

amort(100, 10, 3);
