http://tutorials.jenkov.com/java-date-time/parsing-formatting-dates.html


SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

String dateString = format.format( new Date()   );
Date   date       = format.parse ( "2009-12-31" );    
