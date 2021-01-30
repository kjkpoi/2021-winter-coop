import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    /* Get num_disks from System.in */
    int num_disks;

    Scanner sc = new Scanner(System.in);

    if ((num_disks = sc.nextInt()) < 1) {
      System.out.println("Wrong input.");
      sc.close();
      return;
    }

    /* Execute hanoi function */
    Hanoi hanoiAlgorithm = new Hanoi(num_disks);
    
    hanoiAlgorithm.hanoi(num_disks, 1, 2, 3);

    sc.close();
    return;
  }
}
