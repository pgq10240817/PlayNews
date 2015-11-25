package app.yhpl.kit.widget;

public enum XState {
	RESET(0x0),

	PULL_TO_REFRESH(0x1),

	RELEASE_TO_REFRESH(0x2),

	REFRESHING(0x8),

	MANUAL_REFRESHING(0x9),

	OVERSCROLLING(0x10);
	private int mIntValue;

	static XState mapIntToValue(final int stateInt) {
		for (XState value : XState.values()) {
			if (stateInt == value.getIntValue()) {
				return value;
			}
		}

		return RESET;
	}

	XState(int intValue) {
		mIntValue = intValue;
	}

	int getIntValue() {
		return mIntValue;
	}
}