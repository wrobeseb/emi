package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;

public class Result60 extends ResultSeries60 {

	private static final String OPERATION_TYPE = OperationType.OT_SESSION_MANAGEMENT_60;
	
	public Result60(String message) throws ResponseFormatException, ChecksumValidationException {
		super(message);
	}
	
	@Override
	public String getOperationId() {
		return OPERATION_TYPE;
	}
}
