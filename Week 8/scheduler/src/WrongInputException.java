// Exception for wrong input file of the sheduler.
@SuppressWarnings("serial")
public class WrongInputException extends Exception {
  public WrongInputException(String message) {
    super(message);
  }
}
