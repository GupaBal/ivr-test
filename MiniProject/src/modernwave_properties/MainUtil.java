package modernwave_properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class MainUtil {
	
	public static Properties GetProperties(String path) throws InvalidPropertiesFormatException, IOException {
		FileInputStream fin = new FileInputStream(path);
		Properties p = new Properties();
		p.loadFromXML(fin);
		return p;
	}
	
	public static Properties createProperties(String configfile) {
		if(System.getProperty("configfile")!= null)
			configfile = System.getProperty("configfile");
		
		Properties p = null;
		
		try {
			p = GetProperties(configfile);
		} catch (IOException e) {
			System.out.println("[MainUtil] Log.error configfile not found!!!");
			e.printStackTrace();
		}
		
		System.out.println("[MainUtil] Log.info configfile load success!!!");
		return p;
	}

	
}
