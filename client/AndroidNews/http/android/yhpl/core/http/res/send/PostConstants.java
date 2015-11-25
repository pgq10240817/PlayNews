package android.yhpl.core.http.res.send;

public class PostConstants {

	public static String token;
	public static PostClientBean client;

	static {
		token = "token";
		client = new PostClientBean();
		client.width = 720;
		client.height = 1280;
	}
}
