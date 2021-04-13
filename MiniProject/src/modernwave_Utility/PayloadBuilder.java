package modernwave_Utility;

import java.util.HashMap;
import java.util.Map;

public class PayloadBuilder {

	MessageTypeEnum messageType;
	Map<PacketEnum, String> values = new HashMap<PacketEnum, String>();

	public PayloadBuilder(MessageTypeEnum messageType) {
		this.messageType = messageType;
	}

	public void set(PacketEnum name, String value) throws NullPointerException, IndexOutOfBoundsException {
		if (value == null)
			throw new NullPointerException(name.toString() + ":" + value);
		if (name.getSize() == 0) {
			values.put(name, value);
			return;
		}
		if (name.getSize() < value.length())
			throw new IndexOutOfBoundsException(name.toString() + ":" + value);
		values.put(name, value);
	}

	public String build() throws Exception {

		StringBuilder sb = new StringBuilder();

		for (PacketEnum name : messageType.getNames()) {
			if (!values.containsKey(name))
				throw new Exception("key not found : " + name);

			if (name.getSize() == 0) {
				sb.append(values.get(name));
			} else
				sb.append(String.format("%-" + String.format("%d", name.getSize()) + "s", values.get(name)));
		}
		return sb.toString();
	}
}
