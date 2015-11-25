package test;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;

import models.News;
import play.api.db.DB;

public class ModelTest {

	private String formatted(Date date) {
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	//
	// @Test
	// public void findById() {
	// running(fakeApplication(), new Runnable() {
	// public void run() {
	// JPA.withTransaction(new play.libs.F.Callback0() {
	// public void invoke() {
	// News macintosh = News.findById(21l);
	// assertThat(macintosh.name).isEqualTo("Macintosh");
	// assertThat(formatted(macintosh.introduced)).isEqualTo("1984-01-24");
	// }
	// });
	// }
	// });
	// }

	// @Test
	// public void pagination() {
	// running(fakeApplication(inMemoryDatabase()), new Runnable() {
	// public void run() {
	// Page<News> news = News.page(1, 20, "name", "ASC", "");
	// assertThat(news.getTotalRowCount()).isEqualTo(574);
	// assertThat(news.getList().size()).isEqualTo(20);
	// }
	// });
	// }

	@Test
	public void queryPage() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Page<News> news = News.page(1, 10, "time", "desc", "netease");
				System.out.println(" ----  " + news.getTotalRowCount());
				List<News> mList = news.getList();
				for (News tmp : mList) {
					System.out.println(tmp);
				}

				System.out.println(" ---------------  ");

			}
		});
	}

	@Test
	public void insert() {

		running(fakeApplication(), new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(" ----------------- > " + i);
					News news = new News();
					news.cp = "netease";
					news.snapDetail = "简介描述" + i;
					news.title = "标题" + i;
					news.time = new Date();
					Ebean.save(news);
				}
			}
		});

	}

}
