package tepenet.corp.winw.sezam.is.smsc;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import tepenet.corp.winw.sezam.is.smsc.emi.Message;
import tepenet.corp.winw.sezam.is.smsc.emi.MessageFactory;

public class DefaultDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		
		int start = in.position();
		
        while (in.hasRemaining()) {
            byte current = in.get();

            if (current == '\003') {
                int position = in.position();
                int limit = in.limit();
                try {
                    in.position(start);
                    in.limit(position);
                    
                    Message message = MessageFactory.createInstanceOfMessage(in.slice());
                    
                    out.write(message);
                } finally {

                    in.position(position);
                    in.limit(limit);
                }
                return true;
            }
        }

        in.position(start);

        return false;
	}

}
