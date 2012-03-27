package tepenet.corp.winw.sezam.is.smsc;

public class ChecksumValidationException extends Exception {

	private static final long serialVersionUID = 685192693169536764L;

	public ChecksumValidationException() {
		super();
	}

	public ChecksumValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChecksumValidationException(String message) {
		super(message);
	}

	public ChecksumValidationException(Throwable cause) {
		super(cause);
	}
}
