import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class HanoiTest {

  // Answer for # of disk movements
  int hanoiAns(int numDisks) {
    return (int) Math.pow(2, numDisks) - 1;
  }

  // Test # of disk movements.
  // bar1 and bar2 should be empty when finished.
  // All disks should be in bar3.
  @Test
  public void hanoiAnswerTest() throws HanoiInputException {

    Hanoi h;
    for (int i = 1; i < 10; i++) {
      h = new Hanoi(i);
      assertEquals(hanoiAns(i), h.calculateHanoiSteps());
    }
  } 
  
  // calculateHanoiSteps should always take only a positive integer as the number of disks.
  @Test
  public void hanoiWithNegIntNumDiskTest() throws Exception {
    Hanoi h = new Hanoi(10);
    HanoiInputException hanoiException = 
        assertThrows(HanoiInputException.class, () -> h.calculateHanoiSteps(-1, 1, 2, 3));
    String message = hanoiException.getMessage();
    assertEquals("The number of disks should be a positive number.", message);
  }

  // calculateHanoiSteps should always take 1 or 2 or 3 as the bar number.
  @Test
  public void hanoiWithNegIntBarNumTest() throws Exception {
    Hanoi h = new Hanoi(10);
    HanoiInputException hanoiException = 
        assertThrows(HanoiInputException.class, () -> h.calculateHanoiSteps(1, -1, 2, 3));
    String message = hanoiException.getMessage();
    assertEquals("Bar number should be 1 or 2 or 3.", message);
  }

  // calculateHanoiSteps should always take 1 or 2 or 3 as the bar number.
  @Test
  public void hanoiWithBarNumOutOfRangeTest() throws Exception {
    Hanoi h = new Hanoi(10);
    HanoiInputException hanoiException = 
        assertThrows(HanoiInputException.class, () -> h.calculateHanoiSteps(1, 1, 4, 3));
    String message = hanoiException.getMessage();
    assertEquals("Bar number should be 1 or 2 or 3.", message);
  }

  // calculateHanoiSteps should not take same numbers as the bar numbers.
  @Test
  public void hanoiWithSameBarNumTest() throws Exception {
    Hanoi h = new Hanoi(10);
    HanoiInputException hanoiException = 
        assertThrows(HanoiInputException.class, () -> h.calculateHanoiSteps(1, 1, 3, 3));
    String message = hanoiException.getMessage();
    assertEquals("Bar numbers should not be same.", message);
  }
}
