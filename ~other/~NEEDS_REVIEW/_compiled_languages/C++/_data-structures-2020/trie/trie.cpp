#import <iostream>
#import <string>
#import <cstring>
using namespace std;

/*
 * ------------------------------------------------ Comments
 * $ g++ trie.cpp -o trie; ./trie
 */

/*
 * ------------------------------------------------ Trie
 */
class Node {
private:
  Node ** letters;
  bool isWord;
public:
  Node(void);
  ~Node(void);
  void print(void);
  Node ** getLetters(void);
  void setIsWord(bool b);
  bool getIsWord(void);
};

Node::Node(void){
  // cout << "Creating new Node" << endl;

  // Fix issue of array having unexpected values:
  this->letters = new Node*[26];

  // THIS DID NOT WORK (TO GET AN ARRAY OF NULL)
  // Node * arr[26] = {0};
  // for(int i=0;i<26;i++){
  //   arr[i] = NULL;
  //   cout << "NULL? " << arr[i] << endl;
  // }
  // this->letters = arr;
}

Node::~Node(void){
  cout << "Deleting Node" << endl;
}

void Node::print(void){
  cout << "Node@" << this
  << "{isWord=" << this->isWord
  << ", letters=[" << endl;
  for(int i=0;i<26;i++){
    if (this->letters[i] == NULL) {
      cout << "    ." << endl;
      // cout << "    (NULL) Node@" << this->letters[i] << endl;
    }else{
      cout << "    Node@" << this->letters[i] << endl;
    }
  }

  cout << "]}" << endl;
}

Node ** Node::getLetters(void){
  return this->letters;
}

void Node::setIsWord(bool b){
  this->isWord = b;
}

bool Node::getIsWord(void){
  return this->isWord;
}

/*
 * ------------------------------------------------ Trie
 */

class Trie {
private:
  Node * root;
  // TODO: size
public:
  Trie(void);
  ~Trie(void);
  void add(string s);
  bool has(string s);
  void print(void);
  Node * getRoot(void);
};

Trie::Trie(void){
  // cout << "Creating new Trie" << endl;
  this->root = new Node();
}

Trie::~Trie(void){
  cout << "Deleting Trie" << endl;
}

void Trie::add(string s){
  // cout << "Trie::add called..." << endl;
  Node * curr = this->root;
  for(int i=0; i<s.size();i++){
    int intValue = (int)(s[i]) - 'a';
    if (curr->getLetters()[intValue] == NULL) {
      curr->getLetters()[intValue] = new Node();
    }
    curr = curr->getLetters()[intValue];
  }
  curr->setIsWord(true);
}

bool Trie::has(string s){
  // cout << "Trie::has called..." << endl;
  Node * curr = this->root;
  for(int i=0; i<s.size();i++){
    int intValue = (int)(s[i]) - 'a';
    if (curr->getLetters()[intValue] == NULL) {
      cout << "found " << s << "? " << false << endl;
      return false;
    }
    curr = curr->getLetters()[intValue];
  }
  cout << "found " << s << "? " << curr->getIsWord()  << endl;
  return curr->getIsWord();
}

void Trie::print(void){
  //TODO
  /*

a
  b
    c
      d
        e
b
  r
    i
      a
        n

c
d
e
f
g
// or list of words...

  */
}

Node * Trie::getRoot(void){
  return this->root;
}

/*
 * ------------------------------------------------ Main function
 */

int main(void){
  Trie * trie = new Trie();
  trie->add("brian");
  trie->add("erich");
  trie->add("ana");
  trie->add("bia");
  trie->add("rick");
  trie->add("sandra");

  trie->has("brian");
  trie->has("erich");
  trie->has("ana");
  trie->has("bia");
  trie->has("rick");
  trie->has("sandra");


  trie->has("bria");
  trie->has("briana");
  trie->has("an");
  trie->has("anna");
  trie->has("eric");
  trie->has("erick");
  trie->has("ric");
  trie->has("richard");
  trie->has("sondra");
  trie->has("sandraa");
  return 0;
}

/* OUTPUT

found brian? 1
found erich? 1
found ana? 1
found bia? 1
found rick? 1
found sandra? 1
found bria? 0
found briana? 0
found an? 0
found anna? 0
found eric? 0
found erick? 0
found ric? 0
found richard? 0
found sondra? 0
found sandraa? 0

*/
