package modernwave_Utility;

import java.util.Properties;
import java.util.logging.Logger;

public interface IModules {
	
	public void init();
	public void init(String configDirPath);
	public void init (Properties p);
	public void inir_logger(Logger l);
	public Object getInstance();
	public void release();
	public String toString();
}
