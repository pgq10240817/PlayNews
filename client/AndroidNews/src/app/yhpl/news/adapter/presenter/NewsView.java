package app.yhpl.news.adapter.presenter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.yhpl.core.http.res.ResNews;
import app.yhpl.news.App;
import app.yhpl.news.R;
import app.yhpl.news.adapter.presenter.bean.BaseBean;
import app.yhpl.news.fragment.IFragment;

public class NewsView extends RelativeLayout implements AdapterPresenter {
	private boolean isNeedUpdateUi = false;
	private ResNews mData;
	private IFragment mFragment;
	private TextView mTvTitle, mTvDes;
	private ImageView mIcon;

	public NewsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public NewsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public NewsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NewsView(Context context) {
		super(context);
	}

	@Override
	public void bindData(IFragment fragment, BaseBean bean) {
		this.mData = (ResNews) bean;
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
		if (mData == null) {
			return;
		}
		if (mTvTitle == null) {
			mTvTitle = (TextView) findViewById(R.id.ext_lable);
		}
		if (mTvDes == null) {
			mTvDes = (TextView) findViewById(R.id.ext_txt);
		}
		if (mIcon == null) {
			mIcon = (ImageView) findViewById(R.id.ext_icon);
		}
		if (mTvTitle != null) {
			mTvTitle.setText(mData.title);
		}
		if (mTvDes != null) {
			mTvDes.setText(mData.snapDetail);
		}
		if (mIcon != null) {
			App.getInstance().loadImage(mIcon, mData.icon);
		}
		if (mFragment != null) {
			this.setTag(R.id.ext_news, mData);
			this.setId(R.id.ext_news);
			this.setOnClickListener(mFragment.getFragmentOnClickListener());
		}
	}

}
