package android.yhpl.core.http.res;

public interface ResTag {
	public int STATE_CODE_DEFAULT = 0;
	public int STATE_CODE_SUCCESS = 200;
	public int STATE_CODE_SESSEION_TIMEOUT = 100;
	public int STATE_CODE_EMPTY = 300;
	public int STATE_CODE_NOT_FOUND = 404;
	public int STATE_CODE_ERROR = 500;

	public int APPLY_STATUS_SUBMIT = 1;
	public int APPLY_STATUS_HANDLING = 2;
	public int APPLY_STATUS_SUCCESS = 3;
	public int APPLY_STATUS_FAILED = 4;

	public int CODE_TYPE_REG = 1;
	public int CODE_TYPE_RESETPWD = 2;
	public int CODE_TYPE_LOGIN = 3;
	public int CODE_TYPE_CHANGEPHONE = 4;

}
