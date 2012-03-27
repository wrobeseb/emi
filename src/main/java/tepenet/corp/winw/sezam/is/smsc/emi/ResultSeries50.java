package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;
import tepenet.corp.winw.sezam.is.smsc.emi.utils.EMIUtils;


public abstract class ResultSeries50 extends Result {

	public String MVP = "";		// Modified Validity Period

	private String SEP  = ":"; // Separator;
	public  String SCTS = "";  // Service Centre time-stamp DDMMYYhhmmss
	
	public ResultSeries50() {}
	
	public ResultSeries50(String message) throws ResponseFormatException, ChecksumValidationException {
		super(message);
	}

	protected static Result getPositiveResultInstance(ResultSeries50 resultInstance, String AdC) {
		resultInstance.ACK = POSITIVE;
		resultInstance.AdC = AdC;
		return resultInstance;
	}
	
	protected static Result getNegativeResultInstance(ResultSeries50 resultInstance, String errorCode) {
		resultInstance.ACK = NEGATIVE;
		resultInstance.EC = errorCode;
		return resultInstance;
	}
	
	@Override
	public void extract(String messageBody) throws ResponseFormatException, ChecksumValidationException {
		
		if (EMIUtils.validate(messageBody)) {
			String header = EMIUtils.extractHeader(messageBody);

			String[] headerFields = EMIUtils.extractFields(header);
			
			if (headerFields.length == 4) {
				TRN = headerFields[0];
				LEN = headerFields[1];
				OR  = headerFields[2];
				OT  = headerFields[3];
			}
			
			CHECKSUM = EMIUtils.extractChecksum(messageBody);
			
			String[] fields = EMIUtils.extractFields(messageBody);
			if (fields.length != 8) throw new ResponseFormatException("Bledna liczba pol w pakiecie: \"" + messageBody + "\"");
		
			if (fields[4].equals("A")) {
				ACK = fields[4];
				MVP = fields[5];
			}
			else { 
				NAcK = fields[4];	
				EC   = fields[5];
			}
			
			SM = fields[6];
		}
	}
	
	@Override
	public String toString() {
		StringBuffer header = new StringBuffer();
		StringBuffer body = new StringBuffer();
		
		if (ACK.equals("A")) {
			body.append(ACK).append(SEPARATOR)
				.append(MVP).append(SEPARATOR);
		}
		else {
			body.append(NAcK).append(SEPARATOR)
				.append(EC).append(SEPARATOR);
		}
		
			if (SCTS.equals("")) SCTS = EMIUtils.timeStamp();
		
			if (SM.equals("")) SM = AdC + SEP + SCTS;
			
			body.append(SM);
			
			LEN = EMIUtils.calculateLength(body.toString());
			
			OT = getOperationId();
			
			header.append(TRN).append(SEPARATOR)
			  	  .append(LEN).append(SEPARATOR)
			  	  .append(OR).append(SEPARATOR)
			  	  .append(OT);
		
			String checksum = EMIUtils.computeChecksum(header + SEPARATOR + body + SEPARATOR);
			
		return STX + header + SEPARATOR + body + SEPARATOR + checksum + ETX;
	}
}
