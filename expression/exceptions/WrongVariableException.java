package expression.exceptions;

public class WrongVariableException extends ParseException {
    //    public WrongVariableException(int pos, String message) {
//        super(pos, message);
//    }
    public WrongVariableException(String message) {
        super(message);
    }

}
