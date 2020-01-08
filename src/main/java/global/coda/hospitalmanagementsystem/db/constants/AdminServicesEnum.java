package global.coda.hospitalmanagementsystem.db.constants;

import java.util.HashMap;

public enum AdminServicesEnum {
	ADD(1), VIEW(2), MODIFY(3), DELETE(4), SIGN_OUT(5);
	private final int value;
	private static HashMap<Integer, AdminServicesEnum> map = new HashMap<>();
	static {
		for (AdminServicesEnum enumValue : AdminServicesEnum.values()) {
			map.put(enumValue.value, enumValue);
		}
	}

	private AdminServicesEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static AdminServicesEnum valueOf(int enumValue) {
		return map.get(enumValue);
	}
}
