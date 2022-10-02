package com.example;

import java.nio.file.*; // new (nio 2) in JDK 7
import java.io.IOException;

/**
 * https://www.baeldung.com/java-nio-2-file-api // Nov 2019
 * file APIs in the new file system API (NIO2) that was shipped as a part of Java 7
 */
String HOME = System.getProperty("user.home");
Path path = Paths.get(HOME);
boolean x = Files.exists(path);

//
// create files
//

String fileName = "delete-me-now.txt";
Path fileToCreate = Paths.get(fileName); // same level as /src folder
try {
    Files.createFile(fileToCreate);
} catch (IOException e) { // FileAlreadyExistsException
    e.printStackTrace();
}

// Files.createDirectory(p);
// Files.createDirectories(subdir); // if path does not exist
// Files.delete(p); // fails if file does not exist
// Files.deleteIfExists(p);
// Files.copy(file1, file2);
// Files.copy(file1, file2, StandardCopyOption.REPLACE_EXISTING);
// Files.move(file1, file2);
// Files.move(file1, file2, StandardCopyOption.REPLACE_EXISTING);


//
// Streams
//

String fileName = "foo.txt";

//read file into stream, try-with-resources
try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
    stream.forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}
