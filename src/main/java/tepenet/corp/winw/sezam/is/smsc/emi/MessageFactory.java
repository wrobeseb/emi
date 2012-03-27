package tepenet.corp.winw.sezam.is.smsc.emi;


//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;

//import org.apache.mina.core.buffer.IoBuffer;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.OperationTypeUnknownException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;
//import tepenet.corp.winw.sezam.is.smsc.emi.utils.EMIUtils;

public class MessageFactory {

	public static Message createInstanceOfMessage(String OT, String OR, String message) throws ResponseFormatException, ChecksumValidationException, OperationTypeUnknownException {
		
		Integer iOT = Integer.parseInt(OT);
		
		Message messageObj = null;
		
		switch (iOT) {
			case 51:
				messageObj = OR.equals("O") ? Operation51.getInstance(message) : Result51.getInstance(message);
				break;
			case 52:
				messageObj = OR.equals("O") ? Operation52.getInstance(message) : Result52.getInstance(message);
				break;
			case 53:
				messageObj = OR.equals("O") ? Operation53.getInstance(message) : Result53.getInstance(message);
				break;
			default:
				throw new OperationTypeUnknownException("Operacja \"" + OT + "\" nie jest obslugiwana.");
		}
		
		return messageObj;
	}
	
	/*
	 * apache mina required
	 */
	/*public static Message createInstanceOfMessage(IoBuffer slice) throws IOException, ResponseFormatException, ChecksumValidationException, OperationTypeUnknownException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(slice.asInputStream()));
		
		String message = reader.readLine();
		
		if (EMIUtils.validate(message)) {
			String OR = message.substring(10, 11);
			String OT = message.substring(12, 14);
			
			return createInstanceOfMessage(OT, OR, message);
		}
		
		return null;
	}*/
}
