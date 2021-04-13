package modernwave_Utility;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ByteParser {
	private int bufferSize = 1024;
	private ByteBuffer buffer;
	private int offset = 0;

	public ByteParser() {
		buffer = ByteBuffer.allocate(bufferSize);
		buffer.clear();
	}

	public ByteParser(int size) {
		buffer = ByteBuffer.allocate(size);
		buffer.clear();
	}

	public ByteParser(byte[] data) {
		buffer = ByteBuffer.allocate(data.length);
		buffer.clear();
		buffer = ByteBuffer.wrap(data);
	}

	public byte[] Finish() {

		offset = buffer.position();
		byte[] data = {};

		if (buffer.hasArray()) {
			data = buffer.array();
		}

		byte[] result = new byte[offset];
		System.arraycopy(data, 0, result, 0, offset);

		buffer.flip();
		return result;
	}

	public void putByteArray(byte[] array) {
		buffer.put(array);
	}

	public void putString(String extensionString, int size, int bufferSize) {
		byte[] src = new byte[size + bufferSize];
		ByteBuffer buffSrc = ByteBuffer.allocate(src.length);
		buffSrc.clear();
		buffSrc = ByteBuffer.wrap(src);
		byte[] data = extensionString.getBytes();
		byte[] imsi = new byte[size];
		if (data.length > size) {
			System.arraycopy(data, 0, imsi, 0, size);
			buffSrc.put(imsi);
		} else {
			buffSrc.put(data);
		}
		buffer.put(src);
	}

	public void putShort(short payloadSize) {
		buffer.putShort(payloadSize);
	}

	public void putInt(int data) {
		buffer.putInt(data);
	}

	public void putByte(byte data) {
		buffer.put(data);
	}

	public void putENDByte(byte data) {
		buffer.put(buffer.limit() - 1, data);
	}

	public byte[] getHeader(byte[] header) {
		return buffer.get(header, offset, header.length).array();
	}

	public byte[] getBody(byte[] body) {
		return buffer.get(body, offset, body.length).array();
	}

	public byte getbyte() {
		return buffer.get();
	}

	public int getInt() {
		return buffer.getInt();
	}

	public float getFloat() {

		return buffer.getFloat();
	}

	public short getShort() {
		return buffer.getShort();
	}

	public char getChar() {

		return buffer.getChar();
	}

	public double getDouble() {
		return buffer.getDouble();
	}

	public byte[] getByteArray() {
		return buffer.array();
	}

	public String getString(int len) {
		byte[] temp = new byte[len];

		buffer.get(temp);
		String result = new String(temp);
		return result;
	}

	public String getString() {
		int len = buffer.limit() - buffer.position();
		byte[] temp = new byte[len];

		buffer.get(temp);
		String result = new String(temp);
		return result;
	}

}
