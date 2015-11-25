package app.yhpl.kit.widget;

public enum XMode {
	DISABLED(0x0),

	PULL_FROM_START(0x1),

	PULL_FROM_END(0x2),

	BOTH(0x3);

	public static XMode mapIntToValue(final int modeInt) {
		for (XMode value : XMode.values()) {
			if (modeInt == value.getIntValue()) {
				return value;
			}
		}
		return getDefault();
	}

	static XMode getDefault() {
		return PULL_FROM_START;
	}

	private int mIntValue;

	// The modeInt values need to match those from attrs.xml
	XMode(int modeInt) {
		mIntValue = modeInt;
	}

	public boolean showHeaderLoadingLayout() {
		return this == PULL_FROM_START || this == BOTH;
	}

	public boolean showFooterLoadingLayout() {
		return this == PULL_FROM_END || this == BOTH;
	}

	int getIntValue() {
		return mIntValue;
	}
}
