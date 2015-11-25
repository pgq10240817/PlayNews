package app.yhpl.news;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import app.yhpl.kit.application.ABApplication;
import app.yhpl.kit.utils.DisplayUtil;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.Volley;

public class App extends ABApplication {
	public final static int DEVICE_TYPE = 2;
	public final static String VERSION = "1.0.0";

	@Override
	public void onCreate() {
		super.onCreate();
		DisplayUtil.init(getResources().getDisplayMetrics());
		// UserManager.getInstance().updateUserPurse();
	}

	@Override
	protected void initLogger() {
		super.initLogger();
	}

	@Override
	protected ImageLoader initImageLoader() {
		RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
		ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
		return imageLoader;

		// 指定图片允许的最大宽度和高度
	}

	static class BitmapCache implements ImageCache {

		private LruCache<String, Bitmap> cache;

		public BitmapCache() {
			cache = new LruCache<String, Bitmap>(8 * 1024 * 1024) {
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					return bitmap.getRowBytes() * bitmap.getHeight();
				}
			};
		}

		@Override
		public Bitmap getBitmap(String url) {
			return cache.get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			cache.put(url, bitmap);
		}
	}

}
