package android.yhpl.util.log;

import android.util.Log;

public class MyLog {
	public static void logD(String tag, String method, String msg) {
		Log.d(tag, "[" + method + "] -->" + msg);
	}

	public static void logD(String msg) {
		Log.d("myTag", msg);
	}

	public static void logD(String tag, String msg) {
		Log.d(tag, msg);
	}

	public static void log(String tag, String msg) {
		Log.d(tag, msg);

	}

}
