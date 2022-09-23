- Tutorial: https://www.tutorialspoint.com/fsharp/fsharp_basic_io.htm
- Online compiler: https://repl.it/languages/fsharp

```fsharp
//
// F#
//


open System
Console.Write("What's your name?\n")
Console.Write("Hello, {0}\n", "Brian\n")
Console.Write("\n\n\n")


printf "This is like a print in ruby"
printf "... see?"
printfn ""
printfn "This is only one line!"
printfn "interpolation: %s" "myString"


type Options = 
    | Foo
    | Bar
    | Baz
    
    
let dude = "Brian"
let x = 10
let mutable y = 10
let items = [1..5]
List.append items [6]



let myFunction a b =
    a + " " + b
    
myFunction "1" "2"


let names = ["Brian"; "Erich"]


printfn "\n\n\n"


//-------- types


type User = {
    Name: string
    Address: string
}


type Company = {
    President: User
}


let user = { 
    Name = "Brian"; 
    Address = "123 foobar"
}


printfn "user name: %A \n" user.Name


printfn "myFunction result: %A" (myFunction "1" "2")

```
