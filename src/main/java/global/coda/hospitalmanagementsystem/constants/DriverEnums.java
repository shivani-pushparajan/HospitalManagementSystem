package global.coda.hospitalmanagementsystem.constants;

import java.util.HashMap;

public enum DriverEnums {
	CREATE(1), READ(2), UPDATE(3), DELETE(4), EXIT(5);
	private final int value;
	private static HashMap<Object, Object> map = new HashMap<>();

	private DriverEnums(int value) {
		this.value = value;
	}

	static {
		for (DriverEnums pageType : DriverEnums.values()) {
			map.put(pageType.value, pageType);
		}
	}

	public int getValue() {
		return value;
	}

	public static DriverEnums valueOf(int pageType) {
		return (DriverEnums) map.get(pageType);
	}
}