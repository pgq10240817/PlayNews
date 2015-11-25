package android.yhpl.core.http.res.send;

public class ReqCode extends BaseReqJson {
	public String qq_email;
	public String mobile_phone;
	public int type;

	@Override
	public String toString() {
		return "ReqCode [qq_email=" + qq_email + ", mobile_phone=" + mobile_phone + ", type=" + type + "]";
	}

}
