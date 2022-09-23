# JSONP Example

###### Client

```html
<!DOCTYPE html>
<html>
    <body>
        <h1>JSONP Example</h1>


        <script>
            function foo(objFromServer) {
              console.log(objFromServer.name); // 'brian'
            }
        </script>

        <!-- 
        Make sure the server's response is valid javascript, 
        calling the client function, and passing in server data! 
        The response should look like this: 
            foo({name: 'brian'});
        -->
        <script src="http://example.com/cool_api?key=abc&callback=foo"></script>
        
    </body>
</html>
```

###### Server

```php
<?php
    // Server
    

    // You can check if the key is valid
    $key = $_GET['key']; 

    // The name of the function that the client is using
    $callback = $_GET['callback']; 
    
    // The response should be valid javascript, calling the client function, and passing in server data!
    echo $callback . "({name: 'brian'});"; // foo({name: 'brian'});
?>
```

