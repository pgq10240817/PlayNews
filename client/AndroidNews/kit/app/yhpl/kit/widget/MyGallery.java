package app.yhpl.kit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Gallery;

public class MyGallery extends Gallery {

	public MyGallery(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public MyGallery(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGallery(Context context) {
		super(context);
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		Log.e("myTag", velocityX + " ---  ");
		if (velocityX < -1000) {
			velocityX = -1000;
		} else if (velocityX > 1000) {
			velocityX = 1000;
		}
		return super.onFling(e1, e2, velocityX, velocityY);
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		float ev = e.getY();
		Log.e("myTag", "----->  " + ev);
		if (ev < 250) {
			return true;
		} else {
			return super.onSingleTapUp(e);
		}
		// return super.onSingleTapUp(e);

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

		return super.onScroll(e1, e2, distanceX, distanceY);
	}

}
