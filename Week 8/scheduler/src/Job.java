// A class that contains information of a job such as id, priority, unit, and remaining time length.
public class Job implements Comparable<Job> {
  private int id = -1;
  private int priority = -999;
  private int unit = -1;
  private int remainingTimeLength = -1;

  // Set job information when created.
  public Job(int id, int priority, int unit, int remainingTimeLength) throws WrongInputException {
    // Check whether the work information meets the conditions.
    if (priority > 49 || priority < -50) {
      throw new WrongInputException("Priority of a job should be between -50 and 49");
    } else if (unit > 100 || unit < 1) {
      throw new WrongInputException("Unit of a job should be between 1 and 100");
    } else if (remainingTimeLength > 50 || remainingTimeLength < 1) {
      throw new WrongInputException("Length of a job should be between 1 and 50");
    }

    this.id = id;
    this.priority = priority;
    this.unit = unit;
    this.remainingTimeLength = remainingTimeLength;
  }

  // Function for comparing job priority.
  // If the priority number is low, the priority is high, 
  // and if the priority is the same, the smaller id is executed first.
  @Override
  public int compareTo(Job target) {
    if (this.priority > target.priority) {
      return 1;
    } else if (this.priority == target.priority) {
      return this.id > target.id ? 1 : -1;
    } else {
      return -1;
    }
  }

  // String for printing schedule log.
  @Override
  public String toString() {
    if (remainingTimeLength > 0) {
      return "Job ID " + id + " with priority " + priority 
          + " has been processed, remaining time length: " + remainingTimeLength;
    } else {
      return "Job ID " + id + " with priority "
          + priority + " has been processed, the job is done";
    }
  }

  // Increase the job's priority by its unit.
  public void increasePriorityByUnit() {
    priority += unit;
  }

  // Decrease remaining time length.
  // Since it is executed by unit time, the remaining time length decreases by one.
  public void decreaseRemainingLength() {
    remainingTimeLength -= 1;
  }

  public int getId() {
    return id;
  }

  public int getPriority() {
    return priority;
  }

  public int getRemainingTimeLength() {
    return remainingTimeLength;
  }
}
