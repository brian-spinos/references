// $ gcc trie.c -o trie; ./trie

#import<stdio.h>
#import<stdlib.h>
#import<string.h>

#define ALPHABET_COUNT 26
#define TRUE 1
#define FALSE 0

//
// Structs
//

typedef struct Node {
  int isWord;
  struct Node * letters[ALPHABET_COUNT];
} Node;

typedef struct Trie {
  Node * root;
} Trie;

//
// functions
//

void printAnswer(int isWord, char * word){
  if (isWord == 1){
    printf("has %s? true\n", word);
  }else if (isWord == 0) {
    printf("has %s? false\n", word);
  }else{
    printf("INVALID RESULT\n");
  }
}

Node * createNode(){
  Node * node = (Node *)malloc(sizeof(Node));
  node->isWord = FALSE;
  for (int i=0;i<ALPHABET_COUNT;i++) {
    node->letters[i] = NULL;
  }

  return node;
}

Trie * createTrie(){
  Trie * trie = (Trie*)malloc(sizeof(Trie));
  trie->root = createNode();
  return trie;
}

void printNode(Node * node){
  printf("Node@%p{isWord=%d, array=[", node, node->isWord);

  for(int i=0;i<ALPHABET_COUNT;i++){
    if (node->letters[i] == NULL) {
      printf(". ");
    }else{
      printf("%p ", node->letters[i]);
    }
  }
  printf("]}\n\n");
}

void addWord(Trie * trie, char * word){
  Node * currentNode = trie->root;

  for (int i=0;i<strlen(word);i++) {
    // printNode(currentNode);
    int idx = word[i] - 'a';
    if (currentNode->letters[idx] == NULL) {
      currentNode->letters[idx] = createNode();
    }

    currentNode = currentNode->letters[idx];
  }
  currentNode->isWord = TRUE;
}

int hasWord(Trie * trie, char * word){
  Node * currentNode = trie->root;
  for (int i=0;i<strlen(word);i++) {
    //printNode(currentNode);
    int idx = word[i] - 'a';
    if (currentNode->letters[idx] == NULL) {
      printAnswer(currentNode->isWord, word);
      return FALSE;
    }
    currentNode = currentNode->letters[idx];
  }

  printAnswer(currentNode->isWord, word);
  return currentNode->isWord;
}

//
// Main function
//

int main(){
  Trie * trie = createTrie();
  //printNode(trie->root);

  addWord(trie, "brian");
  addWord(trie, "ana");
  addWord(trie, "erich");
  addWord(trie, "bia");
  addWord(trie, "rick");
  addWord(trie, "sandra");

  hasWord(trie, "sandra");
  hasWord(trie, "rick");
  hasWord(trie, "bia");
  hasWord(trie, "erich");
  hasWord(trie, "ana");
  hasWord(trie, "brian");


  hasWord(trie, "bria");
  hasWord(trie, "an");
  hasWord(trie, "dra");

  return 0;
}

/* OUTPUT

has sandra? true
has rick? true
has bia? true
has erich? true
has ana? true
has brian? true
has bria? false
has an? false
has dra? false

*/
