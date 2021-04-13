package modernwave_ChatClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TEST_Chat_NewClient {

	private Socket socket = null;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutptStream;
	private Socket clientSocket;
	private InetSocketAddress addr = null;
	public boolean Thread = false; // Thread Start!!!
	
	public TEST_Chat_NewClient(InetSocketAddress inetSocketAddress) {
		socket = new Socket();
		this.addr = inetSocketAddress;
		System.out.println("[Chat_NewClient] Log.info Chat_NewClient Constructor init!!!");
	}
	
	public boolean connet() {
	    boolean isThread = false; 
		System.out.println("[Chat_Client] log.info  Client to Server Conneting....");
		try {
			socket.connect(addr);
			System.out.println();
			System.out.println(socket.isConnected());
			if(socket.isConnected()==true) {
				System.out.println( "[Chat_Client] log.info  Client to Server Connet Success!!!");
				
				if(socket.isConnected()== true) {
		        	isThread = true;
		      	  dataInputStream = new DataInputStream(socket.getInputStream());      // Input
	              dataOutptStream = new DataOutputStream(socket.getOutputStream());     // Output
		        	dataRecv(isThread);
		        	dataSend(isThread);
		        
		        }
			}else {
				System.out.println( "[Chat_Client] log.info  Client to Server Connet fail!!!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isThread;
	}
	
	public String date_Create() {
		SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time2 = format2.format(time)+" from Client Message : ";
		return time2;
	}
	
    public void dataRecv(boolean isThread) {     
        new Thread(new Runnable() {
        	boolean RecvThread = isThread;
		@Override
           public void run() {
			System.out.println("[Chat_Server_Reciever] Log.info Start!! Server dataRecv_Thread !!!");
			while(dataInputStream!=null)
                 try {
                    String recvData = dataInputStream.readUTF();
                    if(recvData.equals("q"))
                    	RecvThread = false;
                    else
                       System.out.println(recvData);     
                    
                 } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                 } 
           }
        }).start();
        
     }
     
    public void dataSend(boolean isThread){   
        new Thread(new Runnable() {
        	boolean SendThread = isThread;
        	String DateTemp;
           Scanner in = new Scanner(System.in);
           @Override
           public void run() {
        		System.out.println("[Chat_Server_Reciever] Log.info Start!! Server SendRecv_Thread !!!");
                 while(SendThread) {
                    try {
                       String sendData = in.nextLine();
                       if(sendData.equals("/quit"))
                    	   SendThread = false;
                       else
                    	   DateTemp = date_Create();
                       	   dataOutptStream.writeUTF(DateTemp+sendData);
                                            
                       } catch (Exception e) {
                       }      
                     }
           }
        }).start();
           

     }


}
