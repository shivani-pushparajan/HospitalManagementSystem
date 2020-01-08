package global.coda.hospitalmanagementsystem.db.constants;

import java.util.HashMap;

public enum RolesEnum {

	PATIENT(1), DOCTOR(2), LOCAL_ADMIN(3), GLOBAL_ADMIN(4);
	private final int value;
	private static HashMap<Integer, RolesEnum> map = new HashMap<>();
	static {
		for (RolesEnum enumValue : RolesEnum.values()) {
			map.put(enumValue.value, enumValue);
		}
	}

	private RolesEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static RolesEnum valueOf(int fileType) {
		return map.get(fileType);
	}
}
