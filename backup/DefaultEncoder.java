package tepenet.corp.winw.sezam.is.smsc;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import tepenet.corp.winw.sezam.is.smsc.emi.Message;

public class DefaultEncoder implements ProtocolEncoder {

	@Override
	public void dispose(IoSession arg0) throws Exception {

	}

	@Override
	public void encode(IoSession session, Object obj, ProtocolEncoderOutput out) throws Exception {
		Message message = (Message)obj;
		String messageText = message.toString();
		IoBuffer buffer = IoBuffer.allocate(messageText.length(), false);
		buffer.put(messageText.getBytes());
		buffer.flip();
		out.write(buffer);
	}

}
