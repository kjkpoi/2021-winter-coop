import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    // Get num of disks from System.in
    // The number of disks should be more than 0.
    int numDisks = 0;
    Scanner sc = new Scanner(System.in);

    if ((numDisks = sc.nextInt()) < 1) {
      System.out.println("The number of disks cannot be negetive integer or zero.");
      sc.close();
      return;
    }

    Hanoi hanoiAlgorithm = new Hanoi(numDisks);

    // Print disk movement log and the answer.
    int ans = 0;
    try {
      ans = hanoiAlgorithm.calculateHanoiSteps();
    } catch (HanoiInputException e) {
      System.out.println(e);
    }
    System.out.println("Answer: " + ans);

    sc.close();
    return;
  }
}
