package android.yhpl.core.http.res.send;

import android.yhpl.core.http.HttpArgsTag;

public class PostPage {
	public int offset;
	public int count = HttpArgsTag.COUNT_APPLY_CASH_LOG;

	@Override
	public String toString() {
		return "PostPage [page=" + offset + ", count=" + count + "]";
	}

}
