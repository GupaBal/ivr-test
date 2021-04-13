package Scm_eunms;

public enum ConnectResultCodes {
	ConnectResultCodeFail(0), //
	ConnectResultCodeSuccess(1); //

	private int value;

	private ConnectResultCodes(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static ConnectResultCodes getEnum(int value) {
		for (ConnectResultCodes type : ConnectResultCodes.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
