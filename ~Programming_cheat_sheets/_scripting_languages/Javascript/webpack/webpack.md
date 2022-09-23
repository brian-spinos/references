# Webpack 2 tutorial

- You can use ES6 in webpack.config.js
- Webpack is composed of 'loaders' that are simple npm modules
- You apply loaders to a specific file
- Presets are like 'saved' configurations
- Youtube: 'webpack crash course'

###### Folder structure
```
- myApp/
    - node_modules/
    - dist/
    - src/
    - webpack.config.js
    - package.json
```

```bash
$ npm install -g webpack # -g to get 'webpack' executable

$ webpack app.js bundle.js --watch
```

```javascript
// index.js
let $ = require('jquery') // from npm
```



###### webpack.config.js example
```javascript
// webpack.config.js

const webpack = require('webpack')
const path = require('path')

module.exports = {
    devtool: 'source-map', // 'devtool' should be singular
    entry: [
        './src/index.js'
    ],
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'dist'),
        publicPath: '/dist', // That is the URL: http://localhost/dist
    },
    plugins: [
        new webpack.optimize.OccurenceOrderPlugin(),
        new webpack.NoErrorsPlugin()
    ],
    module: {
        loaders: [
            {
                test: /\.js$/,
                loader: 'babel-loader',
                exclude: /mode_modules/,
                query: {preset: ['es2015']}
            }
        ],
    }
};


```


-------

##### CommonJs

```javascript
// a way to import javascript files
var x = module.exports = {...}
require('./foo')
```


-------

###### Install react on Webpack

```bash
$ npm install -D react react-dom babel babel-preset-react babel-preset-es2015 babel-loader babel-core
```

```json
// .babelrc
{"presets": ["es2015", "react"]}
```

```javascript
// app.js
// WE NEED TO DO SOME STUFF HERE...
```


```javascript
// webpack.config.js

var ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: {...},
    output: {...},
    module: {
        rules: [
            {test: /\.js$/, exclude: /node_modules/, use: 'babel-loader'}
        ]
    },
}

```


###### Env variables
```javascript
process.env.NODE_ENV
```


-------

##### HotModuleReplacement

- SETUP IS HARD
- SEE DOCUMENTATION ON bootstrap-loader
- updates the browser without refreshing
- It does not work well with the 'ExtractTextPlugin'
- But the 'ExtractTextPlugin' SHOULD be used in production


```bash
$ npm install -D
bootstrap-loader
resolve-url-loader
url-loader
imports-loader
bootstrap-sass
jquery
css-loader
node-sass
sass-loader
style-loader


# "resolve-url-loader" and "url-loader" are dependencies of bootstrap-loader
```

-------

###### purifycss-webpack
```bash
# purifycss-webpack
# It removes unecessary CSS
# It is used with 'ExtractTextPlugin'
# It works with .js files also???
$ npm install -D purifycss-webpack
```

-------

###### html-webpack-plugin
```bash
# html-webpack-plugin
# Add it to the 'plugins' section in webpack.config.js
# as: [new HtmlWebpackPlugin()]
# It regenerates the html on the `dist` folder
$ npm install -D html-webpack-plugin

# -p is for 'production' ready minified code!
# It could be -d for 'development'
$ webpack app.js bundle.js -p --watch
```

```javascript
// webpack.config.js

var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    //...
}

```

-------

###### Loaders syntax

- Use the 'plugins' section and the 'module' section in webpack.config.js (there is new syntax?)


```javascript

// It is used to get CSS code generated,
// and adding them to a file
// var ExtractTextPlugin = require('extract-text-webpack-plugin')
```

```javascript
// webpack.config.js

var ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    //...
}

```

```bash
# extract-text-webpack-plugin
# Be careful with the version you use
# check this website for compatibility issues:
# webpack.js.org/guides/migrating
$ npm install -D extract-text-webpack-plugin
```

-------

###### webpack-dev-server

- It reloads the browser automatically when there are changes

```bash
$ npm install -D -g webpack-dev-server
```

```javascript
// package.json

//...
{
    "start": "webpack-dev-server --entry ./src/js/app.js --outputfile ./dist/bundle.js"
}
//...
```

```javascript
// webpack.config.js
devServer: {
    contentBase: path.join(__dirname, 'dist'),
    compress: true,
    port: 9000
}

// output.path: path.resolve(__dirname, 'dist')
```

-------

###### css-loader style-loader


```bash
# style-loader adds the <style> tag to html
$ npm install -D css-loader style-loader

# require('!style-loader!css-loader!./styles.css')

# ATTENTION: add !style-loader!css-loader! to the webpack.config.js instead!!!
```
-------

###### Babel loader


```bash
$ npm install -D
babel-core
babel-loader
babel-preset-es2015

# babel-preset-stage-0 # ???
```

```javascript
// webpack.config.js
//...
{
    test: /\.js$/, loader: 'babel-loader', exclude: /mode_modules/, query: {preset: ['es2015']}
}
//...
```

-------

###### File loader

```bash
# This loader handles files
# add the webpack.config.js configuration for it
$ npm install -D file-loader
```

##### Rimraf

```bash
# used to remove files (like the bundle.js file)
$ npm install -D rimraf
```

```javascript
// package.json
//...
{
    "build": "rimraf dist && webpack src/index.js dist/bundle.js --watch"
}
//...
```





# HotModuleReload

- It reloads the browser when you make changes
- There is a big boilerplate for dev-server
- There is also config code for index.js


# Presets

```json
// .babelrc
// You still need to set them in webpack.config.js

{
    "presets": ["es2015", "stage-0"]
}
```

-------

###### Debugger

- Use it on your code

```javascript
//...
debugger;
//...
```


###### See your code in the browser

```javascript
// webpack.config.js
//...
{
    devtool: 'source-map', // 'devtool' should be singular
    //...
}
//...


// restart webpack
```


-------

###### Minification

```javascript
// webpack.config.js
//...
{
    plugins: [
        new Webpack.optimize.UglifyJsPlugin({...}),
        //...
    ]
    //...
}
//...


// restart webpack
```
