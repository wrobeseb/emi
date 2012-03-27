package tepenet.corp.winw.sezam.is.smsc.emi;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;
import tepenet.corp.winw.sezam.is.smsc.emi.utils.EMIUtils;

/**
 * Submit Short Message operation - 51
 * 
 * This operation is used to submit a Short Message to the SMSC. The operation can be used
 * for Short Messages with an alphanumeric or a binary message text field. In the latter case
 * the MT parameter shall be set to “4”.
 * 
 * • If the AC field is used, it should contain at least 4 numeric characters in every message,
 *   which are not all equal to zero, otherwise it shall be rejected.
 * • If the option ‘Long Message’ is not supported on the SMSC, the maximum length of
 *   AMsg represents 160 characters and NMsg is 160 digits.
 * • If NRq is used, and NAdC and NPID are both used, then this address will be used as
 *   notification address.
 * • If NRq is used, and NAdC or NPID or both are left empty, then the notification is sent to
 *   the originator in the current session. If in this case :
 *   
 *   · the session is ended,
 *   · the originator is not known to the SMSC to have more than one address,
 *   · the originator is not a mobile user submitting messages via a UCP application (option ‘Mobile Subscriber Access via Fixed Network’)
 *   · and the notification has not yet been delivered, 
 *   
 *   then the notification is deleted by the SMSC.
 * • If LRq is used, and LRAd and LPID are both used, then this address (user supplied) will
 *   be used as Last Resort address.
 * • If LRq is used, and LRAd or LPID or both are left empty, then the Last Resort address is
 *   the current session. If in this case:
 *   
 *   · the session is ended,
 *   · the originator is not known to the SMSC to have more than one address,
 *   · and the short message has not yet been delivered,
 *   
 *   then the short message is deleted by the SMSC.
 * • If LRq is empty, the contents of LRAd and LPID are ignored.
 * • If DD is used, then DDT is mandatory.
 * • The priority message field PR can only be used if the originator is subscribed to this service.
 * • If RPID value 0127 (SIM Data Download) is used, MT must be 4 and either MCLs must
 *   be 2 or Xser “GSM DCS information” must be 0xF6 otherwise the message is rejected.
 *   RPID value 0127 (SIM Data Download) is only supported for registered large accounts.
 *   Last resort addressing and Reply path functionality is not applicable to this type of
 *   message. The contents of LRq and RPl is ignored.
 * • If the MCLs field is also specified, the GSM DCS information field in the XSER field
 *   overrules the MCLs field.
 * • If the originator of the UCP51 message is not registered in the SMSC as being a large
 *   account and the Billing Identifier in the XSER field is used, then the UCP51 operation will
 *   be rejected with error code 04 "Operation not allowed".
 */
public class Operation51 extends OperationSeries50 {
	
	private static final String OPERATION_TYPE = OperationType.OT_SUBMIT_SHORT_MESSAGE_51;
	private static final String OPERATION_INITIATOR = InitiatorType.SMT;
	
	public static Operation51 getInstance(String message) {
		return new Operation51(message);
	}
	
	public Operation51(String message) {
		extract(message);
	}
	
	public Operation51(String originator, String recipient, String text) {
		setDefaults();
		
		AdC = recipient;
		OAdC = EMIUtils.originatorExtractor(originator, OTOA);
		switch (Integer.parseInt(MT)) {
			case 2: NMsg = ""; break;
			case 3: AMsg = EMIUtils.encodeIRA(text); break;
			case 4: TMsg = ""; break;
		default:
			break;
		}
	}

	@Override
	public void setDefaults() {
		TRN = "01";
		OR = "O";
		
		NRq  = "1";		// NAdC used
		NT   = "7";		// All notifications
		MT   = "3";		// Alphanumeric message encoded into IRA characters
		OTOA = "5039";	// The OAdC contains an alphanumeric address
	}

	@Override
	public void setResult(String result) throws ResponseFormatException, ChecksumValidationException {
		this.result = new Result51(result);
		this.result.AdC = AdC;
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
