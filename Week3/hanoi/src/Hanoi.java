// Set bars to execute hanoi algorithm.
public class Hanoi {

  int numDisks = 0;

  // Initialize the numDisks and put disks in bar1 from larger disks to smaller disks.
  // Numbers in the bar mean the size of a disk.
  public Hanoi(int numDisks) {
    this.numDisks = numDisks;
  }

  // Print log of disk movement.
  private void moveDisk(int diskNumber, int fromBar, int toBar) {
    System.out.println("Disk " + diskNumber + " moved from bar " + fromBar + " to bar " + toBar);
  }

  // Returns the answer of Hanoi problem with initialized numDisks
  public int calculateHanoiSteps() throws HanoiInputException {
    return calculateHanoiSteps(numDisks, 1, 2, 3);
  }

  // Returns the answer of Hanoi problem with given numDisks.
  // Prints disk movement log.
  public int calculateHanoiSteps(int numDisks, int fromBar, 
                                 int secondBar, int toBar) throws HanoiInputException {
    // The number of disks should be a positive integer.
    if (numDisks < 1) {
      throw new HanoiInputException("The number of disks should be a positive number.");
    }

    // from bar, second bar and to bar numbers should be beteen 0 and 4, exclusively.
    if ((fromBar < 1) || (secondBar < 1) || (toBar < 1) 
        || (fromBar > 3) || (secondBar > 3) || (toBar > 3)) {
      throw new HanoiInputException("Bar number should be 1 or 2 or 3.");
    }
    if ((fromBar == secondBar) || (fromBar == toBar) || (secondBar == toBar)) {
      throw new HanoiInputException("Bar numbers should not be same.");
    }

    // Base case
    if (numDisks == 1) {
      // Print disk movement log.
      moveDisk(1, fromBar, toBar);
      return 1;
    }

    // Return the answer of the Hanoi problem with numDisks
    int first = calculateHanoiSteps(numDisks - 1, fromBar, toBar, secondBar);
    moveDisk(numDisks, fromBar, toBar);
    int third = calculateHanoiSteps(numDisks - 1, secondBar, fromBar, toBar);
    
    return first + 1 + third;
  }
}
