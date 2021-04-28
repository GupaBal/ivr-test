package test;


public enum TargetType {
	TargetTypeGroup(1), //
	TargetTypeAgent(2), //
	TargetTypeAll(3), //
	TargetTypeSkill(4); //

	private int value;

	private TargetType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static TargetType getEnum(int value) {
		for (TargetType type : TargetType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}

}