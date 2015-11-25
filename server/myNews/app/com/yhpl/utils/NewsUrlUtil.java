package com.yhpl.utils;

import com.fasterxml.jackson.databind.JsonNode;

import play.Configuration;
import play.Play;
import play.libs.Json;

public class NewsUrlUtil extends BaseConfig {

	private static String KEY_CONFIG_URL = "httpconfig";

	private static NewsBean mBean;

	static class NewsBean {
		public String channel;
		public String home;
		public String channelNewsFmt;

		public String toString() {
			return "NewsBean [channel=" + channel + ", home=" + home + "]";
		}

	}

	private static void init() {
		if (mBean == null) {
			mBean = new NewsBean();
			Configuration cfg = Play.application().configuration();
			Object map = cfg.getObject(KEY_CONFIG_URL);
			JsonNode n1 = Json.toJson(map);
			JsonNode node = Json.parse(n1.toString());
			mBean = Json.fromJson(node, NewsBean.class);
			System.out.println(mBean);
		}
	}

	public static String getChannelUrl() {
		if (mBean == null) {
			init();
		}
		return mBean.channel;
	}

	public static String getHomeUrl() {
		if (mBean == null) {
			init();
		}
		return mBean.home;
	}

	public static String getChannelNewsUrlWithCidPageCount(String cid) {
		return getChannelNewsUrlWithCidPageCount(cid, 0, 200);

	}

	public static String getChannelNewsUrlWithCidPageCount(String cid, int page, int count) {
		if (mBean == null) {
			init();
		}
		return String.format(mBean.channelNewsFmt, cid, 0, 200);

	}

}
