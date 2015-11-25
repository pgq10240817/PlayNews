package android.yhpl.core.http.res.send;

public class ReqLogin extends BaseReqJson {
	public String name;

	public String password;

	@Override
	public String toString() {
		return "ReqLogin [name=" + name + ", password=" + password + "]";
	}

}
