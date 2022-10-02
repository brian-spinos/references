//
// Command-line example (UNFINISHED)
//

// issues: repeated options, first gather ALL ERRORS, to display all, not just the first one


#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define TRUE 1
#define FALSE 0

// THIS WORKS!!!
// typedef char * String; // char * x; String x;
// typedef int boolean; // int x; boolean x;
// int true = 1;
// int false = 0;

//
// Functions
//

int stringStartsWith(const char *a, const char *b){
   // if(strncmp(a, b, strlen(b)) == 0) return 1;
   // return 0;
   return strstr(a, b) == a; // works!
}

char * myConcat(char * a, char * b){
  char _a[50];
  char _b[50];
  strcpy(_a,a);
  strcpy(_b,b);
  char * res = strcat(_a,_b);
  // printf("CONCAT RES: %s\n", res);
  return res;
}

int equals(char * a, char * b){
  if (strcmp(a, b) == 0) {
    return TRUE;
  }
  return FALSE;
}

char * removeNFromString(char * string, int count){ // TODO: this might be dangerous...
  string = string + count;
  return string;
  // char * newString = string;
  // char * c;
  // int i = 0;
  // for (c = string; *c != '\0'; c++){
  //   if (i >= count) {
  //     *newString = *c;
  //     newString++;
  //   }
  //   i++;
  // }
  // *newString = '\0';
  // printf("\n\nstring: %s\n", string);
  // printf("newString: %s\n", newString);
  // return newString;
}

void checkDupOption(char ** _variable, char * _short, char * _long){
  if (*_variable != NULL) {
    printf("ERROR: Option ");
    printf("%s",_short);
    printf("/");
    printf("%s",_long);
    printf(" already specified\n");
    return;
  }
}

void scanOption(
  char * _opt, char * _value,
  char * _short, char * _long,
  char * _short_eq, char * _long_eq,
  char ** _variable
){

  if (equals(_opt, _short)) {
    checkDupOption(_variable, _short, _long);
    // printf("************************ INFO_1003\n");
    *_variable = _value;
    return;
  }

  if (stringStartsWith(_opt, _short_eq)) {
    checkDupOption(_variable, _short, _long);
    // printf("************************ INFO_1001\n");
    *_variable = removeNFromString(_opt, 3);
    return;
  }

  if (stringStartsWith(_opt, _short)) {
    checkDupOption(_variable, _short, _long);
    // printf("************************ INFO_1002\n");
    *_variable = removeNFromString(_opt, 2);
    return;
  }

  if (equals(_opt, _long)) {
    checkDupOption(_variable, _short, _long);
    // printf("************************ INFO_1004\n");
    *_variable = _value;
    return;
  }

  if (stringStartsWith(_opt, _long_eq)) {
    checkDupOption(_variable, _short, _long);
    // printf("************************ INFO_1005\n");
    // *_variable = removeNFromString(_opt, 3);
    *_variable = removeNFromString(_opt, strlen(_long)+1);
    return;
  }

  if (stringStartsWith(_opt, _long)) {
    checkDupOption(_variable, _short, _long);
    // printf("************************ INFO_1006\n");
    // *_variable = removeNFromString(_opt, 2);
     *_variable = removeNFromString(_opt, strlen(_long));
    return;
  }
}

//
// Main function
//

int main(int argc, char * argv[]){
  char * name;
  char * address;
  char * state;

  for(int i=1;i<(argc);i++){ // start at index 1!
    char * currentOption = argv[i];
    char * currentValue = argv[i+1];
    scanOption(currentOption, currentValue, "-n", "--name", "-n=", "--name=", &name);
    scanOption(currentOption, currentValue, "-a", "--address", "-a=", "--address=", &address);
    scanOption(currentOption, currentValue, "-s", "--state", "-s=", "--state=", &state);
  }

  printf("\n\nname: %s\n", name);
  printf("address: %s\n", address);
  printf("state: %s\n", state);


  // char * s1 = "hello";
  // printf("AAA: %d\n", stringStartsWith(s1, "h"));
  // printf("BBB: %s\n", myConcat("aaa", "bbb"));
  // printf("CCC: %d\n", equals("aaa", "bbb"));
  // printf("DDD: %s\n", removeNFromString("hello", 2));
  // printf("EEE: %d\n", stringStartsWith("hello", myConcat("he", "llo")));

  return 0;
}


/* OUTPUT

$ ./heap --name brian -a "foobar street" --state=AZ

name: brian
address: foobar street
state: AZ


*/
