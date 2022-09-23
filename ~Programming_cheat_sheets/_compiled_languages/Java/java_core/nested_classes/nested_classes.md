# Nested classes

```java
class Main {
    public static class Foo {
        public static class Bar {
            public static int BAZ = 123;
        } 
    }

    public static void main(String[] args){
        System.out.println(Foo.Bar.BAZ); // 123
    }
}
```
