package com.yhpl.vo.req;

import java.util.List;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年11月6日
 */
public class ReqPostCombine {
	public ReqArgsPublic client;
	public List<ReqPostCombineArgs> data;

	@Override
	public String toString() {
		return "ReqPostCombine [client=" + client + ", data=" + data + "]";
	}

	public static class ReqPostCombineArgs {
		public String service;
		public ReqArgsPrivate data;

		@Override
		public String toString() {
			return "ReqPostCombineArgs [service=" + service + ", data=" + data + "]";
		}

	}

}
