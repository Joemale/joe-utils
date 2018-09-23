package exception;

/**
 * 访问出错
 */
public class accessErrorException extends RuntimeException{

    public accessErrorException(String message) {
        super(message);
    }
}

