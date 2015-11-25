package app.yhpl.kit.widget;

import android.content.Context;
import android.view.View;

public interface XIFooter {

	public void initView(Context mContext);

	public View getViewContent();

	public void chageState(XFooterState state);

	public XFooterState getState();

}
