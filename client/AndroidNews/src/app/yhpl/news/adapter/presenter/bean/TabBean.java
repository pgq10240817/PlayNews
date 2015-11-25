package app.yhpl.news.adapter.presenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TabBean implements Parcelable {
	private String mTitleStr;
	private int mTitleRes;
	private int mImgRes;
	private String mFragmentName;

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
			bean.mFragmentName = source.readString();
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
		dest.writeString(mFragmentName);

	}
}
