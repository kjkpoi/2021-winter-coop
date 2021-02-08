//Exception for invalid symbols or size error in input the map. 
@SuppressWarnings("serial")
public class GemMapInputException extends Exception {
    public GemMapInputException(String msg) {
        super(msg);
      }
}
