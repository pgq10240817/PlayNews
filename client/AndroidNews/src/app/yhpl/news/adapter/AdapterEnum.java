package app.yhpl.news.adapter;

import android.widget.BaseAdapter;

public enum AdapterEnum {
	CHANNESL(0, V1ChannelsAdapter.class), NEWS(1, V1NewsAdapter.class);
	private int mFlag;
	private Class<? extends BaseAdapter> clz;

	private AdapterEnum(int flag, Class<? extends BaseAdapter> clz) {
		this.mFlag = flag;
		this.clz = clz;
	}

	public int getFlag() {
		return mFlag;
	}

	public static Class<? extends BaseAdapter> getAdapterClass(int flag) {
		for (AdapterEnum tmp : values()) {
			if (tmp.mFlag == flag) {
				return tmp.clz;
			}
		}
		return null;
	}
}
