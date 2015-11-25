package app.yhpl.kit.widget;

import android.content.Context;
import android.view.View;

public interface XIHeader {

	public void initView(Context mContext);

	public void moveHeader(int scrollY);

	public View getListHeaderContent();

	public void chageState(XState state);

	public int getViewLoadingHeight();

	public void showLastRefreshTime(long time);

}
