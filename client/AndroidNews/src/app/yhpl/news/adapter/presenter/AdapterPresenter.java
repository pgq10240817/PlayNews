package app.yhpl.news.adapter.presenter;

import app.yhpl.news.adapter.presenter.bean.BaseBean;
import app.yhpl.news.fragment.IFragment;

public interface AdapterPresenter {

	void bindData(IFragment fragment, BaseBean bean);

}
