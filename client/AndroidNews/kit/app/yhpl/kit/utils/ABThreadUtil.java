package app.yhpl.kit.utils;

import app.yhpl.kit.log.Logger;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 7/21/14.
 */
public class ABThreadUtil {
	public static final String TAG = ABThreadUtil.class.getSimpleName();

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Logger.e(TAG, e);
		}
	}

}