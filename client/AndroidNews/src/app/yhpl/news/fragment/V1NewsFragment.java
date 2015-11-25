package app.yhpl.news.fragment;

import android.os.Bundle;
import android.yhpl.core.http.TestHttpClient;
import app.yhpl.news.tag.BundleKeys;

public class V1NewsFragment extends V1ListViewFragment {
	private String mCid;

	@Override
	public void initArguments(Bundle bundle) {
		super.initArguments(bundle);
		mCid = bundle.getString(BundleKeys.KEY_S_ID, "");
	}

	@Override
	public void onDataBeginLoading() {
		TestHttpClient.reqNewsList(1000, mCid, mCrtPage + 1, this);
	}
}
