# OOP


```c++
#include <iostream>
#include <string> // so we can use std::string
using namespace std; // so we dont need to prefix std::string, std::cout

//
// Human class
//

class Human {
  public:
    string name;
    int age;
    Human(string name, int age);
    string eat(string food);
  private:
  protected:
};

Human::Human(string name, int age){
  this->name = name;
  this->age = age;
}


string Human::eat(string food){
  return "Human eating " + food + "!";
}

//
// Person class
//

class Person : public Human {
  public:
    string address;
    int ssn;
    Person * friendd;
    Person(string name, int age, string address, int ssn);
    string getAddress();
    void addFriend(Person * f);
  private:
  protected:
};

Person::Person(string name, int age, string address, int ssn) : Human(name, age){
  this->name = name;
  this->age = age;
  this->address = address;
  this->ssn = ssn;
}

string Person::getAddress(){
  return "Person address: " + this->address + "!";
}

void Person::addFriend(Person * f){
  this->friendd = f;
}

//
// Main function
//

int main() {
  cout << "Hello World!";
  cout << endl;

  //Human instance (base class)
  Human * brian = new Human("spinos", 30);
  cout << "Human name: " << brian->name << endl;
  cout << "Human age: " << brian->age << endl;
  cout << endl;

  //Person instance (derived class)
  Person * erich = new Person("erich", 28, "123 foobar st", 1234567890);
  cout << "Person address: " << erich->address << endl;
  cout << "Person ssn: " << erich->ssn << endl;
  cout << "Person name: " << erich->name << endl;
  cout << "Person age: " << erich->age << endl;
  cout << "erich (Person): " << erich->eat("pizza") << endl;
  cout << "erich (Person): " << erich->getAddress() << endl;

  // adding relationships
  Person * rick = new Person("richard", 60, "456 foobar st", 987654321);
  erich->addFriend(rick);
  cout << "friend: " << erich->friendd->name << endl;

  // return 0; // optional ?
}

/*
================= RETURN:
Hello World!
Human name: spinos
Human age: 30

Person address: 123 foobar st
Person ssn: 1234567890
Person name: erich
Person age: 28
erich (Person): Human eating pizza!
erich (Person): Person address: 123 foobar st!
friend: richard
*/

```
