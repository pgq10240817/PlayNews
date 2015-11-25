package android.yhpl.core.http.res;

import app.yhpl.news.adapter.presenter.bean.BaseBean;

public class ResBanner extends BaseBean {
	public String ad_link;

	public String ad_name;

	public String ad_id;

	public String pic_url;

	@Override
	public String toString() {
		return "ResBanner [ad_link=" + ad_link + ", ad_name=" + ad_name + ", ad_id=" + ad_id + ", pic_url=" + pic_url
				+ "]";
	}

}
