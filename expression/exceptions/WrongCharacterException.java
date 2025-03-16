package expression.exceptions;

public class WrongCharacterException extends ParseException {
    //    public WrongCharacterException(int pos, String message) {
//        super(pos, message);
//    }
    public WrongCharacterException(String message) {
        super(message);
    }
}