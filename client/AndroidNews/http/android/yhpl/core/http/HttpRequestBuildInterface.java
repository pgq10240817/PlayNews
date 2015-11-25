package android.yhpl.core.http;

import android.yhpl.core.http.res.send.PostBean;

public interface HttpRequestBuildInterface {

	HttpRequestBuildInterface appendUrlParams(String key, String value);

	HttpRequestBuildInterface appendBodyParams(String key, String value);

	HttpRequestBuildInterface appendHeaderParams(String key, String value);

	void setPostBean(PostBean bean);

}
