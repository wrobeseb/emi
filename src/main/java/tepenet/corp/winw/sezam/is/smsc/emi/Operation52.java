package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;

public class Operation52 extends OperationSeries50 {

	private static final String OPERATION_TYPE = OperationType.OT_DELIVERY_SHORT_MESSAGE_52;
	private static final String OPERATION_INITIATOR = InitiatorType.SMSC;
	
	public static Operation52 getInstance(String message) {
		return new Operation52(message);
	}
	
	public Operation52(String message) {
		extract(message);
	}
	
	@Override
	public void setDefaults() {
		OR = "O";
	}

	@Override
	public void setResult(String result) throws ResponseFormatException, ChecksumValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getOperationId() {
		return OPERATION_TYPE;
	}

	@Override
	public String getInitiator() {
		return OPERATION_INITIATOR;
	}

}
