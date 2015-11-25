package com.yhpl.vo.resp.query;

import com.fasterxml.jackson.databind.JsonNode;
import com.yhpl.vo.req.ReqArgsPrivate;

import play.db.ebean.Model;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年11月24日
 */
public abstract class RespQueryBasePage extends RespQueryBase {

	public RespQueryBasePage(String service) {
		super(service);
	}

	@Override
	protected Model getModel(ReqArgsPrivate args) {
		return null;
	}

	@Override
	protected Class<? extends ReqArgsPrivate> getArgsClass(JsonNode parentNode) {
		return null;
	}

}
