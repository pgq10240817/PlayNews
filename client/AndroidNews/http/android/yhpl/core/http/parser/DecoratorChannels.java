package android.yhpl.core.http.parser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.yhpl.core.http.res.BaseGson;
import android.yhpl.core.http.res.ResChannelPageBean;
import android.yhpl.core.http.res.ResChannels;
import app.yhpl.news.adapter.presenter.bean.ResGroupChannels;
import app.yhpl.news.util.CollectionTools;

import com.google.gson.reflect.TypeToken;

public class DecoratorChannels extends BaseDecorator implements ParserTag {

	@Override
	public BaseGson onDecoratorAll(String jsonStr) {

		Type type = new TypeToken<ResChannelPageBean>() {
		}.getType();
		ResChannelPageBean mPage = GsonUtil.getObjectFromJson(type, jsonStr);
		if (mPage != null && CollectionTools.isListNotEmpty(mPage.content)) {
			List<ResChannels> mContent = (List<ResChannels>) mPage.content;

			int size = mContent.size();
			int step = (size - 1) / 4 + 1;
			List<ResGroupChannels> mContentGroup = new ArrayList<>(step);
			for (int i = 0; i < step; i++) {
				ResGroupChannels group = ResGroupChannels.getGroupFromListWithOffsetLength(mContent, i * 4, 4);
				mContentGroup.add(group);
			}
			mPage.mGroups = mContentGroup;

		}

		return mPage;
	}
}
