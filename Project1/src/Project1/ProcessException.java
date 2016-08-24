package Project1;

public class ProcessException extends Exception{
	public ProcessException(String message) {
		super(message);
	}

	public ProcessException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
