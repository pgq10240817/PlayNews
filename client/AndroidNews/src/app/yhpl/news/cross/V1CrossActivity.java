package app.yhpl.news.cross;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.yhpl.core.http.parser.ParserTag;
import app.yhpl.news.R;
import app.yhpl.news.adapter.AdapterEnum;
import app.yhpl.news.fragment.V1ListViewFragment;
import app.yhpl.news.res.ActivityConfig;
import app.yhpl.news.res.ListViewConfig;
import app.yhpl.news.tag.BundleKeys;

public class V1CrossActivity extends LogActivity {

	@Override
	public ActivityConfig getConfig() {
		ActivityConfig activityConfig = new ActivityConfig();
		activityConfig.putInt(BundleKeys.KEY_I_LAYOUT, R.layout.v1_activity_default);
		return activityConfig;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// menu.add("分享");
		return super.onCreateOptionsMenu(menu);
	}

	public void popFragmentWithTag(String tag) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment target = fragmentManager.findFragmentByTag(tag);
		if (target != null) {
			fragmentManager.popBackStack(tag, 0);
		}
	}

	@Override
	public Fragment getDefaultFragment() {
		Fragment child = Fragment.instantiate(this, V1ListViewFragment.class.getName());

		ListViewConfig config = new ListViewConfig();
		config.putInt(BundleKeys.KEY_I_TITLE, R.string.title_channel);
		config.putInt(BundleKeys.KEY_I_TITLE_LEFT, R.drawable.v1_icon_titlebar_back);
		config.putInt(BundleKeys.KEY_I_LAYOUT, R.layout.v1_fragment_listview_titlebar);
		config.putInt(BundleKeys.KEY_I_HTTP_PARSER_TYPE, ParserTag.HTTP_PARSER_CHANNEL);
		config.putInt(BundleKeys.KEY_I_ADAPTER, AdapterEnum.CHANNESL.getFlag());
		child.setArguments(config.getBundle());
		return child;
	}
	// @Override
	// public void processOnClick(View v) {
	// super.processOnClick(v);
	// if (v.getId() == R.id.bt_titlebar_right) {
	// FragmentManager fragmentManager = getSupportFragmentManager();
	// if
	// (fragmentManager.findFragmentByTag(BundleValues.VAL_S_FRAGMENT_TAG_SHARE)
	// == null) {
	// FragmentTransaction ft = fragmentManager.beginTransaction();
	// ft.add(R.id.drawer_layout, new V1ShareFragment(),
	// BundleValues.VAL_S_FRAGMENT_TAG_SHARE);
	// ft.addToBackStack(null);
	// ft.commit();
	// }
	// }
	// }

}
