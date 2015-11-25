package app.yhpl.kit.services.network;

import app.yhpl.kit.services.network.exception.HippoException;

/**
 * @author Hubert He
 * @version V1.0
 * @Description
 * @Createdate 14-9-25 11:48
 */
public interface Network<T extends HippoRequest<?>> {
	public NetworkResponse performRequest(T hippoRequest) throws HippoException;
}
