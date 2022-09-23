#include <iostream>
#include <string>
using namespace std;


//
// Animal (base class)
//

class Animal {
  public:
    string name;
    int age;
    
    Animal(string name, int age);
    
    string eat(string food);

  private:
    
  protected:
    
};


Animal::Animal(string name, int age){
  this->name = name;
  this->age = age;
}


string Animal::eat(string food){
  return "Eating " + food + "!";
}


//
// Bird (derived class)
//

class Bird: public Animal {
  public:
    string name = "Unknown";
    
    Bird(string name);
    
  private:

  protected:
};

//
// Derived class constructor (including base class constructor)
//
Bird::Bird(string name) : Animal(name, 28){
  this->name = name;
}


//
// main
//


int main(void){
  
  Bird * bird = new Bird("Jasper");
  cout << "Bird name: " << bird->name << endl;
  cout << "Bird age: " <<bird->age << endl;
  cout << "Bird eat: " << bird->eat("seeds") << endl;
  
  return 0;
}


/* Output:

Bird name: Jasper
Bird age: 28
Bird eat: Eating seeds!

*/

