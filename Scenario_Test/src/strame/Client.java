package strame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class Client {

	public static void main(String[] args) {

	     Socket socket = null;            //Server�� ����ϱ� ���� Socket
	        BufferedReader in = null;        //Server�κ��� �����͸� �о���̱� ���� �Է½�Ʈ��
	        BufferedReader in2 = null;        //Ű����κ��� �о���̱� ���� �Է½�Ʈ��
	        PrintWriter out = null;            //������ �������� ���� ��� ��Ʈ��
	        InetAddress ia = null;
	        try {
	            ia = InetAddress.getByName("127.0.0.1");    //������ ����
	            socket = new Socket("127.0.0.1",15555);
	            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            in2 = new BufferedReader(new InputStreamReader(System.in));
	            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
	            System.out.println("socket.toString() >>>>> "+socket.toString());
	            System.out.println(socket.toString());
	        }catch(IOException e) {}
	        
	        try {
	            System.out.print("������ ���� �޼��� : ");
	            String data = in2.readLine();            //Ű����κ��� �Է�
	            out.println(data);                        //������ ������ ����
	            out.flush();

	            String str2 = in.readLine();            //�����κ��� �ǵ��ƿ��� ������ �о����
	            System.out.println("�����κ��� �ǵ��ƿ� �޼��� : " + str2);
	            
				String result = "";
				
				boolean eventReceived = false;
				
				Map<String, String> map = new HashMap<String, String>();
				
					String input = str2;
					System.out.println(input);
					if(input.equals("PinResult") )
						eventReceived = true; 
					if (input.length() == 0 && eventReceived)
					
					if(eventReceived) {
						if(input.startsWith("PinValue"))
							map.put("PinValue", input.substring(input.indexOf(":")+1, input.length()));
						if(input.startsWith("ResultType"))
							map.put("ResultType", input.substring(input.indexOf(":")+1, input.length()));
						if(input.startsWith("FailReason"))
							map.put("FailReason", input.substring(input.indexOf(":")+1, input.length()));
						if(input.startsWith("FailDescription"))
							map.put("FailDescription", input.substring(input.indexOf(":")+1, input.length()));
					}
					
	            
	            
	            
	            socket.close();
	            
	        }catch(IOException e) {}
	    }
		

	}
