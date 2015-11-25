//package android.yhpl.test;
//
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//
//import com.android.volley.VolleyError;
//
//import android.test.AndroidTestCase;
//import android.util.Log;
//import android.yhpl.core.http.HttpResultlListener;
//import android.yhpl.core.http.TestHttpClient;
//import android.yhpl.core.http.res.BaseGson;
//import android.yhpl.core.http.res.ResHttpResult;
//import android.yhpl.core.http.res.send.ReqReg;
//import app.yhpl.news.res.adapter.BaseBean;
//
//public class HttpTest extends AndroidTestCase implements HttpResultlListener {
//	CountDownLatch mCountDownLatch = new CountDownLatch(1);
//
//	// private final static int TAG_HTTP_SINGLE = 1001;
//	// private final static int TAG_HTTP_COMBINE = 1002;
//	private final static int TAG_REQ_AD = 1001;
//	private final static int TAG_REQ_IN = 1002;
//	private final static int TAG_REQ_PURSE_QUERY = 1003;
//
//	public void testHttp() {
//
//		// TestHttpClient.reqAd(TAG_REQ_AD, this);
//		// TestHttpClient.reqInWithType(TAG_REQ_IN, 1, 5, this);
//		// TestHttpClient.reqPurseWithDrawQuery(TAG_REQ_PURSE_QUERY, 1, this);
//
//		reg();
//		try {
//			mCountDownLatch.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	private void reg() {
//		ReqReg reg = new ReqReg();
//		reg.name = "pgq102408171";
//		reg.password = "123456";
//		TestHttpClient.reqReg(1000, reg, this);
//	}
//
//	@Override
//	public void onErrorResponse(VolleyError error) {
//		Log.e("myTag", "  onErrorResponse 111 " + error);
//
//	}
//
//	@Override
//	public void onHttpSuccess(Object userTag, BaseGson baseGson) {
//		Log.e("myTag", userTag + "  result " + baseGson);
//		ResHttpResult httpResult = (ResHttpResult) baseGson;
//		List<? extends BaseBean> content = httpResult.getContent();
//
//		Log.e("myTag", "content:" + content);
//
//		// switch ((Integer) userTag) {
//		// case TAG_HTTP_SINGLE:
//		// List<? extends BaseBean> mResult = null;
//		// if (baseGson instanceof ResHttpResult) {
//		// ResHttpResult result = (ResHttpResult) baseGson;
//		// mResult = result.data.getContent();
//		// }
//		// assertNotNull(mResult);
//		// Log.e("myTag", " result is " + mResult.size());
//		// for (BaseBean iterOne : mResult) {
//		// Log.e("myTag", " result --> " + iterOne);
//		// }
//		// break;
//		// case TAG_HTTP_COMBINE:
//		// ResHttpCombineResult mData = null;
//		// if (baseGson instanceof ResHttpCombineResult) {
//		// mData = ((ResHttpCombineResult) baseGson);
//		// }
//		// assertNotNull(mData);
//		// Log.e("myTag", " result is " + mData.state.getCode());
//		// break;
//		// default:
//		// break;
//		// }
//
//		release();
//	}
//
//	private void release() {
//		mCountDownLatch.countDown();
//	}
//
//	@Override
//	public void onErrorResponse(Object userTag, VolleyError error) {
//		Log.e("myTag", error.networkResponse.statusCode + "  onErrorResponse  " + userTag + " ," + error);
//		assertTrue(false);
//		release();
//
//	}
//
//	// HttpContentController contentController;
//	//
//	// private boolean isResultReturned = false;
//	// private boolean isHttpFailed = false;
//	//
//
//	// private void release() {
//	// if (contentController != null) {
//	// contentController.cancelAllHttp();
//	// contentController = null;
//	// }
//	//
//	// }
//	//
//	// @Override
//	// public void onHttpFinish(int type, Object object) {
//	// Log.e("myTag", "--->onHttpFinish " + object.toString());
//	// isResultReturned = true;
//	// mCountDownLatch.countDown();
//	// release();
//	// }
//	//
//	// @Override
//	// public void onHttpFail(int type, Object object, Throwable e) {
//	// Log.e("myTag", "--->onHttpFail---" + e.toString());
//	// isHttpFailed = true;
//	// isResultReturned = true;
//	// mCountDownLatch.countDown();
//	// release();
//	//
//	// }
//}
