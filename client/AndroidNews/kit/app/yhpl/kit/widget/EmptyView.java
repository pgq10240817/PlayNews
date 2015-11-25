package app.yhpl.kit.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.yhpl.news.R;

public class EmptyView extends RelativeLayout implements View.OnClickListener {
	private ProgressBar mBarProgress;
	private ImageView mIvIcon;
	private TextView mTvHint;
	public final static int STATE_LOADING = 0x1001;
	public final static int STATE_FAILED = 0x1002;
	public final static int STATE_FAILED_NET = 0x1003;
	public final static int STATE_EMPTY = 0x1004;
	public final static int STATE_SUCCESS = 0x1005;
	private int mState = STATE_FAILED;
	private OnEmptyViewDataListener mEmptyViewDataListener;

	public static interface OnEmptyViewDataListener {
		void onEmptyViewRefresh(int oldState);
	}

	public EmptyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		inflatViews();
	}

	public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr, 0);
		inflatViews();
	}

	public EmptyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflatViews();
	}

	public EmptyView(Context context) {
		this(context, null);
	}

	private void inflatViews() {
		inflate(getContext(), R.layout.v1_widget_empty, this);
		setBackgroundColor(Color.WHITE);
		initWidgets();

	}

	public void setOnEmptyViewDataListener(OnEmptyViewDataListener l) {
		this.mEmptyViewDataListener = l;
	}

	private void initWidgets() {
		if (isInEditMode()) {
			return;
		}
		mBarProgress = (ProgressBar) findViewById(R.id.emptyview_progress);
		mIvIcon = (ImageView) findViewById(R.id.emptyview_icon);
		mTvHint = (TextView) findViewById(R.id.emptyview_hint);
		this.setOnClickListener(this);
	}

	public void showLoading() {
		mBarProgress.setVisibility(View.VISIBLE);
		mTvHint.setVisibility(View.VISIBLE);
		mTvHint.setText(R.string.xlistview_header_hint_loading);
		mIvIcon.setVisibility(View.GONE);
		mState = STATE_LOADING;
	}

	public void showEmpty() {
		mBarProgress.setVisibility(View.GONE);
		mIvIcon.setBackgroundResource(R.drawable.v1_icon_empty);
		mIvIcon.setVisibility(View.VISIBLE);
		mTvHint.setVisibility(View.VISIBLE);
		mTvHint.setText(R.string.xlistview_empty);
		mState = STATE_FAILED;
	}

	public void showLoading(String msg) {
		mBarProgress.setVisibility(View.VISIBLE);
		if (TextUtils.isEmpty(msg)) {
			mTvHint.setVisibility(View.GONE);
		} else {
			mTvHint.setText(msg);
			mTvHint.setVisibility(View.VISIBLE);
		}

		mIvIcon.setVisibility(View.GONE);
		mState = STATE_LOADING;
	}

	public void showError() {
		mBarProgress.setVisibility(View.GONE);
		mIvIcon.setBackgroundResource(R.drawable.v1_icon_error);
		mIvIcon.setVisibility(View.VISIBLE);
		mTvHint.setVisibility(View.VISIBLE);
		mTvHint.setText(R.string.xlistview_header_error);
		mState = STATE_FAILED;

	}

	public void showError(String msg) {
		mBarProgress.setVisibility(View.GONE);
		mIvIcon.setBackgroundResource(R.drawable.v1_icon_error);
		mIvIcon.setVisibility(View.VISIBLE);
		if (TextUtils.isEmpty(msg)) {
			mTvHint.setVisibility(View.GONE);
		} else {
			mTvHint.setVisibility(View.VISIBLE);
			mTvHint.setText(msg);
		}
		mState = STATE_FAILED;
	}

	public void showError(int msg) {
		mBarProgress.setVisibility(View.GONE);
		mIvIcon.setBackgroundResource(R.drawable.v1_icon_error);
		mIvIcon.setVisibility(View.VISIBLE);
		if (msg <= 0) {
			mTvHint.setVisibility(View.GONE);
		} else {
			mTvHint.setVisibility(View.VISIBLE);
			mTvHint.setText(msg);
		}
		mState = STATE_FAILED;
	}

	public void setState(int state) {
		if (mState == state) {
			return;
		} else {
			switch (state) {
			case STATE_LOADING:
				if (mEmptyViewDataListener != null) {
					mEmptyViewDataListener.onEmptyViewRefresh(mState);
				}
				showLoading();
				break;
			case STATE_FAILED:
				showError();
				break;
			default:
				break;
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (mState) {
		case STATE_LOADING:

			break;
		case STATE_FAILED:
			setState(STATE_LOADING);
			break;
		default:
			break;
		}

	}
}
