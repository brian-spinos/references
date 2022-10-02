import java.util.*;


/**
 * takes a T and returns nothing
 */
interface Func0<T> {
  void call(T obj);
}


/**
 * takes an T and returns an T
 */
interface Funct<T> {
  T call(T obj);
}


class Observable<T> {
  List<T> data = new ArrayList<>();
  List<Funct<T>> procedures = new ArrayList<>();

  public void from2(T ...arr){
    Collections.addAll(data, arr);
  }

  static <T> Observable<T> from(T ...arr){
    Observable<T> ob = new Observable<T>();
    ob.from2(arr); 
    return ob;
  }

  public Observable<T> map(Funct<T> myLambda){
    this.procedures.add(myLambda);
    return this;
  }

  public Observable<T> subscribe(Func0<T> myLambda){
    for(int i=0; i < this.data.size(); i++){
      final int j = i;

      this.procedures.forEach(proc -> {
        T data2 = this.data.get(j);
        T res = proc.call(data2);
        this.data.set(j, res);
      });
    }

    for(int i=0;i<3;i++){
      myLambda.call(this.data.get(i));
    }

    return this;
  }
}


class Main {
  public static void main(String[] args) {
    System.out.println("\n\n");

      Observable.from("1", "2", "3")
        .map(n -> n + "-A")
        .map(n -> n + "-B")
        .map(n -> n + "-C")
        .subscribe(n -> System.out.println(n));
    
  }
}

/* OUTPUT
1-A-B-C
2-A-B-C
3-A-B-C
*/
