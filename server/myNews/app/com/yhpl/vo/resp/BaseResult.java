package com.yhpl.vo.resp;

/**
 * @author yhpl_pgq
 * @email yhpl_pgq@sina.com
 * @github https://github.com/pgq10240817
 * @data 2015年11月6日
 */
public class BaseResult {
	public String service;
	public ResState state;

	@Override
	public String toString() {
		return "BaseResult [service=" + service + ", state=" + state + "]";
	}

}
