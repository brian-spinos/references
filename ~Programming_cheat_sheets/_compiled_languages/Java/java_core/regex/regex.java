
//
// Regex
//
// https://www.baeldung.com/regular-expressions-java
//

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Foo {
  public static int bar(String regex, String text) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    int matches = 0;
    while (matcher.find()) {
        matches++;
    }
    
    System.out.println("matchesFound: " + matches);
    return matches;
  }
}


class Main {
  public static void main(String[] args) {
    System.out.println("\n\n\n");

    Foo.bar("a", "abc");
    Foo.bar("^foo", "foobar");
    Foo.bar("bar$", "foobar");
    Foo.bar("[bc][a][r]", "foobar");
    Foo.bar("[bc]ar", "foobar");
    Foo.bar("(foo*)", "foobar");
    Foo.bar("(.*)ooba(.*)", "foobar");
  }
}


// data.matches("\\d{10}")
