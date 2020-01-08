package global.coda.hospitalmanagementsystem.constants;

import java.util.HashMap;

public enum FileEnums {
	CSV(1), XML(2);
	private final int value;
	private static HashMap<Object, Object> map = new HashMap<>();
	static {
		for (FileEnums enumValue : FileEnums.values()) {
			map.put(enumValue.value, enumValue);
		}
	}

	private FileEnums(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static FileEnums valueOf(int fileType) {
		return (FileEnums) map.get(fileType);
	}
}
