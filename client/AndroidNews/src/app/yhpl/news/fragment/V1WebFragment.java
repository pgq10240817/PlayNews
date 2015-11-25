package app.yhpl.news.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.yhpl.core.http.res.BaseGson;
import android.yhpl.util.log.MyLog;
import app.yhpl.kit.widget.EmptyView;
import app.yhpl.news.R;
import app.yhpl.news.adapter.V1BaseAdapter;
import app.yhpl.news.res.BaseConfig;
import app.yhpl.news.tag.BundleKeys;
import app.yhpl.news.tag.BundleValues;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

public class V1WebFragment extends V1DataFragment implements OnRefreshListener<WebView> {

	private static final String TAG = "V1WebFragment";
	protected PullToRefreshWebView mWebViewDecor;
	protected WebView mWebView;
	protected String mUrl;
	protected String mFullUrl = null;
	private boolean mIsError;
	private boolean isLoading = false;
	private boolean isVisible;

	private final static int MSG_CHECK = 0x10;
	private final static int MSG_COUNTDOWN = 0x11;
	private final static int MSG_LOAD_FINISHED = 0x12;
	private final static int MSG_LOAD_CANCEL = 0x13;
	private String URL_BLANK = "about:blank";

	private Handler mTimeoutHandler = new Handler() {
		private int mStep = 0;
		private int STEP_TOTAL = 15;

		public void reset() {
			MyLog.log("handle", "reset " + mStep);
			removeCallbacksAndMessages(null);
			mStep = 0;
		}

		public void beginCountDown() {
			reset();
			sendEmptyMessageDelayed(MSG_CHECK, 1000);

		}

		private void check() {
			MyLog.log("handle", "check " + mStep);
			mStep++;
			if (mStep >= STEP_TOTAL) {
				reset();
				int progress = 0;
				if (mWebViewDecor != null && mWebViewDecor.getRefreshableView() != null) {
					progress = mWebViewDecor.getRefreshableView().getProgress();
				}
				MyLog.log("handle", "progress " + progress);
				if (mWebViewDecor != null && mWebViewDecor.getRefreshableView() != null && progress < 100) {
					mIsError = true;
					mWebViewDecor.getRefreshableView().stopLoading();
					if (!V1WebFragment.this.isDestoryed) {
						V1WebFragment.this.onLoadFinished();
					} else {

					}
				} else if (progress == 100) {
					mIsError = false;
					V1WebFragment.this.onLoadFinished();
				}
			}
		}

		public void handleMessage(android.os.Message msg) {
			removeCallbacksAndMessages(null);
			switch (msg.what) {
			case MSG_LOAD_CANCEL:
				mStep = STEP_TOTAL;
				break;
			case MSG_LOAD_FINISHED:
				reset();
				V1WebFragment.this.onLoadFinished();
				break;
			case MSG_CHECK:
				sendEmptyMessageDelayed(MSG_CHECK, 1000);
				check();
				break;
			case MSG_COUNTDOWN:
				beginCountDown();
			default:
				break;
			}

		};

	};

	@Override
	public void initViews(View root) {
		super.initViews(root);
		initWebView(root);
		applyPolicy();
		if (!isLoading) {
			loadUrl(false);
		}

	}

	private boolean isUidChanged() {
		boolean result = false;
		// if (!TextUtils.isEmpty(mFullUrl)) {
		// String val = URLTools.getValueFromUrl(mFullUrl, "uid");
		// if (!TextUtils.isEmpty(val)) {
		// int uid = AppContext.instance().getLoginUid();
		// int oldUid = Integer.parseInt(val);
		// if (oldUid != uid) {
		// result = true;
		// }
		// }
		// }
		return result;
	}

	@Override
	public void initArguments(Bundle bundle) {
		super.initArguments(bundle);

		mConfig = new BaseConfig();
		mConfig.parseBundle(bundle);

		mTitle = bundle.getString(BundleKeys.KEY_S_TITLE);
		if (TextUtils.isEmpty(mTitle)) {
			mTitle = getString(R.string.app_name);
		}

		mUrl = bundle.getString(BundleKeys.KEY_S_URL);

		MyLog.logD("uri is " + mUrl);

		if (TextUtils.isEmpty(mUrl)) {
			return;
		}
	}

	@SuppressLint("NewApi")
	protected void applyPolicy() {
		if (null == mWebViewDecor) {
			return;
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
			mWebView.removeJavascriptInterface("accessibility");
			mWebView.removeJavascriptInterface("accessibilityTraversal");
		}
	}

	protected String getUrl() {
		return mUrl;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void initWebView(View rootView) {

		mWebViewDecor = (PullToRefreshWebView) rootView.findViewById(R.id.web);
		mWebViewDecor.setOnRefreshListener(this);

		mWebView = mWebViewDecor.getRefreshableView();
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setDefaultTextEncodingName("utf-8");
		webSettings.setBuiltInZoomControls(false);
		webSettings.setSupportZoom(false);
		webSettings.setDisplayZoomControls(false);
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setSavePassword(false);
		webSettings.setDomStorageEnabled(true);
		mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		webSettings.setBlockNetworkImage(true);
		mWebView.setVerticalScrollBarEnabled(false);
		mWebView.setVerticalScrollbarOverlay(false);
		mWebView.getSettings().setUserAgentString(
				"Mozilla/5.0 (Linux; U; " + "Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 "
						+ "(KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");

		mWebView.getSettings().setJavaScriptEnabled(true);

		if (Build.VERSION.SDK_INT >= 19) {
			webSettings.setLoadsImagesAutomatically(true);
		} else {
			webSettings.setLoadsImagesAutomatically(false);
		}
		// Intent intent = getActivity().getIntent();
		// if (intent != null) {
		// // mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		// }

		mWebView.setWebViewClient(getWebViewClient());
		mWebView.setWebChromeClient(getWebChromeClient());

	}

	@Override
	public void onResume() {
		isVisible = true;
		super.onResume();
	}

	@Override
	public void onPause() {
		isVisible = false;
		super.onPause();
	}

	@Override
	public void onDestroy() {

		if (mWebView != null) {
			mWebView.stopLoading();
			mWebView.setVisibility(View.GONE);
			mWebView.removeAllViews();
			mWebView.destroy();
			mWebView = null;
		}
		super.onDestroy();
	}

	private void startTimeOutTask() {
		if (mTimeoutHandler != null) {
			mTimeoutHandler.removeCallbacksAndMessages(null);
			mTimeoutHandler.sendEmptyMessage(MSG_COUNTDOWN);
		}

	}

	private void stopTimeOutTask(boolean isFinish) {
		if (mTimeoutHandler != null) {
			mTimeoutHandler.removeCallbacksAndMessages(null);
			if (isFinish) {
				mTimeoutHandler.sendEmptyMessage(MSG_LOAD_FINISHED);
			} else {
				mTimeoutHandler.sendEmptyMessage(MSG_LOAD_FINISHED);
			}

		}
	}

	private WebViewClient getWebViewClient() {
		return new MyWebViewClient();
	}

	private WebChromeClient getWebChromeClient() {
		return new MyWebChromeClient();

	}

	@Override
	public String getTitle() {

		if (mIsError) {
			return null;
		}
		return mTitle;
	}

	public void updateTitleBarTitle(String title) {
		if (isDestoryed) {
			return;
		}
		Fragment mParent = getParentFragment();
		if (mTitleHint != null) {
			mTitleHint.setText(title);
		}
	}

	public class MyWebChromeClient extends WebChromeClient {
		@Override
		public void onReceivedTitle(WebView view, String title) {
			super.onReceivedTitle(view, title);
			// Log.d("ANDROID_LAB", "TITLE=" + title);
			MyLog.logD("title is  " + title);
			if (isVisible) {
				updateTitleBarTitle(title);
			}
		}
	};

	public class MyWebViewClient extends WebViewClient {

		@Override
		public void onLoadResource(WebView view, String url) {
			super.onLoadResource(view, url);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			MyLog.log("shouldOverrideUrlLoading", url);
			// mEmptyLayout.setErrorType(EmptyView.NETWORK_LOADING);
			if (url.startsWith("http")) {
				view.loadUrl(url);
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			MyLog.log("onPageStarted", url);
			super.onPageStarted(view, url, favicon);
			startTimeOutTask();
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			isLoading = false;
			MyLog.log("onPageFinished", " mIsError" + mIsError + "," + url);
			if (isFragmentDestory()) {
				return;
			}
			mWebView.getSettings().setBlockNetworkImage(false);
			if (!mWebView.getSettings().getLoadsImagesAutomatically()) {
				mWebView.getSettings().setLoadsImagesAutomatically(true);
			}
			stopTimeOutTask(true);
			if (mIsError) {
				return;
			}

		}

		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
			// TODO Auto-generated method stub
			super.onReceivedSslError(view, handler, error);
			handler.proceed(); // 接受证书
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			if (failingUrl.startsWith("http") && isLoading) {
				mIsError = true;
			}
			MyLog.logD(" onReceivedError errorCode" + errorCode + "," + failingUrl + "," + description);
			super.onReceivedError(view, errorCode, description, failingUrl);
		}
	}

	private void onLoadFinished() {
		if (isFragmentDestory()) {
			return;
		}
		if (mWebViewDecor != null) {
			mWebViewDecor.onRefreshComplete();
		}
		Log.d("myTag", "onLoadingFinsihed mIsError:" + mIsError);
		// if (mEmptyLayout != null) {
		if (mIsError) {
			// showContentView();
			showError();
		} else {
			showContentView();
			// mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
		}
		// }
	}

	private boolean isFragmentDestory() {

		return isDestoryed;
	}

	protected boolean isNeedRefresh() {
		return false;
	}

	private String addUrlParamters(String fullUrl, String key, String value) {
		int begin = fullUrl.indexOf(key);
		if (begin < 0) {
			if (fullUrl.endsWith("?")) {
				fullUrl = fullUrl + key + "=" + value;
			} else if (fullUrl.endsWith("&")) {
				fullUrl = fullUrl + key + "=" + value;
			} else if (fullUrl.indexOf("?") > 0) {
				fullUrl = fullUrl + "&" + key + "=" + value;
			} else {
				fullUrl = fullUrl + "?" + key + "=" + value;
			}
		}
		return fullUrl;
	}

	public void loadNewUrl(String newUrl, boolean isNeedUid, boolean isNeedAuid) {
		this.mUrl = newUrl;
		if (isDestoryed) {
			return;
		}
		loadUrl(false);

	}

	// private String getFormedUrl(String url) {
	// if (isNeedUID) {
	// int uid = AppContext.instance().getLoginUid();
	// if (uid > 0) {
	// url = URLTools.getFillValueUrl(url, "uid", String.valueOf(uid));
	// }
	// }
	// if (isNeedAuid) {
	// String auid = AppContext.instance().getAuthId();
	// if (!TextUtils.isEmpty(auid)) {
	// url = addUrlParamters(url, "authid", auid);
	// }
	//
	// }
	// return url;
	//
	// }

	private void loadUrl(final Boolean isReload) {
		MyLog.logD("url is " + mUrl + ",isReload:" + isReload);
		mIsError = false;
		if (mWebViewDecor == null || mUrl == null) {
			return;
		}
		isLoading = true;
		showLoadingView();
		// mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
		if (isReload && mWebView.getUrl() != null && !URL_BLANK.equals(mWebView.getUrl())) {
			Log.d("myTag", "reload--");
			// mWebView.reload();
			mWebView.loadUrl("javascript:window.location.reload(true)");
		} else {
			String fullUrl = mUrl;

			// if (isNeedUID) {
			// int uid = AppContext.instance().getLoginUid();
			// if (uid > 0) {
			// fullUrl = addUrlParamters(fullUrl, "uid", String.valueOf(uid));
			// }
			//
			// }
			// if (isNeedAuid) {
			// String auid = AppContext.instance().getAuthId();
			// if (!TextUtils.isEmpty(auid)) {
			// fullUrl = addUrlParamters(fullUrl, "authid", auid);
			// }
			//
			// }
			mFullUrl = fullUrl;
			mWebView.loadUrl(fullUrl);
			MyLog.logD("url is " + mFullUrl);

		}
	}

	public boolean goBackIfCan() {
		boolean result = false;
		if (mWebView != null && mWebView.canGoBack()) {
			WebBackForwardList mWebBackForwardList = mWebView.copyBackForwardList();
			if (mWebBackForwardList != null && mWebBackForwardList.getCurrentIndex() > 1) {
				// mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
				mWebView.goBack();
				result = true;
			}
		}
		return result;
	}

	public boolean canGoBack() {
		boolean result = false;
		if (mWebView != null && mWebView.canGoBack()) {
			WebBackForwardList mWebBackForwardList = mWebView.copyBackForwardList();
			if (mWebBackForwardList != null && mWebBackForwardList.getCurrentIndex() > 1) {
				result = true;
			}
		}
		return result;
	}

	public void gotoRoot(String url, boolean isNeedUid, boolean isNeedAuid) {
		if (mWebView != null && mWebView.canGoBack()) {
			showLoadingView();
			// mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
			WebBackForwardList mWebBackForwardList = mWebView.copyBackForwardList();
			// Log.d("myTag", " --- gotoRoot " +
			// (-mWebBackForwardList.getSize()));
			if (isUidChanged()) {
				// Log.d("myTag", " --- gotoRoot isUidChanged :");
				this.mUrl = url;
				mWebView.goBackOrForward(-mWebBackForwardList.getSize());
			} else {
				// Log.d("myTag", " --- index ");
				int step = 2 - mWebBackForwardList.getSize();
				if (step != 0 && mWebBackForwardList.getCurrentIndex() > 1) {
					mWebView.goBackOrForward(step);
				} else {
					// if (mIsError) {
					// mEmptyLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
					// mEmptyLayout.setOnLayoutClickListener(this);
					// } else {
					// if (isLoading) {
					// mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
					// } else {
					// mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
					// }
					// }
				}
			}
		} else {
			// if (mWebView != null) {
			// loadNewUrl(url, isNeedUid, isNeedAuid);
			// }

		}
	}

	// @Override
	// public void onClick(View v) {
	// super.onClick(v);
	// switch (v.getId()) {
	// case R.id.error_layout:
	// case R.id.img_error_layout:
	// loadUrl(true);
	// break;
	//
	// default:
	// break;
	// }
	// }

	@Override
	public boolean onBackPressed() {
		return goBackIfCan();
	}

	@Override
	public void onRefresh(PullToRefreshBase<WebView> refreshView) {
		// mState = STATE_REFRESH;
		loadUrl(true);
	}

	@Override
	public void onDataBeginLoading() {
		if (mWebView == null) {
			isLoading = false;
		} else {
			loadUrl(false);
		}
	}

	@Override
	public void onEmptyViewRefresh(int oldState) {
		if (oldState == EmptyView.STATE_FAILED) {
			loadUrl(true);
		} else {
			super.onEmptyViewRefresh(oldState);
		}
	}

	@Override
	public void onDataLoadingMore() {

	}

	@Override
	public V1BaseAdapter initAdapter(BaseGson baseGson) {
		return null;
	}

	@Override
	public int getLayoutRes() {
		int layout = mConfig.getLayoutRes();
		return layout == BundleValues.VAL_I_LAYOUT ? R.layout.v1_fragment_web : layout;

	}

}
