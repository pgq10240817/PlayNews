package com.yhpl.utils;

import java.util.List;

import com.yhpl.utils.work.TrimFilter;

public class CollectionUtil {

	public static boolean isEmpty(List list) {
		if (list == null || list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param list
	 * @return true : trim some null obj
	 */
	public static boolean trimList(List list) {
		if (isEmpty(list)) {
			return false;
		} else {
			int length = list.size();
			for (int i = length - 1; i >= 0; i--) {
				Object obj = list.get(i);
				if (obj == null) {
					list.remove(obj);
				}
			}
			return length != list.size();
		}
	}

	public static boolean trimListWithFilter(List list, TrimFilter filter) {
		boolean fiterResult = false;
		if (!isEmpty(list)) {

			int length = list.size();
			for (int i = length - 1; i >= 0; i--) {
				Object obj = list.get(i);
				if (filter.isFilter(obj)) {
					list.remove(obj);
				}
			}
			fiterResult = length != list.size();
		}
		return fiterResult;
	}

}
