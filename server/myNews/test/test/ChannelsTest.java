package test;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yhpl.utils.JsonUtils;
import com.yhpl.vo.req.ReqTags;
import com.yhpl.vo.resp.BaseResult;
import com.yhpl.vo.resp.ResPageBean;
import com.yhpl.vo.resp.ResResultPage;
import com.yhpl.vo.resp.RespTags;
import com.yhpl.vo.resp.query.ResQueryManager;
import com.yhpl.vo.resp.query.RespQueryBase;

import junit.framework.Assert;
import models.Channels;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年11月6日
 */
public class ChannelsTest {
	@Test
	public void insertApps() {

		running(fakeApplication(), new Runnable() {
			public void run() {
				RespQueryBase sRespController = ResQueryManager.getInstance().getDispatcher(RespTags.SERVICE_CHANNELS);
				ObjectNode rootNode = JsonUtils.getObjectMapper().createObjectNode();
				ObjectNode pageNode = rootNode.objectNode();
				pageNode.put(ReqTags.KEY_ARGS_PAGE_OFFSET, 0);
				rootNode.put(ReqTags.KEY_ARGS_PRIVATE, pageNode);
				BaseResult result = sRespController.getResultWithJsonNode(rootNode);
				System.out.println(result);
				Assert.assertNotNull(result);
				ResResultPage pageResult = (ResResultPage) result;
				ResPageBean page = pageResult.data;
				List<Channels> list = (List<Channels>) page.content;
				Assert.assertNotNull(list);

			}
		});

	}
}
