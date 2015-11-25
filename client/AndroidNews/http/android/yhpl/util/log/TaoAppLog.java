package android.yhpl.util.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Build;
import android.util.Log;

/**
 * Created by 古朗 on 13-7-29.
 * <p/>
 * 替代TaoLog 完成日志打印的封装
 * <p/>
 * e.printStackTrace() --> TaoAppLog.printStackTrace(e) Log.e --> TaoAppLog.Loge
 * TaoLog.Loge --> TaoAppLog.loge ......
 */
public class TaoAppLog {

	private static boolean isPrintLog = true;
	private static boolean isSaveLogToLocal = false;
	public static boolean isUploadLogWhenExit = false;

	public static final String TAOBAO_TAG = "Taobao";
	public static final String IMGPOOL_TAG = "TaoSdk.ImgPool";

	public static boolean getLogStatus() {
		return isPrintLog;
	}

	public static boolean getLocalLogStatus() {
		return isSaveLogToLocal;
	}

	public static void setLogSwitcher(boolean open) {
		isPrintLog = open;
	}

	public static void setLogToLocalSwitcher(boolean open) {
		isSaveLogToLocal = open;
	}

	public static void printStackTrace(Throwable e) {
		if (e == null) {
			return;
		}
		if (isPrintLog) {
			e.printStackTrace();
		}
		if (isSaveLogToLocal || isUploadLogWhenExit) {
			saveCrashLog2File(e);
		}
	}

	private static void saveCrashLog2File(Throwable ex) {
		StringBuffer crashBuffer = new StringBuffer();
		try {
			String build = Build.BRAND;
			String mode = Build.MODEL;
			String release = Build.VERSION.RELEASE;

			crashBuffer
					.append("================================================\n");
			DateFormat format = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
			crashBuffer.append(format.format(new Date()) + "\n");
			crashBuffer.append("deviceInfo:bulid=" + build + ";mode=" + mode
					+ ";release=" + release + "\n");

			String result = getThrowableStackTraceStr(ex);
			crashBuffer.append(result);
			crashBuffer.append("\r\n");
			FileLog.getIntance().write("Exception", crashBuffer.toString(),
					true);
		} catch (Exception e) {

		}
	}

	public static String getThrowableStackTraceStr(Throwable ex) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		return writer.toString();
	}

	public static void Logd(String tag, String msg) {
		if (tag != null && msg != null) {
			if (isPrintLog) {
				Log.d(tag, msg);
			}
			if (isSaveLogToLocal || isUploadLogWhenExit) {
				FileLog.getIntance().write(tag, msg, true);
			}
		}

		return;
	}

	public static void Loge(String tag, String msg) {

		if (tag != null && msg != null) {
			if (isPrintLog) {
				Log.e(tag, msg);
			}
			if (isSaveLogToLocal || isUploadLogWhenExit) {
				FileLog.getIntance().write(tag, msg, true);
			}
		}

		return;
	}

	public static void Logi(String tag, String msg) {
		if (tag != null && msg != null) {
			if (isPrintLog) {
				Log.i(tag, msg);
			}
			if (isSaveLogToLocal || isUploadLogWhenExit) {
				FileLog.getIntance().write(tag, msg, true);
			}
		}

		return;
	}

	public static void Logv(String tag, String msg) {
		if (tag != null && msg != null) {
			if (isPrintLog) {
				Log.v(tag, msg);
			}
			if (isSaveLogToLocal || isUploadLogWhenExit) {
				FileLog.getIntance().write(tag, msg, true);
			}
		}

		return;
	}

	public static void Logw(String tag, String msg) {
		if (tag != null && msg != null) {
			if (isPrintLog) {
				Log.w(tag, msg);
			}

			if (isSaveLogToLocal || isUploadLogWhenExit) {
				FileLog.getIntance().write(tag, msg, true);
			}
		}

		return;
	}

	public static void Log2File(String tag, String msg) {

		if (isPrintLog) {
			FileLog.getIntance().write(tag, msg, true);
		}

	}

	/***
	 *
	 * @param logType
	 *            日志标识
	 * @param msg
	 *            写入远端的日志内容
	 */

	public static void log2Online(String logType, String msg) {
		if (isPrintLog) {
			String deviceInfo = android.os.Build.VERSION.RELEASE.replaceAll(
					"\\.", "_") + android.os.Build.MODEL;
			FileLog.getIntance().writeOnLine(deviceInfo, logType, msg);
		}
	}

	// 方便debug时打log用
	public static void LogPojo(String tag, Object obj) {
		// try {
		// if ((isPrintLog || isSaveLogToLocal) && obj != null) {
		// String str = JSON.toJSONString(obj);
		// TaoAppLog.Logd(tag, "soft.toString " + str);
		// }
		// } catch (Exception ex) {
		// // log没打出来就算了
		// }
	}

}
