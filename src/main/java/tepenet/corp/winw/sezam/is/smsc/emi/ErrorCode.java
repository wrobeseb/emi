package tepenet.corp.winw.sezam.is.smsc.emi;

public interface ErrorCode {
	public static final String CHECKSUM_ERROR_01 							 		= "01"; // Checksum error
	public static final String SYNTAX_ERROR_02 								 		= "02"; // Syntax error
	public static final String OPERATION_NOT_SUPPORTED_BY_SYSTEM_03 		 		= "03"; // Operation not supported by system
	public static final String OPERATION_NOT_ALLOWED_04 					 		= "04"; // Operation not allowed
	public static final String CALL_BARRING_ACTIVE_05 						 		= "05"; // Call barring active
	public static final String ADC_INVALID_06 								 		= "06"; // AdC invalid
	public static final String AUTHENTICATION_FAILURE_07 					 		= "07"; // Authentication failure
	public static final String LEGITIMISATION_CODE_FOR_ALL_CALLS_FAILURE_08  		= "08"; // Legitimisation code for all calls, failure
	public static final String GA_NOT_VALID_09 								 		= "09"; // GA not valid
	public static final String REPETITION_NOT_ALLOWED_10					 	 	= "10"; // Repetition not allowed
	public static final String LEGITIMISATION_CODE_FOR_REPETITION_FAILURE_11 	 	= "11"; // Legitimisation code for repetition, failure
	public static final String PRIORITY_CALL_NOT_ALLOWED_12					 		= "12"; // Priority call not allowed
	public static final String LEGITIMISATION_CODE_FOR_PRIORITY_CALL_FAILURE_13  	= "13"; // Legitimisation code for priority call, failure
	public static final String URGENT_MESSAGE_NOT_ALLOWED_14				 	 	= "14"; // Urgent message not allowed
	public static final String LEGITIMISATION_CODE_FOR_URGENT_MESSAGE_FAILURE_15	= "15"; // Legitimisation code for urgent message, failure
	public static final String REVERSE_CHARGING_NOT_ALLOWED_16					    = "16"; // Reverse charging not allowed
	public static final String LEGITIMISATION_CODE_FOR_REV_CHARGING_FAILURE_17		= "17"; // Legitimisation code for rev. charging, failure
	public static final String DEFERRED_DELIVERY_NOT_ALLOWED_18					    = "18"; // Deferred delivery not allowed
	public static final String NEW_AC_NOT_VALID_19									= "19"; // New AC not valid
	public static final String NEW_LEGITIMISATION_CODE_NOT_VALID_20					= "20"; // New legitimisation code not valid
	public static final String STANDARD_TEXT_NOT_VALID_21							= "21"; // Standard text not valid
	public static final String TIME_PERIOD_NOT_VALID_22								= "22"; // Time period not valid
	public static final String MESSAGE_TYPE_NOT_SUPPORTED_BY_SYSTEM_23				= "23"; // Message type not supported by system
	public static final String MESSAGE_TOO_LONG_24									= "24"; // Message too long
	public static final String REQUESTED_STANDARD_TEXT_NOT_VALID_25					= "25"; // Requested standard text not valid
	public static final String MESSAGE_TYPE_NOT_VALID_FOR_THE_PAGER_TYPE_26			= "26"; // Message type not valid for the pager type
	public static final String MESSAGE_NOT_FOUND_IN_SMSC_27							= "27"; // Message not found in smsc
	public static final String SUBSCRIBER_HANG_UP_30								= "30"; // Subscriber hang-up
	public static final String FAX_GROUP_NOT_SUPPORTED_31							= "31"; // Fax group not supported
	public static final String FAX_MESSAGE_TYPE_NOT_SUPPORTED_32					= "32"; // Fax message type not supported
	public static final String ADDRESS_ALREADY_IN_LIST_60_SERIES_33					= "33"; // Address already in list (60 series)
	public static final String ADDRESS_NOT_IN_LIST_60_SERIES_34						= "34"; // Address not in list (60 series)
	public static final String LIST_FULL_CANNOT_ADD_ADDRESS_TO_LIST_60_SERIES_35	= "35"; // List full, cannot add address to list (60 series)
	public static final String RPID_ALREADY_IN_USE_36								= "36"; // RPID already in use
	public static final String DELIVERY_IN_PROGRESS_37								= "37"; // Delivery in progress
	public static final String MESSAGE_FORWARDED_38									= "38"; // Message forwarded
}
