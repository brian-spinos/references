# Angular 2 (Start an app)

```bash
# use node 6.9.0
nvm use 6.9.0


# install the app generator
npm install -g @angular/cli


# create the app
ng new blog
cd blog
npm start 
# Go to http://localhost:4200


# tutorial
# https://angular.io/guide/quickstart
```


```
blog/src/main.ts # Angular entry point

blog/src/app/app.module.ts # Where other components are registered

# Component example:
blog/src/app/foos/foos.component.ts # The class
blog/src/app/foos/foos.component.html
blog/src/app/foos/foos.component.css
```


###### Structural directives

```html
<p *ngFor="let foo of foos">
    {{foo}}
</p>

<p *ngIf="1 > 0">
    {{foo}}
</p>
```

```html
<!-- property binding -->
<img [src]="user.image">

<!-- dynamic HTML classes -->
<div [class.foo]="1 === 1"></div>

<!-- Dont do this, it overwrites all HTML classes -->
<div [class]="user.name"></div>

<!-- Event binding -->
<button (click)="foo(bar, $event)">Hello</button>


<!-- two-way-binding -->
<input type="text" [value]="user.name"
                   (input)="user.name = $event.target.value">

<!-- two-way-binding -->
<input type="text" [(ngModel)]="user.name">

```

###### Pipes

```html
<p>{{name | uppercase }}</p>

<p>{{price | currency:'EUR':true }}</p>

<p>{{user | json}}</p>
```


###### Typescript class

```typescript
export class FooBar { // import { FooBar } from './foobar.component'; // to use this class in another file
	name = 'Brian';
	myArray = [1,2,3];

	// functions
	login(){
		let x = 10;

		for(let x of this.myArray){
			//...
		}

		//lambda:
		someFunc((a, b) => a + b);


		return x;
	}
}

```
