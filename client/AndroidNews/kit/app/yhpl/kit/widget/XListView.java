/**
 * @file XListView.java
 * @package me.maxwin.view
 * @create Mar 18, 2012 6:28:41 PM
 * @author Maxwin
 * @description An ListView support (a) Pull down to refresh, (b) Pull up to load more.
 * 		Implement IXListViewListener, and see stopRefresh() / stopLoadMore().
 */
package app.yhpl.kit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.ListView;

public class XListView extends ListView implements OnScrollListener {

	/* --- lisnter --- */
	private IXListViewListener mListViewListener;
	private XMode mMode = XMode.getDefault();
	private XState mState = XState.RESET;
	private int mTouchSlop;
	private float mLastMotionX, mLastMotionY;
	private float mInitialMotionX, mInitialMotionY;
	private boolean mIsBeingDragged = false;
	private boolean mIsPullAvaiable = false;

	/* ---static --- */
	static final float FRICTION = 2.0f;
	private boolean DEBUG = true;
	private final static String LOG_TAG = "myTag";
	public static final int SMOOTH_SCROLL_DURATION_MS = 200;
	private XIHeader mHeader;
	private XIFooter mFooter;
	private int mHttpType = 0;
	private static final String PREF_TAG_PREFIX = "refreshType";
	private float mInitalY, mInitalX;
	private final static String TAG = "myTag";
	private final static String METHOD = "child";

	public XListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		updateUIForMode();
	}

	public XListView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.listViewStyle);
	}

	public XListView(Context context) {
		this(context, null);
	}

	public final void setMode(XMode mode) {
		if (mode != mMode) {
			if (DEBUG) {
				Log.d(LOG_TAG, "Setting mode to: " + mode);
			}
			mMode = mode;
			updateUIForMode();
		}
	}

	protected void updateUIForMode() {

		this.setOnScrollListener(this);
		ViewConfiguration config = ViewConfiguration.get(getContext());
		mTouchSlop = config.getScaledTouchSlop();

		mHeader = new XListHeader();
		mHeader.initView(getContext());
		addHeaderView(mHeader.getListHeaderContent());
		if (mMode.showFooterLoadingLayout()) {
			mFooter = new XListViewFooter();
			mFooter.initView(getContext());
			mFooter.chageState(XFooterState.LOADING_PREVIEW);
			addFooterView(mFooter.getViewContent());
		}

	}

	public void setXListViewListener(IXListViewListener l) {
		mListViewListener = l;
	}

	public void setRefreshHttpType(int type) {
		mHttpType = type;
	}

	public interface OnXScrollListener extends OnScrollListener {
		public void onXScrolling(View view);
	}

	public interface IXListViewListener {
		public void onRefresh();

		public XFooterState onLoadMore(XFooterState state);
	}

	public final void onRefreshComplete() {
		if (isRefreshing()) {
			// String key = PREF_TAG_PREFIX + mHttpType;
			// long seconds = SystemClock.currentThreadTimeMillis() / 1000;
			// ABPrefsUtil.getInstance().editor.putLong(key, seconds).apply();
			setState(XState.RESET);
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (DEBUG) {
			Log.e(TAG, "listview -- onInterceptTouchEvent " + ev.getAction());
		}

		return super.onInterceptTouchEvent(ev);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

	}

	private void initParentScollView() {

	}

	private boolean isParentScrollAtBottom() {
		return false;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (DEBUG) {
			Log.e(TAG, "dispatchTouchEvent  " + ev.getAction());
		}
		switch (ev.getAction()) {
		case MotionEvent.ACTION_MOVE: {
			break;
		}
		case MotionEvent.ACTION_DOWN: {
			mInitalY = ev.getY();
			mInitalX = ev.getX();
			break;
		}
		}
		return super.dispatchTouchEvent(ev);
	}

	// ===========================================================
	// touch event
	// ===========================================================

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (DEBUG) {
			Log.e(TAG, "listview -- onTouchEvent " + event.getAction());
		}
		boolean result = false;
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE: {
			if (!mIsPullAvaiable) {//
				if (isReadyForPullStart()) {
					mLastMotionY = mInitialMotionY = event.getY();
					mLastMotionX = mInitialMotionX = event.getX();
					mIsPullAvaiable = true;
					onMove(event);
				}

			} else {
				onMove(event);
			}
			break;
		}
		case MotionEvent.ACTION_DOWN: {
			if (isReadyForPull()) {
				mIsPullAvaiable = true;
				mLastMotionY = mInitialMotionY = event.getY();
				mLastMotionX = mInitialMotionX = event.getX();
				mIsBeingDragged = false;
			}
			break;
		}
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP: {
			mIsPullAvaiable = false;
			if (mIsBeingDragged) {
				mIsBeingDragged = false;
				// if (mState == State.RELEASE_TO_REFRESH && (null !=
				// mListViewListener)) {
				if (mState == XState.RELEASE_TO_REFRESH && mListViewListener != null) {
					setState(XState.REFRESHING, true);
					result = true;
					return result;
				}
				if (isRefreshing() && mListViewListener != null) {
					setState(XState.REFRESHING, false);
					result = true;
					return result;
				}
				setState(XState.RESET);

				result = true;
			}
			break;
		}
		}

		return super.onTouchEvent(event);
	}

	private void onMove(MotionEvent event) {
		final float y = event.getY(), x = event.getX();
		final float diff, oppositeDiff, absDiff;
		diff = y - mLastMotionY;
		oppositeDiff = x - mLastMotionX;
		absDiff = Math.abs(diff);
		if (absDiff > mTouchSlop) {
			if (mMode.showHeaderLoadingLayout() && diff >= 1f && isReadyForPullStart()) {
				mLastMotionY = y;
				mLastMotionX = x;
				mIsBeingDragged = true;
			}
		}

		if (mIsBeingDragged) {
			mLastMotionY = event.getY();
			mLastMotionX = event.getX();
			pullEvent();
		}
	}

	private void pullEvent() {
		final int newScrollValue;
		final int itemDimension;
		final float initialMotionValue, lastMotionValue;
		initialMotionValue = mInitialMotionY;
		lastMotionValue = mLastMotionY;
		setSelection(0);
		newScrollValue = Math.round(Math.max(lastMotionValue - initialMotionValue, 0) / FRICTION);
		itemDimension = getHeaderSize();
		setHeaderScroll(newScrollValue);

		if (newScrollValue != 0 && !isRefreshing()) {
			if (mState != XState.PULL_TO_REFRESH && itemDimension >= Math.abs(newScrollValue)) {
				setState(XState.PULL_TO_REFRESH);
			} else if (mState == XState.PULL_TO_REFRESH && itemDimension < Math.abs(newScrollValue)) {
				setState(XState.RELEASE_TO_REFRESH);
			}
		}
	}

	/**
	 * Helper method which just calls scrollTo() in the correct scrolling
	 * direction.
	 * 
	 * @param value
	 *            - New Scroll value
	 */
	protected final void setHeaderScroll(int value) {
		if (DEBUG) {
			Log.d(LOG_TAG, "setHeaderScroll: " + value);
		}
		mHeader.moveHeader(value);

	}

	// @Override
	// public void computeScroll() {
	// Log.e("myTag", "computeScroll " + this.getScrollY());
	// // if (mScroller.computeScrollOffset()) {
	// // if (mScrollBack == SCROLLBACK_HEADER) {
	// // mHeader.setVisiableHeight(mScroller.getCurrY());
	// // } else {
	// // mHeader.setBottomMargin(mScroller.getCurrY());
	// // }
	// // postInvalidate();
	// // // invokeOnScrolling();
	// // }
	// super.computeScroll();
	// }

	final void setState(XState state, final boolean... params) {
		mState = state;
		if (DEBUG) {
			Log.d(LOG_TAG, "State: " + mState.name());
		}
		switch (mState) {
		case RESET:
			onReset();
			break;
		case PULL_TO_REFRESH:
			// onPullToRefresh();
			break;
		case RELEASE_TO_REFRESH:
			// onReleaseToRefresh();
			break;
		case REFRESHING:
			onRefreshing(params[0]);
			break;
		case OVERSCROLLING:
			// NO-OP
			break;
		}

		mHeader.chageState(mState);
	}

	protected final int getFooterSize() {
		// return mFooterLayout.getContentSize();
		return 100;
	}

	// protected final LoadingLayout getHeaderLayout() {
	// // return mHeaderLayout;
	// }

	protected final int getHeaderSize() {
		return mHeader.getViewLoadingHeight();
	}

	public final boolean isRefreshing() {
		return mState == XState.REFRESHING || mState == XState.MANUAL_REFRESHING;
	}

	protected void onRefreshing(final boolean doScroll) {
		if (mMode.showHeaderLoadingLayout()) {
			if (mListViewListener != null) {
				mListViewListener.onRefresh();
			}
		}

	}

	protected void onReset() {
		mIsBeingDragged = false;
		// mLayoutVisibilityChangesEnabled = true;
		//
		// // Always reset both layouts, just in case...
		// mHeaderLayout.reset();
		// mFooterLayout.reset();
		// smoothScrollTo(0);
	}

	public void updateFooterState(XFooterState state) {
		if (mFooter != null) {
			mFooter.chageState(state);
		}
	}

	public void updateFooterState(XFooterState state, int hintStr) {
		if (mFooter != null) {
			mFooter.chageState(state);
		}
	}

	private boolean isReadyForPull() {
		switch (mMode) {
		case PULL_FROM_START:
			return isReadyForPullStart();
		case PULL_FROM_END:
			return isReadyForPullEnd();
		case BOTH:
			return isReadyForPullEnd() || isReadyForPullStart();
		default:
			return false;
		}
	}

	private boolean isReadyForPullStart() {
		boolean result = isFirstItemVisible();
		// if (result) {
		// long now = SystemClock.currentThreadTimeMillis() / 1000;
		// long time = ABPrefsUtil.getInstance().getLong(PREF_TAG_PREFIX +
		// mHttpType, now);
		// mHeader.showLastRefreshTime(now - time);
		// }
		return result;
	}

	private boolean isReadyForPullEnd() {
		return false;

	}

	private boolean isFirstItemVisible() {
		final Adapter adapter = getAdapter();

		if (null == adapter || adapter.isEmpty()) {
			return true;

		} else {

			if (getFirstVisiblePosition() <= 1) {
				final View firstVisibleChild = getChildAt(0);
				if (firstVisibleChild != null) {
					return firstVisibleChild.getTop() >= 0;
				}
			}
		}

		return false;
	}

	private boolean isLastItemVisible() {
		boolean reuslt = false;
		final Adapter adapter = getAdapter();

		if (null == adapter || adapter.isEmpty()) {
			return true;
		} else {
			final int lastItemPosition = getCount() - 1;
			final int lastVisiblePosition = getLastVisiblePosition();

			if (lastVisiblePosition >= lastItemPosition - 1) {
				final int childIndex = lastVisiblePosition - getFirstVisiblePosition();
				final View lastVisibleChild = getChildAt(childIndex);
				if (lastVisibleChild != null) {
					reuslt = lastVisibleChild.getBottom() <= getBottom();
				}
			}
		}

		return reuslt;
	}

	// ===========================================================
	// scroll event
	// ===========================================================
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (mMode.showFooterLoadingLayout()) {
			if (scrollState == SCROLL_STATE_IDLE) {
				if (isLastItemVisible()) {
					if (mListViewListener != null) {
						XFooterState newState = mListViewListener.onLoadMore(mFooter.getState());
						mFooter.chageState(newState);
					}
				}
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

	}

}