package app.yhpl.news.res;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcelable;
import app.yhpl.news.adapter.AdapterEnum;
import app.yhpl.news.adapter.V1BaseAdapter;
import app.yhpl.news.tag.BundleKeys;
import app.yhpl.news.tag.BundleValues;

public class BaseConfig implements Serializable {

	private static final long serialVersionUID = 10001;
	protected Bundle mBundle;
	protected V1BaseAdapter mAdapter;

	public BaseConfig() {
		mBundle = new Bundle();
	}

	public BaseConfig putBoolean(String key, boolean value) {
		mBundle.putBoolean(key, value);
		return this;
	}

	public BaseConfig putString(String key, String value) {
		mBundle.putString(key, value);
		return this;
	}

	public void putBundle(Bundle extra) {
		mBundle.putAll(extra);

	}

	public String getString(String key) {
		return mBundle.getString(key, "");
	}

	public BaseConfig putInt(String key, int value) {
		mBundle.putInt(key, value);
		return this;
	}

	public int getInt(String key) {
		return mBundle.getInt(key, BundleValues.VAL_I_DEFAULT);
	}

	public boolean getBoolean(String key) {
		return mBundle.getBoolean(key, BundleValues.VAL_B_DEFAULT);
	}

	public BaseConfig putParacalArray(String key, ArrayList<? extends Parcelable> value) {
		mBundle.putParcelableArrayList(key, value);
		return this;
	}

	public Bundle getBundle() {
		return mBundle;
	}

	public int getLayoutRes() {
		return mBundle.getInt(BundleKeys.KEY_I_LAYOUT, BundleValues.VAL_I_LAYOUT);
	}

	@SuppressWarnings("unchecked")
	public V1BaseAdapter getAdapter() {
		if (mAdapter == null) {
			try {
				mAdapter = (V1BaseAdapter) AdapterEnum.getAdapterClass(mBundle.getInt(BundleKeys.KEY_I_ADAPTER))
						.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return mAdapter;
	}

	public void parseBundle(Bundle bundle) {
		mBundle = (Bundle) bundle.clone();
		// mBundle.putInt(BundleKeys.KEY_I_LAYOUT,
		// bundle.getInt(BundleKeys.KEY_I_LAYOUT, BundleValues.VAL_I_LAYOUT));
		// mBundle.putInt(BundleKeys.KEY_I_ADAPTER,
		// bundle.getInt(BundleKeys.KEY_I_ADAPTER));
	}
}
