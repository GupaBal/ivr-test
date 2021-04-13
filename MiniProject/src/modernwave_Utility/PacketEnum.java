package modernwave_Utility;

public enum PacketEnum {
	HEADER_MESSAGE_SIZE(4, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	HEADER_MESSAGE_NO(20, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	HEADER_CUSTOMER_SERVICE_ID(16, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //

	MESSAGE_TYPE(10, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //

	AUTH_KEY(40, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	SVC_CODE(16, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	USER_MDN(16, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	SESSION_ID(20, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //

	RESULT_CODE(4, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	SERVICE_SUPPORT_YN(1, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	PLATFORM_TYPE(1, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //

	SMS_KEY(10, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	TIMESTAMP(14, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	MENU_SIZE(4, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	MENU(0, new String(), 0, PacketDataTypeEnum.STRING_TYPE), // Dynimic Size

	SELECT_DTMF(4, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	USER_DATA_SIZE(4, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //
	USER_DATA(0, new String(), 0, PacketDataTypeEnum.STRING_TYPE), //

	HEADER(40, new String(), 0, PacketDataTypeEnum.STRING_TYPE);//

	private int size;
	private String payload;
	private PacketDataTypeEnum valueType;
	private int bufferSize;

	private PacketEnum(int size, String payload, int bufferSize, PacketDataTypeEnum valueType) {
		this.size = size;
		this.payload = payload;
		this.bufferSize = bufferSize;
		this.valueType = valueType;
	}

	public PacketDataTypeEnum getValueType() {
		return this.valueType;
	}

	public void setSize(int size) {
		this.size = size;
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

	public PacketEnum setPayload(String payload) {
		this.payload = payload;
		return this;
	}

	public static String getEnums(PacketEnum type, int value) {
		return String.valueOf(value);
	}

}