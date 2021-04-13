package modernwave_Utility;
public enum PacketDataTypeEnum {
	STRING_TYPE("String"), //
	BYTE_TYPE("byte"), //
	INTEGER_TYPE("integer"), //
	LONG_TYPE("Long");

	String type = null;

	private PacketDataTypeEnum(String type) {
		this.type = type;
	}
}
