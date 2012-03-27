package tepenet.corp.winw.sezam.is.emi;

import java.io.IOException;

//import org.apache.mina.core.buffer.IoBuffer;
import org.junit.Test;

import tepenet.corp.winw.sezam.is.smsc.ChecksumValidationException;
import tepenet.corp.winw.sezam.is.smsc.OperationTypeUnknownException;
import tepenet.corp.winw.sezam.is.smsc.ResponseFormatException;
//import tepenet.corp.winw.sezam.is.smsc.emi.MessageFactory;

public class AppTest 
{
	@Test
    public void testApp() throws ResponseFormatException, IOException, ChecksumValidationException, OperationTypeUnknownException
    {
		//EMIUtils.decodeIRA("576961646F6D6F736320646C61202B34383530333031333437312C207A206964656E747966696B61746F72656D20313230323238313430333433207A6F7374616C6120646F73746172637A6F6E6120323031322D30322D3238206F2031343A30333A34382E");
       
		//String message = "07/00293/O/53/1201/503013471/////////////280212140343/0/000/280212140348/3//576961646F6D6F736320646C61202B34383530333031333437312C207A206964656E747966696B61746F72656D20313230323238313430333433207A6F7374616C6120646F73746172637A6F6E6120323031322D30322D3238206F2031343A30333A34382E/////////////77";
		
		//IoBuffer buffer = IoBuffer.wrap(message.getBytes());
		
		//MessageFactory.createInstanceOfMessage(buffer);
		
/*MinaSocket socket = MinaSocket.getInstance();
    	
    	try {
			socket.connect("10.236.16.86", 2258);
			
			Operation51 message = new Operation51("TP-OBSLUGA", "503013471", "Hello");
			
			socket.send(message);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	finally {
    		socket.close();
    	}*/
    }
}
