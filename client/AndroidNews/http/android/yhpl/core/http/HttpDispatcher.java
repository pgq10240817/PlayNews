package android.yhpl.core.http;

import android.yhpl.core.http.parser.ParserTag;

public class HttpDispatcher {

	public static boolean dispatchHttpWithTypePage(Object tag, HttpResultlListener l, int type, int page) {
		boolean result = false;

		switch (type) {
		case ParserTag.HTTP_PARSER_AD:
			result = true;
			TestHttpClient.reqAd(tag, l);
			break;
		case ParserTag.HTTP_PARSER_CHANNEL:
			result = true;
			TestHttpClient.reqChannelList(tag, page, l);
			break;
		default:
			break;
		}

		return result;

	}
}
