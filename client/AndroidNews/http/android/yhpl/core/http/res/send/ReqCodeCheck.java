package android.yhpl.core.http.res.send;

public class ReqCodeCheck extends ReqCode {
	public String verify_no;

	@Override
	public String toString() {
		return "ReqCodeCheck [verify_no=" + verify_no + ", qq_email=" + qq_email + ", mobile_phone=" + mobile_phone
				+ ", type=" + type + "]";
	}

}
