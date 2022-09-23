# Spock


```
https://www.baeldung.com/groovy-spock

// DOCS: http://spockframework.org/spock/docs/1.1/all_in_one.html
//       http://spockframework.org/spock/docs/1.3/all_in_one.html

// cheatsheet
https://objectpartners.com/2014/04/08/spock-mock-cheatsheet/
```


```xml
<dependency>
    <groupId>org.spockframework</groupId>
    <artifactId>spock-core</artifactId>
    <version>1.0-groovy-2.4</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.codehaus.groovy</groupId>
    <artifactId>groovy-all</artifactId>
    <version>2.4.7</version>
    <scope>test</scope>
</dependency>


<!-- to compile groovy -->
<plugin>
    <groupId>org.codehaus.gmavenplus</groupId>
    <artifactId>gmavenplus-plugin</artifactId>
    <version>1.5</version>
    <executions>
        <execution>
            <goals>
                <goal>compile</goal>
                <goal>testCompile</goal>
            </goals>
        </execution>
     </executions>
</plugin>
```

```groovy
   // gradle
   apply plugin: 'groovy'
   
   testCompile group: 'org.spockframework', name: 'spock-core', version: '1.0-groovy-2.4'
    testCompile group: 'org.spockframework', name: 'spock-guice', version: '1.1-groovy-2.4'
```


```
// where tests go:
src/test/groovy
```


###### Example

```groovy
// Foo.groovy
import spock.lang.Specification

class Foo extends Specification {

    def "one plus one should equal two"() {
        expect:
        1 + 1 == 2
    }

    def "two plus two should equal four"() {
        given:
        int left = 2
        int right = 2

        when:
        int result = left + right

        then:
        result == 4
    }
}



//========================


def list = [1, 2, 3, 4]


// then exception
thrown(IndexOutOfBoundsException)



//
// MOCKs (only to check if a function was called?, cannot replace implementation???)
//


Foo foo = Mock()
def foo = Mock(Foo)
foo.bar(123) == false // uses sensible defaults

// stub methods
foo.bar(20) >> true // canned resuts
foo.bar(20) == true

// return true for any parameter
foo.bar(_) >> true

// multiple responses
foo.bar(_) >>> [true, true, false, true]


//
// mock assertion
//


def "Should verify notify was called"() {
    given:
        def notifier = Mock(Notifier)

    when:
        notifier.notify('foo')

    then:
        1 * notifier.notify('foo') // was called 1 time with 'foo' parameter
        // 1 * notifier.notify(_) // if we dont care about the parameter
        // 1 *  notifier.notify(!'foo') // if NOT called with 'foo'
}

//
// partial mocking use spies YEAH !!!! THIS IS IT! <<<<<<<<<<=========<<<<<<
//


// - https://farenda.com/spock/spock-spy-and-partial-mocking/


def foo = Spy(Foo, constructorArgs: ["hello"])
// When stubbing a method on a spy, the real method no longer gets called:
foo.bar(_) >> "ok"
// now calling the real method, if u want:
foo.bar(_) >> { String x -> callRealMethod(); x.size() > 3 ? "ok" : "fail" }
// or use callRealMethodWithArgs("changed x")


//
// Partial mocks
//

// this is now the object under specification, not a collaborator
def foo = Spy(Foo) {
  // stub a call on the same object
  shouldCallBaz(_) >> true
}

when:
foo.bar("msg") // calls shouldCallBaz internally

then:
// demand a call on the same object
1 * foo.baz("msg")
```




//
// Doubles
//

```groovy
// Fake - a simplified implementation
// Stub - has predefined results (i.e. classes that hardcoded return values)
// Mock - register calls they receive, how many calls and what parameters were used
// Spy - calls the original dependency, but we can change the implementation!!!
```





//
// spies
//
```groovy
// You can use spies and partial mocks (requires Spock 0.7 or newer).

// After creating a spy, you can listen in on the conversation between the caller and the real object underlying the spy:

def subscriber = Spy(SubscriberImpl, constructorArgs: ["Fred"])
subscriber.receive(_) >> "ok"
// Sometimes, it is desirable to both execute some code and delegate to the real method:
subscriber.receive(_) >> { String message -> callRealMethod(); message.size() > 3 ? "ok" : "fail" }
```



//
// Ignore a feature
//
```groovy
@Ignore
def "my feature"() { ... }

@Ignore("TODO")
def "my feature"() { ... }

@Ignore
class MySpec extends Specification { ... }

@IgnoreRest
def "I'll run"() { ... }
```


//
// Stubs
//
```groovy
def person = Stub(Person) {
    getName() >> "Fred"
    getAge() >> 42
}
```



//
// @Unroll
//
```groovy
@Unroll
def "maximum of #a and #b is #c"() {
    expect:
    Math.max(a, b) == c

    where:
    a | b | c
    1 | 2 | 2
}
```


//
// to see later:
//

```groovy
@Subject

@Shared

@Unroll

@FailsWith(...)
```

