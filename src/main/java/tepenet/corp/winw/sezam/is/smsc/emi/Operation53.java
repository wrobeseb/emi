package tepenet.corp.winw.sezam.is.smsc.emi;


public class Operation53 extends OperationSeries50 {

	private static final String OPERATION_TYPE = OperationType.OT_DELIVERY_NOTIFICATION_53;
	private static final String OPERATION_INITIATOR = InitiatorType.SMSC;
	
	public static Operation53 getInstance(String message) {
		return new Operation53(message);
	}
	
	public Operation53(String message) {
		extract(message);
	}
	
	@Override
	public void setDefaults() {
		OR = "O";
	}

	@Override
	public void setResult(String result) {
		
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
