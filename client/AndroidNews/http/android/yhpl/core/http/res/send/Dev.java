package android.yhpl.core.http.res.send;

import app.yhpl.news.App;

public class Dev {
	public static Dev DEV = new Dev();
	public int device_type = App.DEVICE_TYPE;

	public String app_version = App.VERSION;

	@Override
	public String toString() {
		return "Dev [device_type=" + device_type + ", app_version=" + app_version + "]";
	}

}
