package app.yhpl.news.res;

import android.os.Parcel;
import android.os.Parcelable;

public class TabBean implements Parcelable {
	private String mTitleStr;
	private int mTitleRes;
	private int mImgRes;
	private String mFragmentName;
	private ListViewConfig mCfg;
	private String mTitleBarStr;
	private int mTitleBarRes;

	public String getTitleStr() {
		return mTitleStr;
	}

	public void setTitleStr(String titleStr) {
		mTitleStr = titleStr;
	}

	public int getTitleRes() {
		return mTitleRes;
	}

	public void setTitleRes(int titleRes) {
		mTitleRes = titleRes;
	}

	public void setTitleBar(int res) {
		this.mTitleBarRes = res;
	}

	public int getTitleBarRes() {
		return mTitleBarRes;
	}

	public String getTitleBarStr() {
		return mTitleBarStr;
	}

	public void setTitleBar(String mTitleBarStr) {
		this.mTitleBarStr = mTitleBarStr;
	}

	public int getImgRes() {
		return mImgRes;
	}

	public String getFragmentName() {
		return mFragmentName;
	}

	public void setFragmentName(String fragmentName) {
		mFragmentName = fragmentName;
	}

	public void setImgRes(int imgRes) {
		mImgRes = imgRes;
	}

	public ListViewConfig getCfg() {
		return mCfg;
	}

	public void setCfg(ListViewConfig cfg) {
		mCfg = cfg;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<TabBean> CREATOR = new Parcelable.Creator<TabBean>() {

		@Override
		public TabBean createFromParcel(Parcel source) {
			TabBean bean = new TabBean();
			bean.mTitleRes = source.readInt();
			bean.mImgRes = source.readInt();
			bean.mTitleStr = source.readString();
			bean.mTitleBarRes = source.readInt();
			bean.mTitleBarStr = source.readString();
			bean.mFragmentName = source.readString();
			bean.mCfg = new ListViewConfig();
			bean.mCfg.parseBundle(source.readBundle());
			return bean;
		}

		@Override
		public TabBean[] newArray(int size) {
			return new TabBean[size];
		}

	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mTitleRes);
		dest.writeInt(mImgRes);
		if (mTitleStr == null) {
			mTitleStr = "";
		}
		dest.writeString(mTitleStr);
		dest.writeInt(mTitleBarRes);
		dest.writeInt(mImgRes);
		if (mTitleBarStr == null) {
			mTitleBarStr = "";
		}
		dest.writeString(mTitleBarStr);

		dest.writeString(mFragmentName);
		ListViewConfig tmp = mCfg;
		if (tmp == null) {
			tmp = new ListViewConfig();
		}
		dest.writeBundle(tmp.getBundle());

	}
}
