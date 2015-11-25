package com.yhpl.vo.resp.query;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.yhpl.vo.req.ReqArgsPrivate;
import com.yhpl.vo.req.ReqArgsPrivateNormal;
import com.yhpl.vo.req.ReqArgsPrivatePage;

import models.News;
import play.db.ebean.Model;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年11月24日
 */
public class RespQueryNews extends RespQueryBasePage {

	public RespQueryNews(String service) {
		super(service);
	}

	@Override
	protected Page<? extends Model> getPage(int page, int count, ReqArgsPrivatePage pageArgs) {
		return News.page(page, count, pageArgs.type);
	}

	@Override
	protected Class<? extends ReqArgsPrivate> getArgsClass(JsonNode parentNode) {
		return ReqArgsPrivateNormal.class;
	}

	@Override
	protected Model getModel(ReqArgsPrivate args) {
		if (args instanceof ReqArgsPrivateNormal) {
			ReqArgsPrivateNormal reqArgs = (ReqArgsPrivateNormal) args;
			return News.getNewsWithId(reqArgs.id);
		}
		return null;
	}

}
