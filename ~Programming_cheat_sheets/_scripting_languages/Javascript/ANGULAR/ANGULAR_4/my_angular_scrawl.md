# My Angular Scrawl

###### Links

```
# simple angular implementation!
https://github.com/mgechev/light-angularjs


# github: seeschweiler/angular-redux
https://github.com/seeschweiler/angular-redux
```

https://jsonplaceholder.typicode.com/

http://lorempixel.com/400/200


###### Notes

- Github pages: good for front-end only apps, but you can still call your API, (so no server-side code on Github Pages)
- Angular-cli uses Jasmine for testing // describe(...), it(...)
- `ngModel` comes from the `FormsModule`
- When using `.subscribe()`, always use `.unsubscribe()`, to prevent memory leaks? 
- On Heroku, make Heroku compile the `src` folder when deploying, so we dont need to save the `dist` folder on github
- Where should we change routes? Not in the component?
- difference between `pipe` and `directive`: 
    - a pipe only manipulates data, a directive can manipulate the DOM (like adding an image, or making text bold)



###### HTML


```html
<a routerLink="/posts" routerLinkActive="my-active-css-class">Posts</a>

<a routerLink="/" routerLinkActive="active" [routerLinkActiveOptions]="{exact:true}">Home</a>

<a [routerLink]="['/posts', post.id]">Posts</a>

<a [routerLink]="['/posts', post.id]"  [queryParams]="{page: 123}">Posts</a>

```

```
- custom form validators
```


```html
<!-- a template form -->
<form #f="ngForm" (ngSubmit)="foo(f.value)">
    <input ngModel name="email" type="text">
    <input ngModel name="password" type="password">
</form>
```

```html
<input type="text" [(ngModel)]="myControllerVar">

<input type="text" ngModel name="myControllerVar">

<input type="text" ngModel name="firstName" #firstName="ngModel" >
```

```html
<div ngModelGroup="contact" #contact="ngModelGroup">
<div *ngIf="!contact.valid">...</div>
    ...
</div>


ngModel creates a FormControl object under the hood!
ngForm -> creates a FormGroup under the hood, and uses (ngSubmit)
```

```html
<p>{{ foobar | json }}</p>
```

```html
//ngValue if you need to use complex objects
// but we will not use it in this situation
<option *ngFor="let x os xs" [ngValue]="x.id">{{x.name}}</option>

// so use this:
<option *ngFor="let x os xs" [value]="x.id">{{x.name}}</option>
```

```html
<p *ngIf="foo.bar | async as x">{{x}}</p>


<!-- users$ is an observable! -->
{{ users$ | async as user }}

<!--
- Why/How does the `async` pipe prevent memory leaks?
    - because we dont use `.subscribe()` ???
-->
```

```html
<form [formGroup]="myForm">
```

```html
{{ foo | mySummary:10 }}
```

```html
<div *ngIf="username.pending">Loading...</div> // for async validator stuff
```

```html
<!-- 
ng-template 

for *ngIf results:


<div *ngIf="num > 0; then foo else bar"></div>
<ng-template #foo>FOO</ng-template>
<ng-template #bar>BAR</ng-template>

-->
```


```html
<!-- 
ng-container 

whatever is between these tags will appear in the DOM, 
but the `ng-container` tags will never appear in the DOM, e.g.:

<ng-container>
    <p>Hello, this is some content</p>
</ng-container>


will display:

<p>Hello, this is some content</p>

-->
```

```html
<!-- 
ng-content 

it is to be used inside you component's html nas a placeholder for 
the content that will be between your component's HTML tags, 
you can have multiple place holders, e.g.:

Inside your component's html:

<h1>This is the Foo component</h1>
<ng-content select=".bar"></ng-content>
<ng-content select=".baz"></ng-content>

Using your component:
<foo>
    <ng-container class=".bar">Hello, this is some content</ng-container>
    <ng-container class=".baz">More content here</ng-container>
</foo>

-->
```



```html
<div [ngClass]="{'foo-bar': baz, 'x-y-z': qux}"></div>

<div [ngStyle]="{'backgroundColor': foo, 'color': bar}"></div>

<div [class.active]="isActive">Hello</div>
<div [attr.active]="isActive">Hello</div>
<div [style.active]="isActive">Hello</div>
```

```html
<!-- optional operator -->

<!-- You dont need to use *ngIf just for waiting for a variable to be available -->
<div>{{ foo.bar?.baz }}</div>
```

###### Bash

```bash
# custom angular directive

$ ng g d foo-bar

```

```bash
$ ng g p pipes/my-summary/my-summary 
```

```bash
npm i stripe # payments
```


```bash
$ ng lint # lints the syntax of your code
$ ng lint --fix # it will fix easy syntax errors!
```

```bash
# you can add an environment like 'test', or 'myOwnEnvironment' (just like: 'development', 'production') 
```

```bash
$ npm install ng-bootstrap # for twitter-bootstrap's javascript code
```

###### Typescript

```typescript
this.activatedRoute.paramMap // ActivatedRoute
.subscribe((params) => {
    console.log(params) // an object representing the URL
    console.log(+params.get('id')) // /users/123 -->  123 // the `+` is to cast string to integer!
})

this.activatedRoute.queryParamMap.subscribe()


// this.activatedRoute.snapshot.paramMap.get('id') // does not get new values???
// this.activatedRoute.snapshot.queryParamMap.get('page')  // does not get new values???
```

```typescript
"123" === 123 // false
+"123" === 123 // true

d = new Date() // Tue Sep 26 2017 15:35:19 GMT-0700 (US Mountain Standard Time)
+d             // 1506465319859
```

```
Angular - Promise vs Observable

A Promise handles a single event when an async operation completes or fails.

Note: There are Promise libraries out there that support cancellation, but ES6 Promise doesn't so far.

An Observable is like a Stream (in many languages) and allows to pass zero or more events where the callback is called for each event.

Often Observable is preferred over Promise because it provides the features of Promise and more. With Observable it doesn't matter if you want to handle 0, 1, or multiple events. You can utilize the same API in each case.

Observable also has the advantage over Promise to be cancelable

Observable provides operators like map, forEach, reduce, ... similar to an array
```


```typescript
//combine observables:

import { Observable } from 'rxjs/Observable'
import 'rxjs/add/observable/combineLatest'
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/switchMap'


let obs = Observable.combineLatest([
    this.activatedRoute.paramMap,
    this.activatedRoute.queryParamMap
])

//
// `.switchMap` lets you switch to another observable (so you can daisy-chain)
// ATTENTION: If you use .switchMap with the `async` pipe, you will create an infinite loop...
//
// `.map` modifies the content of the observable (so it does not return an observable?)
//
// this is how to return a null observer: `return Observable.of(null)`
//
//
obs.switchMap((combined) => { // not .map, because it does not return what we need
    let paramMap = combined[0]
    let queryParamMap = combined[1]

    let id = paramMap.get('id')
    let page = queryParamMap.get('page')

    return this.myService.getUser()
})
.subscribe((user) => {
    
})
```

```typescript
this.router.navigate(['/'])

// url with query params:
this.router.navigate(['/users', 123], {
    queryParams: {
        page: 123, foo: 'bar'
    }
}) // Router

```
```
 POST /api/authenticate - return a JWT

save JWT on localStorage

- send the JWT on the headders of our other http requests

- HTTP status code:  http://www.restapitutorial.com/httpstatuscodes.html

this.http.post('/api/auth', JSON.stringify(credentials))


<p (click)="authService.logout()">logout</p> // to log out, delete the token from localStorage

- check if the token has expired in the client, if so, destroy the token!

$ npm i angular2-jwt
import {JwtHelper} from 'angular2-jwt'

$ npm install jsonwebtoken # for express


- for role based views, you can check if the current user has that role
```

```typescript
let myString:string = "aaa"
myString.endsWith(" world")
```

```typescript
import {Http, RequestOptions, Headers } from '@angular/http'

let headers = new Headers()
headers.append('Authorization', 'Bearer sdjfhksdfhksjdf')

let options = RequestOptions({headers: headers})

this.http.get('/api/foo', options)
```

```typescript
type Role = 'guest' | 'basic' | 'admin'  // union
let role:Role = 'guest'
```

```
sublime snippets:  tools > Developer > new snippet

source.js
text.html
save as: fooBar.sublime-snippet
$1 $2

to find the files:  sublime text > preferences > browse packages... > User
// /Users/brianspinos777/Library/Application\ Support/Sublime\ Text\ 3/Packages/User/brianBootstrapRow.sublime-snippet 

```

```typescript
import { Component, Pipe, PipeTransform} from '@angular/core'
import { Http, RequestOptions, Headers } from '@angular/http'
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { Router } from '@angular/router';
```

```typescript
// properties in typescript:
// - like fields from the outside, but methods inside
// Foo.name 

get name(){
    return 'brian'
}

set name(n){
    this.name = n
}
```

```typescript
let actalLimit = (args) ? args : 50

if(!value)
    return null

return value.substr(0, actalLimit) + '...'
```

```typescript
@select(state => state.foo.bar) bar; // reach nested objects in the state
```

```typescript
let myObj = {baz: 3}
myObj.set({foo:1, bar:2})
```

```typescript
(<string>foo).endsWith('a')
(foo as string).endsWith('a')
```
