package modernwave_Utility;

import java.util.ArrayList;

public class MsgCreatorUtils {
	private byte[] msgArray;
	private ByteParser parser;
	private ArrayList<InfoType> enums;
	private int SIZE;
	private String commandName = "";

	public MsgCreatorUtils(ArrayList<InfoType> enums) {			// enums을 통해 버퍼 사이즈 계산
		this.enums = enums;
		for (InfoType em : enums)
			SIZE += em.getTotalSize();
		msgArray = new byte[SIZE];
		parser = new ByteParser(msgArray);
	}

	public byte[] getMsgArray() {
		return msgArray;
	}

	public int getPayloadSize() {
		// return SIZE-10;
		return SIZE;
	}



	public byte[] create(MessageType type) {		// Message Type 을 받아옴 
		createHeader((byte) type.getValue(), (short) (SIZE - InfoType.HEADER.getTotalSize())); // Mseeage type 값, HEADER 값 가져와서 - 전체 SIZE 하기 (payload 값 측정)

		
		// HEADER를 제외한 나머지값으로 Payload만들기 
		for (InfoType em : enums) {
			if (em == InfoType.HEADER)
				continue;
			createPayload(em);		// HEADER를 제외한 enums값들로 Payload만들기 
		}
		return msgArray;
	}


	public void createHeader(byte type, short payloadSize) {
		parser.putByte(type);		// Message 값 put
		parser.putShort(payloadSize); // HEADER 값을 제외한 SIZE put
	}

	public void createPayload(InfoType em) {
		if (em.getValueType().equalsIgnoreCase("byte"))
			parser.putByte(Byte.parseByte(em.getPayload()));
		else if (em.getValueType().equalsIgnoreCase("string"))
			parser.putString(em.getPayload(), em.getSize(), em.getBufferSize());
		else if (em.getValueType().equalsIgnoreCase("short"))
			parser.putShort(Short.parseShort(em.getPayload()));
	}

}
