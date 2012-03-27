package tepenet.corp.winw.sezam.is.smsc.emi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.GSM7BitAlphabetTable;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;
import tepenet.corp.winw.sezam.is.smsc.emi.Message;

public class EMIUtils {

	public static String sevenToEight(String text) {
		
		String encodedText = encodeIRA(text);
		
		String bitsRepresentationOfEncodedText = getBitRepresentationOfGivenText(encodedText, 7);
		
		Integer leadingZerosNo = 8 - (bitsRepresentationOfEncodedText.length() % 8);
		
		bitsRepresentationOfEncodedText = appendLeadingZeros(bitsRepresentationOfEncodedText, leadingZerosNo);
		
		String[] bytes = split(bitsRepresentationOfEncodedText, 8);
		
		String result = "";
		
		for (String tByte : bytes) {
			result = Integer.toHexString(Integer.parseInt(tByte, 2)).concat(result);
		}
		
		return Integer.toHexString(result.length()).concat(result).toUpperCase();
	}
	
	public static String timeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	private static String[] split(String text, Integer sliceLength) {
		String[] pairs = new String[text.length() / sliceLength];
		
		int index = 0;
		for (int i = 0; i < text.length(); i += sliceLength)
			if (i + sliceLength <= text.length()) {
				pairs[index] = text.substring(i, i + sliceLength);
				index++;
			}
		
		return pairs;
	}
	
	private static String[] splitFromEnd(String text, Integer sliceLength) {
		String[] slices = new String[text.length() / sliceLength];
		
		int index = 0;
		for (int i = text.length(); i >= 0; i-= sliceLength) {
			if (i - sliceLength >= 0) {
				slices[index] = text.substring(i - sliceLength, i);
				index++;
			}
		}
		
		return slices;
	}
	
	private static String appendLeadingZeros(String binaryRepresentation, int leadingZerosNo) {
		for (int i = 0; i < leadingZerosNo; i++) binaryRepresentation = "0".concat(binaryRepresentation);
		return binaryRepresentation;
	}
	
	private static String getBitRepresentationOfGivenText(String text, Integer length) {
		
		String[] pairs = split(text, 2);
		
		String bitRepresentationOfGivenPairs = "";
		
		for (String pair : pairs) {
			String tBits = Integer.toBinaryString(Integer.parseInt(pair, 16));
			
			if (tBits.length() > length) tBits = tBits.substring(tBits.length() - length, tBits.length());
			if (tBits.length() < length) tBits = appendLeadingZeros(tBits, length - tBits.length());
			
			bitRepresentationOfGivenPairs = tBits.concat(bitRepresentationOfGivenPairs);
		}
		
		return bitRepresentationOfGivenPairs;
	}
	
	public static String originatorExtractor(String originator, String OTOA) {
		if (OTOA != null && OTOA.equals("5039")) {
			return sevenToEight(originator);
		}
		return null;
	}
	
	public static String eightToSeven(String s) {
		String bitsRepresentationOfEncodedText = getBitRepresentationOfGivenText(s.substring(2, s.length()), 8);
		
		String[] bytes = splitFromEnd(bitsRepresentationOfEncodedText, 7);
		
		String result = "";
		
		for (String tByte : bytes) {
			String hex = Integer.toHexString(Integer.parseInt(tByte, 2));
			hex = appendLeadingZeros(hex, 2 - hex.length());
			result = result.concat(hex);
		}
		
		result = decodeIRA(result);
		
		return result;
	}

	public static String decodeIRA(String s)
	{
		StringBuffer result = new StringBuffer();
		
		String[] pairs = split(s, 2);
		
		for (String pair : pairs)
			result.append(GSM7BitAlphabetTable.getChar(Integer.parseInt(pair.substring(0, 1), 16), 
													   Integer.parseInt(pair.substring(1, 2), 16)));
		return result.toString();
	}
	
	public static String encodeIRA(String s) {
		StringBuffer result = new StringBuffer();
		
		for (char tChar : s.toCharArray()) {
			result.append(GSM7BitAlphabetTable.getCode(tChar));
		}
		
		return result.toString().toUpperCase();
	}
	 
		public static String computeChecksum(String message) {
			if (message.startsWith(Message.STX))
				message = message.substring(1, message.length() - 3);
			
	    	int checksum = 0;
	    	
	    	for (char tChar : message.toCharArray()) {
	    		checksum += tChar;
				checksum &= 0xff;
			}
	    	
	    	return Integer.toHexString(checksum).toUpperCase();
		}
		
		public static String extractChecksum(String message) {
			if (!checkMessage(message)) return null;
			
			return message.substring(message.length() - 3, message.length() - 1);
		}
		
		public static String extractBody(String message) {
			if (!checkMessage(message)) return null;
			
			return message.substring(15, message.length() - 3);
		}
		
		public static String extractHeader(String message) {
			if (!checkMessage(message)) return null;
		
			return message.substring(1, 13);
		}
		
		public static String[] extractFields(String message) {
			return message.split(Message.SEPARATOR, -2);
		}
		
		public static boolean validate(String message) throws ResponseFormatException, ChecksumValidationException {
			if (!message.startsWith(Message.STX)) throw new ResponseFormatException("Brak znaku poczatku STX: \"" + message + "\"");
			if (!message.endsWith(Message.ETX))   throw new ResponseFormatException("Brak znaku konca ETX: \"" + message + "\"");
			
			if (!validateChecksum(message)) throw new ChecksumValidationException("Wyliczona suma kontrolna jest inna od zawartej w pakiecie.");
		
			return true;
		}
		
		public static String calculateLength(String body) {
			
			String length = String.valueOf(body.length() + 17);
			
			while(length.length() < 5) length = "0".concat(length);
			
			return length;
		}
		
		private static boolean validateChecksum(String body) {
			String computedChecksum = EMIUtils.computeChecksum(body);
			String checksumFromMessage = EMIUtils.extractChecksum(body);
			
			if (computedChecksum.equals(checksumFromMessage)) return true;
			else return false;
		}
		
		private static Boolean checkMessage(String message) {
			if (message == null || message.isEmpty()) return false;
			if (!message.startsWith(Message.STX) || !message.endsWith(Message.ETX)) return false;
			
			return true;
		}
		
}
