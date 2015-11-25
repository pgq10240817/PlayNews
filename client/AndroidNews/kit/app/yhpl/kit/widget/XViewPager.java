package app.yhpl.kit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import app.yhpl.news.R;

public class XViewPager extends ViewPager {

	private boolean isCanScroll = true;

	public XViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (isInEditMode()) {
			return;
		}
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XViewPager, 0, 0);
		isCanScroll = a.getBoolean(R.styleable.XViewPager_canScroll,
				getResources().getBoolean(R.bool.default_xviewpager_canscroll));
		a.recycle();
	}

	public XViewPager(Context context) {
		super(context);
	}

	@Override
	public void setCurrentItem(int item) {
		if (isCanScroll) {
			super.setCurrentItem(item);
		} else {
			super.setCurrentItem(item, false);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (isCanScroll) {
			return super.onTouchEvent(event);
		} else {
			return false;
		}
	}

	public void setCanScroll(boolean isCanScroll) {
		this.isCanScroll = isCanScroll;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (isCanScroll) {
			return super.onInterceptTouchEvent(event);
		} else {
			return false;
		}

	}
}
