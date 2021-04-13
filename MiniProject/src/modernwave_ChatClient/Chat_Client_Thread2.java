package modernwave_ChatClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

import modernwave_Utility.CryptServiceGw;
import modernwave_Utility.InfoType;
import modernwave_Utility.MessageType;
import modernwave_Utility.MessageTypeEnum;
import modernwave_Utility.MsgCreatorUtils;
import modernwave_Utility.MsgParserUtils;
import modernwave_Utility.PacketEnum;
import modernwave_Utility.PayloadBuilder;

public class Chat_Client_Thread2 extends Thread implements IClientSender {

	DataInputStream dis;
	DataOutputStream dos;
	private Socket socket2;
	Chat_Client_Thread cth;

	@Override
	public void run() {
		System.out.println("Chat_Client_Thread2 run()!!!!");
		socket2 = new Socket();
		try {
			socket2.connect(new InetSocketAddress("192.168.0.143", 10002));
			dos = new DataOutputStream(socket2.getOutputStream());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		while (true) {
			if (socket2.isConnected()) {
//								String localIP = "192.168.0.173";
//								ArrayList<ScmInfoType> enums = new ArrayList<ScmInfoType>();
//								enums.add(ScmInfoType.HEADER);
//								enums.add(ScmInfoType.IVR_IP_ADDR.setPayload(localIP));
//								enums.add(ScmInfoType.ALIVE_CHANNEL_NUM.setPayload(String.valueOf("3")));
//								
//								StringBuilder sb = new StringBuilder();
//								enums.add(ScmInfoType.ALIVE_CHANNELS_STATUS.setPayload(sb.toString()));
//								enums.add(ScmInfoType.ALIVE_ALL_CHANNEL_STATUS.setPayload("W"));
//								sendRequestMessage(enums, ScmMessageType.SMC_ARS_ALIVE_CHECK);
//								sendRequestMessage
//								
//								
//								try {
//									Thread.sleep(10000);
//								} catch (InterruptedException e) {

//				flag Mode = "requestAgent" / "UTFsend";
				Thread thread = Thread.currentThread();
				String flag = "requestAgent";

				if (flag.equals("requestAgent")) {
					requestAgent();
				} else {
					UTFsend();
				}

//				requestAgent();
//				send();

				try {
					Thread.sleep(10000);
					System.out.println("1Thread.sleep(10000)... wake up!!!!");
				
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}

	private void UTFsend() {
		String DateTemp = null;
		try {
			Chat_Client_Thread cth = new Chat_Client_Thread();
			DateTemp = cth.date_Create();
			dos.writeUTF(DateTemp + "Client_AliveCehek_Message!!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void requestAgent() {

		String cid = "3202";
		String target = "1100";
		String extension = "3202";
		String serviceCode = "T1";
		String routeMode = "1";
		String targetType = "1";
		String incomingDateTime = "2021011311";

		ArrayList<InfoType> enums = new ArrayList<InfoType>();
		enums.add(InfoType.HEADER);
		enums.add(InfoType.EXTENSION.setPayload(extension));
		enums.add(InfoType.TARGET_TYPE.setPayload(String.valueOf(targetType)));
		enums.add(InfoType.TARGET.setPayload(target));
		enums.add(InfoType.ROUTE_MODE.setPayload(String.valueOf(routeMode)));
		enums.add(InfoType.SERVICE_CODE.setPayload(serviceCode));
		enums.add(InfoType.DATETIME_STRING.setPayload(incomingDateTime));
		enums.add(InfoType.CID.setPayload(cid));

		sendRequestMessage(enums, MessageType.RequestAgentConnectMsg);

	}

	@Override
	public void sendRequestMessage(ArrayList<InfoType> enums, MessageType messageType) {

		MsgCreatorUtils utils = new MsgCreatorUtils(enums);
		byte[] msgArray = utils.create(messageType);
		String msgRequestHex = MsgParserUtils.byteArrayToHex(msgArray); // byte 데이터를 Hex값으로 변환
		System.out.println(new String(msgArray)); // msgArray byte값을 new String으로 문자열로 출력해봄
		String log_string = "Chat_Client_Thread2 >>> Chat_Server_Thread";
		System.out.println(log_string + ">>" + msgRequestHex); // hex값도 잘 나왔나 출력해보자

		for (InfoType en : enums) { // send 데이터 출력
			if (en == InfoType.HEADER) {
				log_string += "[Header type:" + messageType + " payloadSize:" + utils.getPayloadSize() + "]";
				continue;
			}
			try {
				log_string += "[" + en + ":" + InfoType.getEnums(en, Integer.parseInt(en.getPayload())) + "]";
			} catch (Exception e) {
				log_string += "[" + en + ":" + en.getPayload() + "]";
			}
		}
		System.out.println(log_string);

		try {
			send(msgArray);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void send(byte[] data) throws Exception {
		synchronized (dos) {
			dos.write(data);
			dos.flush();
		}
	}

	@Override
	public void sendRequestMessage(String arg) {

	}

	private void cryptSend() throws Exception {

		String MESSAGE_TYPE = "WV0000Q";
		String MESSAGE_NO = "95b6f450a70e443_0";
		String SESSION_ID = "427952001";
		String USER_MDN = "2001";
		String result = null;

		result = WV0000Q_action(MESSAGE_NO, MESSAGE_TYPE, USER_MDN, SESSION_ID);

		System.out.println("result =" + result);
	}

	public String WV0000Q_action(String HEADER_MESSAGE_NO, String MESSAGE_TYPE, String USER_MDN, String SESSION_ID)
			throws Exception {

		PayloadBuilder payload = new PayloadBuilder(MessageTypeEnum.WV0000Q);

		String AUTH_KEY = "5EAA6D4E3C7815204F0BEAB0A5AB5A80280E3BDA";
		String SVC_CODE = "021600711901";
		String CUSTOMER_SERVICE_ID = "KOTRA001";

		payload.set(PacketEnum.MESSAGE_TYPE, MESSAGE_TYPE);
		payload.set(PacketEnum.AUTH_KEY, AUTH_KEY);
		payload.set(PacketEnum.SVC_CODE, SVC_CODE);
		payload.set(PacketEnum.USER_MDN, USER_MDN);
		payload.set(PacketEnum.SESSION_ID, SESSION_ID);
		String body_enc = CryptServiceGw.EncryptString(payload.build());

		PayloadBuilder header = new PayloadBuilder(MessageTypeEnum.HEADER);

		final int header_size = 40;

		header.set(PacketEnum.HEADER_MESSAGE_SIZE, String.valueOf(body_enc.getBytes().length + header_size));
		header.set(PacketEnum.HEADER_MESSAGE_NO, HEADER_MESSAGE_NO);
		header.set(PacketEnum.HEADER_CUSTOMER_SERVICE_ID, CUSTOMER_SERVICE_ID);

		return interAction(header.build(), body_enc);

	}

	protected String interAction(String header, String body_enc) throws IOException {
		send2(header, body_enc, dos);
		return "success!!";

	}

	private void send2(String header, String body_enc, DataOutputStream dos) throws IOException {

		int totlaLen = body_enc.getBytes().length + 40;

		byte[] buff = new byte[totlaLen];

		System.arraycopy(header.getBytes(), 0, buff, 0, header.getBytes().length);
		System.arraycopy(body_enc.getBytes(), 0, buff, header.getBytes().length, body_enc.getBytes().length);

		System.out.println(String.format("[HEADER:%s]\n[HEADER_LENGTH:%d]", header, header.getBytes().length));
		System.out.println(String.format("\n[BODY_ENCRYPT_BYTES:%s]\n[BODY_ENCRYPT_LENGTH:%d]",
				new String(body_enc.getBytes()), body_enc.getBytes().length));
			dos.write(buff);
			dos.flush();

		System.out.println(String.format("[To Callgate Packet Send Complete .. ][TOTAL DATA LENGTH : %d]", totlaLen));

	}

	private Map<PacketEnum, String> receiveOnce(DataOutputStream dos2) {
		// TODO Auto-generated method stub
		return null;
	}
}
