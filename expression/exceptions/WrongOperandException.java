package expression.exceptions;

public class WrongOperandException extends ParseException {
    //    public WrongOperandException(int pos, String message) {
//        super(pos, message);
//    }
    public WrongOperandException(String message) {
        super(message);
    }

}