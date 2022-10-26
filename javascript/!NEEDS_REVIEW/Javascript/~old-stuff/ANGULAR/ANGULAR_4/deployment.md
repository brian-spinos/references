# These steps are not tested yet... its just a guideline.


# Create an Angular app and deploy to Heroku

```bash
nvm use 8.0.0

# Heroku node app:
# It already has express
git clone https://github.com/heroku/node-js-getting-started.git
cd node-js-getting-started

heroku create my-heroku-app

cd .. # so we can have the 'blog' folder in the same level as index.js
ng new blog
cd blog
ng serve -o
ng build --prod

# in .gitignore remove the '/dist', because we will need that folder


git add .
gcm 'First commit'
git push heroku master
heroku open
heroku logs --tail
```

###### Procfile
```
web: node index.js
```

###### package.json

```json

{
  "name": "node-js-getting-started",
  "version": "0.2.6",
  "description": "A sample Node.js app using Express 4",
  "engines": {
    "node": "6.11.1"
  },
  "main": "index.js",
  "scripts": {
    "start": "node index.js",
    "test": "node test.js"
  },
  "dependencies": {
    "ejs": "2.5.6",
    "express": "4.15.2"
  },
  "devDependencies": {
    "request": "^2.81.0",
    "tape": "^4.7.0"
  },
  "repository": {
    "type": "git",
    "url": "https://github.com/heroku/node-js-getting-started"
  },
  "keywords": [
    "node",
    "heroku",
    "express"
  ],
  "license": "MIT"
}
```

###### index.js (express)
```javascript
var express = require('express');
var app = express();

app.set('port', (process.env.PORT || 5000));

app.use(express.static(__dirname + '/blog/dist'));

app.get('*', function(request, response) {
  response.sendFile(__dirname + '/blog/dist/index.html');
});

app.listen(app.get('port'), function() {
  console.log('Node app is running on port', app.get('port'));
});

```

###### ISSUES

- The .gitignore file was preventing the dist folder to be commited
- We needed to make the dist folder public on express.static so we can get css and js files!
