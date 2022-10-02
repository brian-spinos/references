// Generics in methods

class Person<T>{

  T myObj;

  T getMyObj(){
    return this.myObj;
  }

  void setMyObj(T obj){
    this.myObj = obj;
  }

  public <X, Y, T> int doStuff(X xxx, Y yyy, T ttt){
    System.out.println("doStuff");

    System.out.println("xxx: " + xxx.getClass().getName());
    System.out.println("yyy: " + yyy.getClass().getName());
    System.out.println("ttt: " + ttt.getClass().getName());
    
    return 0;
  }
}

class Main {
  public static void main(String[] args) {
    Person<String> brian = new Person<>();
    brian.setMyObj("CAR");
    brian.doStuff(1, 2, 3);
    brian.doStuff("a", "b", "c");
    brian.doStuff("a", 2, 3);
    brian.<Integer, String, String>doStuff(1, "b", "c");
    brian.<String, String, Integer>doStuff("a", "b", 3);

    //brian.<String, String, Integer>doStuff("a", "b", "c"); // Error!
  }
}
