package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;

public abstract class Result extends Message {
	
	public static final String POSITIVE = "A";
	public static final String NEGATIVE = "N";
	
	/*
	 * Positive response
	 */
	public String ACK = "";		// Positive acknowledgement

	/*
	 * Negative response
	 */
	public String NAcK = "";	// Negative acknowledgement
	public String EC   = "";	// Error code

	public String SM  	   = "";		 // System Message
	
	public String AdC = ""; // Address code recipient, maximum length is 16 digits.
	
	public abstract void extract(String messageBody) throws ResponseFormatException, ChecksumValidationException;
	
	public Result() {}
	
	public Result(String message) throws ResponseFormatException, ChecksumValidationException {
		extract(message);
	}
}
