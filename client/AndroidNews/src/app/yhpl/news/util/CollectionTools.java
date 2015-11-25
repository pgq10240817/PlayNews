package app.yhpl.news.util;

import java.util.List;

public class CollectionTools {

	public static boolean isListEmpty(List<?> list) {
		if (list == null || list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isListNotEmpty(List<?> list) {
		return !isListEmpty(list);
	}

	public static boolean isContains(List<?> list, Object obj) {
		return isListNotEmpty(list) && list.contains(obj);
	}

	public static boolean isContainsAndRemove(List<?> list, Object obj) {
		boolean result = isContains(list, obj);
		if (result) {
			result = list.remove(obj);
		}
		return result;
	}

	public static boolean releaseList(List<?> list) {
		boolean result = isListNotEmpty(list);
		if (result) {
			list.clear();
			list = null;
		}
		return result;
	}
}
