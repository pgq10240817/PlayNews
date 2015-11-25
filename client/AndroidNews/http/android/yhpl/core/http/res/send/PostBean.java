package android.yhpl.core.http.res.send;

import com.google.gson.annotations.Expose;

public class PostBean {

	@Expose
	private transient int mSeriableType = 0;

	public int getSeriableType() {
		return mSeriableType;
	}

	public void setSeriableType(int seriableType) {
		mSeriableType = seriableType;
	}

}
