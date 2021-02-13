// Calcultate the answer to the hanoi problem with three bars and print the disk movement log.
public class Hanoi {

  // Print the log of disk movement.
  private void moveDisk(int diskNumber, int startingPoint, int destination) {
    System.out.println("Disk " + diskNumber + " moved from bar " + startingPoint 
                        + " to bar " + destination);
  }

  // Returns the answer of Hanoi problem with given numDisks.
  public int calculateHanoiSteps(int numDisks, int startingPoint, 
                                 int mid, int destination) throws HanoiInputException {
    // The number of disks should be a positive integer.
    if (numDisks < 1) {
      throw new HanoiInputException("The number of disks should be a positive number.");
    }

    // Base case
    if (numDisks == 1) {
      // Print disk movement log.
      moveDisk(1, startingPoint, destination);
      return 1;
    }

    // Return the answer of the Hanoi problem with numDisks
    int first = calculateHanoiSteps(numDisks - 1, startingPoint, destination, mid);
    moveDisk(numDisks, startingPoint, destination);
    int third = calculateHanoiSteps(numDisks - 1, mid, startingPoint, destination);
    
    return first + 1 + third;
  }
}
