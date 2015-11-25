package app.yhpl.news.adapter.presenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class StringBean extends BaseBean implements Parcelable {
	private String title;
	private int id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static final Parcelable.Creator<StringBean> CREATOR = new Parcelable.Creator<StringBean>() {

		@Override
		public StringBean createFromParcel(Parcel source) {
			return null;
		}

		@Override
		public StringBean[] newArray(int size) {
			return null;
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}

}