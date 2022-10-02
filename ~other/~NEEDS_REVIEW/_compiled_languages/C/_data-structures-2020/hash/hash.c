//
// Hash
//

//
// TODO: resize hash
//

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define TRUE 1
#define FALSE 0

//
// Structs
//

typedef struct Entry {
  char * key;
  char * value;
  struct Entry * next;
} Entry;

typedef struct Hash {
  int size;
  int capacity;
  Entry ** array; // array of Entry pointers
} Hash;

//
// Functions
//

Entry * createEntry(char * key, char * value){
  Entry * entry = (Entry *)malloc(sizeof(Entry));
  entry->key = key;
  entry->value = value;
  entry->next = NULL;
  return entry;
}

void printEntry(Entry * entry){
  if (entry == NULL) {
    printf("NULL\n");
    return;
  }
  if (entry->next == NULL) {
    printf("Entry@%p{key=%s, value=%s, next=NULL}\n", entry, entry->key, entry->value);
  }else{
    printf("Entry@%p{key=%s, value=%s, next=%p}\n", entry, entry->key, entry->value, entry->next);
  }
}

// No new line
void printEntry2(Entry * entry){
  if (entry == NULL) {
    printf("NULL");
    return;
  }
  if (entry->next == NULL) {
    printf("Entry@%p{key=%s, value=%s, next=NULL}", entry, entry->key, entry->value);
  }else{
    printf("Entry@%p{key=%s, value=%s, next=%p}", entry, entry->key, entry->value, entry->next);
  }
}

// after 75% filled, increase capacity 50% of capacity
// iterate through array, and linked lists, and copy one by one to new array
Hash * createHash(int capacity){
  Hash * hash = (Hash *)malloc(sizeof(Hash));
  hash->array = (Entry **)malloc(sizeof(Entry*) * capacity);
  hash->size = 0;
  hash->capacity = capacity;
  return hash;
}

void printHash(Hash * hash){
  printf("Hash@%p{size=%d, capacity=%d, array=[\n", hash, hash->size, hash->capacity);

  Entry * currentEntry = NULL;
  for(int i=0;i<hash->capacity;i++){
    currentEntry = hash->array[i];

    printf("  [%d] -> ", i);
    printEntry2(currentEntry);

    if(currentEntry != NULL)
    currentEntry = currentEntry->next;

    /**
     * printing linked list starting from second element (could be NULL)
     */
    while(currentEntry != NULL){
      printf("\n      -> ");
      printEntry2(currentEntry);
      currentEntry = currentEntry->next;
    }

    printf("\n");
  }
  printf("]}\n\n");
}

int isSameString(char * s1, char * s2){
  if (strcmp(s1,s2) == 0) {
    return TRUE;
  }
  return FALSE;
}

int getPosition(Hash * hash, char * key){ // <================= TODO: ADD LIMITS ???
  int hashcode = 0;
  for(int i=0;i<strlen(key);i++){
    hashcode += (key[i] - 'a');
    hashcode *= 256;
  }

  return (hashcode % hash->capacity);
}

void put(Hash * hash, char * key, char * value){
  int idx = getPosition(hash, key);
  if(hash->array[idx] == NULL){
    hash->array[idx] = createEntry(key, value);
    hash->size++;
    return;
  }

  Entry * currentEntry = hash->array[idx];
  Entry * prev = NULL;
  while(currentEntry != NULL){
    prev = currentEntry;
    // Check if we already have that key, and update it
    if (isSameString(currentEntry->key, key) == TRUE) {
      //printEntry(currentEntry);
      currentEntry->value = value;
      return;
    }
    currentEntry = currentEntry->next;
  }

  // Here currentEntry is NULL
  Entry * newNode = createEntry(key, value);
  prev->next = newNode;
  // printf("ADDING: \n  -> ");
  // printEntry2(prev);
  // printf("\n  -> ");
  // printEntry(newNode);
  hash->size++;
}

char * get(Hash * hash, char * key){ // TODO: can we return NULL ???
  int idx = getPosition(hash, key);
  if(hash->array[idx] == NULL){
    printf("INFO_001: Key %s not found\n", key);
    return NULL;
  }

  Entry * currentEntry = hash->array[idx];
  while(currentEntry != NULL){
    if (isSameString(currentEntry->key, key) == TRUE) {
      printf("Key %s found: %s\n", key, currentEntry->value);
      return currentEntry->value;
    }
    currentEntry = currentEntry->next;
  }

  printf("Key %s NOT_FOUND\n", key);
  return NULL; // Nothing found
}

//
// Main function
//

int main(){
  Hash * hash = createHash(10);
  put(hash, "firstName", "brian");
  put(hash, "lastName", "spinos");
  put(hash, "age", "30");
  put(hash, "address", "123 Foobar St");
  put(hash, "country", "USA");
  put(hash, "state", "AZ");
  put(hash, "state", "WA");
  put(hash, "state", "FL");
  put(hash, "state", "NY");
  put(hash, "xyz", "123");

  printHash(hash);


  get(hash, "firstName");
  get(hash, "lastName");
  get(hash, "age");
  get(hash, "address");
  get(hash, "country");
  get(hash, "state");
  get(hash, "xyz");

  get(hash, "info");
  get(hash, "favorite");
  get(hash, "color");

  return 0;
}

/* OUTPUT

Hash@0x7fab4d401700{size=7, capacity=10, array=[
  [0] -> Entry@0x7fab4d4017a0{key=age, value=30, next=0x7fab4d4017c0}
      -> Entry@0x7fab4d4017c0{key=address, value=123 Foobar St, next=0x7fab4d4017e0}
      -> Entry@0x7fab4d4017e0{key=country, value=USA, next=NULL}
  [1] -> NULL
  [2] -> Entry@0x7fab4d401820{key=xyz, value=123, next=NULL}
  [3] -> NULL
  [4] -> NULL
  [5] -> NULL
  [6] -> Entry@0x7fab4d401760{key=firstName, value=brian, next=0x7fab4d401780}
      -> Entry@0x7fab4d401780{key=lastName, value=spinos, next=NULL}
  [7] -> NULL
  [8] -> Entry@0x7fab4d401800{key=state, value=NY, next=NULL}
  [9] -> NULL
]}

Key firstName found: brian
Key lastName found: spinos
Key age found: 30
Key address found: 123 Foobar St
Key country found: USA
Key state found: NY
Key xyz found: 123
Key info NOT_FOUND
Key favorite NOT_FOUND
Key color NOT_FOUND

*/
