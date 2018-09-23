package exception;

/**
 * 路径有误异常
 */
public class incorrectPathException extends RuntimeException{

        public incorrectPathException(String message) {
            super(message);
        }
}
