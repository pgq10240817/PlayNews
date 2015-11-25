package app.yhpl.news.res;

import android.os.Bundle;
import app.yhpl.news.R;
import app.yhpl.news.tag.BundleValues;

public class ActivityConfig extends BaseConfig {

	private static final long serialVersionUID = 5878416576600333134L;
	private String mFragmentName;
	private String mFragmentTag;

	public String getFragmentName() {
		return mFragmentName;
	}

	public String getFragmentTag() {
		return mFragmentTag;
	}

	@Override
	public int getLayoutRes() {
		int layoutRes = super.getLayoutRes();
		if (layoutRes == BundleValues.VAL_I_LAYOUT) {
			layoutRes = R.layout.v1_activity_default;
		}
		return layoutRes;
	}

	@Override
	public void parseBundle(Bundle bundle) {
		super.parseBundle(bundle);

	}
}
