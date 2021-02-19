import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

public class GemCollectorTest {
  // Pleash rewrite the current directory.
  String currentPath = "/Users/ykoh/Desktop/삼성SDS/개별 연구/2021-winter-coop/Week 4/gemCollector/";
  String testcasePath = currentPath + "src/testcase/";

  @Test
  public void inputWithNotExistingFile() throws Exception {
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    
    InputManager inputManager = new InputManager();
    String filename = testcasePath+"testInvalidSy.txt";
    inputManager.getInput(filename);
    assertEquals("No such file.\n", outContent.toString());
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithInvalidSymbol() throws Exception {
    InputManager inputManager = new InputManager();
    String filename = testcasePath+"testInvalidSymbol.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
                                                              () -> inputManager.getInput(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "Invalid symbol in the map.");
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithSmallerXMap() throws Exception {
    InputManager inputManager = new InputManager();
    String filename = testcasePath+"testSmallerX.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
                                                              () -> inputManager.getInput(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "The x-coordinator length of the map is smaller than the given size.");
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithSmallerYMap() throws Exception {
    InputManager inputManager = new InputManager();
    String filename = testcasePath+"testSmallerY.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
                                                              () -> inputManager.getInput(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "The y-coordinator length of the map is smaller than the given size.");
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithNoStartPoint() throws Exception {
    InputManager inputManager = new InputManager();
    String filename = testcasePath+"testNoStart.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
                                                              () -> inputManager.getInput(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "There is no start point in the map.");
  }

  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void inputWithTwoStartPoint() throws Exception {
    InputManager inputManager = new InputManager();
    String filename = testcasePath+"testTwoStart.txt";
    GemMapInputException gemMapInputException = assertThrows(GemMapInputException.class, 
                                                              () -> inputManager.getInput(filename));
    String message = gemMapInputException.getMessage();
    assertEquals(message, "Two start points exist. There should be one start point.");
  }

  @Test
  @Timeout(value = 40, unit = TimeUnit.SECONDS)
  public void answerTest() throws Exception {
    InputManager inputManager = new InputManager();
    int[] answer = {1, 1, 3, -1};
    for (int i = 0; i < 4; ++i) {
      String filename = testcasePath+"test"+Integer.toString(i)+".txt";
      inputManager.getInput(filename);

      PointInfo[][] pointInfoMap = inputManager.getPointInfoMap();
      Point startPoint = inputManager.getStartPoint();
      Point endPoint = inputManager.getEndPoint();

      GemCollector gemCollector = new GemCollector(pointInfoMap, startPoint, endPoint);
      int gemCollectorAnswer = gemCollector.collect();
      assertEquals(answer[i], gemCollectorAnswer);
    }
  }
  
}
