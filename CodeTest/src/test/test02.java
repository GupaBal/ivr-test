package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class test02 {
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutptStream;
	private DataInputStream dataInputStream2;
	private DataOutputStream dataOutptStream2;
	public boolean isThread = false; // Thread Start!!!
	private static Socket socket;
	ServerSocket serverSocket = null;
	static ServerSocket serverSocket2 = null;
	public static void main(String[] args) throws IOException {

	
		socket = new Socket();
		socket.connect(new InetSocketAddress("192.168.0.173", 14433));
		if(socket.isConnected() == true) {
			System.out.println("connet!!!");
		}
	
	}

}
