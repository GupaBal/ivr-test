package modernwave_Http_Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Logger;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import modernwave_ChatServer.Chat_Server_Reciever;
import modernwave_Utility.IModules;

public class WorkHTTP implements IModules{
	
	private HTTP_Post post;
	public WorkHTTP() {
	}
	@Override
	public void init() {
	}
	@Override
	public Object getInstance() {
		return new HttpRequest(post);
	}
	@Override
	public void init(String configDirPath) {
		

		try {
			JsonParser parser = new JsonParser();
			System.out.println(System.getProperty("user.dir"));
			Object obj = parser.parse(new FileReader(configDirPath + "config.json"));
			JsonObject jsonObject = (JsonObject) obj;
			String url = jsonObject.get("url").toString();
			String type = jsonObject.get("type").toString();
			post = new HTTP_Post(url.replaceAll("\"", ""), type.replaceAll("\"", ""));
			System.out.println("[WorkHTTP] Log.info init(String configDirPath).....");
			
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
	}
	@Override
	public void init(Properties p) {
	}
	@Override
	public void inir_logger(Logger l) {
	}

	@Override
	public void release() {
	}

}
