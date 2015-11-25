package app.yhpl.news.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.yhpl.core.http.HttpDispatcher;
import android.yhpl.core.http.res.BaseGson;
import app.yhpl.kit.widget.XFooterState;
import app.yhpl.kit.widget.XListView;
import app.yhpl.kit.widget.XMode;
import app.yhpl.news.R;
import app.yhpl.news.adapter.V1BaseAdapter;
import app.yhpl.news.res.ListViewConfig;
import app.yhpl.news.tag.BundleKeys;

import com.android.volley.VolleyError;

public class V1ListViewFragment extends V1DataFragment {
	private XListView mListview;

	@Override
	public void initArguments(Bundle bundle) {
		super.initArguments(bundle);
		mConfig = new ListViewConfig();
		mConfig.parseBundle(bundle);
	}

	@Override
	public int getLayoutRes() {
		return mConfig.getLayoutRes();
	}

	@Override
	public boolean needToastIfError() {
		// TODO Auto-generated method stub
		return mConfig.getBoolean(BundleKeys.KEY_B_NEED_TOAST_IF_HTTP_ERROR);
	}

	@Override
	public void initViews(View root) {
		super.initViews(root);
		mListview = (XListView) root.findViewById(R.id.listview);
		int mode = ((ListViewConfig) mConfig).getLoaderMode();
		mListview.setMode(XMode.mapIntToValue(mode));
		// if (mode == Mode.BOTH.ordinal()) {
		// mListview.setPullRefreshEnable(true);
		// mListview.setPullLoadEnable(true);
		// } else if (mode == Mode.PULL_FROM_START.ordinal()) {
		// mListview.setPullRefreshEnable(true);
		// mListview.setPullLoadEnable(false);
		// } else if (mode == Mode.PULL_FROM_END.ordinal()) {
		// mListview.setPullRefreshEnable(false);
		// mListview.setPullLoadEnable(true);
		// } else {
		// mListview.setPullRefreshEnable(false);
		// mListview.setPullLoadEnable(false);
		//
		// }
		//
		// V1BaseAdapter baseAdapter = mConfig.getAdapter();
		// List<StringBean> mtmp = new ArrayList<StringBean>();
		// for (int i = 0; i < 30; i++) {
		// StringBean bean = new StringBean();
		// bean.setTitle(" title " + i);
		// mtmp.add(bean);
		// }
		// baseAdapter.addData(mtmp);
		// mListview.setAdapter(baseAdapter);

		mListview.setRefreshHttpType(mConfig.getInt(BundleKeys.KEY_I_HTTP_PARSER_TYPE));
		mListview.setXListViewListener(new XListView.IXListViewListener() {

			@Override
			public void onRefresh() {
				// Toast.makeText(getActivity(), " onRefresh",
				// Toast.LENGTH_SHORT).show();

				V1ListViewFragment.this.OnRefresh();
				// mListview.postDelayed(new Runnable() {
				//
				// @Override
				// public void run() {
				// if (mListview != null && mListview.getHandler() != null) {
				// mListview.onRefreshComplete();
				// }
				//
				// }
				// }, 1000 * 2);

			}

			@Override
			public XFooterState onLoadMore(XFooterState state) {
				Log.e("myTag", "onLoadMore --- " + state);
				XFooterState newState = state;
				if (state == XFooterState.LOADING_PREVIEW) {
					newState = XFooterState.LOADING;
					// Toast.makeText(getActivity(), " onLoadMore",
					// Toast.LENGTH_SHORT).show();
					onDataLoadingMore();
				} else {
					// Toast.makeText(getActivity(), " onLoadMore is loading",
					// Toast.LENGTH_SHORT).show();
				}

				return newState;

			}
		});

	}

	@Override
	protected void onDataLoadingFail(Object userTag, VolleyError error) {
		super.onDataLoadingFail(userTag, error);
		if (isDestoryed) {
			return;
		}
		if (mListview != null && mListview.getHandler() != null) {
			mListview.onRefreshComplete();
		}
	}

	@Override
	protected void onDataLoadingEnd(boolean isLastPage) {
		super.onDataLoadingEnd(isLastPage);
		if (isDestoryed) {
			return;
		}
		if (mListview != null && mListview.getHandler() != null) {
			mListview.onRefreshComplete();
		}
		mListview.updateFooterState(isLastPage ? XFooterState.LOADING_END : XFooterState.LOADING_PREVIEW);
	}

	@Override
	public void onDataBeginLoading() {
		log("onDataBeginLoading");
		// TestHttpClient.reqMtHomeRmd(1024, this);
		boolean isHandle = HttpDispatcher.dispatchHttpWithTypePage(1000, this,
				mConfig.getInt(BundleKeys.KEY_I_HTTP_PARSER_TYPE), mCrtPage + 1);
		if (!isHandle) {
			showContentView();
		}
		Log.e("myTag", "onDataBeginLoading  " + mConfig.getInt(BundleKeys.KEY_I_HTTP_PARSER_TYPE) + " --isHandle:"
				+ isHandle);
	}

	@Override
	public void onDataLoadingMore() {
		log("onDataLoadingMore");
		Log.e("myTag", "onDataLoadingMore");
		if (isHasMore()) {
			onDataBeginLoading();
		}
	}

	@Override
	public V1BaseAdapter initAdapter(BaseGson baseGson) {
		V1BaseAdapter baseAdapter = mConfig.getAdapter();
		baseAdapter.setFragment(this);
		log("initAdapter :" + baseAdapter.getClass().getSimpleName());
		mListview.setAdapter(baseAdapter);
		return baseAdapter;
	}

}
