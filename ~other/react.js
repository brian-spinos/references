
//-----------------------------------------------------------------------------------------------
Children

this.props.children



<Qux foo={<Bar />} />

//-----------------------------------------------------------------------------------------------

// Memory leaks
// a callback is created for each render... bad
onClick={(e) => this.foo(e)}


//-----------------------------------------------------------------------------------------------

//Lifecycle
1. Constructor
2. Render
3. ComponentDidMount // api calls here
4. ComponentWillUnmount


//-----------------------------------------------------------------------------------------------
// Ref
<p ref={el => this.foo = el}>hello</p>
// this.foo.value 


//-----------------------------------------------------------------------------------------------

// SetState
// if you need to rely on prev state!
this.setState((prevSate, props) => {
    return {...};
});


//-----------------------------------------------------------------------------------------------
// APIs
https://randomuser.me


//-----------------------------------------------------------------------------------------------

// Hoc
Create a function that has a parameter for a component, and returns a class component,

export foo(Bar); // reruns Baz component

Usage: give component extra functionality, like a decorator design pattern


//-----------------------------------------------------------------------------------------------
Immutability 

These functions do not change the original object/array, but return new copies, this not mutating the original!

.slice(9,4)
.filter
.map
.reduce
xx = [...x] // shallow copies ???
yy = {...y} // makes shallow copies :( 


Functions that alter the original object, thus, NOT immutable:

.push
.pop
.foo = 123

//-----------------------------------------------------------------------------------------------
Enzyme:

Shallow: single component, no children

Mount: full dom rendering, can test component life cycles 


Render: render static html

———

Don’t use .instance() because that is private to the component, instead use the buttons that fire that change! :)
So use .simulate()


Enzyme-to-json : convert enzyme to json format!



//-----------------------------------------------------------------------------------------------
Fetch

// responde is an object!!!  

// wrap fetch in try catch block


function loadJson(url) {
  return fetch(url)
    .then(response => {
      if (response.status == 200) {
        return response.json();
      } else {
        throw new Error(response.status);
      }
    })
}

loadJson('no-such-user.json') // (3)
  .catch(alert); // Error: 404
//-----------------------------------------------------------------------------------------------
Fragments


Instead of returning Jsx wrapped by a div, you can wrap it with 

// React class
//...
return(
<React.Fragment>
    <p>hello</p>
    <p>hello</p>
</React.Fragment>
);
//...
//-----------------------------------------------------------------------------------------------
Es6 imports

import defaultExport from "module-name";
import * as name from "module-name";
import { export } from "module-name";
import { export as alias } from "module-name";
import { export1 , export2 } from "module-name";
import { export1 , export2 as alias2 , [...] } from "module-name";
import defaultExport, { export [ , [...] ] } from "module-name";
import defaultExport, * as name from "module-name";
import "module-name";
var promise = import(module-name);
//-----------------------------------------------------------------------------------------------

Test that props are not automatically passed to children components



//-----------------------------------------------------------------------------------------------
import ‘./foo.css’;

className=“foo” // from normal css

//-----------------------------------------------------------------------------------------------

Ducks

https://github.com/erikras/ducks-modular-redux

A file with:

Constants
Initial state
Reducers
Actions

//-----------------------------------------------------------------------------------------------

React


MyComponent.defaultProps={
    name: “Brian”,
    Age: 29
}


Use _myMethodName(){} in class

Input tag:  ref={ (input) => this.foo = input }

// code example
https://github.com/bradtraversy/projectmanager

https://github.com/bradtraversy/redux_crash_course




htmlFor

className

{this.props.foo}


let xs = this.props.foos.map(foo => {
    return (
        <Bar key={foo.id} foo={foo} />
    );
}


componentWillMount // when re-rendered, set variables here, declare variables in the constructor?

ref // for HTML input tags 

class Foo extends Component{
    static defaultProps = {};
}


 //————————————————————

fetch(url, options)
.then(res => res.json())
.then(data => console.log(data))


 //————————————————————
Redux

Reducers - return a new state? From switch statement

In our components class:
    - we don’t need the constructor, because we will not use the “this.state”
 

Actions - functions that call “dispatch” function

// in component class:
componentWillMount(){
    this.props.getMyUsers();
}

componentWillReceiveProps(nextProps){
    //...  nextProps.foo
}
 //————————————————————
Component -> action -> reducer

 //————————————————————
Constructor is to add a state to the component

To use props, you don’t need a constructor


 //————————————————————



 //————————————————————
Use the body of the render function too!!!

{foo.map( x => <li>{x}</li>)}






//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
