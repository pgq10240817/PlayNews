package android.yhpl.core.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.yhpl.core.http.res.send.PostBean;
import android.yhpl.core.http.res.send.ReqTags;

import com.google.gson.Gson;

public class HttpRequestBuilder implements HttpRequestBuildInterface {
	private Map<String, String> mUrlMap = null;
	private Map<String, String> mParamsMap = null;
	private Map<String, String> mHeaderMap = null;
	private PostBean mPostBean;

	@Override
	public HttpRequestBuildInterface appendUrlParams(String key, String value) {
		if (mUrlMap == null) {
			mUrlMap = new HashMap<String, String>();
		}
		mUrlMap.put(key, value);
		return this;
	}

	@Override
	public HttpRequestBuildInterface appendBodyParams(String key, String value) {
		if (mParamsMap == null) {
			mParamsMap = new HashMap<String, String>();
		}
		mParamsMap.put(key, value);
		return this;
	}

	@Override
	public HttpRequestBuildInterface appendHeaderParams(String key, String value) {
		if (mHeaderMap == null) {
			mHeaderMap = new HashMap<String, String>();
		}
		mHeaderMap.put(key, value);
		return this;
	}

	public Map<String, String> getBodyParams() {
		return mParamsMap;
	}

	public Map<String, String> getHeaderParams() {
		return mHeaderMap;
	}

	public String getUrlParams() {
		StringBuilder builder = new StringBuilder("");
		if (mParamsMap != null && mParamsMap.size() > 0) {
			for (Entry<String, String> entry : mParamsMap.entrySet()) {
				builder.append(entry.getKey()).append("&").append(entry.getValue());
			}

		}

		return builder.toString();

	}

	@Override
	public void setPostBean(PostBean bean) {
		this.mPostBean = bean;

	}

	public int getPostBeanType() {
		if (mPostBean != null) {
			return mPostBean.getSeriableType();
		}
		return ReqTags.POST_TYPE_DEFAULT;
	}

	public byte[] getPostBeanDate() {
		byte[] result = null;
		if (mPostBean != null) {
			if (mPostBean.getSeriableType() == ReqTags.POST_TYPE_GSON) {
				Gson gson = new Gson();
				result = gson.toJson(mPostBean).getBytes();
			} else {
				result = mPostBean.toString().getBytes();
			}
		}
		return result;

	}
}
