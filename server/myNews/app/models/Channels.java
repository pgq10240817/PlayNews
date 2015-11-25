package models;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年9月25日
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.h2.util.StringUtils;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import play.db.ebean.Model;

@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
@Entity
@Table(name = "Channels")
public class Channels extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String T_NAME = "Channels";

	@Id
	public Long id;
	public String cid;
	public String cname;
	public String subnum;
	public String extra;

	public static Finder<Long, Channels> find = new Finder<Long, Channels>(Long.class, Channels.class);

	public static Page<Channels> page(int page, int pageSize, String sortBy, String order) {
		return find.orderBy(sortBy + " " + order).findPagingList(pageSize).getPage(page);
	}

	public static int getCount() {
		return find.findRowCount();
	}

	public static Page<Channels> page(int page, int pageSize, String cid) {
		if (StringUtils.isNullOrEmpty(cid)) {
			return null;
		}
		return find.where().ieq("cid", cid).findPagingList(pageSize).getPage(page);
	}

	public static Page<Channels> page(int page, int pageSize) {

		return find.findPagingList(pageSize).getPage(page);
	}

	public static Channels getChannelWithCname(String cname) {
		Channels result = find.where().ieq("cname", cname).findUnique();
		return result;
	}

	@Override
	public String toString() {
		return "Channels [id=" + id + ", cid=" + cid + ", cname=" + cname + "]";
	}

}
