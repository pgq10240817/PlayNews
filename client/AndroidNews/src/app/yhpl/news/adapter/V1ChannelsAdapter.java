package app.yhpl.news.adapter;

import app.yhpl.news.R;
import app.yhpl.news.adapter.presenter.AdapterPresenter;
import app.yhpl.news.adapter.presenter.bean.ResGroupChannels;
import app.yhpl.news.adapter.presenter.bean.WidgetHolder;

public class V1ChannelsAdapter extends V1BaseAdapter<ResGroupChannels> {

	@Override
	public WidgetHolder getWidgetHolderForViewType(int viewType) {
		WidgetHolder holder = new WidgetHolder();
		holder.setWidgetId(R.layout.v1_widget_channels_line);
		// if (viewType == UITypeTag.TYPE_EXT_1) {
		// holder.setWidgetId(R.layout.v1_widget_banner);
		// holder.putView(R.id.banner, null);
		//
		// } else {
		// holder.setWidgetId(R.layout.v1_widget_game_app);
		// holder.putView(R.id.app, null);
		// }
		return holder;
	}

	public void initWidgets(ResGroupChannels bean, WidgetHolder widgetHolder) {
		AdapterPresenter presenter = (AdapterPresenter) widgetHolder.mItemView;
		presenter.bindData(getFragment(), bean);
	}

}
