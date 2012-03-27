package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;

public class Result53 extends ResultSeries50 {
	private static final String OPERATION_TYPE = OperationType.OT_DELIVERY_NOTIFICATION_53;
	private static final String INITIATOR = InitiatorType.SMT;

	public static Result53 getInstance(String message) throws ResponseFormatException, ChecksumValidationException {
		return new Result53(message);
	}
	
	public Result53(String message) throws ResponseFormatException, ChecksumValidationException {
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
