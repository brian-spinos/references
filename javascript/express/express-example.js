/*

$ mkdir MyApp
$ cd MyApp/
$ npm init -y
$ npm i express cors
$ mkdir -p src/main
$ node src/main/app.js
// go to // http://localhost:5000/

*/

//
//=======================================================================================
//

const express = require("express");
const cors = require("cors");

const app = express();
const PORT = 5000;

app.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`);
});

// // or you could use 'body-parser' library
app.use(express.json()); // middleware to parse json payloads, makes `req.body` available !!!

// // middleware to enable CORS ' Cross-Origin Resource Sharing', so we can serve a client in another DOMAIN
// // adds the necessary HTTP headers to the server's responses to inform the client's web browser that cross-origin requests are allowed.
// // Enable CORS for all routes and HTTP methods
app.use(
  cors({
    origin: "https://example.com", // allow only this as a client
  })
);

// // middleware to parse incoming requests with URL-encoded payloads
// // from HTML FORMS ???
// // example: name=John+Doe&age=25&email=johndoe%40example.com
// // 'Content-Type' : 'application/x-www-form-urlencoded'
// // makes it available in `req.body` as JSON
app.use(express.urlencoded({ extended: false }));

//
//======================================================================================= middleware
//

// app.use((req, res, next) => {
//   //... logic
//   console.log("====================================== HELLO FROM MIDDLEWARE");
//   //   console.log(`req: ${JSON.stringify(req)}`);
//   //   console.log(`res: ${JSON.stringify(res)}`);
//   next();
// });

// Custom middleware function
const myMiddleware = (req, res, next) => {
  //... logic
  console.log("====================================== HELLO FROM MIDDLEWARE");
  console.log(`req: ${JSON.stringify(req.params)}`);
  //   console.log(`res: ${JSON.stringify(res)}`);
  console.log("\n");
  next();
};

app.use(myMiddleware);

//
//=======================================================================================
//

// http://localhost:5000/
app.get("/", (req, res) => {
  res.send("Hello World! 444");
});

// http://localhost:5000/foo/123
app.get("/foo/:id", (req, res, next) => {
  //   req.params.id; // parseInt

  // .filter // ????
  let data = { foo: "bar" };
  res.status(200).json(data);
  // res.send('some text');
});

// app.post(); // req.body.userName
// router.put(fn);
// router.delete(fn);

//
//=======================================================================================
//

const router = express.Router();

// router.get(fn);
// router.post(fn);
// router.put(fn);
// router.delete(fn);

// http://localhost:5000/users/students/123
router.get("/students/:id", (req, res, next) => {
  //   req.params.id; // parseInt

  // .filter // ????
  let data = { message: "users/students/123" };
  res.status(200).json(data);
  // res.send('some text');
});

// module.exports = router; // const router = require('./router') // file: './router.js'
app.use("/users", router);
