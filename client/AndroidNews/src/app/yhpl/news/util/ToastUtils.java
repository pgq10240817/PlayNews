package app.yhpl.news.util;

import android.widget.Toast;
import app.yhpl.news.App;

public class ToastUtils {

	public static void showToast(int strResId) {
		Toast.makeText(App.getInstance().getApplicationContext(), strResId, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(String str) {
		Toast.makeText(App.getInstance().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
	}

}
