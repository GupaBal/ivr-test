/*package modernwave_ChatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.BufferUnderflowException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import modernwave_Utility.MessageType;
import modernwave_Utility.MessageTypeEnum;
import modernwave_CustomerInfo.CustomerInfo;
import modernwave_Http_Service.HTTP_Post;
import modernwave_Http_Service.HttpRequest;
import modernwave_Http_Service.WorkHTTP;
import modernwave_Utility.ByteParser;
import modernwave_Utility.CryptServiceGw;
import modernwave_Utility.Header;
import modernwave_Utility.IModules;
import modernwave_Utility.MsgParserUtils;
import modernwave_Utility.PacketEnum;
import modernwave_Utility.dataParsing;
import modernwave_mysql.DatabaseManager;
import modernwave_mysql.QueryDatabase_Dars;

import org.apache.commons.codec.binary.Base64;
public class Chat_Server_Reciever extends Thread implements IModules {
	private HTTP_Post post;
	protected Object waitObj;
	protected DataInputStream dis;
	protected DatabaseManager dm;
	protected QueryDatabase_Dars dars_querydb;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutptStream;
	private DataInputStream dataInputStream2;
	private DataOutputStream dataOutptStream2;
	public boolean isThread = false; // Thread Start!!!
	private Socket socket;
	ServerSocket serverSocket = null;
	private Socket socket2;
	ServerSocket serverSocket2 = null;
	HttpRequest httprequest = null;
	Properties p;
	private final int BIG_ENOUGH = 5012;
	ArrayList<String> results = new ArrayList<String>();

	public Chat_Server_Reciever(HTTP_Post post) {
		this.post = post;
	}

	public Chat_Server_Reciever(Properties p, Object waitObj, DatabaseManager dm, QueryDatabase_Dars dars_querydb,
			Socket socket) {
		System.out.println("[Chat_Server_Reciever] Log.info  Constructor init...");
		this.socket = socket;
		this.waitObj = waitObj;
		this.dm = dm;
		this.p = p;
		this.dars_querydb = dars_querydb;

		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		setName("MCTM RECIEVER");
	}

	protected String logstack(String uuid, String str) {
		String logstack = "(" + uuid + ")";
		logstack += str;
		return logstack;
	}

	@Override
	public void run() {

		try {
			while(true) {
			System.out.println("[Chat_Server_Reciever] Log.info Server Sockte Create()...");
			InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			System.out.println("[Chat_Server_Reciever] Log.info Connected by Client!!!");
			System.out.println("[Chat_Server_Reciever] Log.info Connect with " + socketAddress.getHostString() + " "
					+ socket.getPort());
			if (socket.isConnected() == true) {
				isThread = true;
				dataInputStream = new DataInputStream(socket.getInputStream()); // Input
				dataOutptStream = new DataOutputStream(socket.getOutputStream());
				dataRecv(isThread);
				dataSend(isThread);

				messageRecv(isThread);

			}
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

	public String date_Create() {
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time2 = format2.format(time) + " from Server Message : ";
		return time2;
	}

	public void dataRecv(boolean isThread) {

		WorkHTTP work = new WorkHTTP();
		work.init("");
		HttpRequest httprequest = (HttpRequest) work.getInstance();
		CustomerInfo custinfo = new CustomerInfo();
		new Thread(new Runnable() {
			boolean RecvThread = isThread;
			dataParsing dtP = new dataParsing(p);

			@Override
			public void run() {
				System.out.println("[Chat_Server_Reciever] Log.info Start!! Server dataRecv_Thread !!!");
				while (dataInputStream != null)
					try {
						String recvData = dataInputStream.readUTF();
						if (recvData.equals("q")) {
							RecvThread = false;
						}

						int temp2 = recvData.indexOf("custinfo");
						if (temp2 != -1) {
							results = dtP.customerDataParsiongModule(recvData);
							httprequest.CheckArsOrder_Customer(results);
//							custinfo.printStringCustInfo();
						}

						int temp = recvData.indexOf("request");
						if (temp != -1) {
							System.out.println("[Chat_Server_Reciever] Log.info Chat_Client Request Check!!! ");
							int temp1 = recvData.indexOf("&");
							if (temp1 != -1) {

								Map<String, String> result = null;
								Map<String, Map<String, String>> response_map = null;
								result = dtP.orderDataParsiongModule(recvData);

								response_map = httprequest.CheckArsOrder_Payment(result);

								System.out.println(
										"------------------------------------------------------------------------------------------------------------");
								response_map.forEach((key, value) -> {
									System.out.println(
											"[Chat_Server_Reciever] Log.info Order response_map data print !!!");
									System.out.println("1 : key: " + key + ", value: " + value);
									value.forEach((key2, value2) -> {
										System.out.println("2 : key: " + key2 + ", value: " + value2);
									});
								});
								System.out.println(
										"------------------------------------------------------------------------------------------------------------");

								try {
									dars_querydb.setOrderInfo(result);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						}else {
							System.out.println(recvData);
						}
	

					} catch (IOException e) {
						e.printStackTrace();
					}
			}

		}).start();

	}

	public void dataSend(boolean isThread) {
		new Thread(new Runnable() {
			boolean SendThread = isThread;
			String DateTemp;
			Scanner in = new Scanner(System.in);

			@Override
			public void run() {
				System.out.println("[Chat_Server_Reciever] Log.info Start!! Server SendRecv_Thread !!!");
				while (SendThread) {
					try {
						String sendData = in.nextLine();
						if (sendData.equals("/quit")) {
							SendThread = false;
							System.out.println("[Chat_Server] Log.info Server System End !!!!");
						} else
							DateTemp = date_Create();
						dataOutptStream.writeUTF(DateTemp + sendData);

					} catch (Exception e) {
					}
				}
			}
		}).start();

	}

	static public byte[] readHeader(DataInputStream dis) throws IOException {
		byte[] headerbuff = new byte[40];
		dis.readFully(headerbuff);
		return headerbuff;
	}

	public void messageRecv(boolean isThread) {
		new Thread(new Runnable() {
			boolean SendThread = isThread;
			String DateTemp;
			Scanner in = new Scanner(System.in);

			@Override
			public void run() {
				try {
					System.out.println("messageRecv socket connect wait.........");
					serverSocket2 = new ServerSocket(10011);
					socket2 = serverSocket2.accept();
					dataInputStream2 = new DataInputStream(socket2.getInputStream()); // Input
					dataOutptStream2 = new DataOutputStream(socket2.getOutputStream());
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// recevice Mode : nomal or byte
				String mode = "byte";
				while (dataInputStream2 != null) {

					if (socket2.isConnected() == true) {

						if (mode.equals("byte")) {
							System.out.println("[Chat_Server_Reciever] Log.info Byte Message Thread Start!!!!!");
							// No Parsing origin byte data.....
							byte type;
							try {
								byte[] bytes = new byte[200];
								int readByteCount = dataInputStream2.read(bytes);
								String message = new String(bytes, 0, readByteCount, "UTF-8");
								System.out.println("message" + message);

								type = dataInputStream2.readByte();
								System.out.println("Read :" + type);
								short payloadSize = dataInputStream2.readShort();
								System.out.println("payloadSize:" + payloadSize);
								byte[] payload = new byte[payloadSize];
								dataInputStream2.readFully(payload);

								// 받고
								MsgParserUtils parser = new MsgParserUtils(type, payloadSize, payload);
								String logid = UUID.randomUUID().toString().substring(1, 7);
								System.out.println(
										"Chat_Server_Thread <<< Chat_Client_Thread2[" + parser.getHexString() + "]");

								Header header = new Header(parser.getTypeFromByteArray(),
										parser.getPayloadSizeFromByteArray(false));

								MessageType types = MessageType.getEnum(type & 0xff);
								String log_string = "Chat_Server_Thread <<< Chat_Client_Thread2 [Header type:"
										+ (types == null ? (type + "") : types) + ", payloadLength : " + payloadSize
										+ "]";
								System.out.println(log_string);
								// IResult result = null;

								System.err.println("types =" + types);
								if (types == MessageType.AgentConnectResultMsg) {
									System.out.println("Hello");
								} else {
									System.out.println("World");
								}

							} catch (IOException e) {
								e.printStackTrace();
							}

						} else {
							try {
								while (dataInputStream2 != null) {
									String recvData = dataInputStream2.readUTF();
									System.out.println("messageRecv = " + recvData);

								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				}
			}
		}).start();

	}

	private String read() throws IOException { // byte데이터를 read() 하여 hex값으로 변환

		byte b[] = new byte[BIG_ENOUGH];
		int nosRead = socket2.getInputStream().read(b);
		byte result[] = new byte[nosRead];
		System.arraycopy(b, 0, result, 0, result.length);
		String hex_string = MsgParserUtils.byteArrayToHex(result);

		return hex_string;

	}


	static public byte[] readPayload(DataInputStream dis, int payloadSize) throws IOException {
		byte[] payloadbuff = new byte[payloadSize];
		dis.readFully(payloadbuff);
		return payloadbuff;
	}

	@Override
	public void init() {
		System.out.println("MODULE RODING... Chat_Server_Reciever modules...	");
	}

	@Override
	public void init(String configDirPath) {

	}

	@Override
	public void init(Properties p) {

	}

	@Override
	public void inir_logger(Logger l) {

	}

	@Override
	public Object getInstance() {
		return null;
	}

	@Override
	public void release() {

	}

	public String toString() {
		return "Chat_Server_Reciever";
	}

}*/

package modernwave_ChatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

import com.mysql.fabric.xmlrpc.base.Data;

import modernwave_CustomerInfo.CustomerInfo;
import modernwave_Http_Service.HTTP_Post;
import modernwave_Http_Service.HttpRequest;
import modernwave_Http_Service.WorkHTTP;
import modernwave_Utility.IModules;
import modernwave_Utility.dataParsing;
import modernwave_mysql.DatabaseManager;
import modernwave_mysql.QueryDatabase_Dars;

public class Chat_Server_Reciever extends Thread implements IModules {
	private HTTP_Post post;
	protected Object waitObj;
	protected DataInputStream dis;
	protected DatabaseManager dm;
	protected QueryDatabase_Dars dars_querydb;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutptStream;
	public boolean isThread = false; // Thread Start!!!
	private Socket socket = null;
	ServerSocket serverSocket = null;
	HttpRequest httprequest = null;
	Properties p;
	ArrayList<String> results = new ArrayList<String>();

	public Chat_Server_Reciever(HTTP_Post post) {
		this.post = post;
	}

	public Chat_Server_Reciever(Properties p, Object waitObj, DatabaseManager dm, QueryDatabase_Dars dars_querydb) {
		System.out.println("[Chat_Server_Reciever] Log.info  Constructor init...");
		this.waitObj = waitObj;
		this.dm = dm;
		this.p = p;
		this.dars_querydb = dars_querydb;
	}

	@Override
	public void run() {

		Scanner in = new Scanner(System.in);
		try {
			serverSocket = new ServerSocket(10002);
			socket = serverSocket.accept();
			System.out.println("[Chat_Server_Reciever] Log.info Server Sockte Create()...");
			InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			System.out.println("[Chat_Server_Reciever] Log.info Connected by Client!!!");
			System.out.println("[Chat_Server_Reciever] Log.info Connect with " + socketAddress.getHostString() + " "
					+ socket.getPort());
			if (socket.isConnected() == true) {
				isThread = true;
				dataInputStream = new DataInputStream(socket.getInputStream()); // Input
				dataOutptStream = new DataOutputStream(socket.getOutputStream());
				dataRecv(isThread);
				dataSend(isThread);

			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

	public String date_Create() {
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time2 = format2.format(time) + " from Server Message : ";
		return time2;
	}

	public void dataRecv(boolean isThread) {

		WorkHTTP work = new WorkHTTP();
		work.init("");
		HttpRequest httprequest = (HttpRequest) work.getInstance();
		CustomerInfo custinfo = new CustomerInfo();
		new Thread(new Runnable() {
			boolean RecvThread = isThread;
			dataParsing dtP = new dataParsing(p);

			@Override
			public void run() {
				System.out.println("[Chat_Server_Reciever] Log.info Start!! Server dataRecv_Thread !!!");
				while (dataInputStream != null)
					try {
						String recvData = dataInputStream.readUTF();
						if (recvData.equals("q")) {
							RecvThread = false;
						}

						int temp2 = recvData.indexOf("custinfo");
						if (temp2 != -1) {
							results = dtP.customerDataParsiongModule(recvData);
							httprequest.CheckArsOrder_Customer(results);
//							custinfo.printStringCustInfo();
						}

						int temp = recvData.indexOf("request");
						if (temp != -1) {
							System.out.println("[Chat_Server_Reciever] Log.info Chat_Client Request Check!!! ");
							int temp1 = recvData.indexOf("&");
							if (temp1 != -1) {

								Map<String, String> result = null;
								Map<String, Map<String, String>> response_map = null;
								result = dtP.orderDataParsiongModule(recvData);

								response_map = httprequest.CheckArsOrder_Payment(result);

								System.out.println(
										"------------------------------------------------------------------------------------------------------------");
								response_map.forEach((key, value) -> {
									System.out.println(
											"[Chat_Server_Reciever] Log.info Order response_map data print !!!");
									System.out.println("1 : key: " + key + ", value: " + value);
									value.forEach((key2, value2) -> {
										System.out.println("2 : key: " + key2 + ", value: " + value2);
									});
								});
								System.out.println(
										"------------------------------------------------------------------------------------------------------------");

								try {
									dars_querydb.setOrderInfo(result);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						} else {
							System.out.println(recvData);
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}).start();

	}

	public void dataSend(boolean isThread) {
		new Thread(new Runnable() {
			boolean SendThread = isThread;
			String DateTemp;
			Scanner in = new Scanner(System.in);

			@Override
			public void run() {
				System.out.println("[Chat_Server_Reciever] Log.info Start!! Server SendRecv_Thread !!!");
				while (SendThread) {
					try {
						String sendData = in.nextLine();
						if (sendData.equals("/quit")) {
							SendThread = false;
							System.out.println("[Chat_Server] Log.info Server System End !!!!");
						} else
							DateTemp = date_Create();
						dataOutptStream.writeUTF(DateTemp + sendData);

					} catch (Exception e) {
					}
				}
			}
		}).start();

	}

	@Override
	public void init() {
		System.out.println("MODULE RODING... Chat_Server_Reciever modules...	");
	}
	

	@Override
	public void init(String configDirPath) {

	}

	@Override
	public void init(Properties p) {

	}

	@Override
	public void inir_logger(Logger l) {

	}

	@Override
	public Object getInstance() {
		return null;
	}

	@Override
	public void release() {

	}
	public String toString() {
		return "Chat_Server_Reciever";
	}

}

