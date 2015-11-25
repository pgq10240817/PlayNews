package com.yhpl.vo.resp.query;

import com.avaje.ebean.Page;
import com.yhpl.vo.req.ReqArgsPrivatePage;

import models.Channels;
import play.db.ebean.Model;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年11月24日
 */
public class RespQueryChannels extends RespQueryBasePage {

	public RespQueryChannels(String service) {
		super(service);
	}

	@Override
	protected Page<? extends Model> getPage(int page, int count, ReqArgsPrivatePage pageArgs) {
		return Channels.page(page, count);
	}

}
