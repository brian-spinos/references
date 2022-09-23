//
// Recursion
//

// gcc recursion.c -o recursion; ./recursion

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void countDown(int count){
  if (count == 0) {
    printf("BLAST OFF!!!\n");
    return;
  }
  printf("%d\n", count);
  countDown(count-1);
}

int main(){
  countDown(10);
  return 0;
}

/* OUTPUT

10
9
8
7
6
5
4
3
2
1
BLAST OFF!!!

*/
