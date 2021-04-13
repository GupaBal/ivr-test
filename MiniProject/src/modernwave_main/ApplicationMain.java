package modernwave_main;

import java.lang.reflect.Constructor;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import modernwave_ChatClient.TEST_Chat_NewClient;
import modernwave_ChatServer.Chat_Server_Reciever;
import modernwave_Utility.IModules;
import modernwave_mysql.DatabaseManager;
import modernwave_mysql.QueryDatabase_Dars;
import modernwave_properties.MainUtil;

public class ApplicationMain {
	private static QueryDatabase_Dars dars_querydb;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws NumberFormatException, SQLException, Exception {

		Properties p; // properties ¼±¾ð
		DatabaseManager dm;
		ServerSocket serverSocket = null;
		p = MainUtil.createProperties("config.xml");
		if (p == null)
			System.out.println("[ApplicationMain] Log.error config empty!!!");

		dm = new DatabaseManager(p, "test_db");
//		QueryDatabase_Dars dars_querydb = new QueryDatabase_Dars(dm);
//		String data = dars_querydb.getParameter(5);
//		System.out.println("***Data Bases Connet Success****" + data);
		System.out.println("[ApplicationMain] Log.info [IP : " + p.getProperty("Chat_Server") + "]" + "[port :"
				+ p.getProperty("Chat_Port") + "]");
		Object waitObj = new Object();

//		Socket socket = new Socket();
//		serverSocket = new ServerSocket(10011);
//		socket = serverSocket.accept();

		if (p.getProperty("Module.YN").equals("N")) {
			// Module create
			Class<?> klass = null;
			ArrayList<IModules> modules = new ArrayList<IModules>();
			System.out.println("[ApplicationMain] Log.info Module.YN = Y");
			klass = Class.forName("modernwave_ChatServer.Chat_Server_Reciever");
			Constructor<?> cs = klass.getConstructor(
					new Class[] { Properties.class, Object.class, DatabaseManager.class, QueryDatabase_Dars.class });
//			Object obj = cs.newInstance(p, waitObj, dm, dars_querydb,socket);
//			((Chat_Server_Reciever) obj).run();
		} else {
//			final Chat_Server_Reciever receiver = new Chat_Server_Reciever(p, waitObj, dm, dars_querydb,socket);
//			receiver.run();
			new Chat_Server_Reciever(p, waitObj, dm, dars_querydb).run();
		}
//
	}

}
