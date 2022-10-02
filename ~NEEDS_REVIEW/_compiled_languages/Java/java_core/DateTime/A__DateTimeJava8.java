import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;


/**
 * String format reference:
 * https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
 */
class Main {
  public static void main(String[] args) {

    //
    // LocalDate
    //

    // String to obj 
    String date1 = "20201010";
    String pattern1 = "yyyyMMdd";
    LocalDate ld1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern(pattern1));
    System.out.println("ld1 : " + ld1); // 2020-10-10
    

    // Obj to string (LocalDate)
    LocalDate ldtObj2 = LocalDate.of(2015, Month.JANUARY, 25);
    String pattern2 = "yyyyMMdd";
    String dateStr2 = ldtObj2.format(DateTimeFormatter.ofPattern(pattern2));
    System.out.println("dateStr2: " + dateStr2); // 20150125

    
    //
    // LocalDateTime
    //

    // String to obj 
    String date3 = "20201010112222";
    String pattern3 = "yyyyMMddHHmmss";
    LocalDateTime ld3 = LocalDateTime.parse(date3, DateTimeFormatter.ofPattern(pattern3));
    System.out.println("ld3 : " + ld3); // 2020-10-10T11:22:22


    // Obj to string
    LocalDateTime ldtObj4 = LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30);
    String pattern4 = "yyyyMMdd";
    String dateStr4 = ldtObj4.format(DateTimeFormatter.ofPattern(pattern4));
    System.out.println("dateStr4: " + dateStr4); // 20150125
  }
}


/* GET CURRENT DATE TIME ??
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
  LocalDateTime now = LocalDateTime.now();
  dtf.format(now); // 2017/11/06 12:11:58
*/


/*. get start of day, previous day, end of day

DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HH:mm:ss");
    Instant instant = Instant.now(); // seems it is already GMT ???
    ZoneId zoneId = ZoneId.of("GMT");
    ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
    ZonedDateTime start = zdt.toLocalDate().atStartOfDay( zoneId ).minusDays(1);
    ZonedDateTime end = start.plusDays(1).minusSeconds(1);

    
     // If current day is 2020-06-19
     // 20200618T00:00:00
     // 20200618T23:59:59
    String startTS = sdf.format(start);
    String endTS = sdf.format(end);


*/


/*
    LocalDate ldt = LocalDate.now(ZoneId.of("GMT"));
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    
    // If current day is 2020-06-19 (before 5pm)
    // 20200618000000
    // 20200618235959
    String startTS = dtf.format(ldt.atStartOfDay().minusDays(1));
    String endTS = dtf.format(ldt.atStartOfDay().minusSeconds(1));


*/
