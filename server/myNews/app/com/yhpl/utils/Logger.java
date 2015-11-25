package com.yhpl.utils;

public class Logger {

	public static int logLevel = 0;

	public static int LOG_LEVEL_EVERY = 0;

	public static int LOG_LEVEL_DEBUG = 10;

	public static int LOG_LEVEL_ERROR = 20;

	// * ---------------- debug ------------
	public static void debug(String msg) {
		debug(null, msg);

	}

	public static void debug(String method, String msg) {
		if (logLevel <= LOG_LEVEL_DEBUG) {
			log(method, msg);
		}

	}

	public static void debugWithFmt(String fmt, Object... args) {
		debugWithFmt(null, fmt, args);

	}

	public static void debugWithFmt(String method, String fmt, Object... args) {
		if (logLevel <= LOG_LEVEL_DEBUG) {
			logWithFmt(method, fmt, args);
		}
	}

	// * ---------------- end debug ------------

	// * ---------------- error ------------
	public static void error(String msg) {
		error(null, msg);

	}

	public static void error(String method, String msg) {
		if (logLevel <= LOG_LEVEL_ERROR) {
			log(method, msg);
		}

	}

	public static void errorWithFmt(String fmt, Object... args) {
		errorWithFmt(null, fmt, args);

	}

	public static void errorWithFmt(String method, String fmt, Object... args) {
		if (logLevel <= LOG_LEVEL_ERROR) {
			logWithFmt(method, fmt, args);
		}
	}
	// * ---------------- end error ------------

	public static void log(String method, String msg) {
		System.out.println("log:" + msg);
		System.out.println("【" + method + "】:" + msg);
	}

	public static void logWithFmt(String method, String fmt, Object... args) {
		if (TextUtil.isEmpty(method)) {
			play.Logger.debug(String.format(fmt, args));
		} else {
			play.Logger.debug("【" + method + "】:" + String.format(fmt, args));
		}
	}

}
