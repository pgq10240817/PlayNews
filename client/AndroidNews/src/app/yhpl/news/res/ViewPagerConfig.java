package app.yhpl.news.res;

import java.util.List;

import android.os.Bundle;
import app.yhpl.news.R;
import app.yhpl.news.tag.BundleKeys;
import app.yhpl.news.tag.BundleValues;

public class ViewPagerConfig extends BaseConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 111L;

	@Override
	public void parseBundle(Bundle bundle) {
		super.parseBundle(bundle);
		mBundle.putBoolean(BundleKeys.KEY_TAB_INDICATOR_TOP,
				bundle.getBoolean(BundleKeys.KEY_TAB_INDICATOR_TOP, BundleValues.VAL_TAB_INDICATOR_TOP));
		mBundle.putBoolean(BundleKeys.KEY_B_TITLEBAR,
				bundle.getBoolean(BundleKeys.KEY_B_TITLEBAR, BundleValues.VAL_B_TITLEBAR));
		mBundle.putParcelableArrayList(BundleKeys.KEY_TABS, bundle.getParcelableArrayList(BundleKeys.KEY_TABS));

	}

	@Override
	public int getLayoutRes() {
		int layoutRes = super.getLayoutRes();
		if (layoutRes == BundleValues.VAL_I_LAYOUT) {
			if (isTitleBarShow()) {
				layoutRes = isIndicatorTop() ? R.layout.v1_fragment_viewpager_indicator_top_titlebar
						: R.layout.v1_fragment_viewpager_indicator_bottom_titlebar;
			} else {
				layoutRes = isIndicatorTop() ? R.layout.v1_fragment_viewpager_indicator_top
						: R.layout.v1_fragment_viewpager_indicator_bottom;
			}

		}
		return layoutRes;
	}

	public boolean isIndicatorTop() {
		return mBundle.getBoolean(BundleKeys.KEY_TAB_INDICATOR_TOP, BundleValues.VAL_TAB_INDICATOR_TOP);
	}

	public boolean isTitleBarShow() {
		return mBundle.getBoolean(BundleKeys.KEY_B_TITLEBAR, BundleValues.VAL_B_TITLEBAR);
	}

	public List<TabBean> getTabs() {
		return mBundle.getParcelableArrayList(BundleKeys.KEY_TABS);
	}

}
