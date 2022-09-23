// Node.js Buffers

var buffer1 = Buffer.from("ABCDEF"); // <Buffer 41 42 43 44 45 46>
var buffer2 = Buffer.from("GHIJKL"); // <Buffer 47 48 49 4a 4b 4c>

buffer1.write("123"); // starts write from index ZERO

buffer1.toString(); // '123DEF'


buffer1.toJSON() // { type: 'Buffer', data: [ 49, 50, 51, 68, 69, 70 ] }

var buffer3 = Buffer.concat([buffer1, buffer2])

buffer3.toString(); // '123DEFGHIJKL'

buffer1.compare(buffer2); // -1

buffer1.copy(buffer2) // buffer2 is overwritten

buffer1.length // 6
