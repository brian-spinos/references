// Closures

let globalVar = 0;

let createClosure = () => {
	// The closure will get a **COPY** of this context
	// This will be like the closure's instance variables
	let closureInstanceVar = 1000; 

	// The closure
	let myClosure = () => {
		closureInstanceVar++; // Change the closure's "instance variable"
		globalVar++; // We do have access to change globalVar
		return closureInstanceVar;
	};

	return myClosure;
};

let closure1 = createClosure();
closure1(); // 1001
closure1(); // 1002
closure1(); // 1003

let closure2 = createClosure();
closure2(); // 1001
closure2(); // 1002
closure2(); // 1003
closure2(); // 1004

globalVar; // 7
