package modernwave_Utility;

import java.io.Serializable;

public class Header implements Serializable {

	private byte type;
	private short payloadSize;

	public Header(byte type, short payloadSize) {
		this.type = type;
		this.payloadSize = payloadSize;
	}

	public byte getType() {
		return this.type;
	}

	public short getPayloadLength() {
		return this.payloadSize;
	}

	public byte[] getByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getString() {
		// TODO Auto-generated method stub
		return "Header";
	}

	public String getHexString() {
		ByteParser parser = new ByteParser(new byte[3]);
		parser.putByte(type);
		parser.putShort(payloadSize);
		return MsgParserUtils.byteArrayToHex(parser.getByteArray());
	}

}
