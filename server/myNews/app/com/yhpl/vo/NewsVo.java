package com.yhpl.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsVo {
	private String url_3w;

	private String votecount;

	private String replyCount;

	private String digest;

	private String url;

	private String docid;

	private String title;

	private String source;

	private String priority;

	private String lmodify;

	private String boardid;

	private String imgsrc;

	private String subtitle;

	private String ptime;

	public String getUrl_3w() {
		return url_3w;
	}

	public void setUrl_3w(String url_3w) {
		this.url_3w = url_3w;
	}

	public String getVotecount() {
		return votecount;
	}

	public void setVotecount(String votecount) {
		this.votecount = votecount;
	}

	public String getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(String replyCount) {
		this.replyCount = replyCount;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getLmodify() {
		return lmodify;
	}

	public void setLmodify(String lmodify) {
		this.lmodify = lmodify;
	}

	public String getBoardid() {
		return boardid;
	}

	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}

	public String getImgsrc() {
		return imgsrc;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	@Override
	public String toString() {
		return "ClassPojo [url_3w = " + url_3w + ", votecount = " + votecount + ", replyCount = " + replyCount
				+ ", digest = " + digest + ", url = " + url + ", docid = " + docid + ", title = " + title
				+ ", source = " + source + ", priority = " + priority + ", lmodify = " + lmodify + ", boardid = "
				+ boardid + ", imgsrc = " + imgsrc + ", subtitle = " + subtitle + ", ptime = " + ptime + "]";
	}
}
