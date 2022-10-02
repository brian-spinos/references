# PHP OOP


```php

//----------------------------------------------------- Abstract classes
abstract class Foobar {
  abstract function funcD($d);
  abstract function funcE($e);
  public function funcF($f){
    echo $f;
  }
}

//----------------------------------------------------- Interfaces
interface IBaz { 
  public function funcX($x);
  public function funcY($y);
  public function funcZ($z);
}

//----------------------------------------------------- Base class
class Foo {
  private $fieldA = "AAA";
  protected $fieldB = "BBB";
  public $fieldC = "CCC";
  
  public function __constructor($f1, $f2, $f3){
    $this->fieldA = $f1;
    $this->fieldB = $f2;
    $this->fieldC = $f3;
  }

  private function funcA(){
    echo "funcA";
  }
  protected function funcB(){
    echo "funcB";
  }
  public function funcC(){
    echo "funcC";
  }
}

//----------------------------------------------------- Derived class
class Bar extends Foo implements IBaz{
  private $fieldAA = "AAA_2";
  protected $fieldBB = "BBB_2";
  public $fieldCC = "CCC_2";
  
  public static $count = 0;
  
  public static function doCount(){
    echo "doCount() called";
  }
  
  public function __constructor($f1, $f2, $f3){
    
    parent(111, 222, 333);
    
    $this->fieldAA = $f1;
    $this->fieldBB = $f2;
    $this->fieldCC = $f3;
  }

  private function funcAA(){
    echo "funcAA";
  }
  protected function funcBB(){
    echo "funcBB";
  }
  public function funcCC(){
    echo "funcCC";
  }
  
  //---
  
  public function funcX($x){
    echo $x;
  }
  public function funcY($y){
    echo $y;
  }
  public function funcZ($z){
    echo $z;
  }
}

//-----------------------------------------------------
function puts($val){
  echo "\n";
  echo $val;
  echo "\n";
}

//-----------------------------------------------------
$foo = new Foo("aaa_f", "bbb_f");
puts($foo->fieldC);
puts($foo->fieldC);
puts($foo->funcC());


$bar = new Bar("aaa", "bbb");

puts($bar::$count); // 0
puts($bar::doCount()); // doCount() called

puts($bar->fieldC);
puts($bar->fieldCC);

puts($bar->funcC());
puts($bar->funcCC());

```
