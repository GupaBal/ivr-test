package modernwave_ChatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

import modernwave_Utility.Header;
import modernwave_Utility.MessageType;
import modernwave_Utility.MsgParserUtils;

public class Char_Server_MessageReciever extends Thread {
	
	private Socket socket;
	ServerSocket serverSocket = null;
	protected DataInputStream dis;	
	public Char_Server_MessageReciever(Socket socket2) {
		this.socket = socket2;
	}

	@Override
	public void run() {
//
//		try {
//			System.out.println("[Chat_Server_Reciever] Log.info AliveCehck Server Sockte Create()...");
//			InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
//			System.out.println("[Chat_Server_Reciever] Log.info Connected by Client!!!");
//			System.out.println("[Chat_Server_Reciever] Log.info Connect with " + socketAddress.getHostString() + " "+ socket.getPort());
//			if (socket.isConnected() == true) {
//
//				while (true) {
//					System.out.println("[Chat_Server_Reciever] Log.info Byte Message Thread Start!!!!!");
//					if (socket.isConnected() == true) {
//
//						// No Parsing origin byte data.....
//					
//						byte type = dis.readByte();
//						System.out.println("[Chat_Server_Reciever] Read :" + type);
//						short payloadSize = dis.readShort();
//						System.out.println("[Chat_Server_Reciever] payloadSize:" + payloadSize);
//						byte[] payload = new byte[payloadSize];
//						dis.readFully(payload);
//						if(!(payloadSize == 0)) {
//							// Parsing byte data start
//							MsgParserUtils parser = new MsgParserUtils(type, payloadSize, payload);
//							String logid = UUID.randomUUID().toString().substring(1, 7);
//							System.out.println(logstack(logid, "Chat_Server << Chat_Client [" + parser.getHexString() + "]"));
//							Header header = new Header(parser.getTypeFromByteArray(), parser.getPayloadSizeFromByteArray(false));						
//							
//							if(!parser.checkUsable()) {
//								System.out.println("!parser.checkUsable() = "+ parser.checkUsable());
//							}
//							
//							//MessageType Find
//							MessageType types = MessageType.getEnum(type & 0xff);
//							String log_string = "Chat_Server << Chat_Client [Header type:" + (types == null ? (type + "") : types) + ", payloadLength : " + payloadSize + "]";
//							System.out.println(logstack(logid, log_string));
//							
//							if (types == MessageType.CallInfoMsg) {
//								System.out.println("types == MessageType.CallInfoMsg");
//							}
//						}else {
//							System.out.println("Data Empty!!!");
//						}
//			
//					}
//				}
//			}
//
//		} catch (IOException e2) {
//			e2.printStackTrace();
//		}
	}
	protected String logstack(String uuid, String str) {
		String logstack = "(" + uuid + ")";
		logstack += str;
		return logstack;
	}

	} 