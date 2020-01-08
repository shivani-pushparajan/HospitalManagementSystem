package global.coda.hospitalmanagementsystem.db.constants;

import java.util.HashMap;

public enum UserServicesEnum {
	VIEW(1), MODIFY(2), SIGN_OUT(3);
	private final int value;
	private static HashMap<Integer, UserServicesEnum> map = new HashMap<>();
	static {
		for (UserServicesEnum enumValue : UserServicesEnum.values()) {
			map.put(enumValue.value, enumValue);
		}
	}

	private UserServicesEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static UserServicesEnum valueOf(int enumValue) {
		return map.get(enumValue);
	}
}
