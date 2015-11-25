package app.yhpl.kit.support.recyclerview.adapter;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import app.yhpl.kit.adapter.typeadapter.ABAdapterTypeRender;
import app.yhpl.news.R;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 1/19/15.
 */
public abstract class ABRecyclerViewTypeAdapter extends RecyclerView.Adapter<ABRecyclerViewHolder> {
	@TargetApi(Build.VERSION_CODES.DONUT)
	@Override
	public ABRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		ABAdapterTypeRender<ABRecyclerViewHolder> render = getAdapterTypeRender(viewGroup, viewType);
		ABRecyclerViewHolder holder = render.getReusableComponent(viewGroup);
		holder.itemView.setTag(R.id.ab__id_adapter_item_type_render, render);
		render.fitEvents();
		return holder;
	}

	@TargetApi(Build.VERSION_CODES.DONUT)
	@Override
	public void onBindViewHolder(ABRecyclerViewHolder holder, int position) {
		ABAdapterTypeRender<ABRecyclerViewHolder> render = (ABAdapterTypeRender<ABRecyclerViewHolder>) holder.itemView
				.getTag(R.id.ab__id_adapter_item_type_render);
		render.fitDatas(position);
	}

	/**
	 * 根据指定position的item获取对应的type，然后通过type实例化一个AdapterTypeRender的实现
	 *
	 * @return
	 */
	public abstract ABAdapterTypeRender<ABRecyclerViewHolder> getAdapterTypeRender(ViewGroup viewGroup, int type);
}
