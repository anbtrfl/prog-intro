package expression.exceptions;

public class WrongBracketsException extends ParseException {
    //    public WrongBracketsException(int pos, String message) {
//        super(pos, message);
//    }
    public WrongBracketsException(String message) {
        super(message);
    }
}
