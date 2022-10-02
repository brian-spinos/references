# JNDI


// https://www.baeldung.com/jndi



```java
JndiTemplate jndiTemplate = new JndiTemplate();
ctx = (InitialContext) jndiTemplate.getContext();


ds = new DriverManagerDataSource("jdbc:h2:mem:mydb");
ctx.bind("java:comp/env/jdbc/datasource", ds);

DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
```


```java
// It is possible to use JNDI without an application server, like Glassfish
import javax.naming.*; // JNDI


//The following code shows how to store data in JNDI:
Context ctx = new InitialContext();
ctx.bind("jndiName", "value");
ctx.close();


//Second piece of code shows how to look for things in JNDI:
Context ctx = new InitialContext();
Object result = ctx.lookup("jndiName");
ctx.close();

```

