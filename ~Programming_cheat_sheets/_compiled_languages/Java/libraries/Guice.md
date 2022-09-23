# Guice


```
https://www.baeldung.com/guice
```

```
// field injection is hard to test
```

```xml

<dependency>
    <groupId>com.google.inject</groupId>
    <artifactId>guice</artifactId>
    <version>4.1.0</version>
</dependency>
```


```java
// Guice starts at the main method of your app
// MyBasicModule is a class I create to setup stuff
public static void main(String[] args){
    Injector injector = Guice.createInjector(new MyBasicModule());
    IFoo f = injector.getInstance(IFoo.class);
}

// com.google.inject.AbstractModule
// here you can pass default parameters to a class constructor! see tutorial
// you can also bind to an instance of the class!
public class MyBasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IFoo.class).to(FooImpl.class);

        bind(Communicator.class)
            .annotatedWith(Names.named("DefaultCommunicator"))
            .to(Communicator.class);
    }
}


// in your class - field injection here ???
// com.google.inject.Inject
// com.google.inject.Named
@Inject @Named("DefaultCommunicator")
Communicator communicator;








// Method Injection
// Here we use a setter method to achieve the injection:
@Inject
public void setEmailCommunicator(@Named("EmailComms") CommunicationMode emailComms) {
    this.emailComms = emailComms;
}







// Constructor Injection
// You can also inject dependencies using a constructor:
@Inject
public Communication(@Named("IMComms") CommunicationMode imComms) {
    this.imComms= imComms;
}
```


```
// Singleton

// Eager Singleton
```


