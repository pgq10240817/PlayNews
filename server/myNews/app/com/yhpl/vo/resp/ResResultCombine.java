package com.yhpl.vo.resp;

import com.fasterxml.jackson.databind.node.ArrayNode;

public class ResResultCombine extends BaseResult {

	// public List<ResPageBean> data;
	public ArrayNode data;

	@Override
	public String toString() {
		return "ResCombineResult [data=" + data + "]";
	}

}
