package android.yhpl.core.http.res;

import java.util.List;

import app.yhpl.news.adapter.presenter.bean.BaseBean;
import app.yhpl.news.adapter.presenter.bean.ResGroupChannels;

public class ResChannelPageBean extends ResPageBean<ResChannels> {
	public List<ResGroupChannels> mGroups;

	@Override
	public List<? extends BaseBean> getContent() {
		return mGroups;
	}

}
