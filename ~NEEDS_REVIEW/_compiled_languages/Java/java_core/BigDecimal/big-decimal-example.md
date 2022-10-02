# BigDecimal Example

```java
import java.math.BigDecimal;

class Main {
  public static void main(String[] args) {
    double a = 0.03;
    double b = 0.02;
    double c = a - b;
    System.out.println(c); // 0.009999999999999998

    /**
     * num1.add(num2);
     * num1.subtract(num2);
     * num1.multiply(num2);
     * num1.divide(num2);
     * ...
     */
    BigDecimal d = BigDecimal.valueOf(0.03);
    BigDecimal e = BigDecimal.valueOf(0.02);
    BigDecimal f = d.subtract(e);
    System.out.println(f); // 0.01
  }
}
```
