package android.yhpl.core.http.res;

public class Page {
	public String total;

	private int more;

	public String count;

	public boolean isMore() {
		return more == 1;
	}

	@Override
	public String toString() {
		return "Page [total=" + total + ", more=" + more + ", count=" + count + "]";
	}
	
}
