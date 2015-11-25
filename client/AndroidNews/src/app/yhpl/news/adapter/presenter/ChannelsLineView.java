package app.yhpl.news.adapter.presenter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.yhpl.core.http.res.ResChannels;
import app.yhpl.news.R;
import app.yhpl.news.adapter.presenter.bean.BaseBean;
import app.yhpl.news.adapter.presenter.bean.ResGroupChannels;
import app.yhpl.news.fragment.IFragment;

public class ChannelsLineView extends LinearLayout implements AdapterPresenter {
	private boolean isNeedUpdateUi = false;
	private ResGroupChannels mData;
	private IFragment mFragment;

	public ChannelsLineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public ChannelsLineView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ChannelsLineView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ChannelsLineView(Context context) {
		super(context);
	}

	@Override
	public void bindData(IFragment fragment, BaseBean bean) {
		this.mData = (ResGroupChannels) bean;
		this.mFragment = fragment;
		isNeedUpdateUi = true;
		postUpdate();

	}

	private void postUpdate() {
		if (isNeedUpdateUi && getHandler() != null) {
			isNeedUpdateUi = false;
			this.post(new Runnable() {

				@Override
				public void run() {
					updateUi();

				}
			});
		}

	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		postUpdate();
	}

	protected void updateUi() {
		int viewCount = getChildCount();
		for (int i = 0; i < viewCount; i++) {
			ResChannels channel = ResGroupChannels.getChannel(mData, i);
			if (channel != null) {
				TextView mTv = (TextView) getChildAt(i);
				mTv.setText(channel.cname);
				mTv.setId(R.id.ext_channel);
				if (mFragment != null) {
					mTv.setTag(channel);
					mTv.setOnClickListener(mFragment.getFragmentOnClickListener());
				}
				getChildAt(i).setVisibility(View.VISIBLE);
			} else {
				getChildAt(i).setVisibility(View.INVISIBLE);
			}

		}
	}

}
