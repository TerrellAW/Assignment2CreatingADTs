package exceptions;

public class NoSuchElementException extends Exception {
	public NoSuchElementException() {
		super();
	}

	public NoSuchElementException( String message ) {
		super( message );
	}
}
