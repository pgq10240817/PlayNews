package android.yhpl.core.http.req;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import android.yhpl.core.http.HttpRequestBase;
import android.yhpl.core.http.parser.BaseDecorator;
import android.yhpl.core.http.parser.ParserFacotory;
import android.yhpl.core.http.res.BaseGson;
import android.yhpl.core.http.res.ResChecker;
import android.yhpl.core.http.res.ResHttpResult;
import android.yhpl.core.http.res.ResPageBean;
import android.yhpl.core.http.res.ResState;
import android.yhpl.core.http.res.ResTag;

import com.android.volley.NetworkResponse;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;

public class ReqSingle extends HttpRequestBase {

	public ReqSingle(int method, String url, ErrorListener listener, Object userTag) {
		super(method, url, listener, userTag);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseGson getParsedBean(NetworkResponse response) {
		ResHttpResult result = new ResHttpResult();

		try {
			String rawData = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			JSONObject root = new JSONObject(rawData);
			ResState state = getResState(root.getJSONObject("state"));
			String service = root.getString("service");
			result.service = service;
			result.state = state;
			BaseDecorator decorator = ParserFacotory.getFacotory().getBeanDecortor(mDecoratorType);
			if (ResChecker.isResStateValide(result.state)) {

				if (decorator != null) {
					result.data = decorator.onDecoratorAll(root.getJSONObject("data").toString());
					if (result.data instanceof ResPageBean) {
						if (!ResChecker.isResPageAppNotEmpty((ResPageBean) result.data)) {
							result.state.setCode(ResTag.STATE_CODE_EMPTY);
						}
					}
				}
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;

	}

	private ResState getResState(JSONObject obj) {
		ResState state = new ResState();
		try {
			state.setCode(obj.getInt("code"));
			state.setMsg(obj.getString("msg"));
			state.setTips(obj.getString("tips"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
}
