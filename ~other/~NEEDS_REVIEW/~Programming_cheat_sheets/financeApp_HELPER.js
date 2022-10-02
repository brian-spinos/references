
function getMonthlyDates(fromDate, toDate, day){
	var dates = [];

	fromDateM = moment(new Date(fromDate));
	toDateM = moment(new Date(toDate));
	
	var fromDay = fromDateM.date();

	if(day > fromDay){
		// this month
		var str = (fromDateM.month() + 1) + '/' + day + '/' + fromDateM.year();
	}else{
		// next month
		var fromDateM = fromDateM.add(1, 'months');
		var str = (fromDateM.month() + 1) + '/' + day + '/' + fromDateM.year();
	}

	var firstDate = moment(new Date(str));

	while(firstDate < toDateM){
		// console.log(firstDate.format('MM-DD-YYYY'));
		dates.push(firstDate.format('MM-DD-YYYY'));
		firstDate = firstDate.add(1, 'months');
	}

	return dates;
}


getMonthlyDates('01/01/2017', '01/01/2018', 15);



//===========================================================


function getWeeklyDates(fromDate, toDate, day){
	var dates = [];

	fromDateM = moment(new Date(fromDate));
	toDateM = moment(new Date(toDate));
	
	var fromDay = fromDateM.date();

	if(day > fromDay){
		// this month
		var str = (fromDateM.month() + 1) + '/' + day + '/' + fromDateM.year();
	}else{
		// next month
		var fromDateM = fromDateM.add(1, 'months');
		var str = (fromDateM.month() + 1) + '/' + day + '/' + fromDateM.year();
	}

	var firstDate = moment(new Date(str));

	while(firstDate < toDateM){
		// console.log(firstDate.format('MM-DD-YYYY'));
		dates.push(firstDate.format('MM-DD-YYYY'));
		firstDate = firstDate.add(1, 'weeks');
	}

	return dates;
}


getWeeklyDates('01/01/2017', '01/01/2018', 15);




//===========================================================


function getBiWeeklyDates(fromDate, toDate, day){
	var dates = [];

	fromDateM = moment(new Date(fromDate));
	toDateM = moment(new Date(toDate));
	
	var fromDay = fromDateM.date();

	if(day > fromDay){
		// this month
		var str = (fromDateM.month() + 1) + '/' + day + '/' + fromDateM.year();
	}else{
		// next month
		var fromDateM = fromDateM.add(1, 'months');
		var str = (fromDateM.month() + 1) + '/' + day + '/' + fromDateM.year();
	}

	var firstDate = moment(new Date(str));

	while(firstDate < toDateM){
		// console.log(firstDate.format('MM-DD-YYYY'));
		dates.push(firstDate.format('MM-DD-YYYY'));
		firstDate = firstDate.add(2, 'weeks');
	}

	return dates;
}


getBiWeeklyDates('01/01/2017', '01/01/2018', 15);


//===========================================================


function getAlldays(fromDate, toDate){
	var dates = [];
	var hash = {};

	fromDateM = moment(new Date(fromDate));
	toDateM = moment(new Date(toDate));
	
	var fromDay = fromDateM.date();


	var currentDate = fromDateM;

	while(currentDate < toDateM){
		// console.log(currentDate.format('MM-DD-YYYY'));
		dates.push(currentDate.format('MM-DD-YYYY'));
		hash[currentDate.format('MM-DD-YYYY')] = null;
		currentDate = currentDate.add(1, 'days');
	}

	return hash; // days['10-16-2017'] === null
}


getAlldays('01/01/2017', '01/01/2018');

