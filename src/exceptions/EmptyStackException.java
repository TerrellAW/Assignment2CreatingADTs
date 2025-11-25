package exceptions;

/**
 * Custom exception class for stack
 */
public class EmptyStackException extends Exception {

    private static final long serialVersionUID = 682267963980463371L;
    /**
     * constructor of an exception
     */
    public EmptyStackException() {
        super();
    }

    /**
     * constructor for an exception with a message paratment
     * @param message the error message
     */
    public EmptyStackException(String message) {
        super(message);
    }
}
