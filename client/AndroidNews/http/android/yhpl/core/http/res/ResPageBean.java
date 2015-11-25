package android.yhpl.core.http.res;

import java.util.List;

import app.yhpl.news.adapter.presenter.bean.BaseBean;

public class ResPageBean<T extends BaseBean> extends BaseGson {
	public List<T> content;

	private int nextOffset;

	@Override
	public List<? extends BaseBean> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getNextOffset() {
		return nextOffset;
	}

	public void setNextOffset(int nextOffset) {
		this.nextOffset = nextOffset;
	}

	@Override
	public String toString() {
		return "ResPageBean [content = " + content + ", nextOffset = " + nextOffset + "]";
	}
}
