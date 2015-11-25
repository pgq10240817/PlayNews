package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.yhpl.utils.JsonUtils;
import com.yhpl.vo.resp.BaseResult;
import com.yhpl.vo.resp.RespTags;
import com.yhpl.vo.resp.query.ResQueryManager;
import com.yhpl.vo.resp.query.RespQueryBase;

import play.mvc.Controller;
import play.mvc.Result;

public class ChannelsController extends Controller {
	public static String sService = RespTags.SERVICE_CHANNELS;
	protected static RespQueryBase sRespController = ResQueryManager.getInstance().getDispatcher(sService);

	private static JsonNode getPostJson() {
		JsonNode json = request().body().asJson();
		return json;
	}

	public static String get404Result() {
		return ResQueryManager.getInstance().get404Result(sService);
	}

	public static String getEmptyResult() {
		return ResQueryManager.getInstance().getEmptyResult(sService);
	}

	public static Result channelList() {

		JsonNode json = getPostJson();
		if (json == null) {
			return ok(get404Result());
		}
		BaseResult resultBean = sRespController.getResultWithJsonNode(json);
		String result = null;
		result = JsonUtils.toJson(resultBean);
		if (result == null) {
			result = getEmptyResult();
		} else {
			return ok(result);
		}
		return ok(result);
	}

}
