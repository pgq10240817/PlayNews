package app.yhpl.news.adapter;

import android.yhpl.core.http.res.ResNews;
import app.yhpl.news.R;
import app.yhpl.news.adapter.presenter.AdapterPresenter;
import app.yhpl.news.adapter.presenter.bean.WidgetHolder;

public class V1NewsAdapter extends V1BaseAdapter<ResNews> {

	@Override
	public WidgetHolder getWidgetHolderForViewType(int viewType) {
		WidgetHolder holder = new WidgetHolder();
		holder.setWidgetId(R.layout.v1_widget_news);
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

	public void initWidgets(ResNews bean, WidgetHolder widgetHolder) {
		AdapterPresenter presenter = (AdapterPresenter) widgetHolder.mItemView;
		presenter.bindData(getFragment(), bean);
	}

}
