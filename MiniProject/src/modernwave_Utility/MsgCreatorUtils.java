package modernwave_Utility;

import java.util.ArrayList;

public class MsgCreatorUtils {
	private byte[] msgArray;
	private ByteParser parser;
	private ArrayList<InfoType> enums;
	private int SIZE;
	private String commandName = "";

	public MsgCreatorUtils(ArrayList<InfoType> enums) {			// enums�� ���� ���� ������ ���
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



	public byte[] create(MessageType type) {		// Message Type �� �޾ƿ� 
		createHeader((byte) type.getValue(), (short) (SIZE - InfoType.HEADER.getTotalSize())); // Mseeage type ��, HEADER �� �����ͼ� - ��ü SIZE �ϱ� (payload �� ����)

		
		// HEADER�� ������ ������������ Payload����� 
		for (InfoType em : enums) {
			if (em == InfoType.HEADER)
				continue;
			createPayload(em);		// HEADER�� ������ enums����� Payload����� 
		}
		return msgArray;
	}


	public void createHeader(byte type, short payloadSize) {
		parser.putByte(type);		// Message �� put
		parser.putShort(payloadSize); // HEADER ���� ������ SIZE put
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
