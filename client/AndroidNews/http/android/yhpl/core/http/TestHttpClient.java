package android.yhpl.core.http;

import android.yhpl.core.http.parser.ParserTag;
import android.yhpl.core.http.req.ReqSingle;
import android.yhpl.core.http.res.send.PostServiceBean;
import android.yhpl.core.http.res.send.ReqPostPage;
import android.yhpl.core.http.res.send.ReqTags;
import app.yhpl.news.App;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class TestHttpClient {
	private static RequestQueue mRequestQueue;

	static {
		mRequestQueue = Volley.newRequestQueue(App.getInstance());
	}

	public static void cancelAll(Object tag) {
		mRequestQueue.cancelAll(tag);

	}

	public static void reqAd(Object tag, HttpResultlListener listener) {
		ReqSingle requestBase = new ReqSingle(Request.Method.POST, HttpURLTag.AD, listener, tag);
		ReqPostPage data = new ReqPostPage();
		data.offset = 0 * ReqTags.PAGE_COUNT + 1;
		PostServiceBean serviceBean = new PostServiceBean();
		serviceBean.data = data;
		requestBase.setPostBean(serviceBean);
		requestBase.setDecoratorType(ParserTag.HTTP_PARSER_AD);
		mRequestQueue.add(requestBase);
		mRequestQueue.start();
	}

	public static void reqChannelList(Object tag, int page, HttpResultlListener listener) {
		ReqSingle requestBase = new ReqSingle(Request.Method.POST, HttpURLTag.CHANNELS_LIST, listener, tag);

		ReqPostPage data = new ReqPostPage();
		data.count = ReqTags.PAGE_COUNT_CHANNELS;
		data.offset = (page - 1) * ReqTags.PAGE_COUNT_CHANNELS + 1;
		PostServiceBean serviceBean = new PostServiceBean();
		serviceBean.data = data;
		requestBase.setPostBean(serviceBean);
		requestBase.setDecoratorType(ParserTag.HTTP_PARSER_CHANNEL);
		mRequestQueue.add(requestBase);
		mRequestQueue.start();
	}

	public static void reqNewsList(Object tag, String type, int page, HttpResultlListener listener) {
		ReqSingle requestBase = new ReqSingle(Request.Method.POST, HttpURLTag.NEWS_LIST, listener, tag);

		ReqPostPage data = new ReqPostPage();
		data.offset = (page - 1) * ReqTags.PAGE_COUNT + 1;
		data.type = type;
		PostServiceBean serviceBean = new PostServiceBean();
		serviceBean.data = data;
		requestBase.setPostBean(serviceBean);
		requestBase.setDecoratorType(ParserTag.HTTP_PARSER_NEWS);
		mRequestQueue.add(requestBase);
		mRequestQueue.start();
	}

}
