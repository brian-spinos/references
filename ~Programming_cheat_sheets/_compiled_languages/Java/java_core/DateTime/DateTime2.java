import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Main {
  public static void main(String[] args) {
    

    // 2019-10-20T02:04:57.252Z
    System.out.println("==== 1");
    System.out.println(new Date().toInstant()); 
    

    // Date to string (with specified format)
    // 2019-10-20 02:00:00
    System.out.println("==== 2");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String dateString = format.format(new Date());
    System.out.println(dateString); 


    try{
      // String to date  (with specified format)
      // Tue Oct 19 00:00:00 UTC 1999
      SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
      Date date = format2.parse("1999-10-19"); 
      System.out.println("==== 3");
      System.out.println(date);
    } catch(ParseException e){
      System.out.println(e.getMessage());
    }


    try{
      // String to date  (with specified format)
      // 1999-10-19T00:00:00Z
      SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
      Date date3 = format3.parse("1999-10-19"); 
      System.out.println("==== 4");
      System.out.println(date3.toInstant()); 
    } catch(ParseException e){
      System.out.println(e.getMessage());
    }


    // miliseconds:
    // 1571537796793
    System.out.println("==== 5");
    System.out.println(new Date().getTime()); 


    // Fri Jul 14 02:40:00 UTC 2017
    System.out.println("==== 6");
    System.out.println(new Date(1500000000000L)); 


    // Tue May 13 16:53:20 UTC 2014
    System.out.println("==== 7");
    System.out.println(new Date(1400000000000L)); 
  }
}

/* OUTPUT
==== 1
2019-10-20T02:36:21.507Z
==== 2
2019-10-20 02:36:22
==== 3
Tue Oct 19 00:00:00 UTC 1999
==== 4
1999-10-19T00:00:00Z
==== 5
1571538982387
==== 6
Fri Jul 14 02:40:00 UTC 2017
==== 7
Tue May 13 16:53:20 UTC 2014
*/
