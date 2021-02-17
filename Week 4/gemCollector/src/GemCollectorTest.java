import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

public class GemCollectorTest {
  // Pleash rewrite the current directory.
  String currentPath = 
      "/Users/ykoh/Desktop/삼성SDS/개별 연구/2021-winter-coop/Week 4/gemCollector/";
  String testcaseFolderPath = currentPath + "src/testcase/";

  @Test
  public void inputWithNotExistingFile() throws Exception {
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    String filename = testcaseFolderPath + "testInvalidSy.txt";

    new GemCollector(filename);
    assertEquals("No such file.\n", outContent.toString());
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithInvalidSymbol() throws Exception {
    String filename = testcaseFolderPath + "testInvalidSymbol.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
        () -> new GemCollector(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "Invalid symbol in the map.");
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithSmallerXMap() throws Exception {
    String filename = testcaseFolderPath + "testSmallerX.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
        () -> new GemCollector(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "The x-coordinator length of the map does not match the given size.");
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithSmallerYMap() throws Exception {
    String filename = testcaseFolderPath + "testSmallerY.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
        () -> new GemCollector(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "The y-coordinator length of the map is smaller than the given size.");
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithLargeMap() throws Exception {
    String filename = testcaseFolderPath + "testLargeMap.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
        () -> new GemCollector(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "Map size should not exceed 15x15.");
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithNoStartPoint() throws Exception {
    String filename = testcaseFolderPath + "testNoStart.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
        () -> new GemCollector(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "There is no start point in the map.");
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithTwoStartPoint() throws Exception {
    String filename = testcaseFolderPath + "testTwoStart.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
        () -> new GemCollector(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "Two start points exist. There should be one start point.");
  }

  @Test
  @Timeout(value = 40, unit = TimeUnit.SECONDS)
  public void answerTest() throws Exception {
    int[] answer = {1, 1, 3, -1};
    for (int i = 0; i < 4; ++i) {
      String filename = testcaseFolderPath + "test" + Integer.toString(i) + ".txt";
      GemCollector gemCollector = new GemCollector(filename);
      int gemCollectorAnswer = gemCollector.collect();
      assertEquals(answer[i], gemCollectorAnswer);
    }
  }
  
}
