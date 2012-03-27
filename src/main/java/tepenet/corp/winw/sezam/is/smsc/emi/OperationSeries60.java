package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.emi.utils.EMIUtils;

public abstract class OperationSeries60 extends Operation {

	private static final String INITIATOR = InitiatorType.SMT;
	
	public String OAdC = ""; //  Any valid X.121, E.164, TCP/IP or abbreviated address, excluding prefixes
	public String OTON = ""; //  Originator Type of Number:
							 //		1 = International number (starts with the country code)
							 //		2 = National number (default value if omitted)
							 //		6 = Abbreviated number (registered large account identification)
	public String ONPI = ""; //  Originator Numbering Plan Id:
							 //		1 = E.164 address (default value if omitted)
							 //		3 = X121 address
							 //		5 = SMSC specific: Private (TCP/IP address/abbreviated number)
	public String STYP = ""; //  Subtype of operation:
							 //		1 = open session
							 //		2 = reserved
							 //		3 = change password
							 //		4 = open provisioning session
							 //		5 = reserved
							 //		6 = change provisioning password
	public String PWD = "";	 //  Current password encoded into IRA characters
	public String NPWD = ""; //	 New password encoded into IRA characters
	public String VERS = ""; //  Version number ‘0100'
	public String LAdC = ""; //  Address for VSMSC list operation
	public String LTON = ""; //  Type of Number list address
	public String LNPI = ""; //  Numbering Plan Id list address
	public String OPID = ""; //  Originator Protocol Identifier:
							 //		00 = Mobile station
							 //		39 = PC application
	public String RES1 = "";
	
	public abstract void setDefaults();

	@Override
	public String toString() {
		
		setDefaults();
		
		StringBuffer header = new StringBuffer();
		StringBuffer body = new StringBuffer();
		
		body.append(OAdC).append(SEPARATOR)
			.append(OTON).append(SEPARATOR)
			.append(ONPI).append(SEPARATOR)
			.append(STYP).append(SEPARATOR)
			.append(PWD).append(SEPARATOR)
			.append(NPWD).append(SEPARATOR)
			.append(VERS).append(SEPARATOR)
			.append(LAdC).append(SEPARATOR)
			.append(LTON).append(SEPARATOR)
			.append(LNPI).append(SEPARATOR)
			.append(OPID).append(SEPARATOR)
			.append(RES1);
		
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
