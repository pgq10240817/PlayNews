package com.yhpl.vo.resp.query;

import java.util.HashMap;
import java.util.Map;

import com.yhpl.utils.JsonUtils;
import com.yhpl.vo.resp.ResResultPage;
import com.yhpl.vo.resp.ResState;
import com.yhpl.vo.resp.RespTags;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年11月6日
 */
public class ResQueryManager {

	private static ResQueryManager sInstance;
	private Map<String, RespQueryBase> mDisPatchers;

	private ResQueryManager() {
		mDisPatchers = new HashMap<String, RespQueryBase>();
	}

	// public String getErrorResp(String service) {
	// ResSingleResult resResult = new ResSingleResult();
	// String result = "";
	// resResult.service = service;
	// resResult.state = new ResState();
	// resResult.state.code = 404;
	// resResult.state.msg = RespTags.MSG_ERROR;
	// result = JsonUtils.toJson(resResult);
	//
	// return result;
	// }

	public String getErrorResp(String service) {
		ResResultPage resResult = new ResResultPage();
		String result = "";
		resResult.service = service;
		resResult.state = new ResState();
		resResult.state.code = 404;
		resResult.state.msg = RespTags.MSG_ERROR;
		result = JsonUtils.toJson(resResult);

		return result;
	}

	public static ResQueryManager getInstance() {
		if (sInstance == null) {
			synchronized (ResQueryManager.class) {
				if (sInstance == null) {
					sInstance = new ResQueryManager();
				}
			}
		}
		return sInstance;

	}

	public String get404Result(String service) {
		String result = "";
		ResResultPage resResult = new ResResultPage();
		resResult.service = service;
		resResult.state = new ResState();
		resResult.state.code = 404;
		resResult.state.msg = RespTags.MSG_ERROR;
		result = JsonUtils.toJson(resResult);

		return result;
	}

	public String getResultWithCodeAndMsg(String service, int code, String msg) {
		String result = "";
		ResResultPage resResult = new ResResultPage();
		resResult.service = service;
		resResult.state = new ResState();
		resResult.state.code = code;
		resResult.state.msg = msg;
		result = JsonUtils.toJson(resResult);

		return result;
	}

	public String getEmptyResult(String service) {
		String result = "";
		ResResultPage resResult = new ResResultPage();
		resResult.service = service;
		resResult.state = new ResState();
		resResult.state.code = 100;
		resResult.state.msg = RespTags.MSG_EMPTY;
		result = JsonUtils.toJson(resResult);

		return result;
	}

	public String getServerError(String service) {
		String result = "";
		ResResultPage resResult = new ResResultPage();
		resResult.service = service;
		resResult.state = new ResState();
		resResult.state.code = 500;
		resResult.state.msg = RespTags.MSG_ERROR;
		result = JsonUtils.toJson(resResult);
		return result;
	}

	public RespQueryBase getDispatcher(String service) {
		if (mDisPatchers.containsKey(service)) {
			return mDisPatchers.get(service);
		} else {
			return initDispatcher(service);
		}
	}

	private RespQueryBase initDispatcher(String service) {
		if (RespTags.SERVICE_CHANNELS.equals(service)) {
			return new RespQueryChannels(service);
		} else if (RespTags.SERVICE_NEWS.equals(service)) {
			return new RespQueryNews(service);
		}
		return null;
	}

}
