
// Vanilla javascript


//========================================================================================================


        <script src="./script.js"></script>
    </body>

//========================================================================================================


class Person {
    static #PRIVATE_STATIC_FIELD;
    static PUBLIC_STATIC_FIELD;

    #privateField;
    publicField;

    constructor(firstName, lastName){
        console.log('Person created...');
        Person.#PRIVATE_STATIC_FIELD = 42;
        this.#privateField = 42;

        Person.PUBLIC_STATIC_FIELD = 420;
        this.publicField = 420;

        this.firstName = firstName;
        this.lastName = lastName;

        // delete this.#privateField;   // Syntax error
        // this.#undeclaredField = 444; // Syntax error
    }

    #privateMethod() {
        return 'hello world';
    }

    static #privateStaticMethod() {
        return 'hello world';
    }

    greeting() {
        console.log(`Hello! from ${this.firstName} ${this.lastName}`);
    }
}


const brian = new Person('brian', 'spinos');
// brian.#privateField === 42;   // Syntax error


brian.greeting();
console.table(brian);
console.log(Person.PUBLIC_STATIC_FIELD); // 420

//========================================================================================================

class ClassWithPrivateField {
    #privateField;
}

class SubClass extends ClassWithPrivateField {
    #subPrivateField;
  
    constructor() {
      super();
      this.#subPrivateField = 23;
    }
  }

const instance = new SubClass();
console.log(instance); 

// console.log(instance.subPrivateField); // undefined ??? why? should throw an error...



//======================================================================================================== Arrays

// https://www.w3schools.com/jsref/jsref_obj_array.asp


const fruits = ["Banana", "Orange", "Apple", "Mango"];

fruits.toString(); // 'Banana,Orange,Apple,Mango'
fruits.join(" * ");
fruits.pop(); // removes the last item from the array, and returns the item 
fruits.push("Kiwi"); // adds item to end of the array, returns new array length
fruits.shift(); // removes first item from array, and returns it
fruits.unshift("Lemon"); // adds item to the beginning of the array, returns the new array length
const arr4 = arr1.concat(arr2, arr3, 'item');
fruits.splice(2, 0, "Lemon", "Kiwi"); // add items to index 2, and deletes 0 items
        x = [1,2,3,4,5]
        x.splice(2,0,2000,3000) // returns []
        x //  [1, 2, 2000, 3000, 3, 4, 5]

fruits.splice(0, 1); // using slice to remove the first item

const citrus = fruits.slice(1); // creates a new array, including the index 1 onto the end of the array

const citrus2 = fruits.slice(1, 3); // creates a new array, include index 1, up-to and excluding index 3


//========================================================================================================



