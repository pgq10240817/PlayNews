package app.yhpl.news.adapter.presenter.bean;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WidgetHolder {

	public int mViewType;
	public int mPostion;
	public View mItemView;
	private int mWidgetId;
	private SparseArray<View> mViewHolder;

	public int getViewType() {
		return mViewType;
	}

	public void setViewType(int viewType) {
		mViewType = viewType;
	}

	public void setPostion(int pos) {
		this.mPostion = pos;
	}

	public int getPostion() {
		return this.mPostion;
	}

	public int getWidgetId() {
		return mWidgetId;
	}

	public void setWidgetId(int widgetId) {
		mWidgetId = widgetId;
	}

	public SparseArray<View> getViewHolder() {
		return mViewHolder;
	}

	public void setViewHolder(SparseArray<View> viewHolder) {
		mViewHolder = viewHolder;
	}

	public void putView(int id, View view) {
		if (mViewHolder == null) {
			mViewHolder = new SparseArray<View>();
		}
		mViewHolder.put(id, view);

	}

	public TextView getTv(int id) {
		return (TextView) mViewHolder.get(id);
	}

	public ImageView getIv(int id) {
		return (ImageView) mViewHolder.get(id);
	}

	public View getView(int id) {
		return mViewHolder.get(id);
	}
}
