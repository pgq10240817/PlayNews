package app.yhpl.kit.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;
import app.yhpl.kit.log.Logger;
import app.yhpl.kit.services.network.http.ABHttpUtil;
import app.yhpl.kit.thread.ThreadPool;
import app.yhpl.kit.utils.ABPrefsUtil;
import app.yhpl.news.R;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

public class ABApplication extends Application {

	private static ABApplication instance;
	protected static ImageLoader sImageLoader;
	protected static Handler sHandler = new Handler();

	public static ABApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		initLogger(); // 初始化日志工具
		initThreadPool(); // 初始化线程池
		initNetWorkSSLScheme(); // 初始化AINetWork中SSL Scheme
		sImageLoader = initImageLoader(); // 初始化图片加载器
		initCrashHandler(); // 初始化程序崩溃捕捉处理
		initPrefs(); // 初始化SharedPreference
		initABActionbar(); // 初始化Actionbar配置
		// 初始化Http连接
		initHttpConfig();
	}

	public static void runDelay(Runnable run) {
		sHandler.postDelayed(run, 0);
	}

	public static void runDelay(Runnable run, long delay) {
		sHandler.postDelayed(run, delay);
	}

	/**
	 * 初始化线程池
	 */
	protected void initThreadPool() {
		ThreadPool.initThreadPool(-1);
	}

	/**
	 * 初始化AINetWork中SSL Scheme
	 */
	protected void initNetWorkSSLScheme() {
	}

	/**
	 * 初始化日志
	 */
	protected void initLogger() {
		Logger.initLogger(null);
	}

	/**
	 * 初始化图片加载器（子类需要重写）
	 */
	protected ImageLoader initImageLoader() {
		return null;

	}

	public ImageLoader getImageLoader() {
		return sImageLoader;
	}

	public void loadImage(ImageView mIcon, String url) {
		ImageLoader il = getImageLoader();
		if (il != null) {
			ImageListener listener = ImageLoader.getImageListener(mIcon, R.drawable.v1_icon_default,
					R.drawable.v1_icon_default);
			il.get(url, listener);
		}
	}

	/**
	 * 初始化程序崩溃捕捉处理
	 */
	protected void initCrashHandler() {
		// ABCrashHandler.init(getApplicationContext());
	}

	/**
	 * 初始化SharedPreference
	 */
	protected void initPrefs() {
		ABPrefsUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
	}

	/**
	 * 初始化Actionbar配置（如果要使用Actionbar，则需要子类重写）
	 */
	protected void initABActionbar() {

	}

	protected void initHttpConfig() {
		ABHttpUtil.initHttpConfig(null, null);
	}

}
