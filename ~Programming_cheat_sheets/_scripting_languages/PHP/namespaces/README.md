# PHP namespaces

- include/require a file
- and `use` the fully qualified namespace of the classes you brought in!

```php
<?php namespace app\user;

//...

require_once './helpers/Test.php'; 
use app\helpers\Test;


$test = new Test();
$test->foo();

//...

```
