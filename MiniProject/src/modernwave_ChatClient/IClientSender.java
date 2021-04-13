package modernwave_ChatClient;

import java.util.ArrayList;

import modernwave_Utility.InfoType;
import modernwave_Utility.MessageType;


public interface IClientSender {
//	public void sendRequestMessage(ArrayList<InfoType> enums, MessageType messageType);
	public void sendRequestMessage(String arg);

	public void sendRequestMessage(ArrayList<InfoType> enums, MessageType messageType);
}
