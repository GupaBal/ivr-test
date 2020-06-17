package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Client.*;
import SCM.MsgCreatorUtils;
import SCM.MsgParserUtils;
import Scm_eunms.ScmInfoType;
import Scm_eunms.ScmMessageType;
import Scm_eunms.ScmNextFlagEnum;

public class ScmTestClient {

	private Socket clientSocket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	public ScmTestClient() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			new Thread(new Runnable() {
				int flag = 0;
				boolean isThread = true; // Thread를 종료하기 위한 변수 설정

				@Override
				public void run() {
					while (isThread)
						if (flag == 1) {
							System.out.println("run() start!!");
							try {
								ALIVE_CHECK();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							flag = 0;
						} else {
							try {
								Thread.sleep(12000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							flag = 1;
						}
				}
			}).start();

				System.out.println("COMMAND : ");
				String Transfer_Msg = br.readLine();

				switch (Transfer_Msg) {

				case "CUSTOMER_INFO":
					CUSTOMER_INFO();

					break;
				case "SEUL":

					break;
				// 주기적으로 동작하는 Alive Check만들기
				}

		

		}
	}

	public synchronized void CUSTOMER_INFO() throws IOException {
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress("192.168.56.1", 10002));
		if (!socket.isClosed()) {

			ArrayList<ScmInfoType> enums = new ArrayList<ScmInfoType>();
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			enums.add(ScmInfoType.HEADER.setPayload("2340"));

			System.out.println("NAME : ");
			String NAME = br.readLine();
			enums.add(ScmInfoType.NAME.setPayload(NAME));

			System.out.println("JUMIN : ");
			String JUMIN = br.readLine();
			enums.add(ScmInfoType.JUMIN.setPayload(JUMIN));

			System.out.println("AGE : ");
			String AGE = br.readLine();
			enums.add(ScmInfoType.AGE.setPayload(AGE));

			System.out.println("TELL_NUM : ");
			String TELL_NUM = br.readLine();
			enums.add(ScmInfoType.TELL_NUM.setPayload(TELL_NUM));

			System.out.println("HOBBY : ");
			String HOBBY = br.readLine();
			enums.add(ScmInfoType.HOBBY.setPayload(HOBBY));

			String Text = "헤더" + ScmInfoType.HEADER + "\n" + "이름 : " + NAME + "\n" + "주민번호 : " + JUMIN + "\n" + "나이 : "
					+ AGE + "\n" + "전화번호 : " + TELL_NUM + "\n" + "취미 : " + HOBBY + "\n";

			try {
				dos.writeUTF(Text);
			} finally {
				socket.close();
			}
		}
	}

	private void ALIVE_CHECK() throws IOException {
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress("192.168.56.1", 10002));
		if (!socket.isClosed()) {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			String Text1 = "ALIVE CHECK!!!!!!!!!!!!!!!";
			try {
				System.out.println("ALIVE CHECK : " + Text1);
				dos.writeUTF(Text1);

			} finally {
				socket.close();
			}
		}

	}

	public static void main(String[] args) throws IOException {

		new ScmTestClient();

	}

}
