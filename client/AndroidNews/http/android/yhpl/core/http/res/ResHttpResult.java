package android.yhpl.core.http.res;

import java.util.List;

import app.yhpl.news.adapter.presenter.bean.BaseBean;

public class ResHttpResult extends BaseGson {

	public ResState state;

	public String service;

	public BaseGson data;

	public boolean isMore() {
		if (data instanceof ResPageBean) {
			ResPageBean page = (ResPageBean) data;
			return page.getNextOffset() > 0;

		}
		return false;
	}

	@Override
	public List<? extends BaseBean> getContent() {
		if (data != null) {
			return data.getContent();
		}
		return null;
	}

	@Override
	public String toString() {
		return "ResHttpResult [state=" + state + ", service=" + service + ", data=" + data + "]";
	}

}
