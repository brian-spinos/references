package com.example;

public class Conversions {
  public static void main(String[] args) {
    String myStr = "abc";

    int myInt = 123;
    long mylong = 456L;
    float myFloat = 1.23F;
    double myDouble = 4.56;

    boolean myBoolean = true;
    byte myByte = 127;
    char myChar = 'a'; // 2 bytes
    short myShort = 32767; // 2 bytes


    //
    // To String
    // String.ValueOf(xxx)
    //

    String a1 = String.valueOf(myInt);
    System.out.println("a1: " + a1);

    String a2 = String.valueOf(mylong);
    System.out.println("a2: " + a2);

    String a3 = String.valueOf(myFloat);
    System.out.println("a3: " + a3);

    String a4 = String.valueOf(myDouble);
    System.out.println("a4: " + a4);

    String a5 = String.valueOf(myBoolean);
    System.out.println("a5: " + a5);

    String a6 = String.valueOf(myByte);
    System.out.println("a6: " + a6);

    String a7 = String.valueOf(myChar);
    System.out.println("a7: " + a7);

    String a8 = String.valueOf(myShort);
    System.out.println("a8: " + a8);

    //
    // From String
    //

    int b1 = Integer.parseInt("123");
    System.out.println("b1: " + b1);

    long b2 = Long.parseLong("123");
    System.out.println("b2: " + b2);

    float b3 = Float.parseFloat("12.34F");
    System.out.println("b3: " + b3);

    double b4 = Double.parseDouble("45.67D");
    System.out.println("b4: " + b4);

    boolean b5 = Boolean.parseBoolean("true");
    System.out.println("b5: " + b5);

    byte b6 = Byte.parseByte("127");
    System.out.println("b6: " + b6);

    char b7 = "a".charAt(0); // <---------------------------- different
    System.out.println("b7: " + b7);

    short b8 = Short.parseShort("32767");
    System.out.println("b8: " + b8);
  }
}

/** OUTPUT:
 * 
 * a1: 123
 * a2: 456
 * a3: 1.23
 * a4: 4.56
 * a5: true
 * a6: 127
 * a7: a
 * a8: 32767
 * b1: 123
 * b2: 123
 * b3: 12.34
 * b4: 45.67
 * b5: true
 * b6: 127
 * b7: a
 * b8: 32767
 * 
 */
