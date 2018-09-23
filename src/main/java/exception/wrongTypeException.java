package exception;

/**
 * 类型有误
 */
public class wrongTypeException  extends RuntimeException{

    public wrongTypeException(String message) {
        super(message);
    }
}
