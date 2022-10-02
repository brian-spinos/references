package com.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * https://mkyong.com/java8/java-8-stream-read-a-file-line-by-line/
 * https://reflectoring.io/processing-files-using-java-8-streams/
 */
public class MyFileUtil {

  public static void getEachLine(String fileName) {
    try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
      lines.forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void createFile(String fileName) {
    try {
      Files.createFile(Paths.get(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void createDir(String folderName) {
    try {
      Files.createDirectory(Paths.get(folderName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static boolean exists(String fileName) {
    return Files.exists(Paths.get(fileName));
  }

  public static void delete(String fileName) {
    try {
      // Files.delete(Paths.get(fileName)); // NoSuchFileException
      Files.deleteIfExists(Paths.get(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void copy(String fileName, String fileName2) {
    Path copied = Paths.get(fileName2);
    Path originalPath = Paths.get(fileName);
    try {
      Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void write(String fileName, String data) {
    try {
      Files.write(Paths.get(fileName), data.getBytes(), StandardOpenOption.WRITE);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void append(String fileName, String data) {
    try {
      Files.write(Paths.get(fileName), data.getBytes(), StandardOpenOption.APPEND);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void move(String fileName, String fileName2) {
    try {
      Files.move(Paths.get(fileName), Paths.get(fileName2));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    MyFileUtil.getEachLine("src/main/resources/myFile.txt");
    MyFileUtil.createFile("src/main/resources/myFile-2.txt"); // FileAlreadyExistsException
    MyFileUtil.delete("src/main/resources/myFile-2.txt");
    MyFileUtil.createDir("src/main/resources/myFolder");
    MyFileUtil.copy("src/main/resources/myFile.txt", "src/main/resources/myFile-COPY.txt");
    MyFileUtil.write("src/main/resources/myFile.txt", "xyz\n");
    MyFileUtil.append("src/main/resources/myFile.txt", "abc\n");
    MyFileUtil.move("src/main/resources/myFile.txt", "src/main/resources/myFile-moved.txt");
    
    boolean fileExists = MyFileUtil.exists("src/main/resources/myFile.txt");
    System.out.println("fileExists: " + fileExists);
  }
}
