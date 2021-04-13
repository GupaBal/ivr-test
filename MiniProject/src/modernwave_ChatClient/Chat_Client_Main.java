package modernwave_ChatClient;


public class Chat_Client_Main extends Thread {
	private IClientSender sender;

	public Chat_Client_Main(IClientSender sender) {
		this.sender = sender;
	}
	
	public synchronized void Client_Request_Message(String message) {
		sender.sendRequestMessage(message);
	}


	public static void main(String[] args) {
		IClientSender sender = null;
		sender = new Chat_Client_Thread("192.168.0.143", 10002);
		Chat_Client_Main client =  new Chat_Client_Main(sender);
		String message = "from Client_Server Hi Hello world!!!!!";
		//client.Client_Request_Message(message);
		
	}

}
