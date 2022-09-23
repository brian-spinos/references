# Webworker

```bash
$ node server.js

# Go to http://localhost:12345/
```


###### client.html
```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>

    <h2>Web Worker Test!</h2>

    <button onclick="startWorker()">Start Worker</button>
    <button onclick="stopWorker()">Stop Worker</button>

    <p><strong>Note:</strong> Internet Explorer 9 and earlier versions do not support Web Workers.</p>


    <script>
      let w;

      const startWorker = () => {
        if (window.Worker) {
          w = new Worker('worker.js');
          let msg = {
            instruction: 'do-this',
            data: {
              foo: 123,
              bar: 456,
            }
          };

          // send message to our worker:
          w.postMessage(msg);

          // receive data from worker:
          w.onmessage = (e) => {
            console.log(`=======> MESSAGE_RECEIVED_FROM_WORKER: ${e.data}`);
          };


        }
      };

      const stopWorker = () => {
        if (w) {
          // To terminate the worker
          w.terminate();
          w = undefined; // Not sure we need this step...
          console.log('=======> WORKER_TERMINATED');
        } else {
          console.log('=======> NO_WORKER_INSTANTIATED');
        }
      };

      console.log('=======> INSIDE_CLIENT');
    </script>


  </body>
</html>

```

###### worker.js
```js
/**
 * We do not have access to:
 * - window object
 * - document object
 * - parent object
 *
 * But, it has access to:
 * - navigator object (useragent, geolocation, cookeyenabled ?)
 * - location (host, hostname, href, pathname, etc.)
 * - XMLHttpRequest (for ajax calls)
 * - setTimeout, clearTimeout setInterval, clearInterval
 * - Application cache
 * - importScripts()
 * - spawning other webworkers
 */

// Receive message from client
this.onmessage = (e) => {
  if(e.data.instruction === 'do-this'){

    // Send message to client
    this.postMessage('Hello from worker! do-this received in worked');
  }
};

console.log('=======> WORKER_INSTANTIATED');

// Checkout what is available to the worker:
console.log('THIS: ', this);

```

###### server.js
```js
const express = require('express')
const app = express()
const port = 12345

app.get('/', function(request, response){
  response.sendFile(__dirname + '/client.html');
});

app.get('/worker.js', function(request, response){
  response.sendFile(__dirname + '/worker.js');
});

app.listen(port, (err) => {
  if (err) {
    return console.log('something bad happened', err)
  }
  console.log(`server is listening on ${port}`)
})

```
