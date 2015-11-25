package android.yhpl.core.http.res.ui;

import java.util.ArrayList;
import java.util.List;

import android.yhpl.core.http.res.BaseGson;
import app.yhpl.news.adapter.presenter.bean.BaseBean;

public class UIPage extends BaseGson {

	private List<BaseBean> beans;

	public void addUIBeanWithType(BaseBean bean, int uitype) {
		if (beans == null) {
			beans = new ArrayList<BaseBean>();
		}
		bean.setViewType(uitype);
		beans.add(bean);
	}

	public void addUIBeanFirstWithType(BaseBean bean, int uitype) {
		if (beans == null) {
			beans = new ArrayList<BaseBean>();
		}
		bean.setViewType(uitype);
		beans.add(0, bean);
	}

	@Override
	public List<? extends BaseBean> getContent() {
		return beans;
	}

}
