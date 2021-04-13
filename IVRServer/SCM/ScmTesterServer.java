package SCM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Scm_eunms.ScmInfoType;
import Scm_eunms.ScmMessageType;
import Scm_eunms.ScmNextFlagEnum;

public class ScmTesterServer {

	private static ArrayList<byte[]> scmPacketParser(byte[] payload) {
		int front = 0;
		int tail = 0;
		ArrayList<byte[]> arr_v = new ArrayList<byte[]>();

		for (int i = 0; i < payload.length; i++) {
			if (payload[i] == (byte) 1 || payload[i] == (byte) 2 || payload[i] == (byte) 3 || payload[i] == (byte) 4 || //
					payload[i] == (byte) 5 || payload[i] == (byte) 6 || payload[i] == (byte) 7 || payload[i] == (byte) 8
					|| //
					payload[i] == (byte) 9 || payload[i] == (byte) 10 || payload[i] == (byte) 11
					|| payload[i] == (byte) 12) {
				int sum = tail - front;
				if (sum <= 0) {
					tail++;
					continue;
				} else {
					front++;
					byte[] im = new byte[sum - 1];
					int index = 0;
					for (int j = front; j <= front + im.length - 1; j++) {
						im[index++] = payload[j];
					}
					arr_v.add(im);
					front = i;
				}
			}
			tail++;
		}

		return arr_v;
	}

	public static void main(String[] args) throws IOException {

		new ScmTesterServer();
		ServerSocket sSocket = new ServerSocket(10002);
		System.out.println("IVR Server Start!!!!");
		boolean terminated = false;

		while (!terminated) {
			try {
				Socket socket = null;
				socket = sSocket.accept();

				DataInputStream dis = new DataInputStream(socket.getInputStream());

				if (socket.isConnected()) {
					System.out.println("SCM Client Welcome!!! Connect!!!");
					String Text = dis.readUTF();
					System.out.println("Servser에서 받은 메시지 \n" + Text);

					byte stx = dis.readByte();
					// 2. LENGTH
					byte[] length_b = new byte[3];
					dis.read(length_b);
					String length = new String(length_b);
					// 3. COMMAND
					byte[] command_b = new byte[3];
					dis.read(command_b);
					String command = new String(command_b);
					// 4. NEXT FLAG
					byte[] nextFlag_b = new byte[1];
					dis.read(nextFlag_b);

					// 4. Arg cnt
					byte[] argCnt_b = new byte[2];
					dis.read(argCnt_b);

					System.out.println("stx : " + stx);
					System.out.println("length_b : " + new String(length_b));
					System.out.println("command_b : " + new String(command_b));
					System.out.println("nextFlag_b : " + new String(nextFlag_b));
					System.out.println("argCnt_b : " + new String(argCnt_b));

					int payloadSize = Integer.parseInt(new String(length_b)) - 10;
					byte[] payload = new byte[payloadSize];
					dis.readFully(payload);
					System.out.println("payload : " + new String(payload));

					if (ScmMessageType.SMC_ARS_AGENT_REQ.getValue().equals(command)) {
						Socket cSocket = new Socket();
						try {
							List<byte[]> payloads = scmPacketParser(payload);

							DataOutputStream dos = new DataOutputStream(cSocket.getOutputStream());
							ArrayList<ScmInfoType> enums = new ArrayList<ScmInfoType>();
							enums.add(ScmInfoType.HEADER);
							enums.add(ScmInfoType.ARSEXTENSION.setPayload("3201"));
							enums.add(ScmInfoType.AGT_CALL.setPayload(""));
							enums.add(ScmInfoType.FLAG.setPayload("2"));

							MsgCreatorUtils creator = new MsgCreatorUtils(enums,
									ScmMessageType.ARS1STEPTNS_CONFIRM.getValue());
							byte[] result = creator.create(ScmNextFlagEnum.T_NO_MORE_PACKET);
							MsgParserUtils util = new MsgParserUtils(result);
							System.out.println(util.getHexString());

//									dos.write(new String(result).getBytes());

							enums = new ArrayList<ScmInfoType>();
							enums.add(ScmInfoType.HEADER);
							enums.add(ScmInfoType.ARSEXTENSION.setPayload("3201"));
							enums.add(ScmInfoType.QUEUE.setPayload("2001"));
							enums.add(ScmInfoType.EXPECTED_WAIT_CNT.setPayload("1"));
							enums.add(ScmInfoType.EXPECTED_WAIT_TIME.setPayload("1020"));
							enums.add(ScmInfoType.ANNOUNCE.setPayload("1"));

							creator = new MsgCreatorUtils(enums, ScmMessageType.SMC_ARS_TRANSFER.getValue());
							result = creator.create(ScmNextFlagEnum.T_NO_MORE_PACKET);
							util = new MsgParserUtils(result);
							System.out.println(util.getHexString());

							dos.write(new String(result).getBytes());

						} finally {
							cSocket.close();
						}
					} else {
						System.out.println(command);
					}

				}

			} catch (Exception e) {
			}
		}

	}

}
