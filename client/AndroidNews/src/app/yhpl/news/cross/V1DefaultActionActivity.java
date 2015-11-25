package app.yhpl.news.cross;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.yhpl.core.http.parser.ParserTag;
import android.yhpl.core.http.res.ResChannels;
import android.yhpl.core.http.res.ResNews;
import app.yhpl.news.R;
import app.yhpl.news.adapter.AdapterEnum;
import app.yhpl.news.fragment.V1NewsFragment;
import app.yhpl.news.fragment.V1WebFragment;
import app.yhpl.news.res.BaseConfig;
import app.yhpl.news.res.ListViewConfig;
import app.yhpl.news.tag.BundleKeys;

public class V1DefaultActionActivity extends LogActivity {

	public static final int ACTION_WEB = 0X2001;
	public static final int ACTION_NEWS = 0X2002;
	private int mUiAction = 0;

	@Override
	protected void initArguments(Bundle bundles) {
		super.initArguments(bundles);
		mUiAction = bundles.getInt(BundleKeys.KEY_I_UI_ACTION, 0);
	}

	public static void handleResNews(Context context, ResNews banner) {
		if (banner != null) {
			String link = banner.url;
			if (TextUtils.isEmpty(link)) {
				return;
			}
			if (!link.startsWith("http")) {
				link = "http://" + link;
			}
			if (link.startsWith("http")) {

				Intent intent = new Intent(context, V1DefaultActionActivity.class);
				Bundle extras = new Bundle();
				extras.putInt(BundleKeys.KEY_I_UI_ACTION, ACTION_WEB);
				extras.putString(BundleKeys.KEY_S_TITLE, banner.title);
				extras.putString(BundleKeys.KEY_S_URL, link);
				intent.putExtras(extras);
				context.startActivity(intent);
			}
		}
	}

	public static void handleActionWithBundle(Context context, int actionId, Bundle extrasBundle) {
		Intent intent = new Intent(context, V1DefaultActionActivity.class);
		Bundle extras = new Bundle();
		extras.putInt(BundleKeys.KEY_I_UI_ACTION, actionId);
		intent.putExtras(extras);
		context.startActivity(intent);
	}

	public static void handleWithResChannel(Context context, ResChannels channel) {
		Intent intent = new Intent(context, V1DefaultActionActivity.class);
		Bundle extras = new Bundle();
		extras.putString(BundleKeys.KEY_S_ID, channel.cid);
		extras.putInt(BundleKeys.KEY_I_UI_ACTION, ACTION_NEWS);
		intent.putExtras(extras);
		context.startActivity(intent);
	}

	@Override
	public Fragment getDefaultFragment() {
		Fragment child = null;
		BaseConfig mCfg = null;
		switch (mUiAction) {
		case ACTION_WEB:
			child = Fragment.instantiate(this, V1WebFragment.class.getName());
			mCfg = new ListViewConfig();
			// mCfg.putInt(BundleKeys.KEY_I_TITLE, R.string.title_find_pwd);
			mCfg.putInt(BundleKeys.KEY_I_TITLE_LEFT, R.drawable.v1_icon_back);
			mCfg.putInt(BundleKeys.KEY_I_LAYOUT, R.layout.v1_fragment_web_titlebar);
			Bundle activityBundle = getIntent().getExtras();
			if (activityBundle != null) {
				mCfg.putString(BundleKeys.KEY_S_URL, activityBundle.getString(BundleKeys.KEY_S_URL, ""));
				mCfg.putString(BundleKeys.KEY_S_TITLE, activityBundle.getString(BundleKeys.KEY_S_TITLE, ""));
			}
			break;
		case ACTION_NEWS:
			child = Fragment.instantiate(this, V1NewsFragment.class.getName());
			ListViewConfig config = new ListViewConfig();
			config.putInt(BundleKeys.KEY_I_TITLE, R.string.title_news);
			config.putInt(BundleKeys.KEY_I_TITLE_LEFT, R.drawable.v1_icon_titlebar_back);
			config.putInt(BundleKeys.KEY_I_LAYOUT, R.layout.v1_fragment_listview_titlebar);
			config.putInt(BundleKeys.KEY_I_HTTP_PARSER_TYPE, ParserTag.HTTP_PARSER_NEWS);
			config.putInt(BundleKeys.KEY_I_ADAPTER, AdapterEnum.NEWS.getFlag());
			activityBundle = getIntent().getExtras();
			if (activityBundle != null) {
				config.putString(BundleKeys.KEY_S_ID, activityBundle.getString(BundleKeys.KEY_S_ID, ""));
			}
			child.setArguments(config.getBundle());
			break;

		default:
			break;
		}

		if (mCfg != null && child != null) {
			child.setArguments(mCfg.getBundle());
		}
		return child;

	}

}
