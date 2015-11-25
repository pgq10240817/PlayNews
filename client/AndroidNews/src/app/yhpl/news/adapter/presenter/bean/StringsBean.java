package app.yhpl.news.adapter.presenter.bean;

import java.util.List;

public class StringsBean extends BaseBean {
	private String title;
	private List<StringBean> mContents;

	public List<StringBean> getContents() {
		return mContents;
	}

	public void setContents(List<StringBean> contents) {
		mContents = contents;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
