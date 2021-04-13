package Scm_eunms;

public enum ScmNextFlagEnum {
	T_NO_MORE_PACKET("T"), N_MORE_PACKET("N");

	String value;

	private ScmNextFlagEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static ScmNextFlagEnum getEnum(String value) {
		for (ScmNextFlagEnum v : ScmNextFlagEnum.values()) {
			if (v.getValue().equalsIgnoreCase(value))
				return v;
		}

		return null;
	}
}
