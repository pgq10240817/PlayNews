package app.yhpl.news.adapter;

import java.util.List;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.yhpl.kit.log.Logger;
import app.yhpl.news.adapter.presenter.bean.BaseBean;
import app.yhpl.news.adapter.presenter.bean.WidgetHolder;
import app.yhpl.news.fragment.IFragment;
import app.yhpl.news.tag.LogTags;

public abstract class V1BaseAdapter<T extends BaseBean> extends android.widget.BaseAdapter {

	private List<T> mData;
	protected LayoutInflater mLayoutInflater;
	protected IFragment mFragment;

	private String getClassName() {
		return this.getClass().getSimpleName();
	}

	public IFragment getFragment() {
		return mFragment;
	}

	public void setFragment(IFragment fragment) {
		mFragment = fragment;
	}

	protected void log(String method) {
		Logger.d(LogTags.FRAGMENT, String.format("[%s]%s-->", getClassName(), method));
	}

	public V1BaseAdapter() {
		super();
	}

	public V1BaseAdapter(List<T> mData) {
		super();
		this.mData = mData;
	}

	public void addData(List<T> addData, boolean isAppend) {
		if (addData == null) {
			return;
		}
		log("addData size:" + addData.size() + ",isAppend" + isAppend);
		if (mData == null) {
			mData = addData;
		} else {
			if (isAppend) {
				mData.addAll(addData);
			} else {
				mData = addData;
			}
		}
		notifyDataSetChanged();
	}

	public void addData(List<T> addData) {
		addData(addData, false);
	}

	@Override
	public int getCount() {
		return mData == null ? 0 : mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData == null ? 0 : mData.get(position);
	}

	public T getRealItem(int position) {
		if (mData == null) {
			return null;
		} else {
			return mData.get(position);
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	public abstract WidgetHolder getWidgetHolderForViewType(int viewType);

	public abstract void initWidgets(T bean, WidgetHolder widgetHolder);

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		T baseBean = getRealItem(position);
		WidgetHolder widgetHolder = null;

		if (convertView != null) {
			widgetHolder = (WidgetHolder) convertView.getTag();
			if (widgetHolder.getViewType() != baseBean.getViewType()) {
				convertView = null;
			}
		}
		if (convertView == null) {
			if (mLayoutInflater == null) {
				mLayoutInflater = LayoutInflater.from(parent.getContext());
			}
			widgetHolder = getWidgetHolderForViewType(baseBean.getViewType());
			widgetHolder.mViewType = baseBean.getViewType();
			convertView = mLayoutInflater.inflate(widgetHolder.getWidgetId(), parent, false);
			widgetHolder.mItemView = convertView;
			convertView.setTag(widgetHolder);
			registerViewListener(widgetHolder.getViewHolder(), convertView);
		}
		widgetHolder.setPostion(position);
		initWidgets(baseBean, widgetHolder);
		return convertView;
	}

	private void registerViewListener(SparseArray<View> mViewHolder, View parent) {
		if (mViewHolder != null) {
			int size = mViewHolder.size();
			for (int i = 0; i < size; i++) {
				int key = mViewHolder.keyAt(i);
				mViewHolder.setValueAt(i, parent.findViewById(key));
			}

		}
	}

}
