# jUnit


```
// Junit 5 (we use 4.12)
https://www.baeldung.com/junit
```


```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.1.0</version>
    <scope>test</scope>
</dependency>
```



```java
@BeforeEach
@BeforeAll // needs static method ???

@AfterEach
@AfterAll // needs static method, YES

@Test

@Disable


assertEquals(1,1)

Assertions.assertTrue(foo)
```
