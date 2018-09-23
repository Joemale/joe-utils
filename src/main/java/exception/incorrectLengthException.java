package exception;

/**
 * 长度有误
 */
public class incorrectLengthException extends RuntimeException{

    public incorrectLengthException(String message) {
        super(message);
    }
}
