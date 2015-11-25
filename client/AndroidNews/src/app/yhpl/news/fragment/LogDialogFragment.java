package app.yhpl.news.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import app.yhpl.kit.log.Logger;
import app.yhpl.news.R;
import app.yhpl.news.cross.LogActivity;
import app.yhpl.news.res.BaseConfig;
import app.yhpl.news.tag.LogTags;

public abstract class LogDialogFragment extends DialogFragment implements FragmentOnClickListener {

	protected boolean isDestoryed = false;

	/** titlebar* */
	protected ImageView mTitlebarLeft, mTitlebarRight, mTitleBarImg;
	protected TextView mTitleHint;
	protected BaseConfig mConfig;

	private String getClassName() {
		return this.getClass().getSimpleName();
	}

	protected void log(String method) {
		Logger.d(LogTags.FRAGMENT, String.format("[%s]%s-->", getClassName(), method));
	}

	public abstract int getLayoutRes();

	public void initViews(View root) {

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		log("onAttach");
		Bundle bundle = getArguments();
		if (bundle == null || bundle.isEmpty()) {
			bundle = activity.getIntent().getExtras();
		}
		if (bundle != null && bundle.size() > 0) {
			initArguments(bundle);
		}
	}

	public void initArguments(Bundle bundle) {

	}

	@Override
	public void onDetach() {
		log("onDetach");
		super.onDetach();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		log("onCreate");
		setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CustomDialogFragment);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		log("onCreateView");
		return inflater.inflate(getLayoutRes(), container, false);

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		log("onViewCreated");
		isDestoryed = false;
		initViews(view);
	}

	@Override
	public void onClick(View v) {
		boolean handled = false;
		switch (v.getId()) {
		case R.id.bt_titlebar_left:
			handled = onTitleBarButtonClick(true);
			break;
		case R.id.bt_titlebar_right:
			handled = onTitleBarButtonClick(false);
			break;

		default:
			handled = processOnClick(v);
			break;
		}
		if (!handled) {
			LogActivity act = (LogActivity) getActivity();
			act.processOnClick(v);
		}
	}

	@Override
	public void onClick(View v, Bundle args) {

	}

	@Override
	public void onLongClick(View view, Bundle bundle) {

	}

	public boolean processOnClick(View view) {

		return false;
	}

	public boolean onBackPressed() {
		return false;
	}

	public boolean onTitleBarButtonClick(boolean isLeft) {
		boolean result = false;
		if (isLeft) {
			getActivity().finish();
		}
		return result;
	}

	public final boolean isFragmentInvalide() {
		return isDestoryed;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		log("setUserVisibleHint :" + isVisibleToUser);
	}

	protected final void resignInput() {
		if (isDestoryed) {
			return;
		}
		InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(
				Context.INPUT_METHOD_SERVICE);

		View mFocusView = getActivity().getCurrentFocus();
		if (mFocusView != null) {
			IBinder token = mFocusView.getWindowToken();
			if (token != null) {
				inputMethodManager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		resignInput();
		isDestoryed = true;
		log("onDestroyView");
	}

	@Override
	public void onStart() {
		super.onStart();
		log("onStart");
	}

	@Override
	public void onStop() {
		super.onStop();
		log("onStop");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		log("onDestroy");
	}

}
