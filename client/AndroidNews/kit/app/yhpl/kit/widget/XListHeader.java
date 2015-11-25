/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package app.yhpl.kit.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import app.yhpl.news.R;

import com.nineoldandroids.animation.ObjectAnimator;

public class XListHeader implements XIHeader {
	private View mView;
	private ImageView mArrowImageView;
	private ProgressBar mProgressBar;
	private TextView mHintTextView;
	private TextView mHintTime;

	private Animation mRotateUpAnim;
	private Animation mRotateDownAnim;

	private final int ROTATE_ANIM_DURATION = 180;
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_REFRESHING = 2;
	private final static long MIN = 60;
	private final static long HOUR = 60 * 60;
	private final static long DAY = 24 * 60 * 60;
	private final static long MONTH = 30 * 24 * 60 * 60;

	private XState mState = XState.RESET;

	private int mHeaderViewHeight = 0;
	private int mHeaderMaxOffset = 0;

	@Override
	public void initView(Context context) {

		mHeaderViewHeight = context.getResources().getDimensionPixelOffset(R.dimen.indicator_header_height);

		mHeaderMaxOffset = mHeaderViewHeight * 2;
		mView = LayoutInflater.from(context).inflate(R.layout.xlistview_header, null);

		mView.setPadding(0, -mHeaderViewHeight, 0, 0);

		mRotateUpAnim = new RotateAnimation(0.0f, -180.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateUpAnim.setFillAfter(true);
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateDownAnim.setFillAfter(true);
		View tmp = mView.findViewById(R.id.xlistview_header_hint_textview);
		if (tmp instanceof TextView) {
			mHintTextView = (TextView) tmp;
		}
		tmp = (ProgressBar) mView.findViewById(R.id.xlistview_header_progressbar);
		if (tmp instanceof ProgressBar) {
			mProgressBar = (ProgressBar) tmp;
		}
		tmp = (ImageView) mView.findViewById(R.id.xlistview_header_arrow);
		if (tmp instanceof ImageView) {
			mArrowImageView = (ImageView) tmp;
		}
		mHintTime = (TextView) mView.findViewById(R.id.xlistview_header_time);
	}

	public static String getLastRefreshTimeStr(long time) {
		if (time < 0) {
			return "1秒前";
		} else if (time < 60) {
			return time + "秒前";
		} else if (time < HOUR) {
			int minutes = (int) (time / MIN);
			return minutes + "分钟前";
		} else if (time < DAY) {
			int hour = (int) (time / HOUR);
			return hour + "小时前";
		} else if (time < MONTH) {
			int day = (int) (time / DAY);
			return day + "天前";
		} else {
			return "1月前";
		}
	}

	@Override
	public void showLastRefreshTime(long time) {
		mHintTime.setText(getLastRefreshTimeStr(time));

	}

	// public void setState(int state) {
	// if (state == mState)
	// return;
	//
	// if (state == STATE_REFRESHING) { // 显示进度
	// mArrowImageView.clearAnimation();
	// mArrowImageView.setVisibility(View.INVISIBLE);
	// mProgressBar.setVisibility(View.VISIBLE);
	// } else { // 显示箭头图片
	// mArrowImageView.setVisibility(View.VISIBLE);
	// mProgressBar.setVisibility(View.INVISIBLE);
	// }
	//
	// switch (state) {
	// case STATE_NORMAL:
	// if (mState == STATE_READY) {
	// mArrowImageView.startAnimation(mRotateDownAnim);
	// }
	// if (mState == STATE_REFRESHING) {
	// mArrowImageView.clearAnimation();
	// }
	// mHintTextView.setText(R.string.xlistview_header_hint_normal);
	// break;
	// case STATE_READY:
	// if (mState != STATE_READY) {
	// mArrowImageView.clearAnimation();
	// mArrowImageView.startAnimation(mRotateUpAnim);
	// mHintTextView.setText(R.string.xlistview_header_hint_ready);
	// }
	// break;
	// case STATE_REFRESHING:
	// mHintTextView.setText(R.string.xlistview_header_hint_loading);
	// break;
	// default:
	// }
	//
	// mState = state;
	// }

	@Override
	public void moveHeader(int scrollY) {
		Log.e("myTag", " moveHeader -->  " + scrollY + "  , " + mHeaderViewHeight);
		if (mState == XState.REFRESHING) {

		} else {
			scrollY += -mHeaderViewHeight;
		}
		if (scrollY > mHeaderMaxOffset - mHeaderViewHeight) {
			scrollY = mHeaderMaxOffset - mHeaderViewHeight;
		} else if (scrollY < -mHeaderViewHeight) {
			scrollY = -mHeaderViewHeight;
		}
		Log.e("myTag", " moveHeader   " + scrollY);
		mView.setPadding(0, scrollY, 0, 0);
	}

	public void setMoveHeader(int height) {
		if (height > mHeaderMaxOffset) {
			height = mHeaderMaxOffset;
		} else if (height < -mHeaderViewHeight) {
			height = -mHeaderViewHeight;
		}
		Log.e("myTag", " setMoveHeader   " + height);
		mView.setPadding(0, height, 0, 0);
	}

	@Override
	public View getListHeaderContent() {
		return mView;
	}

	@Override
	public void chageState(XState state) {
		Log.e("myTag", "  header  --->  state  " + state);
		mState = state;
		if (mArrowImageView != null) {
			mArrowImageView.setVisibility(View.GONE);
		}

		switch (mState) {
		case RESET:
			onReset();
			break;
		case REFRESHING:
			onRefreshing();
			break;
		case PULL_TO_REFRESH:
			onPull();
			break;
		case RELEASE_TO_REFRESH:
			onRelease();
			break;

		default:
			break;
		}

	}

	private void onReset() {
		mArrowImageView.clearAnimation();
		mHintTextView.setText(R.string.xlistview_header_hint_normal);
		android.util.Log.e("myTag", "reset   ---->  " + mView.getPaddingTop());
		ObjectAnimator animator = ObjectAnimator.ofInt(this, "MoveHeader", mView.getPaddingTop(), -mHeaderViewHeight);
		animator.setInterpolator(new DecelerateInterpolator());
		animator.setDuration(200);
		animator.start();
	}

	private void onRefreshing() {
		if (mProgressBar != null) {
			mProgressBar.setVisibility(View.VISIBLE);
		}
		if (mArrowImageView != null) {
			mArrowImageView.clearAnimation();
			mArrowImageView.setVisibility(View.GONE);
		}
		ObjectAnimator animator = ObjectAnimator.ofInt(this, "MoveHeader", mView.getPaddingTop(), 0);
		animator.setInterpolator(new DecelerateInterpolator());
		animator.setDuration(200);
		animator.start();
		mHintTextView.setText(R.string.xlistview_header_hint_loading);
	}

	private void onPull() {
		if (mProgressBar != null) {
			mProgressBar.setVisibility(View.INVISIBLE);
		}
		if (mArrowImageView != null) {
			mArrowImageView.setVisibility(View.VISIBLE);
			mArrowImageView.clearAnimation();
			mArrowImageView.startAnimation(mRotateDownAnim);
		}
		mHintTextView.setText(R.string.xlistview_header_hint_normal);
	}

	private void onRelease() {
		if (mArrowImageView != null) {
			mArrowImageView.setVisibility(View.VISIBLE);
			mArrowImageView.clearAnimation();
			mArrowImageView.startAnimation(mRotateUpAnim);
		}
		mHintTextView.setText(R.string.xlistview_header_hint_ready);
	}

	@Override
	public int getViewLoadingHeight() {
		return mHeaderViewHeight;
	}

	// @Override
	// public void reset() {
	// android.util.Log.e("myTag", "reset   ---->  " + mView.getPaddingTop());
	// ObjectAnimator animator = ObjectAnimator.ofInt(this, "MoveHeader",
	// mView.getPaddingTop(), 0);
	// // ObjectAnimator animator = ObjectAnimator.ofInt(this, "MoveHeader",
	// // mRootView.getPaddingTop(), 0);
	// animator.setInterpolator(new DecelerateInterpolator());
	// animator.setDuration(200);
	// animator.start();
	// }
}