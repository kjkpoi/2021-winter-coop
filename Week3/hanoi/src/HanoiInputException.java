// Customer exception for wrong input to calculateHanoiSteps().
@SuppressWarnings("serial")
class HanoiInputException extends Exception {
  public HanoiInputException(String msg) {
    super(msg);
  }
}
