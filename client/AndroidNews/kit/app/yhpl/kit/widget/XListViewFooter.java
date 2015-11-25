package app.yhpl.kit.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.yhpl.news.R;

public class XListViewFooter implements XIFooter {

	private Context mContext;

	private View mRoot;
	private View mContentView;
	private View mProgressBar;
	private TextView mHintView;
	private XFooterState mState;

	/**
	 * normal status
	 */
	public void normal() {
		mHintView.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.GONE);
	}

	/**
	 * loading status
	 */
	public void loading() {
		mHintView.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void initView(Context mContext) {
		this.mContext = mContext;
		mRoot = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.xlistview_footer, null);

		mContentView = mRoot.findViewById(R.id.xlistview_footer_content);
		mProgressBar = mRoot.findViewById(R.id.xlistview_footer_progressbar);
		mHintView = (TextView) mRoot.findViewById(R.id.xlistview_footer_hint_textview);
	}

	@Override
	public View getViewContent() {
		return mRoot;
	}

	@Override
	public void chageState(XFooterState state) {
		mState = state;
		mHintView.setVisibility(View.INVISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
		mHintView.setVisibility(View.INVISIBLE);
		switch (mState) {
		case RESET:
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_normal);
			break;
		case LOADING_PREVIEW:
		case LOADING:
			mProgressBar.setVisibility(View.VISIBLE);
			break;
		case LOADING_FAILED:
			break;
		case LOADING_END:
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_loading_end);
			break;
		default:
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_ready);
			break;
		}
	}

	@Override
	public XFooterState getState() {
		return mState;
	}

}