package android.yhpl.core.http.parser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class GsonUtil {

	public static <T> T getObjectFromJson(Class<T> clazz, String jsonStr) {
		if (TextUtils.isEmpty(jsonStr)) {
			return null;
		}
		Gson gson = new Gson();
		T result = null;
		try {
			result = gson.fromJson(jsonStr, clazz);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String getJsonStr(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	public static <T> T getObjectFromJson(Type type, String jsonStr) {
		if (TextUtils.isEmpty(jsonStr)) {
			return null;
		}
		Gson gson = new Gson();
		T result = null;
		try {
			result = gson.fromJson(jsonStr, type);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
		List<T> lst = new ArrayList<T>();
		try {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			Gson gson = new Gson();
			for (final JsonElement elem : array) {
				lst.add(gson.fromJson(elem, clazz));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}

}
