# SASS


- its like a programing language, with arrays, variables, functions, mixins!
- its extention is -> .scss (new extension)
- it has another syntax -> .sass (old extension) (no curly braces, no semicolons!)



###### Variables

```scss
// remember that variables have scope!, BUT you can overrite the global variable
$main: #444; 

.foo{
    color: $main;

    h2 {
        font-size: 3em;
    }
}

```

###### Interpolation

```scss

$side: top; 

sup {
    #{$side}: -0.5em;
}

.foo-#{$side} {
    background: #777;
}

```




###### @import
```scss
// it imports a file!
@import "buttons";  // import the _buttons.scss  // its a partial!


```


###### Nesting

```scss

.foo{
    color: #444;

    h2 {
        font-size: 3em;
    }
}

```




```scss
text: {
    decoration: underline;    // same as text-*
}

```





```scss

&.foo {    // & referes to the parent
    
}



.sidebar {
    float: right;
    .users & {        //  & referes to the .sidebar
        width: 200px;
    }
}

```



###### Mixins

```scss
// its like a ruby module/function! for properties! and it accepts arguments and a optional default value! awesome!

@mixin button {     // @mixin block should be declared before the @include statement, so put them in the top!
    color: #444;
}


@mixin box-sizing($x: border-box){
    -webkit-box-sizing: $x;
    -moz-box-sizing: $x;
    box-sizing: $x;
}

.btn-a {
    @include button;
    @include box-sizing(content-box);
    background: #eee;
}

.btn-b {
    @include button;
    @include box-sizing;
    background: #eee;
}

```



###### @extend

```scss

// copies in a css declaration ???

.content{
    border: 1px;
}

.foo {
    @extend .content;
    background: #ddd;
}

```

