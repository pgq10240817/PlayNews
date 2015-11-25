package android.yhpl.core.http.res;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年9月25日
 */
import app.yhpl.news.adapter.presenter.bean.BaseBean;

public class ResNews extends BaseBean {

	public long id;

	public String title;

	public String source;

	public String cp;

	public String time;

	public String cid;

	public String snapDetail;
	public String icon;
	public String url;
	public String extra;

	@Override
	public String toString() {
		return "ResNews [id=" + id + ", title=" + title + ", source=" + source + ", cp=" + cp + ", time=" + time
				+ ", cid=" + cid + ", snapDetail=" + snapDetail + ", icon=" + icon + ", url=" + url + ", extra="
				+ extra + "]";
	}

}
