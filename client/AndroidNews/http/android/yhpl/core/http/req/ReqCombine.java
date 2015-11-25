//package android.yhpl.core.http.req;
//
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Type;
//import java.util.HashMap;
//import java.util.Iterator;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.yhpl.core.http.HttpRequestBase;
//import android.yhpl.core.http.parser.BaseDecorator;
//import android.yhpl.core.http.parser.ParserFacotory;
//import android.yhpl.core.http.res.BaseGson;
//import android.yhpl.core.http.res.ResChecker;
//import android.yhpl.core.http.res.ResHttpCombineResult;
//import android.yhpl.core.http.res.ResPageBean;
//import android.yhpl.core.http.res.ResState;
//import android.yhpl.core.http.res.ResTag;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.Response.ErrorListener;
//import com.android.volley.toolbox.HttpHeaderParser;
//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//
//public class ReqCombine extends HttpRequestBase {
//
//	public ReqCombine(int method, String url, ErrorListener listener, Object userTag) {
//		super(method, url, listener, userTag);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@Override
//	protected BaseGson getParsedBean(NetworkResponse response) {
//		ResHttpCombineResult result = new ResHttpCombineResult();
//
//		try {
//			String rawData = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//			JSONObject root = new JSONObject(rawData);
//			ResState state = getResState(root.getJSONObject("state"));
//			String service = root.getString("service");
//			result.service = service;
//			result.state = state;
//			if (ResChecker.isResStateValide(result.state)) {
//				result.combineData = new HashMap<String, BaseGson>();
//				BaseDecorator decorator = ParserFacotory.getFacotory().getBeanDecortor(mDecoratorType);
//				JSONArray mArray = root.getJSONArray("data");
//				if (mArray == null) {
//					JSONObject data = root.getJSONObject("data");
//					handleManyPropertiesAsService(data, result);
//				} else {
//					handleArrayAsService(mArray, result);
//				}
//
//				// JSONObject data = root.getJSONObject("data");
//				// Iterator<String> keys = data.keys();
//				// boolean isEmpty = true;
//
//				// while (keys.hasNext()) {
//				// String singleService = keys.next();
//				// Type type =
//				// ParserFacotory.getFacotory().getBeanType(singleService);
//				// if (type != null) {
//				// Gson gson = new Gson();
//				// BaseGson child =
//				// gson.fromJson(data.getJSONObject(singleService).toString(),
//				// type);
//				// if (ResChecker.isResPageAppNotEmpty((ResPageBean) child)) {
//				// isEmpty = false;
//				// result.combineData.put(singleService, child);
//				// }
//				// }
//				// }
//				//
//				// if (isEmpty) {
//				// result.state.setCode(ResTag.STATE_CODE_EMPTY);
//				// }
//				if (decorator != null) {
//					result = (ResHttpCombineResult) decorator.onDecoratorAll(result);
//				}
//			}
//
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		return result;
//
//	}
//
//	private void handleManyPropertiesAsService(JSONObject data, ResHttpCombineResult result) {
//		Iterator<String> keys = data.keys();
//		boolean isEmpty = true;
//		while (keys.hasNext()) {
//			String singleService = keys.next();
//			Type type = ParserFacotory.getFacotory().getBeanType(singleService);
//			if (type != null) {
//				Gson gson = new Gson();
//				BaseGson child = null;
//				try {
//					child = gson.fromJson(data.getJSONObject(singleService).toString(), type);
//				} catch (JsonSyntaxException e) {
//					e.printStackTrace();
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				if (child != null && ResChecker.isResPageAppNotEmpty((ResPageBean) child)) {
//					isEmpty = false;
//					result.combineData.put(singleService, child);
//				}
//			}
//		}
//
//		if (isEmpty) {
//			result.state.setCode(ResTag.STATE_CODE_EMPTY);
//		}
//	}
//
//	private void handleArrayAsService(JSONArray serviceArray, ResHttpCombineResult result) {
//
//		int serviceNodeSize = serviceArray.length();
//		for (int serviceNode = 0; serviceNode < serviceNodeSize; serviceNode++) {
//			JSONObject serviceData = null;
//			try {
//				serviceData = serviceArray.getJSONObject(serviceNode);
//			} catch (JSONException e1) {
//				e1.printStackTrace();
//			}
//			if (serviceData == null) {
//				continue;
//			}
//			Iterator<String> keys = serviceData.keys();
//			boolean isEmpty = true;
//			while (keys.hasNext()) {
//				String singleService = keys.next();
//				Type type = ParserFacotory.getFacotory().getBeanType(singleService);
//				if (type != null) {
//					Gson gson = new Gson();
//					BaseGson child = null;
//					try {
//						child = gson.fromJson(serviceData.getJSONObject(singleService).toString(), type);
//					} catch (JsonSyntaxException e) {
//						e.printStackTrace();
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					if (child != null && ResChecker.isResPageAppNotEmpty((ResPageBean) child)) {
//						isEmpty = false;
//						result.combineData.put(singleService, child);
//					}
//				}
//				break;
//			}
//			if (isEmpty) {
//				result.state.setCode(ResTag.STATE_CODE_EMPTY);
//			}
//		}
//
//	}
//
//	private ResState getResState(JSONObject obj) {
//		ResState state = new ResState();
//		try {
//			state.setCode(obj.getInt("code"));
//			state.setMsg(obj.getString("msg"));
//			state.setTips(obj.getString("tips"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return state;
//	}
//}
