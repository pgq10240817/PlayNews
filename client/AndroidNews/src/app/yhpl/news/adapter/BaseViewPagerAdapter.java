package app.yhpl.news.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import app.yhpl.kit.log.Logger;
import app.yhpl.news.res.BaseConfig;
import app.yhpl.news.res.TabBean;

import com.viewpagerindicator.IconPagerAdapter;

public class BaseViewPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
	private List<TabBean> mTabs;
	private Context mContext;

	public BaseViewPagerAdapter(Context mContext, FragmentManager fm, List<TabBean> mTabs) {
		super(fm);
		this.mTabs = mTabs;
		this.mContext = mContext;
	}

	@Override
	public Fragment getItem(int index) {
		Logger.v("viewpager", "getItem " + index);
		Fragment child = Fragment.instantiate(mContext, mTabs.get(index).getFragmentName());
		BaseConfig cfg = mTabs.get(index).getCfg();
		if (cfg != null) {
			child.setArguments(cfg.getBundle());
		}

		return child;
	}

	@Override
	public int getCount() {
		return mTabs == null ? 0 : mTabs.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Log.e("myTag", " position :" + position);
		return mTabs.get(position).getTitleStr();
	}

	public void setTitleBarTitle(TextView tv, int page) {
		if (tv != null && page >= 0 && page < mTabs.size()) {
			TabBean tab = mTabs.get(page);
			if (tab.getTitleBarRes() > 0) {
				tv.setText(tab.getTitleBarRes());
			} else {
				String title = tab.getTitleBarStr();
				if (!TextUtils.isEmpty(title)) {
					tv.setText(title);
				}
			}
		}
	}

	@Override
	public int getIconResId(int index) {
		return mTabs.get(index).getImgRes();
		// return 0;
	}

	@Override
	public int getBackGroud() {
		return 0;
	}

	@Override
	public int getDefaultIconResId(int index) {
		return 0;
	}

	@Override
	public String getImageUrl(int index) {
		return null;
	}

}
