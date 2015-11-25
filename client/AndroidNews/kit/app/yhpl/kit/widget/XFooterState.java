package app.yhpl.kit.widget;

public enum XFooterState {
	RESET(0x0),

	LOADING_PREVIEW(0x1), LOADING(0x2), LOADING_END(0x3), LOADING_FAILED(0x4),

	ERROR(0x4);

	private int mIntValue;

	static XFooterState mapIntToValue(final int stateInt) {
		for (XFooterState value : XFooterState.values()) {
			if (stateInt == value.getIntValue()) {
				return value;
			}
		}

		return RESET;
	}

	XFooterState(int intValue) {
		mIntValue = intValue;
	}

	int getIntValue() {
		return mIntValue;
	}
}