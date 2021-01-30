import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;


@Testable
public class HanoiTest {

  int hanoiAns(int num_disks) {
    if (num_disks == 1) {
      return 1;
    }
    return hanoiAns(num_disks - 1) * 2 + 1;
  }

  @Test
  public void firstTest() {
    Hanoi h = new Hanoi(1);

    assertEquals(hanoiAns(1), h.hanoi(1, 1, 2, 3));
    assertTrue(h.bar1.isEmpty());
    assertTrue(h.bar2.isEmpty());
    assertFalse(h.bar3.isEmpty());
  }

  @Test
  public void secondTest() {
    Hanoi h = new Hanoi(2);

    assertEquals(hanoiAns(2), h.hanoi(2, 1, 2, 3));
    assertTrue(h.bar1.isEmpty());
    assertTrue(h.bar2.isEmpty());
    assertFalse(h.bar3.isEmpty());
  }

  @Test
  public void thirdTest() {
    Hanoi h = new Hanoi(3);

    assertEquals(hanoiAns(3), h.hanoi(3, 1, 2, 3));
    assertTrue(h.bar1.isEmpty());
    assertTrue(h.bar2.isEmpty());
    assertFalse(h.bar3.isEmpty());
  }

  @Test
  public void forthTest() {
    Hanoi h = new Hanoi(4);

    assertEquals(hanoiAns(4), h.hanoi(4, 1, 2, 3));
    assertTrue(h.bar1.isEmpty());
    assertTrue(h.bar2.isEmpty());
    assertFalse(h.bar3.isEmpty());
  }
}
