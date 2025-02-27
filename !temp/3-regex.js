let regex = /brian/;
let res = regex.test("hello brian spinos");
console.log(res);

"foo foo foo".match(/foo/g);
//  ['foo', 'foo', 'foo']

/*


/brian/gmi -- g is for multiple results, m is for multi-line,   i - case insensitive

[a-ZA-Z]

[0-9]

(cat|dog)


/./ -- represent any character


/(brian){3,5}/  -- between 3 and 5
/(brian){3,}/   -- 3 or more
/(brian){3}/    -- exactly 3


*/

/(foo)+/.test(""); // - 1 or more
/(foo)*/.test(""); // - zero or more
/(foo)?/.test(""); // - zero or 1

/*

\d - digit
\w - word
\s -space 

// negative versions:

\D
\W
\S


/^brian$/.test("brian")






//////////


/[a-z]/.test("hello brian spinos")



*/

// Lookahead and Lookbehind
// These are used to assert if a match is possible based on what's around the matched characters.

// Positive Lookahead X(?=   )
"1a 2b 3c".match(/\d(?=\D)/g); //  ['1', '2', '3']

// Negative Lookahead X(?!   )
"1-BAR 2-FOO 3-BAR".match(/\d(?!-FOO)/g); //  ['1', '3']

// Positive Lookbehind (?<=   )X
"a1 b2 c3".match(/(?<=\D)\d/g); //  ['1', '2', '3']

// Negative Lookbehind (?<!   )X
"BAR-1 FOO-2 BAR-3".match(/(?<!FOO-)\d/g); //  ['1', '3']
