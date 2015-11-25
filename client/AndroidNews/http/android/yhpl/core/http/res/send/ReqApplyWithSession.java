package android.yhpl.core.http.res.send;

public class ReqApplyWithSession extends BaseReqJson {
	public PostPage pagination;
	public PostSession session = PostSession.SESSION;
	public int bank_type;
	public String account_no;
	public String account_name;
	public float money;

	@Override
	public String toString() {
		return "ReqApplyWithSession [pagination=" + pagination + ", session=" + session + ", bank_type=" + bank_type
				+ ", account_no=" + account_no + ", account_name=" + account_name + ", money=" + money + "]";
	}

}
