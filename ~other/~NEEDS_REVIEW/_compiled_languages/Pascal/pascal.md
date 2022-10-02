# Pascal

- Online compiler: http://rextester.com/l/pascal_online_compiler

- Tutorial: https://www.tutorialspoint.com/pascal/index.htm


```pascal
// example.pas

// one line comment

(* one line comment *)

{*
    Multiple 
    line 
    comment
*}


program Brian; 

//=============================== Program global variables
var a, b: integer;
var c: integer;


var age, weekdays : integer;
    tax_rate, net_income : real;
    choice, is_ready : boolean;
    initials, grade : char;
    name, surname : string;


//=============================== Constants
const Foo = 'THIS_IS_FOO_CONSTANT';

//=============================== Pointers
var number: integer;
    number_p: ^integer;
    number_p_address: ^word;


//=============================== Records
type Book = record
    book_id: longint;
    title: packed array [1..50] of char;
    author: packed array [1..50] of char;
    subject: packed array [1..100] of char;
end;

var book1, book2: Book; (* Declare book1 and book2 of type Book *)


//=============================== Enums
type SUMMER = (April, May, June, July, September);


//=============================== A procedure example
// a procedure has no return *)
procedure myProcedure;
var x: integer;
begin
    // local variables
    x := 10;
    writeln('myProcedure: x is ', x);
end;


//=============================== A function returning the max between two numbers
function max(num1, num2: integer): integer;

// local variable declaration
var result: integer;

begin
    if (num1 > num2) then
        result := num1
   
    else
        result := num2;
    max := result;
end;


//=============================== Execution of a program
begin
    // actual initialization
    a := 10;
    b := 20;
    c := a + b;


    // Concatenation
    writeln('Value of', ' a = ', a , ', b = ', b, ' and c = ', c);
    writeln('Foo: ', Foo);
   

    writeln('max: ', max(20, 40));
   

    //========== Calling a procedure
    myProcedure();
   
   
    //========== Pointers START
    number := 100;
    writeln('Number is: ', number); // 100
    
    number_p := @number;
    writeln('number_p points to a value: ', number_p^); // 100
    
    number_p^ := 200;
    writeln('Number is: ', number); // 200
    writeln('number_p points to a value: ', number_p^); // 200
    
    
    number_p_address := addr(number_p);
    writeln('Address of number_p: ', number_p_address^); // 49072 (a random address)
   
   
    //========== Pointers END
   
   
    writeln('-----------------------------------------');
   

    //========== RECORDS START

    // book 1 specification
    book1.title := 'Some Book A';
    book1.author := 'Brian Smith'; 
    book1.subject := 'This is a book about something A';
    book1.book_id := 1000001;

    // book 2 specification
    book2.title := 'Some Book B';
    book2.author := 'Erich Bell';
    book2.subject := 'This is a book about something B';
    book2.book_id := 1000002;
 
    // Print book1 info
    writeln('Book 1 book_id : ', book1.book_id);
    writeln('Book 1 title : ', book1.title);
    writeln('Book 1 author : ', book1.author);
    writeln('Book 1 subject : ', book1.subject);
    writeln; 

    // Print book2 info
    writeln('Book 2 book_id : ', book2.book_id);
    writeln('Book 2 title : ', book2.title);
    writeln('Book 2 author : ', book2.author);
    writeln('Book 2 subject : ', book2.subject);
    writeln; 

    // ========== RECORDS END

   
end.


{* OUTPUT:

Value of a = 10, b = 20 and c = 30
Foo: THIS_IS_FOO_CONSTANT
max: 40
myProcedure: x is 10
Number is: 100
number_p points to a value: 100
Number is: 200
number_p points to a value: 200
Address of number_p: 49072
-----------------------------------------
Book 1 book_id : 1000001
Book 1 title : Some Book A
Book 1 author : Brian Smith
Book 1 subject : This is a book about something A

Book 2 book_id : 1000002
Book 2 title : Some Book B
Book 2 author : Erich Bell
Book 2 subject : This is a book about something B

*}


```
