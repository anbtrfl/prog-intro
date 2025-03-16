package expression.exceptions;

public class WrongConstException extends ParseException {
    //    public WrongConstException(int pos, String message) {
//        super(pos, message);
//    }
    public WrongConstException(String message) {
        super(message);
    }
}
