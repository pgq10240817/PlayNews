package android.yhpl.core.http;

import android.yhpl.core.http.res.BaseGson;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;

public interface HttpResultlListener extends ErrorListener {

	void onHttpSuccess(Object userTag, BaseGson baseGson);

	void onErrorResponse(Object userTag, VolleyError error);

}
