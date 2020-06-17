package Scm_eunms;

public enum ScmInfoType {
	ARSEXTENSION(4, new String(), 0, "String"), //
	QUEUE(4, new String(), 0, "String"), //
	AGT_CALL(4, new String(), 0, "String"), //
	FLAG(1, new String(), 0, "String"), //
	AREA_CODE(2, new String(), 0, "String"), //
	CENTER_ID(2, new String(), 0, "String"), //
	PRI_GID(5, new String(), 0, "String"), //
	SEC_GID(5, new String(), 0, "String"), //
	CALLER_INFO(20, new String(), 0, "String"), //
	IVR_IP_ADDR(15, new String(), 0, "String"), //
	SERVICE_CODE(8, new String(), 0, "String"), //
	CUST_CLASS(1, new String(), 0, "String"), //
	EXPECTED_WAIT_CNT(3, new String(), 0, "String"), //
	EXPECTED_WAIT_TIME(4, new String(), 0, "String"), //
	ANNOUNCE(1, new String(), 0, "String"), //
	SUBSCRIBER_CODE(1, new String(), 0, "String"), //
	CALL_SEGMENT(1, new String(), 0, "String"), //
	IVR_OR_VMS(1, new String(), 0, "String"), // 1: IVR , 2: VMS
	BOOKING_OR_AB(1, new String(), 0, "String"), // 1: BOOKING(예약성공) , 2: AB(포기)
	CID(16, new String(), 0, "String"), //
	CUSTOMER_ID1(16, new String(), 0, "String"), //
	CUSTOMER_ID2(16, new String(), 0, "String"), //
	INCOMMINGDATE(14, new String(), 0, "String"), //
	REQUEST_TIME(14, new String(), 0, "String"), //
	OUTDATETIME(14, new String(), 0, "String"), //
	DESIGNATED_AGENT(8, "****", 0, "String"), //
	ETC_INFO(20, "**", 0, "String"), //
	SCREENPOP_DATA(200, "**", 0, "String"), //
	ARGUMENT_INDEX(1, new String(), 0, "Byte"), //

	VMS_TELNO(8, new String(), 0, "String"), //
	CUST_LEVEL(1, new String(), 0, "String"), //
	CUST_ID(20, new String(), 0, "String"), //
	CUST_DATA(270, new String(), 0, "String"), //

	ALIVE_CHANNEL_NUM(4, new String(), 0, "String"), //
	ALIVE_CHANNELS_STATUS(200, new String(), 0, "String"), //
	ALIVE_ALL_CHANNEL_STATUS(1, new String(), 0, "String"), //

	PASSWORD_IVR_EXTENSION(4, new String(), 0, "String"), //
	PASSWORD_AGENT_TEL(4, new String(), 0, "String"), //
	PASSWORD_ERROR_CODE(2, new String(), 0, "String"), //
	PASSWORD_MIN_LENGTH(2, new String(), 0, "String"), //
	PASSWORD_MAX_LENGTH(2, new String(), 0, "String"), //
	PASSWORD_LENGTH(3, new String(), 0, "String"), //
	PASSWORD_CONTENT(200, new String(), 0, "String"), //


	NAME(4, new String(), 0, "String"),
	JUMIN(15, new String(), 0, "String"),
	AGE(2, new String(), 0, "String"),
	TELL_NUM(15, new String(), 0, "String"),
	HOBBY(300, new String(), 0, "String"),
	
	HEADER(10, "1234567890", 0, "String");//

	private int size;
	private int MAX_SIZE;
	private String payload;
	private int bufferSize;
	private String valueType;

	private ScmInfoType(int size, String payload, int bufferSize, String valueType) {
		this.MAX_SIZE = size;

		if (payload.length() > 0) {
			if (this.MAX_SIZE < payload.length())
				this.size = size;
			else
				this.size = payload.length();
		} else {
			this.size = size;

		}
		this.payload = payload;
		this.bufferSize = bufferSize;
		this.valueType = valueType;
	}

	public String getValueType() {
		return this.valueType;
	}

	public int getSize() {
		return this.size;
	}

	public int getBufferSize() {
		return this.bufferSize;
	}

	public String getPayload() {
		return this.payload;
	}

	public int getTotalSize() {
		return this.size + this.bufferSize;
	}

	public ScmInfoType setPayload(String payload) {
		if (this.MAX_SIZE < payload.length())
			this.size = MAX_SIZE;
		else
			this.size = payload.length();
		this.payload = payload;
		return this;
	}

	public static String getEnums(ScmInfoType type, String value) {
		return String.valueOf(value);
	}
}
