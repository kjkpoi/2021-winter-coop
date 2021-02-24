import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.Test;

public class SchedulerTest {
  String testcaseFolderPath 
      = "/Users/ykoh/Desktop/삼성SDS/개별 연구/2021-winter-coop/Week 8/scheduler/src/testcase/";
  
  @Test
  public void answerTest1() {
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    String filename = testcaseFolderPath + "scheduler_input1.txt";
    String answerFileName = testcaseFolderPath + "scheduler_input1_ans.txt";
    try {
      Scheduler scheduler = new Scheduler(filename);
      scheduler.schedule();
      String actualOutString = outContent.toString();
      String[] actualOutStrings = actualOutString.split("\n");

      // Compare the answer and actual output.
      FileInputStream inputStream = null;
      inputStream = new FileInputStream(answerFileName);
      BufferedReader inFile = new BufferedReader(new InputStreamReader(inputStream));

      for (int i = 0; i < actualOutStrings.length; ++i) {
        assertEquals(inFile.readLine(), actualOutStrings[i]);
      }

      inFile.close();
    } catch (Exception e) {
      assert (false);
    }
  }

  @Test
  public void answerTest2() {
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    String filename = testcaseFolderPath + "scheduler_input2.txt";
    String answerFileName = testcaseFolderPath + "scheduler_input2_ans.txt";
    try {
      Scheduler scheduler = new Scheduler(filename);
      scheduler.schedule();
      String actualOutString = outContent.toString();
      String[] actualOutStrings = actualOutString.split("\n");

      // Compare the answer and actual output.
      FileInputStream inputStream = null;
      inputStream = new FileInputStream(answerFileName);
      BufferedReader inFile = new BufferedReader(new InputStreamReader(inputStream));

      for (int i = 0; i < actualOutStrings.length; ++i) {
        assertEquals(inFile.readLine(), actualOutStrings[i]);
      }

      inFile.close();
    } catch (Exception e) {
      assert (false);
    }
  }

  

  @Test
  public void inputWithAlphabet() {
    String filename = testcaseFolderPath + "scheduler_wronginput1.txt";
    WrongInputException wrongInputException = assertThrows(WrongInputException.class,
        () -> new Scheduler(filename));
    String message = wrongInputException.getMessage();
    assertEquals("Wrong input file format: Job information must be expressed as an integer.", 
        message);
  }
  
  @Test
  public void inputOutOfRange() {
    String filename = testcaseFolderPath + "scheduler_wronginput2.txt";
    WrongInputException wrongInputException = assertThrows(WrongInputException.class,
        () -> new Scheduler(filename));
    String message = wrongInputException.getMessage();
    assertEquals("Length of a job should be between 1 and 50", 
        message);
  }

  @Test
  public void jobCountMismatch() {
    String filename = testcaseFolderPath + "scheduler_wronginput3.txt";
    WrongInputException wrongInputException = assertThrows(WrongInputException.class,
        () -> new Scheduler(filename));
    String message = wrongInputException.getMessage();
    assertEquals("The actual number of jobs does not match the declared number of jobs.", 
        message);
  }
}
