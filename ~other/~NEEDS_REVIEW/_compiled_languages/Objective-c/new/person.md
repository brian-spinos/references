# Simple Objective-c class


###### person.m

```objective-c

//
// Compile the code:
//      $ gcc -framework Foundation test.m -o test
//      $ ./test
//

#import <Foundation/Foundation.h>

@interface Person : NSObject {
    NSString * name;
    NSInteger age;
}

@property(nonatomic, readwrite, assign)  NSString *name; // generates the functions `name` and `setName`
@property(nonatomic, readwrite, assign)  NSInteger age; // generates the functions `age` and `setAge`

-(id)initWithName:(NSString *)name andAge:(NSInteger)age;
-(void)print;
-(void)greet;
@end

@implementation Person

@synthesize name;
@synthesize age;

-(id)initWithName:(NSString *)_name andAge:(NSInteger)_age{
    self.name = _name;
    self.age = _age;

    return self;
}

-(void)print{
    NSLog(@"Name: %@", name);
    NSLog(@"Age: %ld", age);
}

-(void)greet{
    NSLog(@"Hello, my name is %@, I am %ld years old.", name, age);
}

@end


int main(){
    NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];

    Person * brian = [[Person alloc]initWithName:@"Brian Spinos" andAge:28];


    [brian greet]; // Hello, my name is Brian Spinos, I am 28 years old.


    [brian setName:@"Erich Smith"];
    [brian setAge:22];


    [brian greet]; // Hello, my name is Erich Smith, I am 22 years old.


    NSString * name = [brian name];
    NSLog(@"name: %@", name);

    [brian setName:@"Rick Smith"];


    NSString * name2 = [brian name];
    NSLog(@"name2: %@", name2); // name2: Rick Smith


    //
    // NSNumber
    //

    NSNumber * myNumber = @123; // equivalent to [NSNumber numberWithInt:42]
    NSLog(@"NSNumber: %@", myNumber); // NSNumber: 123


    //
    // Strings
    //


    NSString * myString = @"Hello";
    NSLog(@"NSString: %@", myString);

    //
    // Array
    //

    NSArray * myArray = @[@"AAA", @"BBB", @"CCC"];
    NSLog(@"NSArray: %@", myArray);

    //
    // Hashes
    //

    NSDictionary * myHash = @{@"name": @"brian", @"age": @28};
    NSLog(@"NSDictionary: %@", myHash);



    [pool drain];
    return 0;
}

```
