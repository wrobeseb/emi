package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;

public class Result52 extends ResultSeries50 {
	private static final String OPERATION_TYPE = OperationType.OT_DELIVERY_SHORT_MESSAGE_52;
	private static final String INITIATOR = InitiatorType.SMT;
	
	public static Result52 getInstance(String message) throws ResponseFormatException, ChecksumValidationException {
		return new Result52(message);
	}
	
	public Result52(String message) throws ResponseFormatException, ChecksumValidationException {
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
