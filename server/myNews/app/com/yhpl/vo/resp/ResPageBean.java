package com.yhpl.vo.resp;

import java.util.List;

import play.db.ebean.Model;

public class ResPageBean {
	public List<? extends Model> content;

	public int nextOffset;

	public int type;

	@Override
	public String toString() {
		return "ResPageBean [content = " + content + ", nextOffset = " + nextOffset + "]";
	}
}
