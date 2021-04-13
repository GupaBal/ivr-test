package modernwave_Http_Service;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class StringToHTTPString {

	public static String toXML(Map<String, String> strings) {
		return null;

	}

	public static String toRequestOrderConfirmURL(Map<String, String> param) {

		String paramURL = "";

		Iterator entries = param.entrySet().iterator();
		while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();
			Object key = thisEntry.getKey();
			Object value = thisEntry.getValue();

			paramURL = paramURL.concat(key + "=" + value);

			if (entries.hasNext()) {
				paramURL = paramURL.concat("&");
			}
		}
		return String.format(paramURL);
	}

}
