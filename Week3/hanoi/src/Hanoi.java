// Calcultate the answer to the hanoi problem with three bars and print the disk movement log.
public class Hanoi {

  // Print the log of disk movement.
  private void moveDisk(int diskNumber, int startingPoint, int destination) {
    System.out.println("Disk " + diskNumber + " moved from bar " + startingPoint 
                        + " to bar " + destination);
  }

  // Calculate the step of moving the number of given disks
  // from startingPoint bar to destination bar. 
  // Print disk movement log during the execution.
  public int calculateHanoiSteps(int numDisks, int startingPoint, 
                                 int mid, int destination) throws HanoiInputException {
    // The number of disks should be a positive integer.
    if (numDisks < 1) {
      throw new HanoiInputException("The number of disks should be a positive number.");
    }

    // The three bars should be different from each other.
    if (startingPoint == mid || mid == destination || destination == startingPoint) {
      throw new HanoiInputException("The three bars should be different from each other.");
    }

    // Base case
    if (numDisks == 1) {
      // Print disk movement log.
      moveDisk(1, startingPoint, destination);
      return 1;
    }

    // Calculate the number of times the top numDisks-1 disks are moved to the mid bar. 
    int prevMove = calculateHanoiSteps(numDisks - 1, startingPoint, destination, mid);
    // The numDisks-th disk is moved to the end.
    moveDisk(numDisks, startingPoint, destination);
    // Calculate the number of times disks in the mid bar are moved to the destination bar.
    int postMove = calculateHanoiSteps(numDisks - 1, mid, startingPoint, destination);
    
    // Return the answer of the Hanoi problem with numDisks
    return prevMove + 1 + postMove;
  }
}
