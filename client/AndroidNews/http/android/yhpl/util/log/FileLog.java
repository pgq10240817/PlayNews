package android.yhpl.util.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Environment;

/**
 * Created by diskzhou on 13-8-8.
 */
public class FileLog {
	private static boolean isPrintLog = false;

	// 用于格式化日期,作为日志文件名的一部分
	private DateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	private String logFileName;

	private static FileLog instance;

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	private FileLog() {
		initLog();
	}

	// 返回Filelog的实例
	public synchronized static FileLog getIntance() {
		if (instance == null) {
			instance = new FileLog();
		}
		return instance;
	}

	private void initLog() {
		String time = formatter.format(new Date());
		logFileName = "printlog" + "_" + time + ".log";
	}

	public void writeOnLine(final String deviceType, final String logType,
			final String logContent) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				final String url = "http://10.32.80.31:8080/apkdownload/test_src/uploadlog.php";
				try {
					String parameter = String.format(
							"device=%s&type=%s&content=%s",
							URLEncoder.encode(deviceType, "UTF-8"),
							URLEncoder.encode(logType, "UTF-8"),
							URLEncoder.encode(logContent, "UTF-8"));
					String urlNameString = url + "?" + parameter;
					URL realUrl = new URL(urlNameString);
					// 打开和URL之间的连接
					connection = (HttpURLConnection) realUrl.openConnection();
					// 设置通用的请求属性
					connection.setRequestMethod("GET");
					connection.setRequestProperty("accept", "*/*");
					connection.setRequestProperty("connection", "Keep-Alive");
					connection
							.setRequestProperty("user-agent",
									"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)network_monitor");
					connection.setRequestProperty("Accept-Encoding",
							"gzip,deflate");
					connection.setUseCaches(false);
					// 建立实际的连接

					connection.connect();
					connection.getResponseCode();
				} catch (Exception e) {
				} finally {
					if (null != connection) {
						connection.disconnect();
					}
				}
			}
		});
	}

	/**
	 *
	 * @param tag
	 * @param message
	 * @param append
	 *            本地写入追加
	 */
	public void write(String tag, String message, boolean append) {

		BufferedWriter out = null;
		File file = new File(Environment.getExternalStorageDirectory()
				.getPath() + "/log", logFileName);

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, append)));
			StringBuffer logBuffer = new StringBuffer();
			logBuffer.append(formatter.format(new Date()));
			logBuffer.append("    ");
			logBuffer.append(tag);
			logBuffer.append("    ");
			logBuffer.append(message);
			logBuffer.append("\n");
			out.write(logBuffer.toString());
		} catch (Exception e) {
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
					out = null;
				}
			} catch (IOException e) {
			}
		}
	}
}
