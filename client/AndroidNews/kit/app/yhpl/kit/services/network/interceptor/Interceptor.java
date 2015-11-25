package app.yhpl.kit.services.network.interceptor;

import app.yhpl.kit.services.network.HippoRequest;
import app.yhpl.kit.services.network.exception.HippoException;

/**
 * @author Hubert He
 * @version V1.0
 * @Description
 * @Createdate 14-9-24 18:08
 */
public interface Interceptor<T extends HippoRequest> {

	boolean getInterruptRule(T request);

	void dispatch(T request) throws HippoException;
}
