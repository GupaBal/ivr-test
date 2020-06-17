package SCM;

import java.util.ArrayList;
import Scm_eunms.*;



public class MsgCreatorUtils {
	// private final int EXTENSION_SIZE = 10;
	// private final int GROUP_CODE_SIZE = 4;
	// private final int SERVICE_CODE_SIZE = 4;
	// private final int DATETIME_STRING_SIZE = 10;
	// private final int CID_SIZE = 20;
	// private final int DN_SIZE = 15;
	// private final int ETC_INFO_SIZE = 20;
	// private final int FORECAST_SEC_SIZE = 4;
	// private final int NAME_SIZE = 20;
	// private final int VALUE_SIZE = 64;

	private byte[] msgArray;
	private ByteParser parser;
	private ArrayList<InfoType> enums;
	private ArrayList<ScmInfoType> enums_scm;
	private ArrayList<TRType> enums_tr;
	private int SIZE;
	private String commandName = "";

	public MsgCreatorUtils(ArrayList<InfoType> enums) {
		this.enums = enums;
		for (InfoType em : enums)
			SIZE += em.getTotalSize();
		msgArray = new byte[SIZE];
		parser = new ByteParser(msgArray);
	}

	public MsgCreatorUtils(ArrayList<ScmInfoType> enums_scm, String commandName) {
		this.enums_scm = enums_scm;
		for (ScmInfoType em : enums_scm)
			SIZE += em.getTotalSize();
		// SIZE = SIZE + enums_scm.size() - 1 + 1 + 1; // - header 1 , + ARGUMENT_INDEX 1 , + ETX 1
		SIZE = SIZE + enums_scm.size() - 1 + 1; // - header 1 , + ETX 1
		msgArray = new byte[SIZE];
		parser = new ByteParser(msgArray);
		this.commandName = commandName;
	}

	public MsgCreatorUtils(ArrayList<TRType> enums_tr, String commandName, String nulldata) {
		this.enums_tr = enums_tr;
		for (TRType em : enums_tr)
			SIZE += em.getTotalSize();
		msgArray = new byte[SIZE];
		parser = new ByteParser(msgArray);
		this.commandName = commandName;
	}

	public byte[] getMsgArray() {
		return msgArray;
	}

	public int getPayloadSize() {
		// return SIZE-10;
		return SIZE;
	}

	public byte[] create_ocx(ArrayList<TRType> enums_header) {
		createHeader_ocx(enums_header);

		for (TRType em : enums_tr) {
			if (em == TRType.HEADER)
				continue;
			createPayload(em);
		}
		return msgArray;
	}

	private void createHeader_ocx(ArrayList<TRType> enums_header) {
		for (TRType data : enums_header) {
			parser.putString(data.getData(), data.getTotalSize(), 0);
		}
	}

	public byte[] create(MessageType type) {
		createHeader((byte) type.getValue(), (short) (SIZE - InfoType.HEADER.getTotalSize()));

		for (InfoType em : enums) {
			if (em == InfoType.HEADER)
				continue;
			createPayload(em);
		}
		return msgArray;
	}

	public byte[] create(ScmNextFlagEnum nextFlag) {

		createHeader_Scm(String.format("%03d", SIZE), commandName, nextFlag.getValue(), String.format("%02d", enums_scm.size() - 1));
		int i = 0;
		for (ScmInfoType em : enums_scm) {
			if (em == ScmInfoType.HEADER)
				continue;
			createPayload(ScmInfoType.ARGUMENT_INDEX.setPayload(String.valueOf(++i)));
			createPayload(em);
		}
		// createPayload(ScmInfoType.ARGUMENT_INDEX.setPayload(String.valueOf(++i)));
		parser.putByte((byte) 3); // ETX
		return msgArray;
	}

	public void createHeader_Scm(String length, String command, String nextFlag, String arg_cnt) {
		parser.putByte((byte) 2);// STX
		parser.putString(length, 3, 0);
		parser.putString(command, 3, 0);
		parser.putString(nextFlag, 1, 0);
		parser.putString(arg_cnt, 2, 0);
	}

	public void createHeader(byte type, short payloadSize) {
		parser.putByte(type);
		parser.putShort(payloadSize);
	}

	public void createPayload(TRType em) {
		if (em.getValueType().equalsIgnoreCase("byte"))
			parser.putByte(Byte.parseByte(em.getData()));
		else if (em.getValueType().equalsIgnoreCase("string"))
			parser.putString(em.getData(), em.getTotalSize(), em.getBufferSize());
	}

	public void createPayload(ScmInfoType em) {
		if (em.getValueType().equalsIgnoreCase("byte"))
			parser.putByte(Byte.parseByte(em.getPayload()));
		else if (em.getValueType().equalsIgnoreCase("string"))
			parser.putString(em.getPayload(), em.getSize(), em.getBufferSize());
	}

	public void createPayload(InfoType em) {
		if (em.getValueType().equalsIgnoreCase("byte"))
			parser.putByte(Byte.parseByte(em.getPayload()));
		else if (em.getValueType().equalsIgnoreCase("string"))
			parser.putString(em.getPayload(), em.getSize(), em.getBufferSize());
	}

}
