#import<iostream>
using namespace std;

//
// ---------------------------------------------- Comments
// $ g++ hash.cpp -o hash; ./hash;
//

//
// ---------------------------------------------- Hash
//

class Entry {
private:
  string key;
  string value;
  Entry * next;
public:
  Entry(string key, string value);
  ~Entry(void);
  Entry * getNext(void);
  void setNext(Entry * entry);
  string getValue(void);
  void setValue(string value);
  string getKey(void);
  void setKey(string key);
  void print(void);
};

Entry::Entry(string key, string value){
  // cout << "Creating Entry" << endl;
  this->key = key;
  this->value = value;
}

Entry::~Entry(void){
  cout << "Deleting Entry" << endl;
}

Entry * Entry::getNext(void){
  return next;
}

void Entry::setNext(Entry * node){
  next = node;
}

string Entry::getValue(void){
  return value;
}

void Entry::setValue(string value){
  this->value = value;
}

string Entry::getKey(void){
  return key;
}

void Entry::print(void){
  cout
  << "Entry@" << this
  << "{key=" << key
  << ", value=" << value
  << ", next=" << next
  << "}" << endl;
}

//
// ---------------------------------------------- Hash
//

class Hash {
private:
  int size;
  int capacity;
  Entry ** array;
public:
  Hash(int capacity);
  ~Hash(void);
  void put(string key, string value);
  string * get(string key);
  int calculateHash(string key);
  void print(void);
};

Hash::Hash(int capacity){
  // cout << "Creating Hash" << endl;
  this->size = 0;
  this->capacity = capacity;
  this->array = new Entry*[capacity];
}

Hash::~Hash(void){
  cout << "Deleting Hash" << endl;
}

void Hash::put(string key, string value){
  Entry * entry = new Entry(key, value);

  int hash = calculateHash(key);
  int index = hash % capacity;
  // cout
  // << "hash: " << hash
  // << ", index: " << index
  // << endl;

  if (array[index] == NULL) {
    array[index] = entry;
    size++;
    return;
  }

  Entry * curr = array[index];
  Entry * prev = NULL;
  while(curr != NULL){
    // curr->print();
    if (curr->getKey() == key) { // update existing entry
      curr->setValue(value);
      // size++; // update is not +1
      return;
    }
    prev = curr;
    curr = curr->getNext();
  }

  // curr will be NULL here (end of linked-list)
  prev->setNext(entry);
  size++;
}

// Returning string *, because we cannot return NULL, for string
string * Hash::get(string key){
  // cout << "--------------------- Hash::get called..." << endl;
  int hash = calculateHash(key);
  int index = hash % capacity;

  if (array[index] == NULL) {
    cout << "KEY_NOT_FOUND: " << key << endl;
    return NULL;
  }

  Entry * curr = array[index];
  Entry * prev = NULL;

  while(curr != NULL){
    if (curr->getKey() == key) {
      cout << "GOT: " << curr->getValue() << endl;
      // curr->print();
      return new string(curr->getValue());
    }
    prev = curr;
    curr = curr->getNext();
  }

  return NULL;
}

void Hash::print(void){
  cout
  << "Hash@" << this
  << "{size=" << size
  << ", capacity=" << capacity
  << ", array=[" << endl;

  for(int i=0;i<capacity;i++){
    Entry * curr = array[i];
    cout << "  " << i << " -> ";
    while (curr != NULL) {
      cout
      << curr->getKey()
      << "(" << curr->getValue() << ")"
      << " -> ";
      curr = curr->getNext();
    }
    cout << endl;
  }

  cout << "]}" << endl;
}

//
// ---------------------------------------------- Helpers
//

int Hash::calculateHash(string key){
  int hash = 0;
  for(int i=0;i<key.length();i++){
    hash += key[i];
    hash *= 31;
  }
  if (hash < 0) hash *= -1; // ensure positive index
  // cout << "hash: " << hash << endl;
  return hash;
}

//
// ---------------------------------------------- Main function
//

int main(void){
  Hash * hash = new Hash(20);
  hash->put("firstName", "brian");
  hash->put("lastName", "spinos");
  hash->put("city", "phoenix");
  hash->put("state", "AZ");
  hash->put("country", "USA");
  hash->put("address", "123 foobar st");
  hash->put("age", "32");

  hash->get("firstName");
  hash->get("lastName");
  hash->get("city");
  hash->get("state");
  hash->get("country");
  hash->get("address");
  hash->get("age");

  hash->get("unknown-key-1");
  hash->get("unknown-key-2");

  hash->print();

  return 0;
}

/* OUTPUT

GOT: brian
GOT: spinos
GOT: phoenix
GOT: AZ
GOT: USA
GOT: 123 foobar st
GOT: 32
KEY_NOT_FOUND: unknown-key-1
KEY_NOT_FOUND: unknown-key-2
Hash@0x7f9c6ec01700{size=7, capacity=20, array=[
  0 ->
  1 -> city(phoenix) -> state(AZ) -> age(32) ->
  2 ->
  3 ->
  4 ->
  5 ->
  6 ->
  7 ->
  8 ->
  9 ->
  10 -> country(USA) ->
  11 -> firstName(brian) ->
  12 ->
  13 ->
  14 ->
  15 ->
  16 -> address(123 foobar st) ->
  17 ->
  18 ->
  19 -> lastName(spinos) ->
]}

*/
