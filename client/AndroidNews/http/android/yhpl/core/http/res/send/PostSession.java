package android.yhpl.core.http.res.send;

public class PostSession {
	public long uid;
	public String sid;
	public static PostSession SESSION = new PostSession();

	@Override
	public String toString() {
		return "PostSession [uid=" + uid + ", sid=" + sid + "]";
	}

}
