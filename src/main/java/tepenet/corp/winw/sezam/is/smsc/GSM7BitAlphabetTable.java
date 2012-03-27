package tepenet.corp.winw.sezam.is.smsc;

import java.util.ArrayList;

public class GSM7BitAlphabetTable {

	public static ArrayList<GSM7BitChar> alphabet;
	
	static {
		alphabet = new ArrayList<GSM7BitAlphabetTable.GSM7BitChar>();
		
		alphabet.add(new GSM7BitChar(2,0, ' '));
		alphabet.add(new GSM7BitChar(2,1,'!'));
		alphabet.add(new GSM7BitChar(2,5,'%'));
		alphabet.add(new GSM7BitChar(2,8,'('));
		alphabet.add(new GSM7BitChar(2,11,'+'));
		alphabet.add(new GSM7BitChar(2,14,'.'));
		alphabet.add(new GSM7BitChar(3,1,'1'));
		alphabet.add(new GSM7BitChar(3,4,'4'));
		alphabet.add(new GSM7BitChar(3,7,'7'));
		alphabet.add(new GSM7BitChar(3,10,':'));
		alphabet.add(new GSM7BitChar(3,13,'='));
		alphabet.add(new GSM7BitChar(4,1,'A'));
		alphabet.add(new GSM7BitChar(4,4,'D'));
		alphabet.add(new GSM7BitChar(4,7,'G'));
		alphabet.add(new GSM7BitChar(4,10,'J'));
		alphabet.add(new GSM7BitChar(4,13,'M'));
		alphabet.add(new GSM7BitChar(5,0,'P'));
		alphabet.add(new GSM7BitChar(5,3,'S'));
		alphabet.add(new GSM7BitChar(5,6,'V'));
		alphabet.add(new GSM7BitChar(5,9,'Y'));
		alphabet.add(new GSM7BitChar(6,2,'b'));
		alphabet.add(new GSM7BitChar(6,5,'e'));
		alphabet.add(new GSM7BitChar(6,8,'h'));
		alphabet.add(new GSM7BitChar(6,11,'k'));
		alphabet.add(new GSM7BitChar(6,14,'n'));
		alphabet.add(new GSM7BitChar(7,1,'q'));
		alphabet.add(new GSM7BitChar(7,4,'t'));
		alphabet.add(new GSM7BitChar(7,7,'w'));
		alphabet.add(new GSM7BitChar(7,10,'z'));
		alphabet.add(new GSM7BitChar(2,2,'"'));
		alphabet.add(new GSM7BitChar(2,6,'&'));
		alphabet.add(new GSM7BitChar(2,9,')'));
		alphabet.add(new GSM7BitChar(2,12,','));
		alphabet.add(new GSM7BitChar(2,15,'/'));
		alphabet.add(new GSM7BitChar(3,2,'2'));
		alphabet.add(new GSM7BitChar(3,5,'5'));
		alphabet.add(new GSM7BitChar(3,8,'8'));
		alphabet.add(new GSM7BitChar(3,11,';'));
		alphabet.add(new GSM7BitChar(3,14,'>'));
		alphabet.add(new GSM7BitChar(4,2,'B'));
		alphabet.add(new GSM7BitChar(4,5,'E'));
		alphabet.add(new GSM7BitChar(4,8,'H'));
		alphabet.add(new GSM7BitChar(4,11,'K'));
		alphabet.add(new GSM7BitChar(4,14,'N'));
		alphabet.add(new GSM7BitChar(5,1,'Q'));
		alphabet.add(new GSM7BitChar(5,4,'T'));
		alphabet.add(new GSM7BitChar(5,7,'W'));
		alphabet.add(new GSM7BitChar(5,10,'Z'));
		alphabet.add(new GSM7BitChar(6,3,'c'));
		alphabet.add(new GSM7BitChar(6,6,'f'));
		alphabet.add(new GSM7BitChar(6,9,'i'));
		alphabet.add(new GSM7BitChar(6,12,'l'));
		alphabet.add(new GSM7BitChar(6,15,'o'));
		alphabet.add(new GSM7BitChar(7,2,'r'));
		alphabet.add(new GSM7BitChar(7,5,'u'));
		alphabet.add(new GSM7BitChar(7,8,'x'));
		alphabet.add(new GSM7BitChar(2,3,'#'));
		alphabet.add(new GSM7BitChar(2,7,'\''));
		alphabet.add(new GSM7BitChar(2,10,'*'));
		alphabet.add(new GSM7BitChar(2,13,'-'));
		alphabet.add(new GSM7BitChar(3,0,'0'));
		alphabet.add(new GSM7BitChar(3,3,'3'));
		alphabet.add(new GSM7BitChar(3,6,'6'));
		alphabet.add(new GSM7BitChar(3,9,'9'));
		alphabet.add(new GSM7BitChar(3,12,'<'));
		alphabet.add(new GSM7BitChar(3,15,'?'));
		alphabet.add(new GSM7BitChar(4,3,'C'));
		alphabet.add(new GSM7BitChar(4,6,'F'));
		alphabet.add(new GSM7BitChar(4,9,'I'));
		alphabet.add(new GSM7BitChar(4,12,'L'));
		alphabet.add(new GSM7BitChar(4,15,'O'));
		alphabet.add(new GSM7BitChar(5,2,'R'));
		alphabet.add(new GSM7BitChar(5,5,'U'));
		alphabet.add(new GSM7BitChar(5,8,'X'));
		alphabet.add(new GSM7BitChar(6,1,'a'));
		alphabet.add(new GSM7BitChar(6,4,'d'));
		alphabet.add(new GSM7BitChar(6,7,'g'));
		alphabet.add(new GSM7BitChar(6,10,'j'));
		alphabet.add(new GSM7BitChar(6,13,'m'));
		alphabet.add(new GSM7BitChar(7,0,'p'));
		alphabet.add(new GSM7BitChar(7,3,'s'));
		alphabet.add(new GSM7BitChar(7,6,'v'));
		alphabet.add(new GSM7BitChar(7,9,'y'));
	}
	
	public static String getCode(char character) {
		GSM7BitChar gsm7BitChar = getGSM7BitChar(character);
		return new StringBuffer().append(Integer.toHexString(gsm7BitChar.x)).append(Integer.toHexString(gsm7BitChar.y)).toString();
	}
	
	public static char getChar(String code) {
		return getGSM7BitChar(code).character;
	}
	
	public static char getChar(int x, int y) {
		return getGSM7BitChar(x, y).character;
	}
	
	private static GSM7BitChar getGSM7BitChar(char character) {
		for (GSM7BitChar gsm7BitChar : alphabet) {
			if (gsm7BitChar.character == character) {
				return gsm7BitChar;
			}
		}
		return null;
	}
	
	private static GSM7BitChar getGSM7BitChar(String code) {
		return getGSM7BitChar(Integer.valueOf(code.substring(0, 1)), Integer.valueOf(code.substring(1, 2)));
	}
	
	private static GSM7BitChar getGSM7BitChar(int x, int y) {
		for (GSM7BitChar gsm7BitChar : alphabet) {
			if (gsm7BitChar.x == x && gsm7BitChar.y == y) {
				return gsm7BitChar;
			}
		}
		return null;
	}
	
	static class GSM7BitChar {
		public int x;
		public int y;
		
		public char character;
	
		public GSM7BitChar(int x, int y, char character) {
			this.x = x;
			this.y = y;
			this.character = character;
		}
	}
}
