package SCM;


public class ScmHeader implements IPacket {
	private byte stx = 2;
	private String payload_length;
	private String command_name;
	private String nextFlag;
	private String argumentCnt;
	private final int HEADER_SIZE = 10;

	public ScmHeader(String payload_length, String command_name, String nextFlag, String argumentCnt) {
		this.payload_length = payload_length;
		this.command_name = command_name;
		this.nextFlag = nextFlag;
		this.argumentCnt = argumentCnt;
	}

	public byte getStx() {
		return stx;
	}

	public String getPayload_length() {
		return payload_length;
	}

	public String getCommand_name() {
		return command_name;
	}

	public String getNextFlag() {
		return nextFlag;
	}

	public String getArgumentCnt() {
		return argumentCnt;
	}

	@Override
	public byte[] getByteArray() {
		ByteParser parser = new ByteParser(new byte[HEADER_SIZE]);
		parser.putByte(stx);
		parser.putString(payload_length, 3, 0);
		parser.putString(command_name, 3, 0);
		parser.putString(nextFlag, 1, 0);
		parser.putString(argumentCnt, 2, 0);
		return parser.getByteArray();
	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return "ScmHeader";
	}

	public String getHexString() {
		ByteParser parser = new ByteParser(new byte[HEADER_SIZE]);
		parser.putByte(stx);
		parser.putString(payload_length, 3, 0);
		parser.putString(command_name, 3, 0);
		parser.putString(nextFlag, 1, 0);
		parser.putString(argumentCnt, 2, 0);
		return MsgParserUtils.byteArrayToHex(parser.getByteArray());
	}

}
