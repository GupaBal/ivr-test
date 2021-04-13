package modernwave_Utility;


public enum InfoType {
	EXTENSION(10, new String(), 1, "String"), //
	SKILL_CODE(10, new String(), 1, "String"), //
	TARGET(10, new String(), 1, "String"), //
	CONNECTED_AGENT(10, new String(), 1, "String"), //
	GROUP_CODE(4, new String(), 1, "String"), //
	SERVICE_CODE(4, new String(), 1, "String"), //
	DATETIME_STRING(10, new String(), 1, "String"), //
	CID(20, new String(), 1, "String"), //
	PRIORITY(2, new String(), 0, "Short"), //
	CUSTOMERKEY(20, new String(), 1, "String"), //
	TO_QUEUE_ON_BUSY(1, new String(), 0, "byte"), //
	CONNECT_ON_READY(1, new String(), 0, "byte"), //
	ROUTE_MODE(1, new String(), 0, "byte"), //
	TARGET_TYPE(1, new String(), 0, "byte"), //
	CONNECT_RESULT_CODE(1, new String(), 0, "byte"), //
	QUEUE_COUNT(1, new String(), 0, "byte"), //
	EXTRAINFO(1, new String(), 0, "byte"), //
	DN(15, new String(), 1, "String"), //
	ETC_INFO(20, new String(), 1, "String"), //
	CUSTOMER_KEY(20, new String(), 1, "String"), //
	FORECAST_SEC(4, new String(), 1, "String"), //
	NAME(20, new String(), 1, "String"), //
	VALUE(64, new String(), 1, "String"), //
	HEADER(3, new String(), 0, "String");//
	
	
	private int size;
	private String payload;
	private int bufferSize;
	private String valueType;

	// 처음에 초기화 진행후 각각의 값을 this로 저장해서 사용한다. 
	private InfoType(int size, String payload, int bufferSize, String valueType) {
		this.size = size;
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

	
	public InfoType setPayload(String payload) {
		this.payload = payload;
		return this;
	}

	public static String getEnums(InfoType type, int value) {
		if (type == InfoType.TARGET_TYPE) {
			return String.valueOf(TargetType.getEnum(value));
		} else if (type == InfoType.ROUTE_MODE) {
			return String.valueOf(RouteMode.getEnum(value));
		} else if (type == InfoType.CONNECT_RESULT_CODE) {
			return String.valueOf(ConnectResultCodes.getEnum(value));
		} else
			return String.valueOf(value);
	}

}
