package app.yhpl.kit.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class DisplayUtil {
	private static DisplayMetrics displayMetrics;
	private static float density = 2.0f;

	public static void hideTitle(Activity activity) {
		// call this before setContentView
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	public static void init(Activity activity) {
		density = getDensity(activity);
	}

	public static void init(DisplayMetrics display) {
		if (null == displayMetrics) {
			displayMetrics = display;
		}
		if (displayMetrics != null) {
			density = displayMetrics.density;
		}
	}

	public static void fullScreen(Activity activity) {
		hideTitle(activity);
		Window window = activity.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	public static DisplayMetrics getDisplayMetrics(Activity activity) {
		if (null == displayMetrics) {
			displayMetrics = new DisplayMetrics();
			activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		}
		return displayMetrics;
	}

	public static int getWidthPixels(Activity activity) {
		return getDisplayMetrics(activity).widthPixels;
	}

	public static int getHeightPixels(Activity activity) {
		return getDisplayMetrics(activity).heightPixels;
	}

	public static int getWidthDips(Activity activity) {
		return px2dip(activity, getWidthPixels(activity));
	}

	public static int getHeightDips(Activity activity) {
		return px2dip(activity, getHeightPixels(activity));
	}

	public static float getDensity(Activity activity) {
		return getDisplayMetrics(activity).density;
	}

	public static float getScaleDensity(Activity activity) {
		return getDisplayMetrics(activity).scaledDensity;
	}

	public static int getDensityDpi(Activity activity) {
		return getDisplayMetrics(activity).densityDpi;
	}

	public static int px2dip(float pxValue, float density) {
		return (int) (pxValue / density + 0.5f);
	}

	public static int dip2px(float dipValue, float density) {
		return (int) (dipValue * density + 0.5f);
	}

	public static int dip2pxIfInit(float dipValue) {
		return (int) (dipValue * density + 0.5f);
	}

	public static int px2sp(float pxValue, float scaleDensity) {
		return (int) (pxValue / scaleDensity + 0.5f);
	}

	public static int sp2px(float spValue, float scaleDensity) {
		return (int) (spValue * scaleDensity + 0.5f);
	}

	public static int px2dip(Activity activity, float pxValue) {
		return px2dip(pxValue, getDensity(activity));
	}

	public static int dip2px(Activity activity, float dipValue) {
		return (int) dip2px(dipValue, getDensity(activity));
	}

	public static int px2sp(Activity activity, float pxValue) {
		return px2sp(pxValue, getScaleDensity(activity));
	}

	public static int sp2px(Activity activity, float spValue) {
		return sp2px(spValue, getScaleDensity(activity));
	}

	public static float getScale(Activity activity) {
		return getDensity(activity) / 160;
	}

}