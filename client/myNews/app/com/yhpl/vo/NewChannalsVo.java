package com.yhpl.vo;

public class NewChannalsVo {
	private NewChannalVo[] tList;

	public NewChannalVo[] gettList() {
		return tList;
	}

	public void settList(NewChannalVo[] tList) {
		this.tList = tList;
	}

	@Override
	public String toString() {
		return "ClassPojo [tList = " + tList + "]";
	}
}
