package modernwave_Utility;


public class MsgParserUtils {
	private ByteParser parser;
	private byte[] byteArray;

	public MsgParserUtils(String HexString) {			// ����� ���ֱ�
		HexString = HexString.replaceAll(" ", "");
		this.setHexStringToByteArray(HexString);		// this�� ������ �ֱ����� this�� �ϳ�?
	}
	
	public MsgParserUtils(byte type, short payloadSize, byte[] payloadArray) {
		byte[] tmp = new byte[payloadSize + 3];
		ByteParser parserTmp = new ByteParser(tmp);
		parserTmp.putByte(type);
		parserTmp.putShort(payloadSize);
		parserTmp.putByteArray(payloadArray);
		this.byteArray = parserTmp.getByteArray();
		this.parser = new ByteParser(byteArray);
		
	}

	public void setHexStringToByteArray(String s) {			//	this���� ���� 
		this.byteArray = hexStringToByteArray(s);	
		this.parser = new ByteParser(byteArray);
	}
	
	public byte[] hexStringToByteArray(String s) {	//	 hex���� String���� ��ȯ ���� 
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public String getHexString() {
		StringBuilder sb = new StringBuilder(byteArray.length * 2);
		for (byte b : byteArray)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}

	public static String byteArrayToHex(byte[] a) {		//	byte���� hex�� ��ȯ  �⺻������ hex�� 0x�� ���۵Ǵϱ� *2?
		StringBuilder sb = new StringBuilder(a.length * 2);
				for(byte b : a)
					sb.append(String.format("0%2x", b));
		return sb.toString();
	}
	public byte getTypeFromByteArray() {
		return parser.getbyte();
	}

	public short getPayloadSizeFromByteArray(boolean isReverse) {
		System.out.println("boolean isReverse = "+isReverse);
		if (isReverse)
			return Short.reverseBytes(parser.getShort());
		else
			System.out.println("parser.getShort(); ="+parser.getShort());
			return parser.getShort();
	}

	public boolean checkUsable() {
		if (byteArray.length <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
