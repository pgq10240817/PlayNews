package app.yhpl.news.fragment;

import android.os.Bundle;
import android.view.View;
import app.yhpl.news.res.ListViewConfig;

public class V1NormalFragment extends V1DataFragment {
	// protected InteterminateStatusView mIndeterminateView;

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
	public void initViews(View root) {
		super.initViews(root);
		// mIndeterminateView = (InteterminateStatusView)
		// root.findViewById(R.id.inteterminate_status_view);
		// if (mIndeterminateView != null) {
		// mIndeterminateView.setOnIndeterminateListener(this);
		// }
	}

	@Override
	public void onDataLoadingMore() {
		log("onDataLoadingMore");
	}

	@Override
	public void onDataBeginLoading() {

	}

	// protected void setIndeterminateStatus(boolean isIndeterminate) {
	// if (mIndeterminateView != null) {
	// mIndeterminateView.setOnIndeterminateStatus(isIndeterminate);
	// }
	//
	// }

	// @Override
	// public boolean handleIndeterminateStateChange(View view, boolean
	// isIndeterminater) {
	// return false;
	// }
	//
	// @Override
	// public void onInedeterminateEnter() {
	// resignInput();
	// }

}
