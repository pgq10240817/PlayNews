package android.yhpl.core.http.res;

public class ResState {
	private String tips;

	private int code;

	private String msg;

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ClassPojo [tips = " + tips + ", code = " + code + ", msg = " + msg + "]";
	}
}
