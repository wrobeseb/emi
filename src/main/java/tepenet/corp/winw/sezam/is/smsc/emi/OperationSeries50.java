package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.emi.utils.EMIUtils;


public abstract class OperationSeries50 extends Operation {
	
	public String AdC 	= "";	// M // Address code recipient for the SM
	public String OAdC  = "";	// M // Address code originator
	public String AC 	= "";	// O // Authentication code originator
	public String NRq	= "";	// O // Notification Request
	public String NAdC  = "";	// O // Notification Address
	public String NT    = "";	// O // Notification Type
	public String NPID  = "";	// O // Notification PID value
	public String LRq   = "";	// O // Last Resort Address request
	public String LRAd  = "";	// M // Last Resort Address
	public String LPID  = "";	// M // LRAD PID value
	public String DD    = "";	// O // Deferred Delivery requested
	public String DDT   = "";	// O // Deferred delivery time in DDMMYYHHmm
	public String VP    = "";	// O // Validity period in DDMMYYHHmm
	public String RPID  = "";	// O // Replace PID value
	public String SCTS  = "";	// - // Service Centre Time Stamp in DDMMYYHHmmss
	public String Dst   = "";	// - // Delivery status
	public String Rsn   = "";	// - // Reason code
	public String DSCTS = "";	// - // Delivery time stamp in DDMMYYHHmmss
	public String MT    = "";  // M // Message Type.
		// 		   MT=2
		public String NB	 = "";  // - // No. of bits in Transparent Data (TD) message.
		public String NMsg  = "";   // O // Numeric message
		//		   MT=3
		//			  NB	   		// - // No. of bits in Transparent Data (TD) message.
		public String AMsg  = "";   // O // Alphanumeric message encoded into IRA characters.
		//		   MT=4
		//		   	  NB	   		// C // No. of bits in Transparent Data (TD) message. This field is M (Mandatory) if the TMsg field is used.
		public String TMsg  = "";   // O // TD message encoded into IRA characters.
	public String MMS   = "";	// O // More Messages to Send (to the same SME)
	public String PR    = "";	// O // Priority Requested
	public String DCs	= "";	// - // Deprecated
	public String MCLs  = "";	// O // Message Class. Shall be supplied when MT=4 and Xser “GSM DCS information” is not supplied.
	public String RPI   = "";	// O // Reply Path
	public String CPg   = "";	// - // (reserved for Code Page)
	public String RPLy  = "";	// - // (reserved for Reply type)
	public String OTOA  = "";	// O // Originator Type Of Address
	public String HPLMN = "";	// - // Home PLMN Address. E.164 number of the originating MSC.
	public String XSer  = "";	// O // Extra Services 
	public String RES4  = "";	// - //
	public String RES5  = "";	// - //
	
	public abstract void setDefaults();
	
	public void extract(String message){
		String body = message;

		if (message.startsWith(Message.STX) && message.endsWith(Message.ETX)) {
			body = EMIUtils.extractBody(message);
			
			String header = EMIUtils.extractHeader(message);

			String[] headerFields = EMIUtils.extractFields(header);
			
			if (headerFields.length == 4) {
				TRN = headerFields[0];
				LEN = headerFields[1];
				OR  = headerFields[2];
				OT  = headerFields[3];
			}
			
			CHECKSUM = EMIUtils.extractChecksum(message);
		}
		
		String[] fields = EMIUtils.extractFields(body);
		
		AdC = fields[0];
		OAdC = fields[1];
		AC = fields[2];
		NRq = fields[3];
		NAdC = fields[4];
		NT = fields[5];
		NPID = fields[6];
		LRq = fields[7];
		LRAd = fields[8];
		LPID = fields[9];
		DD = fields[10];
		DDT = fields[11];
		VP = fields[12];
		RPID = fields[13];
		SCTS = fields[14];
		Dst = fields[15];
		Rsn = fields[16];
		DSCTS = fields[17];
		MT = fields[18];
		NB = fields[19];

		switch (Integer.parseInt(MT)) {
			case 2: NMsg = fields[20]; break;
			case 3: AMsg = fields[20]; break;
			case 4: TMsg = fields[20]; break;
		default:
			break;
		}

		MMS = fields[21];
		PR = fields[22];
		DCs = fields[23];
		MCLs = fields[24];
		RPI = fields[25];
		CPg = fields[26];
		RPLy = fields[27];
		OTOA = fields[28];
		HPLMN = fields[29];
		XSer = fields[30];
		RES4 = fields[31];
		RES5 = fields[32];
	}
	
	public String toString() {

		StringBuffer header = new StringBuffer();
		StringBuffer body = new StringBuffer();
		
		body.append(AdC).append(SEPARATOR)
			  .append(OAdC).append(SEPARATOR)
			  .append(AC).append(SEPARATOR)
			  .append(NRq).append(SEPARATOR)
			  .append(NAdC).append(SEPARATOR)
			  .append(NT).append(SEPARATOR)
			  .append(NPID).append(SEPARATOR)
			  .append(LRq).append(SEPARATOR)
			  .append(LRAd).append(SEPARATOR)
			  .append(LPID).append(SEPARATOR)
			  .append(DD).append(SEPARATOR)
			  .append(DDT).append(SEPARATOR)
			  .append(VP).append(SEPARATOR)
			  .append(RPID).append(SEPARATOR)
			  .append(SCTS).append(SEPARATOR)
			  .append(Dst).append(SEPARATOR)
			  .append(Rsn).append(SEPARATOR)
			  .append(DSCTS).append(SEPARATOR)
			  .append(MT).append(SEPARATOR)
			  .append(NB).append(SEPARATOR);
		
		switch (Integer.parseInt(MT)) {
			case 2: body.append(NMsg).append(SEPARATOR); break;
			case 3: body.append(AMsg).append(SEPARATOR); break;
			case 4: body.append(TMsg).append(SEPARATOR); break;
		default:
			body.append(SEPARATOR); break;
		}

		body.append(MMS).append(SEPARATOR)
	          .append(PR).append(SEPARATOR)
	          .append(DCs).append(SEPARATOR)
	          .append(MCLs).append(SEPARATOR)
	          .append(RPI).append(SEPARATOR)
	          .append(CPg).append(SEPARATOR)
	          .append(RPLy).append(SEPARATOR)
	          .append(OTOA).append(SEPARATOR)
	          .append(HPLMN).append(SEPARATOR)
	          .append(XSer).append(SEPARATOR)
	          .append(RES4).append(SEPARATOR)
	          .append(RES4);
		
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
