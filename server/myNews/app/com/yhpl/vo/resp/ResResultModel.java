package com.yhpl.vo.resp;

import play.db.ebean.Model;

public class ResResultModel extends BaseResult {

	public Model data;

	@Override
	public String toString() {
		return "ResSingleResult [data=" + data + "]";
	}

}
