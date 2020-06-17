package Scm_eunms;

public enum TRType {
	// HEADER
	HEADER_AGENT_ID(10, new String(), 0, "String"), //
	HEADER_AGENT_IP(16, new String(), 0, "String"), //
	HEADER_AGENT_LISTEN_PORT(5, new String(), 0, "String"), //
	HEADER_IVR_IP(16, new String(), 0, "String"), //
	HEADER_IVR_LISTEN_PORT(5, new String(), 0, "String"), //
	HEADER_AGENT_TELNUM(10, new String(), 0, "String"), //
	HEADER_REQUEST_CODE(5, new String(), 0, "String"), //
	HEADER_REQ_RESPONSE_CODE(4, new String(), 0, "String"), //

	// TR001
	TR001_CARD_NUM(16, new String(), 0, "String"), //
	TR001_CARD_YYMM(4, new String(), 0, "String"), //
	TR001_CARD_PW(2, new String(), 0, "String"), //
	TR001_JUMIN(13, new String(), 0, "String"), //

	// TR020
	TR020_PHONE_CERTI_NUM(6, new String(), 0, "String"), //

	HEADER(71, "1234567890", 0, "String");//

	private int size;
	private String data;
	private int bufferSize;
	private String type;

	private TRType(int size, String data, int bufferSize, String type) {
		this.size = size;
		this.data = data;
		this.bufferSize = bufferSize;
		this.type = type;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public String getData() {
		return data;
	}

	public String getValueType() {
		return type;
	}

	public int getTotalSize() {
		return this.size + this.bufferSize;
	}

	public TRType setPayload(String data) {
		this.data = data;
		return this;
	}

}
