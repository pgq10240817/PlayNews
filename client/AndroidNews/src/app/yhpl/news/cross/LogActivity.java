package app.yhpl.news.cross;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import app.yhpl.kit.log.Logger;
import app.yhpl.kit.present.ABSupportFragmentActivity;
import app.yhpl.news.R;
import app.yhpl.news.fragment.LogFragment;
import app.yhpl.news.res.ActivityConfig;
import app.yhpl.news.tag.LogTags;

public class LogActivity extends ABSupportFragmentActivity {

	protected Fragment mContent;
	protected ActivityConfig mConfig;

	private String getClassName() {
		return this.getClass().getSimpleName();
	}

	private void log(String method) {
		Logger.d(LogTags.ACTCITY, String.format("[%s]%s-->", getClassName(), method));
	}

	protected void initArguments(Bundle bundle) {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		log("onCreate");
		ActivityConfig childConfig = getConfig();
		if (childConfig != null) {
			mConfig = childConfig;
		} else {
			mConfig = new ActivityConfig();
		}
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null && bundle.size() > 0) {
				initArguments(bundle);
			}
		}

		setContentView(mConfig.getLayoutRes());
		mContent = getDefaultFragment();
		if (mContent != null) {
			String tag = mConfig.getFragmentTag();
			if (!TextUtils.isEmpty(tag)) {
				getSupportFragmentManager().beginTransaction()
						.add(R.id.div_fragment, mContent, mConfig.getFragmentTag()).commit();
			} else {
				getSupportFragmentManager().beginTransaction().add(R.id.div_fragment, mContent).commit();
			}
		}
	}

	public Fragment getDefaultFragment() {
		String name = mConfig.getFragmentName();
		if (TextUtils.isEmpty(name)) {
			return null;
		}
		return Fragment.instantiate(this, name);
	}

	public ActivityConfig getConfig() {
		return null;
	}

	@Override
	protected void onStart() {
		super.onStart();
		log("onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		log("onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		log("onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		log("onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		log("onDestroy");
	}

	public void processOnClick(View v) {

	}

	@Override
	public void onBackPressed() {
		if (mContent instanceof LogFragment) {
			LogFragment intan = (LogFragment) mContent;
			boolean isHandle = intan.onBackPressed();
			if (isHandle) {
				return;
			}
		}

		super.onBackPressed();
	}
}
