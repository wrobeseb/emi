package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;

public class Operation60 extends OperationSeries60 {

	private static final String OPERATION_TYPE = OperationType.OT_SESSION_MANAGEMENT_60;
	
	@Override
	public void setDefaults() {
		
		TRN = "01";
		OR = "O";

		ONPI = "5"; 	// SMSC specific: Private (TCP/IP address/abbreviated number)
		VERS = "0100";	// Version number
		OPID = "39";	// PC application
	}

	@Override
	public void setResult(String result) throws ResponseFormatException, ChecksumValidationException {
		this.result = new Result60(result);
	}

	@Override
	public String getOperationId() {
		return OPERATION_TYPE;
	}

}
