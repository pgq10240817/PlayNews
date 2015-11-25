package app.yhpl.news.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.yhpl.core.http.HttpResultlListener;
import android.yhpl.core.http.HttpURLTag;
import android.yhpl.core.http.res.BaseGson;
import android.yhpl.core.http.res.ResHttpResult;
import app.yhpl.kit.widget.EmptyView;
import app.yhpl.kit.widget.EmptyView.OnEmptyViewDataListener;
import app.yhpl.news.MyBuildConfig;
import app.yhpl.news.R;
import app.yhpl.news.adapter.V1BaseAdapter;
import app.yhpl.news.adapter.presenter.bean.BaseBean;
import app.yhpl.news.util.ResHttpResultUtil;
import app.yhpl.news.util.ToastUtils;

import com.android.volley.VolleyError;

public abstract class V1DataFragment extends LogFragment implements OnEmptyViewDataListener, HttpResultlListener {
	private EmptyView mEmptyView;
	protected int mCrtPage;
	protected V1BaseAdapter baseAdapter;
	protected boolean isInViewPager;
	private boolean isNeedDataLoading = true;
	private boolean isDataHasMore = false;
	protected String mTitle;

	private void checkParent() {
		Fragment parent = getParentFragment();
		int i = 3;
		while (i > 0 && parent != null) {
			// Log.e("myTag", this.getClass().getSimpleName() + " -- " + i);
			i--;
			if (parent instanceof V1ViewPagerFragment) {
				isInViewPager = true;
				break;
			}
			parent = parent.getParentFragment();

		}
		// isInViewPager = parent instanceof V1ViewPagerFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		checkParent();
		log("isInViewPager : " + isInViewPager);
	}

	public boolean needToastIfError() {
		return false;
	}

	public String getTitle() {
		return mTitle;
	}

	@Override
	public void initViews(View root) {
		super.initViews(root);
		mCrtPage = 0;
		mEmptyView = (EmptyView) root.findViewById(R.id.emptyview);
		if (mEmptyView != null) {
			mEmptyView.setOnEmptyViewDataListener(this);
		}
		if (isNeedDataLoading && !isInViewPager) {
			isNeedDataLoading = false;
			onDataBeginLoading();
		}
	}

	protected void onPageVisibleHint(boolean isVisible) {

	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		onPageVisibleHint(isVisibleToUser);
		if (isVisibleToUser && isInViewPager) {
			if (isNeedDataLoading) {
				isNeedDataLoading = false;
				onDataBeginLoading();
			}
		}
	}

	@Override
	public void onEmptyViewRefresh(int oldState) {
		mCrtPage = 0;
		onDataBeginLoading();

	}

	protected void OnRefresh() {
		mCrtPage = 0;
		onDataBeginLoading();
	}

	public abstract void onDataBeginLoading();

	public final boolean isHasMore() {
		return isDataHasMore;
	}

	public void onDataLoadingMore() {

	}

	public V1BaseAdapter initAdapter(BaseGson baseGson) {
		return null;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		if (isFragmentInvalide()) {
			return;
		}
		// if (mCrtPage == 0) {
		// if (mEmptyView != null) {
		// mEmptyView.showError();
		// }
		// }
	}

	@Override
	public void onErrorResponse(Object userTag, VolleyError error) {
		if (MyBuildConfig.DEBUG) {
			Log.e(HttpURLTag.TAG, " onErrorResponse:" + error);
		}
		if (isFragmentInvalide()) {
			return;
		}
		if (mCrtPage == 0) {
			if (mEmptyView != null) {
				mEmptyView.showError(R.string.xlistview_header_error);
			}
			if (needToastIfError()) {
				ToastUtils.showToast(R.string.xlistview_header_error);
			}
		}
		onDataLoadingFail(userTag, error);

	}

	protected void onDataLoadingFail(Object userTag, VolleyError error) {

	}

	@Override
	public void onHttpSuccess(Object userTag, BaseGson baseGson) {
		if (MyBuildConfig.DEBUG) {
			Log.e(HttpURLTag.TAG, " onHttpSuccess:" + baseGson);
		}
		if (isFragmentInvalide()) {
			return;
		}
		onDataLoadingSuccess(userTag, baseGson);
	}

	protected void onDataLoadingSuccess(Object tag, BaseGson baseGson) {
		if (baseAdapter == null) {
			baseAdapter = initAdapter(baseGson);
		}
		if (baseGson instanceof ResHttpResult) {
			ResHttpResult result = (ResHttpResult) baseGson;
			isDataHasMore = result.isMore();
		}
		if (baseAdapter != null) {
			List<? extends BaseBean> mList = baseGson.getContent();
			if (mList != null && !mList.isEmpty()) {
				boolean isAppend = mCrtPage > 0;
				if (mCrtPage == 0) {
					showContentView();
				}
				baseAdapter.addData(mList, isAppend);
				// if(baseGson instanceof ResHttpResult){
				// ResHttpResult httpResult = (ResHttpResult)baseGson;
				// Page mPage = httpResult.paginated;
				// if(mPage!=null){
				// boolean more = mPage.isMore();
				// if(!more){
				// onDataLoadingEnd();
				// }
				// }
				// }

				mCrtPage++;
				onDataLoadingEnd(!isHasMore());

			} else {
				isDataHasMore = false;
				if (mCrtPage == 0) {
					showEmpty();
				} else {
					showError();
					if (needToastIfError()) {
						String error = ResHttpResultUtil.getStateMsg(baseGson);
						if (TextUtils.isEmpty(error)) {
							ToastUtils.showToast(R.string.xlistview_header_error);
						} else {
							ToastUtils.showToast(error);
						}

					}
				}

			}

		}
	}

	protected final void showContentView() {
		if (mEmptyView != null) {
			mEmptyView.setVisibility(View.GONE);
		}
	}

	protected final void showLoadingView() {
		if (mEmptyView != null) {
			mEmptyView.setVisibility(View.VISIBLE);
			mEmptyView.showLoading();
		}
	}

	protected void onDataLoadingEnd(boolean isLastPage) {

	}

	protected final void showLoadingView(String msg) {
		if (mEmptyView != null) {
			mEmptyView.setVisibility(View.VISIBLE);
			mEmptyView.showLoading(msg);
		}
	}

	protected final void showError() {
		if (mEmptyView != null) {
			mEmptyView.setVisibility(View.VISIBLE);
			mEmptyView.showError();
		}
	}

	protected final void showEmpty() {
		if (mEmptyView != null) {
			mEmptyView.setVisibility(View.VISIBLE);
			mEmptyView.showEmpty();
		}
	}

}
