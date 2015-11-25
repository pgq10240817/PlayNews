package android.yhpl.core.http.res;

import app.yhpl.news.util.CollectionTools;

public class ResChecker implements ResTag {
	public static boolean isResStateValide(ResState state) {
		boolean result = false;
		if (state != null && state.getCode() == STATE_CODE_SUCCESS) {
			result = true;
		}
		return result;

	}

	public static boolean isResPageAppNotEmpty(ResPageBean page) {
		boolean result = false;
		if (page != null && CollectionTools.isListNotEmpty(page.getContent())) {
			result = true;
		}
		return result;

	}
}
