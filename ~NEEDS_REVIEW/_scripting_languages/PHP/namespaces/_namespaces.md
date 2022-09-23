# PHP namespaces example


###### brian.php
```php
<?php

    namespace ddd\eee\fff;

    use aaa\bbb\ccc as abc;

    include 'erich.php';

    class User{
        function init($x){
            echo "<p>Hello B: $x</p>";
        }
    }

    // Works!
    $u = new User();
    $u->init(1);

    
    // Works!
    $u = new abc\User();
    $u->init(2);

?>
```

###### erich.php

```php
<?php 
    
    namespace aaa\bbb\ccc;

    class User{
        function init($x){
            echo "<p>Hello E: $x</p>";
        }
    }

    // Works!
    // $u = new User();
    // $u->init(0);

?>
```


###### Output

```
Hello B: 1

Hello E: 2
```
