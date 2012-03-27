package tepenet.corp.winw.sezam.is.smsc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import tepenet.corp.winw.sezam.is.smsc.emi.Message;


public class MinaSocket {

	private static final long CONNECT_TIMEOUT = 60*1000L;
	
	private IoSession session;
	
	public static MinaSocket getInstance() {
		return new MinaSocket();
	}
	
	public void connect(final String host, final Integer port) throws IOException, InterruptedException {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				NioSocketConnector connector = new NioSocketConnector();
				connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
				connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new DefaultCodecFactory()));
				connector.setHandler(new MinaHandler());
				for (;;) {
			        try {
			            ConnectFuture future = connector.connect(new InetSocketAddress(host, port));
			            future.awaitUninterruptibly();
			            session = future.getSession();
			            break;
			        } catch (RuntimeIoException e) {
			            System.err.println("Failed to connect.");
			            e.printStackTrace();
			            try {
							Thread.sleep(5000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
			        }
			    }
				
				session.getCloseFuture().awaitUninterruptibly();
			    connector.dispose();
			}
		}).start();
	}
	
	public void send(Message message) {
		session.write(message);
	}
	
	public void close() {
		session.close(true);
	}
}
