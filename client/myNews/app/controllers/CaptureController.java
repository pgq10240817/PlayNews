package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.h2.mvstore.DataUtils;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.yhpl.utils.CollectionUtil;
import com.yhpl.utils.DateUtil;
import com.yhpl.utils.JsonFileUtil;
import com.yhpl.utils.NewsUrlUtil;
import com.yhpl.utils.work.TrimFilter;
import com.yhpl.vo.NewChannalVo;
import com.yhpl.vo.NewChannalsVo;
import com.yhpl.vo.NewsVo;

import models.Channels;
import models.News;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class CaptureController extends Controller {

	public static Result capture() {
		return ok("111");
	}

	public static Result initChannels() {
		NewChannalsVo chanals = (NewChannalsVo) JsonFileUtil.getGetUrlContentAsObject(NewsUrlUtil.getChannelUrl(),
				NewChannalsVo.class);
		if (chanals != null) {
			NewChannalVo[] channelArray = chanals.gettList();
			List<Channels> beans = new ArrayList<Channels>();
			for (int i = 0; i < channelArray.length; i++) {
				NewChannalVo jsonObj = channelArray[i];
				Channels bean = new Channels();
				bean.cname = jsonObj.getTname();
				bean.cid = jsonObj.getTid();
				bean.subnum = jsonObj.getSubnum();
				beans.add(bean);

			}
			Ebean.beginTransaction();
			for (int i = 0; i < channelArray.length; i++) {
				Channels bean = beans.get(i);
				Channels target = Channels.getChannelWithCname(bean.cname);
				if (target == null) {
					Ebean.save(bean);
				} else {
					System.out.println("exist -- " + target.cname);
				}
			}

			Ebean.commitTransaction();

		}
		return ok("init Channels success");
	}

	public static Result initNews() {
		Page<Channels> pageChannel = Channels.page(1, 20, "id", "asc");
		if (pageChannel.getTotalRowCount() > 0) {
			List<Channels> channelBeans = pageChannel.getList();
			if (!CollectionUtil.isEmpty(channelBeans)) {
				for (int i = 0; i < channelBeans.size(); i++) {

					// A.解析数据
					Channels channelBean = channelBeans.get(i);
					String url = NewsUrlUtil.getChannelNewsUrlWithCidPageCount(channelBean.cid);
					JsonNode node = JsonFileUtil.getGetUrlContentAsJsonNode(url);
					ArrayNode arrayNodes = (ArrayNode) node.get(channelBean.cid);
					Iterator<JsonNode> iter = arrayNodes.iterator();
					List<News> mNews = new ArrayList<News>();
					while (iter.hasNext()) {
						JsonNode childNode = iter.next();
						NewsVo childNews = Json.fromJson(childNode, NewsVo.class);
						News news = new News();
						news.cid = channelBean.cid;
						news.cp = childNews.getSource();
						news.icon = childNews.getImgsrc();
						news.url = childNews.getUrl();
						news.title = childNews.getTitle();
						news.snapDetail = childNews.getDigest();
						news.time = DateUtil.getDateFromString(childNews.getPtime());
						mNews.add(news);
						System.out.println("child:" + childNews);
					}

					// B.过滤数据库
					CollectionUtil.trimListWithFilter(mNews, new TrimFilter<News>() {

						@Override
						public boolean isFilter(News t) {
							return t != null && News.getNewsWithTitle(t.title) != null;
						}
					});

					// C.POJO -> DB
					if (!CollectionUtil.isEmpty(mNews)) {
						System.out.println("save --- > :" + channelBean.cid);
						Ebean.save(mNews);
					}

				}
			}
		}

		return ok("init News success");
	}

	public static Result page(String table) {

		if (News.T_NAME.equals(table)) {
			Page<News> news = News.page(1, 10, "time", "desc", "netease");
			int count = news.getTotalPageCount();
			System.out.println("count is " + count);
			List<News> mList = news.getList();
			return ok(Json.toJson(mList));
		} else {
			return ok("not found");
		}

	}

}
