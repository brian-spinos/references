# create Angular App (any Node.js app)




#========= Steps

nvm use 8.0.0

ng new blog # first create the app, then add express!
cd blog
ng build --prod

# in .gitignore remove the '/dist', because we will need that folder

npm install --save express # not dev!


touch app.js # fill it in!
``````````````
var express = require('express');
var app = express();

app.set('port', (process.env.PORT || 5000));

app.use(express.static(__dirname + '/dist'));

app.get('*', function(request, response) {
  response.sendFile(__dirname + '/dist/index.html');
});

app.listen(app.get('port'), function() {
  console.log('Node app is running on port', app.get('port'));
});
``````````````





- package.json

``````````````
"scripts": {
    "start": "node app.js"
},


"engines": {
    "node": "8.0.0"
},
"main": "app.js"

````````````



heroku create spinos-angular4-test



touch Procfile
````````````
web: node app.js
````````````





#======= workflow
ng build --prod
git add .
gcm 'First commit'
git push heroku master
heroku open
heroku logs --tail











#========= debug:

heroku run bash
heroku run node


#========== commands
heroku apps
heroku local web
heroku open
heroku config:set FOO=123
heroku pg:psql
heroku apps:remove my-app-name
heroku apps:destroy my-app-name

git remote # list remotes
git remote remove origin


push existing app to heroku  # ???
heroku git:remote -a my-app-name # my-app-name.herokuapp.com










#================== add code to github:

- have 2 remotes: one for heroku, another for your git repo
    -> create repo in github!
    -> Go to command line
    -> heroku login
    -> git remote add origin git@github.com:brianspinos777/angular4test.git
    -> git push -u origin master


- how to create an app from an existing project?

