package modernwave_Utility;


public enum RouteMode {
	RouteModeWork(0), //
	RouteModeNight(1), //
	RouteModeNotWork(2); //

	private int value;

	private RouteMode(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static RouteMode getEnum(int value) {
		for (RouteMode type : RouteMode.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
