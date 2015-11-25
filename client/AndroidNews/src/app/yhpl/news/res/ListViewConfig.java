package app.yhpl.news.res;

import android.os.Bundle;
import app.yhpl.news.R;
import app.yhpl.news.tag.BundleKeys;
import app.yhpl.news.tag.BundleValues;

public class ListViewConfig extends BaseConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6241325152979527167L;
	private int mSly = R.style.ListView_Common;

	// private int mLoaderMode = Mode.BOTH.ordinal();

	public int getListSly() {
		return mSly;
	}

	public int getLoaderMode() {
		return mBundle.getInt(BundleKeys.KEY_I_LOADER_MODE, BundleValues.VAL_I_LOADER_MODE);
	}

	@Override
	public int getLayoutRes() {
		int layoutRes = super.getLayoutRes();
		if (layoutRes == BundleValues.VAL_I_LAYOUT) {
			layoutRes = R.layout.v1_fragment_listview;
		}
		return layoutRes;
	}

	@Override
	public void parseBundle(Bundle bundle) {
		super.parseBundle(bundle);
		mBundle.putInt(BundleKeys.KEY_I_SLY, bundle.getInt(BundleKeys.KEY_I_SLY, BundleValues.VAL_TAB_I_SLY_LISTVIEW));
		mBundle.putInt(BundleKeys.KEY_I_LOADER_MODE,
				bundle.getInt(BundleKeys.KEY_I_LOADER_MODE, BundleValues.VAL_I_LOADER_MODE));
	}
}
