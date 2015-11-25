package android.yhpl.core.http;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

import android.util.Log;
import android.yhpl.core.http.res.BaseGson;
import android.yhpl.core.http.res.send.PostBean;
import android.yhpl.core.http.res.send.ReqTags;
import app.yhpl.news.MyBuildConfig;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

public class HttpRequestBase extends Request implements HttpRequestBuildInterface {

	private HttpRequestBuilder mHttpRequestBuilder;
	private Object mUserTag;
	protected int mDecoratorType;

	public HttpRequestBase(int method, String url, ErrorListener listener, Object userTag) {
		super(method, url, listener);
		this.mUserTag = userTag;
		init();
		// Log.e("myTag", Thread.currentThread().getName() + " BaseRequest " +
		// url);
		if (MyBuildConfig.DEBUG) {
			Log.e(HttpURLTag.TAG, Thread.currentThread().getName() + " BaseRequest  " + url);
		}
	}

	public final void setDecoratorType(int type) {
		this.mDecoratorType = type;
	}

	@Override
	public String getUrl() {
		return super.getUrl() + mHttpRequestBuilder.getUrlParams();
	}

	@Override
	protected Response parseNetworkResponse(NetworkResponse response) {
		Log.e("myTag", " parseNetworkResponse  " + Thread.currentThread().getName());
		BaseGson result = getParsedBean(response);
		Response resultBean = null;
		if (result != null) {
			resultBean = Response.success(result, HttpHeaderParser.parseCacheHeaders(response, false));
		} else {
			resultBean = Response.error(new VolleyError("解析失败"));
		}
		return resultBean;
	}

	protected BaseGson getParsedBean(NetworkResponse response) {
		return null;
	}

	@Override
	protected void deliverResponse(Object response) {
		Log.e("myTag", mUserTag + " deliverResponse  " + Thread.currentThread().getName());
		if (getErrorListener() instanceof HttpResultlListener) {
			((HttpResultlListener) getErrorListener()).onHttpSuccess(mUserTag, (BaseGson) response);
		}
	}

	@Override
	public void deliverError(VolleyError error) {
		super.deliverError(error);
		if (getErrorListener() instanceof HttpResultlListener) {
			((HttpResultlListener) getErrorListener()).onErrorResponse(mUserTag, error);
		}
	}

	private void init() {
		if (mHttpRequestBuilder == null) {
			mHttpRequestBuilder = new HttpRequestBuilder();
		}
	}

	public HttpRequestBuildInterface appendUrlParams(String key, String value) {
		mHttpRequestBuilder.appendUrlParams(key, value);
		return this;
	}

	public HttpRequestBuildInterface appendBodyParams(String key, String value) {
		mHttpRequestBuilder.appendBodyParams(key, value);
		return this;
	}

	@Override
	public HttpRequestBuildInterface appendHeaderParams(String key, String value) {
		mHttpRequestBuilder.appendHeaderParams(key, value);
		return this;

	}

	@Override
	public void setPostBean(PostBean bean) {
		mHttpRequestBuilder.setPostBean(bean);
	}

	@Override
	public Map getHeaders() throws AuthFailureError {
		Map result = mHttpRequestBuilder.getHeaderParams();
		return result == null ? Collections.EMPTY_MAP : result;
	}

	@Override
	protected Map getParams() throws AuthFailureError {
		Map result = mHttpRequestBuilder.getBodyParams();
		return result == null ? Collections.EMPTY_MAP : result;
	}

	@Override
	public String getBodyContentType() {
		if (mHttpRequestBuilder.getPostBeanType() == ReqTags.POST_TYPE_GSON) {
			return "application/json; charset=" + getParamsEncoding();
		} else {
			return super.getBodyContentType();
		}
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		byte[] result = mHttpRequestBuilder.getPostBeanDate();
		try {
			Log.e("myTag", "req:" + new String(result, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (result != null && result.length > 0) {
			return result;
		} else {
			return super.getBody();
		}
	}

}
