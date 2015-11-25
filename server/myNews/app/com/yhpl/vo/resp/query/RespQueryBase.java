package com.yhpl.vo.resp.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.h2.util.StringUtils;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yhpl.utils.JsonUtils;
import com.yhpl.vo.req.ReqArgsPrivate;
import com.yhpl.vo.req.ReqArgsPrivatePage;
import com.yhpl.vo.req.ReqTags;
import com.yhpl.vo.resp.BaseResult;
import com.yhpl.vo.resp.ResPageBean;
import com.yhpl.vo.resp.ResResultModel;
import com.yhpl.vo.resp.ResResultPage;
import com.yhpl.vo.resp.ResState;
import com.yhpl.vo.resp.RespTags;

import play.db.ebean.Model;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年11月6日
 */
public abstract class RespQueryBase {

	protected Map<String, BaseResult> mMapResult = new HashMap<String, BaseResult>();
	protected String mService;

	public RespQueryBase(String service) {
		this.mService = service;
	}

	public BaseResult getRespJsonNode(List<? extends Model> list, int nextOffset) {
		ResResultPage respJson = new ResResultPage();
		respJson.service = mService;
		respJson.state = new ResState();
		if (nextOffset <= 0 && (list == null || list.size() == 0)) {
			respJson.state.code = 100;
			respJson.state.msg = RespTags.MSG_EMPTY;
		} else {
			respJson.state.code = 200;
			respJson.state.msg = RespTags.MSG_OK;
			respJson.data = new ResPageBean();
			respJson.data.nextOffset = nextOffset;
			respJson.data.content = list;
		}

		return respJson;

	}

	public BaseResult getRespJsonNode(Model model) {
		ResResultModel respJson = new ResResultModel();
		respJson.service = mService;
		respJson.state = new ResState();
		if (model == null) {
			respJson.state.code = 100;
			respJson.state.msg = RespTags.MSG_EMPTY;
		} else {
			respJson.state.code = 200;
			respJson.state.msg = RespTags.MSG_OK;
			respJson.data = model;
		}

		return respJson;

	}

	protected abstract Page<? extends Model> getPage(int page, int count, ReqArgsPrivatePage pageArgs);

	protected abstract Model getModel(ReqArgsPrivate args);

	protected Class<? extends ReqArgsPrivate> getArgsPageClass() {
		return ReqArgsPrivatePage.class;
	}

	protected abstract Class<? extends ReqArgsPrivate> getArgsClass(JsonNode argsNode);

	protected String getCahcedKey(ReqArgsPrivate reqArgs) {
		if (reqArgs instanceof ReqArgsPrivatePage) {
			ReqArgsPrivatePage reqArgsPage = (ReqArgsPrivatePage) reqArgs;
			String mType = reqArgsPage.type;
			int page = getPage(reqArgsPage);
			if (page == 0) {
				return mType + "-" + page;
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	protected int getPage(ReqArgsPrivatePage reqArgsPage) {
		int reqOffset = reqArgsPage.offset;
		if (reqOffset <= 0) {
			reqOffset = 1;
		}
		int count = getCount(reqArgsPage);
		int page = (reqOffset - 1) / count;
		return page;
	}

	protected int getCount(ReqArgsPrivatePage reqArgsPage) {
		int count = reqArgsPage.count;
		if (count <= 0) {
			reqArgsPage.count = ReqTags.PAGE_COUNT;
		}
		return count;
	}

	// protected abstract Type

	private BaseResult getResultFromDb(ReqArgsPrivate args) {
		Model model = getModel(args);
		return getRespJsonNode(model);
	}

	private BaseResult getPageResultFromDb(ReqArgsPrivatePage pageArgs) {
		int page = getPage(pageArgs);
		int count = getCount(pageArgs);
		int nextOffset = (page + 1) * count + 1;
		System.out.println("  RespDispatcherBanner  next offset --- > " + nextOffset);

		Page<? extends Model> pageApps = getPage(page, count, pageArgs);
		List<? extends Model> respContent = pageApps.getList();
		int totalPage = pageApps.getTotalPageCount();
		if (page >= totalPage - 1) {
			nextOffset = ReqTags.PAGE_END;
			return getRespJsonNode(respContent, nextOffset);
		} else {
			return getRespJsonNode(respContent, nextOffset);
		}
	}

	public BaseResult getResultWithJsonNode(JsonNode node) {
		BaseResult result = null;
		if (node == null) {
			return result;
		}
		if (node instanceof ObjectNode) {
			ObjectNode parent = (ObjectNode) node;
			JsonNode argNode = parent.get(ReqTags.KEY_ARGS_PRIVATE);
			if (argNode != null) {
				ReqArgsPrivate reqArgs = null;
				if (argNode instanceof ObjectNode) {
					ObjectNode objectNode = (ObjectNode) argNode;
					JsonNode offsetNode = objectNode.get(ReqTags.KEY_ARGS_PAGE_OFFSET);
					if (offsetNode != null) {
						reqArgs = JsonUtils.getObjectMapper().convertValue(argNode, getArgsPageClass());
					} else {
						reqArgs = JsonUtils.getObjectMapper().convertValue(argNode, getArgsClass(argNode));
					}
				} else {
					reqArgs = JsonUtils.getObjectMapper().convertValue(argNode, getArgsClass(argNode));
				}
				if (reqArgs instanceof ReqArgsPrivatePage) {
					ReqArgsPrivatePage reqArgsPage = (ReqArgsPrivatePage) reqArgs;
					String chacheKey = getCahcedKey(reqArgsPage);
					if (StringUtils.isNullOrEmpty(chacheKey)) {
						result = getPageResultFromDb(reqArgsPage);
					} else {
						if (mMapResult.containsKey(chacheKey)) {
							System.out.println("cahche");
							return mMapResult.get(chacheKey);
						} else {
							result = getPageResultFromDb(reqArgsPage);
							mMapResult.put(chacheKey, result);
						}
					}
				} else {
					result = getResultFromDb(reqArgs);
				}
			}

		}
		return result;
	}
}