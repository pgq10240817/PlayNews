package com.yhpl.utils;

import play.Configuration;

public class BaseConfig {

	protected final static String getStringtWithKey(Configuration cfg, String key) {
		return cfg.getString(key);
	}

	protected final static int getIntWithKey(Configuration cfg, String key) {
		return cfg.getInt(key);
	}

	protected final static Object getObjectWithKey(Configuration cfg, String key) {
		return cfg.getObject(key);
	}

}
