package com.yhpl.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewChannalVo {
	private String template;

	private String topicid;

	private String hasCover;

	private String alias;

	private String subnum;

	private String tag;

	private String recommendOrder;

	private String isNew;

	private String img;

	private String isHot;

	private String hasIcon;

	private String recommend;

	private String cid;

	private String headLine;

	private String tagDate;

	private String color;

	private String bannerOrder;

	private String ename;

	private String tname;

	private String showType;

	private String special;

	private String tid;

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getHasCover() {
		return hasCover;
	}

	public void setHasCover(String hasCover) {
		this.hasCover = hasCover;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSubnum() {
		return subnum;
	}

	public void setSubnum(String subnum) {
		this.subnum = subnum;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRecommendOrder() {
		return recommendOrder;
	}

	public void setRecommendOrder(String recommendOrder) {
		this.recommendOrder = recommendOrder;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public String getHasIcon() {
		return hasIcon;
	}

	public void setHasIcon(String hasIcon) {
		this.hasIcon = hasIcon;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getHeadLine() {
		return headLine;
	}

	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}

	public String getTagDate() {
		return tagDate;
	}

	public void setTagDate(String tagDate) {
		this.tagDate = tagDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBannerOrder() {
		return bannerOrder;
	}

	public void setBannerOrder(String bannerOrder) {
		this.bannerOrder = bannerOrder;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	@Override
	public String toString() {
		return "ClassPojo [template = " + template + ", topicid = " + topicid + ", hasCover = " + hasCover
				+ ", alias = " + alias + ", subnum = " + subnum + ", tag = " + tag + ", recommendOrder = "
				+ recommendOrder + ", isNew = " + isNew + ", img = " + img + ", isHot = " + isHot + ", hasIcon = "
				+ hasIcon + ", recommend = " + recommend + ", cid = " + cid + ", headLine = " + headLine
				+ ", tagDate = " + tagDate + ", color = " + color + ", bannerOrder = " + bannerOrder + ", ename = "
				+ ename + ", tname = " + tname + ", showType = " + showType + ", special = " + special + ", tid = "
				+ tid + "]";
	}

}
