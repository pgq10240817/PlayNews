package app.yhpl.news.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import app.yhpl.kit.widget.XViewPager;
import app.yhpl.news.R;
import app.yhpl.news.adapter.BaseViewPagerAdapter;
import app.yhpl.news.res.TabBean;
import app.yhpl.news.res.ViewPagerConfig;
import app.yhpl.news.tag.BundleKeys;
import app.yhpl.news.tag.BundleValues;

import com.viewpagerindicator.PageIndicator;

public class V1ViewPagerFragment extends LogFragment implements OnPageChangeListener, OnClickListener {
	protected ViewPager mViewPager;
	protected PageIndicator mIndicator;
	private BaseViewPagerAdapter mAdapter;
	private int mPageIndex;

	@Override
	public void initArguments(Bundle bundle) {
		super.initArguments(bundle);
		mConfig = new ViewPagerConfig();
		mConfig.parseBundle(bundle);
	}

	public List<TabBean> getTabs() {
		return ((ViewPagerConfig) mConfig).getTabs();
	}

	protected boolean isIndicatorTop() {
		return ((ViewPagerConfig) mConfig).isIndicatorTop();
	}

	@Override
	public int getLayoutRes() {
		return mConfig.getLayoutRes();
	}

	public Fragment getCurrentFragment() {
		// Fragment fragment = getChildFragmentManager().findFragmentByTag(
		// "android:switcher:" + R.id.pager + ":" + mPageIndex);
		Fragment fragment = getChildFragmentManager().findFragmentByTag(
				"android:switcher:" + mViewPager.getId() + ":" + mViewPager.getCurrentItem());
		return fragment;
	}

	public int getCurrentPageIndex() {
		return mPageIndex;
	}

	@Override
	public boolean onBackPressed() {
		Fragment ft = getCurrentFragment();
		if (ft instanceof LogFragment) {
			LogFragment intance = (LogFragment) ft;
			return intance.onBackPressed();
		}
		return super.onBackPressed();
	}

	@Override
	public void initViews(View root) {
		super.initViews(root);
		initTitleBar(root);
		mViewPager = (ViewPager) root.findViewById(R.id.pager);
		if (mAdapter == null) {
			mAdapter = new BaseViewPagerAdapter(getActivity(), getChildFragmentManager(), getTabs());
		}
		mViewPager.setAdapter(mAdapter);
		int offsetLimit = mConfig.getInt(BundleKeys.KEY_I_VIEWPAGER_OFFSET_LIMIT);
		if (offsetLimit < BundleValues.VAL_I_VIEWPAGER_OFFSET_LIMIT_MIN) {
			offsetLimit = BundleValues.VAL_I_VIEWPAGER_OFFSET_LIMIT_MIN;
		}
		mPageIndex = 0;
		mViewPager.setOffscreenPageLimit(offsetLimit);
		if (mViewPager instanceof XViewPager) {
			((XViewPager) mViewPager).setCanScroll(mConfig.getBoolean(BundleKeys.KEY_B_VIEWPAGER_CAN_SCROLL));
		}
		mIndicator = (PageIndicator) root.findViewById(R.id.indicator);
		mIndicator.setViewPager(mViewPager);
		mIndicator.setOnPageChangeListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	@Override
	public void onPageScrolled(int page, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int page) {
		mAdapter.setTitleBarTitle(mTitleHint, page);
		this.mPageIndex = page;
	}

}
