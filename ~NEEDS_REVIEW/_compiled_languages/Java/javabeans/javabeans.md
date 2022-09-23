# java beans

Java bean is just a set of conventions. EJB is a standard for J2EE business components.

Specifically a Java bean:

- has a public default constructor;
- readable property methods precedes with "get";
- writable property methods precedes with "set"; and
- is Serializable.
For example, a Java bean with a property of "margin" would minimally look like this:

```java
public class MyBean implements Serializable {
  private int margin;

  public MyBean() { }
  public int getMargin() { return margin; }
  public void setMargin(int margin) { this.margin = margin; }
}
```

EJB, despite the name, is almost completely unrelated.
