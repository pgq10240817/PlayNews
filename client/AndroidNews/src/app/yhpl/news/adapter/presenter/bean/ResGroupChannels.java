package app.yhpl.news.adapter.presenter.bean;

import java.util.List;

import android.yhpl.core.http.res.ResChannels;
import app.yhpl.news.util.CollectionTools;

public class ResGroupChannels extends BaseBean {
	private List<ResChannels> mGroup;

	public List<ResChannels> getGroup() {
		return mGroup;
	}

	public void setGroup(List<ResChannels> group) {
		mGroup = group;
	}

	public static ResChannels getChannel(ResGroupChannels group, int offset) {
		if (group == null) {
			return null;
		}
		List<ResChannels> list = group.mGroup;
		if (CollectionTools.isListEmpty(list)) {
			return null;
		}
		if (list.size() <= offset) {
			return null;
		}

		return list.get(offset);
	}

	public static ResGroupChannels getGroupFromListWithOffsetLength(List<ResChannels> list, int offset, int length) {
		ResGroupChannels result = null;
		if (CollectionTools.isListEmpty(list)) {
			return result;
		} else {
			int totalSize = list.size();
			if (offset >= totalSize) {
				return result;
			}
			List<ResChannels> subList = null;
			int nextOffset = offset + length;
			if (nextOffset > totalSize) {
				subList = list.subList(offset, totalSize);
			} else {
				subList = list.subList(offset, nextOffset);
			}

			result = new ResGroupChannels();
			result.mGroup = subList;
		}
		return result;
	}

}
