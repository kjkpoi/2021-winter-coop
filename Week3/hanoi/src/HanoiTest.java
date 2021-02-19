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

  // Check if hanoi's answer is correct.
  @Test
  public void hanoiAnswerTest() throws HanoiInputException {

    Hanoi hanoi;
    for (int i = 1; i < 10; i++) {
      hanoi = new Hanoi();
      assertEquals(hanoiAns(i), hanoi.calculateHanoiSteps(i, 1, 2, 3));
    }
  } 
  
  // calculateHanoiSteps() should always take only a positive integer as the number of disks.
  @Test
  public void hanoiWithNegIntNumDiskTest() throws Exception {
    Hanoi hanoi = new Hanoi();
    HanoiInputException hanoiInputException = 
        assertThrows(HanoiInputException.class, () -> hanoi.calculateHanoiSteps(-1, 1, 2, 3));
    String message = hanoiInputException.getMessage();
    assertEquals("The number of disks should be a positive number.", message);
  }

  @Test
  public void honoiWithZeroDiskTest() throws Exception {
    Hanoi hanoi = new Hanoi();
    HanoiInputException hanoiInputException =
        assertThrows(HanoiInputException.class,() -> hanoi.calculateHanoiSteps(0, 1, 2, 3));
    String message = hanoiInputException.getMessage();
    assertEquals("The number of disks should be a positive number.", message);
  }

  @Test
  public void hanoiWithSameBars() throws Exception {
    Hanoi hanoi = new Hanoi();
    HanoiInputException hanoiInputException =
        assertThrows(HanoiInputException.class, () -> hanoi.calculateHanoiSteps(4, 1, 1, 2));
    String message = hanoiInputException.getMessage();
    assertEquals("The three bars should be different from each other.", message);
  }
}
