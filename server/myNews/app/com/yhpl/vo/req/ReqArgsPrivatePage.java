package com.yhpl.vo.req;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年11月6日
 */
public class ReqArgsPrivatePage extends ReqArgsPrivate {
	public int offset;
	public String type = "";
	public int count = ReqTags.PAGE_COUNT;
	public long versionId;

	@Override
	public String toString() {
		return "ReqPostPage [offset=" + offset + ", type=" + type + ", versionId=" + versionId + "]";
	}

}
