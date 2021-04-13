package modernwave_ChatClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import modernwave_Utility.InfoType;
import modernwave_Utility.MessageType;
import modernwave_Utility.MsgCreatorUtils;
import modernwave_Utility.MsgParserUtils;

public class Chat_Client_AliveCheck extends Thread implements IClientSender{
	
	DataInputStream dis;
	DataOutputStream dos;
	private Socket socket;
	public Chat_Client_Thread Cth;
	
	public Chat_Client_AliveCheck(String ip, int prot) throws IOException {
		
		System.out.println("[Chat_Client] log.info  Client to Server Conneting....");
		socket = new Socket();
		socket.connect(new InetSocketAddress(ip, prot));
		System.out.println("[Chat_Client_AliveCheck] log.info  AliveCheck to Server Conneting....");

		if (socket.isConnected() == true) {
			System.out.println("[Chat_Client_AliveCheck] log.info  AliveCheck to Server Start!!!");
			System.out.println("socket.isConnected()" + socket.isConnected());
			AliveCheck();
		}
	}
	public Chat_Client_AliveCheck(IClientSender sender2) {
		// TODO Auto-generated constructor stub
	}
	public synchronized void AliveCheck() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//
//						ArrayList<InfoType> enums = new ArrayList<InfoType>();
//						enums.add(InfoType.HEADER);
//						enums.add(InfoType.EXTRAINFO.setPayload("1"));
//						MsgCreatorUtils utils = new MsgCreatorUtils(enums);
//						byte[] msgArray = utils.create(MessageType.AliveCheckMsg);
//						String msgRequestHex = MsgParserUtils.byteArrayToHex(msgArray);
//
//						System.out.println("IVR>>MCTM [" + msgRequestHex + "],");
//						String log_string = "IVR>>MCTM ";
//						for (InfoType en : enums) {
//							if (en == InfoType.HEADER) {
//								log_string += "[Header type:" + MessageType.getEnum(msgArray[0]) + " payloadSize:"
//										+ utils.getPayloadSize() + "]";
//								continue;
//							}
//							log_string += "[" + en + ":" + en.getPayload() + "]";
//						}
//
//						System.out.println("log_string >> " + log_string);
//
//						try {
//							Thread.sleep(20000);
//							try {
//								send(msgArray);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						} catch (InterruptedException e1) {
//							e1.printStackTrace();
//							close();
//						}
//							
//						try {
//							
//						} catch (Exception e) {
//						
//						}
//						
//					}
//
//				}
//		}).start();
		
	}	
	

	private void send(byte[] data) throws Exception {
		synchronized (dos) {
			dos.write(data);
		}
	}
	
	
	private void close() {
		if (!socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void sendRequestMessage(ArrayList<InfoType> enums, MessageType messageType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendRequestMessage(String arg) {
		// TODO Auto-generated method stub
		
	}

}
