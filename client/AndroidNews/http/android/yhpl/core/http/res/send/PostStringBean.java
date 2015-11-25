package android.yhpl.core.http.res.send;

import android.text.TextUtils;

public class PostStringBean extends PostBean {
	private String mPostContent;

	public PostStringBean(String mPostContent) {
		setSeriableType(ReqTags.POST_TYPE_STRING);
		this.mPostContent = mPostContent;
	}

	@Override
	public String toString() {
		return TextUtils.isEmpty(mPostContent) ? "" : mPostContent;
	}

}
