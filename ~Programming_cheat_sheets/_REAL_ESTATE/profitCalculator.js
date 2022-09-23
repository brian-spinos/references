/**
 * TIMMURD (tax deductible)
 * PITI
 * add tax benefits
 * ROI
 * NOI
 * LTV
 * GROSS/NET
 * TAX on income
 */
calculate = (options) => {

  let propPrice =
  options.propPrice;

  let downPaymentPercentage = options.downPaymentPercentage;


  //
  // based on price of property
  //

  let rentMonth = 
  (propPrice * 0.007);

  let rentYear = 
  (propPrice * 0.007) * 12;

  /**
   * 1.2% of property value
   */
  let propTaxYear = 
  (propPrice * 0.012);

  let propTaxMonth = 
  (propPrice * 0.012) / 12;
  
  /**
   * insurance: $35 for every $100,000
   */
  let propInsuranceMonth =
  (propPrice * 0.00035);

  let propInsuranceYear =
  (propPrice * 0.00035) * 12;

  let propInsuranceMonthTaxDeduction =
  (propInsuranceMonth * 0.4);

  let downPayment = 
  (propPrice / 100 ) * downPaymentPercentage;

  let closingCosts =
  (propPrice / 100) * 5;

  /**
   * 0.25 is what I should have payed in tax,
   * but since it is deductable, I get back that money!
   */
  let depreciationYear = 
  (propPrice / 27.5) * 0.25;

  let depreciationMonth =
  (depreciationYear / 12);

  let appreciationYear =
  (propPrice * 0.03);

  let appreciationMonth =
  (appreciationYear / 12);

  let loan =
  (propPrice / 100 ) * (100 - downPaymentPercentage)

  //
  // based on mortgage
  //
  
  let mortgageMonth = loan * 0.005; // between 0.005 - 0.008 (lower is better)

  let totalPayed = // at the end of 30 years
  (mortgageMonth * 12 * 30);

  let totalInterest =
  (mortgageMonth * 12 * 30) - loan;

  let totalPrincipalPayed =
  (mortgageMonth * 12 * 30) - totalInterest;
  
  /**
   * 40% of mortgage for vacancy, repairs
   */
  let monthOperatingExpenses =
  (mortgageMonth * 1.4);

  let monthOperatingExpensesTaxCredit = 
  (mortgageMonth * 0.4) * 0.4;


  let managementMonth = 
  (rentMonth * 0.1); // 10% of rent for management

  let managementMonthTaxCredit =
  (managementMonth * 0.4);


  let profitPerMonth = 
  rentMonth
  - mortgageMonth
  - managementMonth
  - propTaxMonth
  - propInsuranceMonth
  + propInsuranceMonthTaxDeduction
  + managementMonthTaxCredit
  + depreciationMonth
  + appreciationMonth
  + monthOperatingExpensesTaxCredit;

  let profitPerYear = 
  profitPerMonth * 12;

  let returnOnDownPayment =
  (profitPerYear / downPayment) * 100;

  let capRate = 
  (profitPerYear / propPrice) * 100;

  console.log('propPrice',
  propPrice/1000 + 'K');

  console.log('downPayment',
  downPayment/1000 + 'K');

  console.log('loan',
  loan/1000 + 'K');

  console.log('mortgage/mo',
  mortgageMonth/1000 + 'K');

  console.log('totalPayed',
  totalPayed/1000 + 'K');

  console.log('totalInterest',
  totalInterest/1000 + 'K');

  console.log('totalPrincipalPayed', totalPrincipalPayed/1000 + 'K');

  console.log('monthOperatingExpenses', monthOperatingExpenses/1000 + 'K');

  console.log('rentMonth',
  rentMonth/1000 + 'K');

  console.log('rentYear',
  rentYear/1000 + 'K');

  console.log('profitPerMonth',
  profitPerMonth/1000 + 'K');

  console.log('profitPerYear',
  profitPerYear/1000 + 'K');

  console.log('returnOnDownPayment',
  returnOnDownPayment + '%');

  console.log('capRate',
  capRate + '%');

  console.log('closingCosts',
  closingCosts/1000 + 'K');

  console.log('closingCosts + downPayment',
  (closingCosts + downPayment)/1000 + 'K');

  console.log('depreciationMonth',
  depreciationMonth/1000 + 'K');

  console.log('depreciationYear',
  depreciationYear/1000 + 'K');

  console.log('propTaxMonth',
  propTaxMonth/1000 + 'K');

  console.log('monthOperatingExpensesTaxCredit',
  monthOperatingExpensesTaxCredit/1000 + 'K');

  console.log('propInsuranceMonth',
  propInsuranceMonth/1000 + 'K');

  console.log('propInsuranceMonthTaxDeduction',
  propInsuranceMonthTaxDeduction/1000 + 'K');

  console.log('managementMonthTaxCredit',
  managementMonthTaxCredit/1000 + 'K');

  console.log('========================');

  console.log('returnOnDownPayment',
  returnOnDownPayment + '%');

  console.log('closingCosts + downPayment',
  (closingCosts + downPayment)/1000 + 'K');

  console.log('profitPerMonth',
  profitPerMonth/1000 + 'K');
};


calculate({
  propPrice: 1000000,
  downPaymentPercentage: 20
});

/*
propPrice 1000K
downPayment 200K
loan 800K
mortgage/mo 4K
totalPayed 1440K
totalInterest 640K
totalPrincipalPayed 800K
monthOperatingExpenses 5.6K
rentMonth 7K
rentYear 84K
profitPerMonth 5.267575757575758K
profitPerYear 63.2109090909091K
returnOnDownPayment 31.60545454545455%
capRate 6.321090909090909%
closingCosts 50K
closingCosts + downPayment 250K
depreciationMonth 0.7575757575757575K
depreciationYear 9.09090909090909K
propTaxMonth 1K
monthOperatingExpensesTaxCredit 0.64K
propInsuranceMonth 0.35K
propInsuranceMonthTaxDeduction 0.14K
managementMonthTaxCredit 0.28K
========================
returnOnDownPayment 31.60545454545455%
closingCosts + downPayment 250K
profitPerMonth 5.267575757575758K
*/
