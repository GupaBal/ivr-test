package modernwave_ChatClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import modernwave_Utility.InfoType;
import modernwave_Utility.MessageType;
import modernwave_Utility.MsgCreatorUtils;
import modernwave_Utility.MsgParserUtils;
import modernwave_Utility.ScmInfoType;

public class Chat_Client_Thread extends Thread implements IClientSender {

	DataInputStream dis;
	DataOutputStream dos;
	private Socket socket;
	private Socket socket2;

	public Chat_Client_Thread(){
		
	}
	
	public Chat_Client_Thread(String ip, int prot) {

		try {
			System.out.println("[Chat_Client] log.info  Client to Server Conneting....");
			socket = new Socket();
			socket.connect(new InetSocketAddress(prot));
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());

			if (socket.isConnected() == true) {
				System.out.println("[Chat_Client] log.info  Client to Server Connet Success!!!");
				dataRecv();
				dataSend();
				Chat_Client_Thread2 meseagetherad = new Chat_Client_Thread2();
				meseagetherad.start();
				
//				while(true) {
//					try {
//						Chat_Client_Thread2.sleep(10000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.start();
		setName("Client Server");
	}

	public String date_Create() {
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time2 = format2.format(time) + " from Client Message : ";
		return time2;
	}



	public void dataRecv() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("[Chat_Server_Reciever] Log.info Start!! Server dataRecv_Thread !!!");
				while (dis != null)
					try {
						String recvData = dis.readUTF();
						if (recvData.equals("q")) {
							System.out.println("[Chat_Client_Thread] Log.info Client_Thread End!!!");
						} else
							System.out.println(recvData);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}).start();

	}

	public void dataSend() {
		new Thread(new Runnable() {
			String DateTemp;
			Scanner in = new Scanner(System.in);

			@Override
			public void run() {
				System.out.println("[Chat_Server_Reciever] Log.info Start!! Server SendRecv_Thread !!!");
				while (in!=null) {
					try {
						String sendData = in.nextLine();
						if (sendData.equals("/quit")) {
							dos.writeUTF("Client Connetcion out!!!!");
						} else
							DateTemp = date_Create();
						dos.writeUTF(DateTemp + sendData);

					} catch (Exception e) {
					}
				}
			}
		}).start();

	}

	@Override
	public void sendRequestMessage(String arg) {

	}

	@Override
	public void sendRequestMessage(ArrayList<InfoType> enums, MessageType messageType) {
		// TODO Auto-generated method stub

	}

}
