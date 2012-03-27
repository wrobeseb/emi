package tepenet.corp.winw.sezam.is.smsc.emi;

// 00/00000/O/51/body/checksum
public abstract class Message {
	
	public final static String SEPARATOR = "/";
	
	public final static String STX = "\002";
	public final static String ETX = "\003";
	
	// HEADER
	protected String TRN = "";
	protected String LEN = "";
	protected String OR  = "";
	protected String OT  = "";
	
	// CHECKSUM
	protected String CHECKSUM = "";
	
	public abstract String toString();
	
	public abstract String getOperationId();
	
	public abstract String getInitiator();
}
