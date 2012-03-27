package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;

public class Result51 extends ResultSeries50 {
	private static final String OPERATION_TYPE = OperationType.OT_SUBMIT_SHORT_MESSAGE_51;
	private static final String INITIATOR = InitiatorType.SMSC;
	
	private Result51() {}
	
	public static Result51 getPositiveResultInstance(String AdC) {
		return (Result51) getPositiveResultInstance(new Result51(), AdC);
	}
	
	public static Result51 getNegativeResultInstance(String errorCode) {
		return (Result51) getNegativeResultInstance(new Result51(), errorCode);
	}
	
	public static Result51 getInstance(String message) throws ResponseFormatException, ChecksumValidationException {
		return new Result51(message);
	}
	
	public Result51(String message) throws ResponseFormatException, ChecksumValidationException {
		super(message);
	}
	
	@Override
	public String getOperationId() {
		return OPERATION_TYPE;
	}

	@Override
	public String getInitiator() {
		return INITIATOR;
	}

	
}
