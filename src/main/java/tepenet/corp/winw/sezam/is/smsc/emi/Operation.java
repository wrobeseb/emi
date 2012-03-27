package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;

public abstract class Operation extends Message {
	
	protected Result result;
	
	public abstract void setResult(String result) throws ResponseFormatException, ChecksumValidationException;
	
	public void setResult(Result result) {
		this.result = result;
	}
	
	public Result getResult() {
		return this.result;
	}
}
