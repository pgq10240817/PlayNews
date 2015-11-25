package android.yhpl.core.http.js;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JsObject {
	@JavascriptInterface
	public void callAndroidMethod(String msg) {
		Log.e("myTag", " callAndroidMethod -->" + msg);
	}

}
