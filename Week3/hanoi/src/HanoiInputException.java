@SuppressWarnings("serial")
// Customer exception for wrong input to calculateHanoiSteps().
class HanoiInputException extends Exception {
    public HanoiInputException (String msg) {
        super(msg);
    }
}
