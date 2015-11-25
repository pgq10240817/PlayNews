package com.yhpl.vo.resp;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年11月6日
 */
public class RespTags {

	public static final String SERVICE_CHANNELS = "channels";
	public static final String SERVICE_NEWS = "news";

	public static String KEY_RESP_OFFSET = "nextOffset";
	public static String KEY_RESP_CONTENT = "content";
	public static String KEY_RESP_DATA = "data";
	public static String KEY_RESP_STATE = "state";
	public static int PAGE_END = -2;

	public static int CODE_TOKEN_INVALIDED = 100;
	public static int CODE_OK = 200;

	public static final String MSG_ERROR = "API调用错误";
	public static final String MSG_EMPTY = "资源为空";
	public static final String MSG_OK = "OK";
	public static final String MSG_OK_UPDATE = "更新成功";

	public static final String MSG_ERROR_TOKEN_INVALIDE = "用户登录过期，请重新登录";

}
