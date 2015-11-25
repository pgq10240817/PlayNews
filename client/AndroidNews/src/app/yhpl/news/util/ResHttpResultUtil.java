package app.yhpl.news.util;

import android.yhpl.core.http.res.BaseGson;
import android.yhpl.core.http.res.ResHttpResult;
import android.yhpl.core.http.res.ResState;

public class ResHttpResultUtil {

	public static int getStateCode(BaseGson gson) {
		if (gson instanceof ResHttpResult) {
			ResHttpResult httpresult = (ResHttpResult) gson;
			ResState state = httpresult.state;
			if (state != null) {
				return state.getCode();
			}

		}
		return -1;
	}

	public static <T> T getDataAs(BaseGson gson, Class<T> clazz) {
		T result = null;
		if (gson instanceof ResHttpResult) {
			ResHttpResult httpresult = (ResHttpResult) gson;
			BaseGson data = httpresult.data;
			try {
				result = (T) data;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getStateMsg(BaseGson gson) {
		if (gson instanceof ResHttpResult) {
			ResHttpResult httpresult = (ResHttpResult) gson;
			ResState state = httpresult.state;
			if (state != null) {
				return state.getMsg();
			}
		}
		return "网络异常";
	}

}
