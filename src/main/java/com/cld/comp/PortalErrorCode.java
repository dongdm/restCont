package com.cld.comp;

public class PortalErrorCode {
	/**
	 * 交易成功/验证通过
	 */
	public final static String SuccessTransaction = "0000";
	
	/**
	 * 输入参数不合法
	 */
	public final static String InputParamNotIegitimate = "9998";
	
	/**
	 * 未知账户
	 */
	public final static String UnknownAccountException = "2101";
	
	/**
	 * 错误凭证
	 */
	public final static String IncorrectCredentialsException = "2102";
	
	/**
	 * 账户已冻结
	 */
	public final static String LockedAccountException = "2103";

	/**
	 * 错误次数过多
	 */
	public final static String ExcessiveAttemptsException = "2104";
	
	/**
	 * 未知凭证
	 */
	public final static String AuthenticationException = "2105";
	
	
}
