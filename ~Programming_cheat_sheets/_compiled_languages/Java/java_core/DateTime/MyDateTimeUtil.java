package com.example;

import java.time.*;
import java.util.Date;

public class MyDateTimeUtil {

  /**
   * OUTPUT:
   * localDate1:     2020-12-21
   * localDateTime1: 2020-12-21T15:12:27.233
   * localDate3:     2020-12-21
   * localDateTime3: 2020-12-21T15:12:27.233
   */
  public static void examples() {
    LocalDate localDate7 = LocalDate.now();
    System.out.println("localDate7:      " + localDate7);

    LocalDateTime localDateTime8 = LocalDateTime.now();
    System.out.println("localDateTime8:  " + localDateTime8);


    Clock clock = Clock.system(ZoneId.of("MST", ZoneId.SHORT_IDS));
    //Clock clock = Clock.system(ZoneId.of("GMT", ZoneId.SHORT_IDS));

    LocalDate localDate9 = LocalDate.now(clock);
    System.out.println("localDate9:      " + localDate9);

    LocalDateTime localDateTime10 = LocalDateTime.now(clock);
    System.out.println("localDateTime10:  " + localDateTime10);
  }

  public static LocalDate toLocalDate(Date date) { // via Instant
    return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
  }

  public static LocalDate toLocalDate(Instant instant) {
    return instant
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
  }

  public static LocalDateTime toLocalDateTime(Date date) { // via Instant
    return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
  }

  public static LocalDateTime toLocalDateTime(Instant instant) {
    return instant
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
  }

  public static Date toDate(LocalDate localDate) { // via Instant
    return Date.from(localDate.atStartOfDay()
            .atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date toDate(Instant instant) { // via Instant
    return Date.from(instant);
  }

  public static void main(String[] args) {
    System.out.println("LocalDate.now():     " + LocalDate.now());     // 2020-12-21
    System.out.println("LocalDateTime.now(): " + LocalDateTime.now()); // 2020-12-21T15:43:40.067
    System.out.println("Instant.now():       " + Instant.now());       // 2020-12-21T22:43:40.067Z

    // Using the format above:
    System.out.println("LocalDate:           " + LocalDate.parse("2015-10-21"));
    System.out.println("LocalDateTime:       " + LocalDateTime.parse("2020-10-22T13:14:15.001"));
    System.out.println("Instant:             " + Instant.parse("2020-10-23T13:14:15.001Z"));

    System.out.println("\n");

    LocalDate localDate1 = MyDateTimeUtil.toLocalDate(new Date());
    System.out.println("localDate1:      " + localDate1);

    LocalDate localDate2 = MyDateTimeUtil.toLocalDate(Instant.now());
    System.out.println("localDate2:      " + localDate2);

    LocalDateTime localDateTime3 = MyDateTimeUtil.toLocalDateTime(new Date());
    System.out.println("localDateTime3:  " + localDateTime3);

    LocalDateTime localDateTime4 = MyDateTimeUtil.toLocalDateTime(Instant.now());
    System.out.println("localDateTime4:  " + localDateTime4);

    Date date5 = MyDateTimeUtil.toDate(LocalDate.now());
    System.out.println("date5:           " + date5);

    Date date6 = MyDateTimeUtil.toDate(Instant.now());
    System.out.println("date6:           " + date6);

    System.out.println("\n");

    MyDateTimeUtil.examples();
  }
}

/* OUTPUT:

LocalDate.now():     2020-12-21
LocalDateTime.now(): 2020-12-21T15:46:20.018
Instant.now():       2020-12-21T22:46:20.018Z
LocalDate:           2015-10-21
LocalDateTime:       2020-10-22T13:14:15.001
Instant:             2020-10-23T13:14:15.001Z


localDate1:      2020-12-21
localDate2:      2020-12-21
localDateTime3:  2020-12-21T15:46:20.037
localDateTime4:  2020-12-21T15:46:20.037
date5:           Mon Dec 21 00:00:00 MST 2020
date6:           Mon Dec 21 15:46:20 MST 2020


localDate7:      2020-12-21
localDateTime8:  2020-12-21T15:46:20.047
localDate9:      2020-12-21
localDateTime10:  2020-12-21T15:46:20.047

*/
