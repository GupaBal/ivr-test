package modernwave_main;

import java.net.InetSocketAddress;

import modernwave_ChatClient.TEST_Chat_NewClient;

public class TEST_Main {

	public static void main(String[] args) {

		TEST_Chat_NewClient cctrl = null;
		String ip = "192.168.0.173";
		int port = 10002;
		
		cctrl = new TEST_Chat_NewClient(new InetSocketAddress(ip, port));	
		cctrl.connet();
	}

}
