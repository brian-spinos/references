# PHP namespaces


```php
<?php
    
    # index.php
    
    namespace Brian\App\Index;

    use Brian\App\Resources as Res; // $bar = new Res\Bar();

    include 'foo.php';
    include 'bar.php';

    echo "----------------- bar\n";
    $bar = new Res\Bar();
    //echo $bar->barPrivateField;      // fatal error
    //echo $bar->barProtectedField;    // fatal error
    echo $bar->barPublicField;
    //$bar->barPrivateMethod();        // fatal error
    //$bar->barProtectedMethod();    // fatal error
    $bar->barPublicMethod();
    echo "----------------- bar's parentStuff\n";
    $bar->parentStuff();
    echo "----------------- bar accessing Foo methods and fields\n";
    //$bar->fooPrivateMethod();     // fatal error
    //$bar->fooProtectedMethod();   // fatal error
    $bar->fooPublicMethod();
    // echo $bar->fooPrivateField; // Undefined
    // echo $bar->fooProtectedField; // Cannot access protected property
    echo $bar->fooPublicField;

?>
```


```php
<?php

# bar.php

namespace Brian\App\Resources;

class Bar extends Foo{

    private $barPrivateField = "Bar private field\n";
    protected $barProtectedField = "Bar protected field\n";
    public $barPublicField = "Bar public field\n";

    private function barPrivateMethod(){
        echo "Bar private function\n";
    }
    protected function barProtectedMethod(){
        echo "Bar protected function\n";
    }
    public function barPublicMethod(){
        echo "Bar public function\n";
    }
    // to be used outside the class definition (it needs to be public)
    public function parentStuff(){
        //echo $this->fooPrivateField;  // undefined
        echo $this->fooProtectedField;
        echo $this->fooPublicField;

        // $this->fooPrivateMethod();  // fatal error
        $this->fooProtectedMethod();
        $this->fooPublicMethod();
    }
}

?>
```

```php
<?php

# foo.php

namespace Brian\App\Resources;

class Foo {

    private $fooPrivateField = "Foo private field\n";
    protected $fooProtectedField = "Foo protected field\n";
    public $fooPublicField = "Foo public field\n";

    private function fooPrivateMethod(){
        echo "Foo private function\n";
    }
    protected function fooProtectedMethod(){
        echo "Foo protected function\n";
    }
    public function fooPublicMethod(){
        echo "Foo public function\n";
    }
}

?>
```
