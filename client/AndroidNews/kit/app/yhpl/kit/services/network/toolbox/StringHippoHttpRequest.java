package app.yhpl.kit.services.network.toolbox;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import org.apache.http.NameValuePair;

import app.yhpl.kit.services.CancelableTask;
import app.yhpl.kit.services.network.HippoHttpRequest;
import app.yhpl.kit.services.network.HippoResponse;
import app.yhpl.kit.services.network.NetworkResponse;
import app.yhpl.kit.services.network.RequestListener;
import app.yhpl.kit.services.network.exception.HippoException;

/**
 * @author Hubert He
 * @version V1.0
 * @Description
 * @Createdate 14-9-24 17:09
 */
public class StringHippoHttpRequest extends HippoHttpRequest<String> {

	private final String encoding;

	private Collection<CancelableTask> cancelableTaskCollection;

	/**
	 * 完全构造函数
	 *
	 * @param method
	 * @param url
	 * @param headers
	 * @param body
	 * @param listener
	 * @param errorListener
	 */
	public StringHippoHttpRequest(int method, String encoding, String url, NameValuePair[] headers, byte[] body,
			RequestListener<String> listener, HippoResponse.ErrorListener errorListener) {
		super(method, url, headers, body, listener, errorListener);
		this.encoding = encoding;
	}

	/**
	 * 构造函数
	 *
	 * @param url
	 * @param headers
	 * @param body
	 * @param listener
	 * @param errorListener
	 */
	public StringHippoHttpRequest(String url, NameValuePair[] headers, byte[] body, RequestListener<String> listener,
			HippoResponse.ErrorListener errorListener) {
		this(Method.GET, DEFAULT_RESPONSE_ENCODING, url, headers, body, listener, errorListener);
	}

	/**
	 * 构造函数
	 *
	 * @param url
	 * @param headers
	 * @param body
	 * @param listener
	 * @param errorListener
	 */
	public StringHippoHttpRequest(int method, String url, NameValuePair[] headers, byte[] body,
			RequestListener<String> listener, HippoResponse.ErrorListener errorListener) {
		this(method, DEFAULT_RESPONSE_ENCODING, url, headers, body, listener, errorListener);
	}

	@Override
	public HippoResponse<String> parseResponse(NetworkResponse response) {
		HippoResponse<String> hippoResponse;
		if (response.isSuccess()) {
			try {
				hippoResponse = new HippoResponse<>(new String(response.getData(), encoding));
			} catch (UnsupportedEncodingException e) {
				hippoResponse = new HippoResponse<>(new HippoException(
						"Oops! Unsupported encoding, you must kidding me!", e));
			}
		} else {
			hippoResponse = new HippoResponse<>(new HippoException("Cannot parse response due to this error happen.",
					response.getError()));
		}
		return hippoResponse;
	}

	@Override
	public void addListener(Collection<CancelableTask> cancelableTaskCollection) {
		this.cancelableTaskCollection = cancelableTaskCollection;
	}

	@Override
	public void remove() {
		if (cancelableTaskCollection != null) {
			cancelableTaskCollection.remove(this);
		}
	}
}
