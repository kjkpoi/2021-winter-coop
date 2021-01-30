import java.util.Stack;

public class Hanoi {
  int num_disks;

  Stack<Integer> bar1 = new Stack<>();

  Stack<Integer> bar2 = new Stack<>();

  Stack<Integer> bar3 = new Stack<>();

  public Hanoi (int num_disks) {
    this.num_disks = num_disks;
    
    for (int i=num_disks; i>=1; i--) {
      bar1.push(i);
    }
  }

  public int hanoi (int num_disks, int from_bar, int second_bar, int to_bar) {
    if (num_disks == 1) {

      /* Pop a disk from from_bar */
      int disk_toMove = 0;

      switch (from_bar) {
        case 1: 
          disk_toMove = bar1.pop();
          break;
        case 2:
          disk_toMove = bar2.pop();
          break;
        case 3:
          disk_toMove = bar3.pop();
          break;
        default:
          assert(false);
          break;
      }
  
      /* Print disk movement */
      System.out.println("Disk "+disk_toMove+" moved from bar "+from_bar+" to bar "+to_bar);

      /* Push a disk to to_bar */
      switch (to_bar) {
        case 1:
          bar1.push(disk_toMove);
          break;
        case 2:
          bar2.push(disk_toMove);
          break;
        case 3:
          bar3.push(disk_toMove);
          break;
        default:
          assert(false);
          break;
      }
      return 1;
    }

    int first = hanoi(num_disks-1, from_bar, to_bar, second_bar);

    int second = hanoi(1, from_bar, second_bar, to_bar);

    int third = hanoi(num_disks-1, second_bar, from_bar, to_bar);
    
    return first+second+third;
  }
}
