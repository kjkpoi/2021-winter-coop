import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    int numDisks = 0;  // The number of disks to move from fromBar to toBar.

    // Get the number of disks from System.in
    Scanner sc = new Scanner(System.in);
    numDisks = sc.nextInt();

    // Print disk movement log and the answer.
    Hanoi hanoi = new Hanoi();
    int ans = 0;
    try {
      ans = hanoi.calculateHanoiSteps(numDisks, 1, 2, 3);
      System.out.println("Answer: " + ans);
    } catch (HanoiInputException e) {
      System.out.println(e);
    }

    sc.close();
    return;
  }
}
