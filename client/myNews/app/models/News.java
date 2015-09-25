package models;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年9月25日
 */
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yhpl.utils.TextUtil;

import play.data.format.Formats;
import play.db.ebean.Model;

@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
@Entity
@Table(name = "news3")
public class News extends Model {

	public static final String T_NAME = "news3";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	public String title;

	public String source;

	public String cp;

	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date time;

	public String cid;

	public String snapDetail;
	public String icon;
	public String url;
	public String extra;

	public static Finder<Long, News> find = new Finder<Long, News>(Long.class, News.class);

	/**
	 * Return a page of News
	 *
	 * @param page
	 *            Page to display
	 * @param pageSize
	 *            Number of computers per page
	 * @param sortBy
	 *            Computer property used for sorting
	 * @param order
	 *            Sort order (either or asc or desc)
	 * @param filter
	 *            Filter applied on the name column
	 */
	public static Page<News> page(int page, int pageSize, String sortBy, String order, String filter) {
		// return find.where().ilike("channel", "%" + filter +
		// "%").orderBy(sortBy + " " + order).fetch("company")
		// .findPagingList(pageSize).setFetchAhead(false).getPage(page);
		return find.where().ilike("cid", "%" + filter + "%").orderBy(sortBy + " " + order).findPagingList(pageSize)
				.getPage(page);
	}

	public static News getNewsWithTitle(String title) {
		if (TextUtil.isEmpty(title)) {
			return null;
		} else {
			return find.where().ieq("title", title).findUnique();
		}
	}
}
