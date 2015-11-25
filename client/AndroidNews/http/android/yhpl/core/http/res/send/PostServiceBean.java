package android.yhpl.core.http.res.send;

public class PostServiceBean extends PostBean {

	public String token = "";
	public PostClientBean client = PostConstants.client;
	public PostBean data;

	@Override
	public String toString() {
		return "PostTemplateBean [token=" + token + ", client=" + client + ", data=" + data + "]";
	}

	@Override
	public int getSeriableType() {
		return ReqTags.POST_TYPE_GSON;
	}

}
