# Phantomjs (like selenium, but no browser!)


```
To use Phantomjs with rails and capybara, use this gem:
https://github.com/teampoltergeist/poltergeist
```

http://phantomjs.org/quick-start.html



```bash

# Install
brew instal phantomjs


# Javascript console
phantomjs

# console.log("hello");
# phantom.exit()


# use a script!
phantomjs test.js 
```

###### test.js

```javascript
// test.js

var page = require('webpage').create();
var url = 'http://localhost:3000';

var callback = function(status) {
  var title;
  title = page.evaluate(function() {
    return document.title;
  });
  console.log("Title: " + title);
  return phantom.exit();
};

page.open(url, callback);
```
