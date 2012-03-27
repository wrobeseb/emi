package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;
import tepenet.corp.winw.sezam.is.smsc.emi.utils.EMIUtils;

public abstract class ResultSeries60 extends Result {

	private static final String INITIATOR = InitiatorType.SMSC;
	
	public ResultSeries60(String message) throws ResponseFormatException, ChecksumValidationException {
		super(message);
	}
	
	@Override
	public void extract(String messageBody) throws ResponseFormatException, ChecksumValidationException {
		if (EMIUtils.validate(messageBody)) {
			String[] fields = EMIUtils.extractFields(messageBody);
			if (fields.length != 8 && fields.length != 7) throw new ResponseFormatException("Bledna liczba pol w pakiecie: \"" + messageBody + "\"");
		
			if (fields[4].equals("A")) {
				ACK = fields[4];
				SM = fields[5];
			}
			else { 
				NAcK = fields[4];	
				EC   = fields[5];
				SM = fields[6];
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer header = new StringBuffer();
		StringBuffer body = new StringBuffer();
		
		if (ACK.equals("A")) {
			body.append(ACK).append(SEPARATOR);
		}
		else {
			body.append(NAcK).append(SEPARATOR)
				.append(EC).append(SEPARATOR);
		}
		
		LEN = EMIUtils.calculateLength(body.toString());
		
		OT = getOperationId();
		
		header.append(TRN).append(SEPARATOR)
		  	  .append(LEN).append(SEPARATOR)
		  	  .append(OR).append(SEPARATOR)
		  	  .append(OT);
	
		String checksum = EMIUtils.computeChecksum(header + SEPARATOR + body + SEPARATOR);
		
		return STX + header + SEPARATOR + body + SEPARATOR + checksum + ETX;
	}

	@Override
	public String getInitiator() {
		return INITIATOR;
	}

}
