package android.yhpl.core.http.res;

import java.util.ArrayList;
import java.util.List;

public class ResCombineGson extends BaseGson {
	private List<BaseGson> mData;

	public List<BaseGson> getData() {
		return mData;
	}

	public void setData(List<BaseGson> data) {
		mData = data;
	}

	public void appendBean(BaseGson bean) {
		if (mData == null) {
			mData = new ArrayList<BaseGson>();
		}
		mData.add(bean);
	}
}
