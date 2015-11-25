package android.yhpl.core.http.res;

import app.yhpl.news.adapter.presenter.bean.BaseBean;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年9月25日
 */

public class ResChannels extends BaseBean {

	public long id;
	public String cid;
	public String cname;
	public String subnum;
	public String extra;

	@Override
	public String toString() {
		return "ResChannels [id=" + id + ", cid=" + cid + ", cname=" + cname + ", subnum=" + subnum + ", extra="
				+ extra + "]";
	}

}
