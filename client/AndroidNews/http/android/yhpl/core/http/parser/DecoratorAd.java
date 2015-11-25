package android.yhpl.core.http.parser;

import java.lang.reflect.Type;

import android.yhpl.core.http.res.BaseGson;
import android.yhpl.core.http.res.ResBanner;
import android.yhpl.core.http.res.ResPageBean;

import com.google.gson.reflect.TypeToken;

public class DecoratorAd extends BaseDecorator implements ParserTag {

	@Override
	public BaseGson onDecoratorAll(String jsonStr) {

		Type type = new TypeToken<ResPageBean<ResBanner>>() {
		}.getType();
		ResPageBean<ResBanner> mPage = GsonUtil.getObjectFromJson(type, jsonStr);
		return mPage;
	}
}
