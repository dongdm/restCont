/**
 * 
 */
package com.cld.utils;

import com.alibaba.fastjson.JSONObject;
import com.cld.comp.JSONError;
import com.cld.comp.JSONResponse;
import com.cld.comp.PortalErrorCode;
import com.cld.comp.TransactionStatus;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.Locale;

/** 
 * @className ResponseMes.java
 * @author dongdm
 * @createTime 2015年3月14日 下午3:02:14
 * @classDesc 
 */
public class ResponseMes {
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午11:05:13
	 * @desc   获取简单参数错误信息(不包含错误具体信息)
	 * @return
	 */
	public static String getSimpleParamErrorResponseMes(MessageSource messageSource){
		ArrayList<JSONError> errors = null;
		return getResponseMes(errors,messageSource);
	}
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午9:31:23
	 * @desc  手动验证传入参数是否为空
	 * @param errors  参数验证信息
	 * @param messageSource
	 * @return
	 */
	public static String getResponseMes(ArrayList<JSONError> errors,MessageSource messageSource){
		String responseStr = null;
		JSONResponse jsonResponse = new JSONResponse();
		TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode(PortalErrorCode.InputParamNotIegitimate);
		trans.setReplyText(messageSource.getMessage("error." + trans.getReplyCode(), null, Locale.CHINESE));
		jsonResponse.setTrans(trans);
		if(null != errors && errors.size() > 0){
			jsonResponse.setValidation(errors);
		}
		responseStr =  JSONObject.toJSONString(jsonResponse);
		return responseStr;
	}
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午9:31:23
	 * @desc  文件上传出错返回错误信息
	 * @param messageSource
	 * @return
	 */
	public static String getUploadErrorResponseMes(MessageSource messageSource){
		String responseStr = null;
		JSONResponse jsonResponse = new JSONResponse();
		TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode("1002");
//		trans.setReplyText(messageSource.getMessage("error." + trans.getReplyCode(), null, Locale.CHINESE));
		trans.setReplyText("文件上传出错,文件类型不支持");
		jsonResponse.setTrans(trans);
		responseStr =  JSONObject.toJSONString(jsonResponse);
		return responseStr;
	}
	
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月23日 下午7:40:18
	 * @desc   未知用户
	 * @param messageSource  错误提示信息
	 * @return web段Json串的错误提示
	 */
	public static String getUnknownAccountResponseMes(MessageSource messageSource){
		JSONResponse jSONResponse = new JSONResponse();
		TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode(PortalErrorCode.UnknownAccountException);
		trans.setReplyText(messageSource.getMessage("error." + trans.getReplyCode(), null, Locale.CHINESE));
		jSONResponse.setTrans(trans);
		String responseStr = JSONObject.toJSONString(jSONResponse);
		return responseStr;
	}
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午11:22:46
	 * @desc  错误凭证
	 * @param messageSource
	 * @return
	 */
	public static String getIncorrectCredentialsResponseMes(MessageSource messageSource){
		JSONResponse jSONResponse = new JSONResponse();
		TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode(PortalErrorCode.IncorrectCredentialsException);
		trans.setReplyText(messageSource.getMessage("error." + trans.getReplyCode(), null, Locale.CHINESE));
		jSONResponse.setTrans(trans);
		String responseStr = JSONObject.toJSONString(jSONResponse);
		return responseStr;
	}
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午11:22:46
	 * @desc  错误次数过多
	 * @param messageSource
	 * @return
	 */
	public static String getExcessiveAttemptsResponseMes(MessageSource messageSource){
		JSONResponse jSONResponse = new JSONResponse();
		TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode(PortalErrorCode.ExcessiveAttemptsException);
		trans.setReplyText(messageSource.getMessage("error." + trans.getReplyCode(), null, Locale.CHINESE));
		jSONResponse.setTrans(trans);
		String responseStr = JSONObject.toJSONString(jSONResponse);
		return responseStr;
	}
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午11:22:46
	 * @desc  验证未通过,堆栈轨迹如下
	 * @param messageSource
	 * @return
	 */
	public static String getAuthenticationResponseMes(MessageSource messageSource){
		JSONResponse jSONResponse = new JSONResponse();
		TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode(PortalErrorCode.AuthenticationException);
		trans.setReplyText(messageSource.getMessage("error." + trans.getReplyCode(), null, Locale.CHINESE));
		jSONResponse.setTrans(trans);
		String responseStr = JSONObject.toJSONString(jSONResponse);
		return responseStr;
	}
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午11:26:15
	 * @desc  账户已冻结
	 * @param messageSource
	 * @return
	 */
	public static String getLockedAccountResponseMes(MessageSource messageSource){
		JSONResponse jSONResponse = new JSONResponse();
		TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode(PortalErrorCode.LockedAccountException);
		trans.setReplyText(messageSource.getMessage("error." + trans.getReplyCode(), null, Locale.CHINESE));
		jSONResponse.setTrans(trans);
		String responseStr = JSONObject.toJSONString(jSONResponse);
		return responseStr;
	}
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午10:55:03
	 * @desc   获得响应成功信息返回客户端
	 * @param messageSource  成功提示信息
	 * @param data  返回客户端数据
	 * @return
	 */
	public static String getSuccessResponseMes(Object data,MessageSource messageSource){
		JSONResponse jSONResponse = new JSONResponse();
		TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode(PortalErrorCode.SuccessTransaction);
		trans.setReplyText(messageSource.getMessage("error." + trans.getReplyCode(), null, Locale.CHINESE));
		jSONResponse.setTrans(trans);
		jSONResponse.setData(data);
		String responseStr = JSONObject.toJSONString(jSONResponse);
		return responseStr;
	}
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午10:55:03
	 * @desc   获得简单的响应成功信息返回客户端
	 * @param messageSource  成功提示信息
	 * @return
	 */
	public static String getSimpleSuccessResponseMes(MessageSource messageSource){
		return getSuccessResponseMes(null,messageSource);
	}
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午9:31:23
	 * @desc  文件上传出错返回错误信息
	 * @param size
	 * @return
	 */
	public static String getUploadSizeErrorResponseMes(long size){
		String responseStr = null;
		JSONResponse jsonResponse = new JSONResponse();
		TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode("1002");
//		trans.setReplyText(messageSource.getMessage("error." + trans.getReplyCode(), null, Locale.CHINESE));
		trans.setReplyText("文件上传出错,文件不能超过" + (size / 1024 / 1024) + "M");
		jsonResponse.setTrans(trans);
		responseStr =  JSONObject.toJSONString(jsonResponse);
		return responseStr;
	}
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月24日 上午9:31:23
	 * @desc  文件上传出错返回错误信息
	 * @param messageSource
	 * @return
	 */
	public static String getUploadTypeErrorResponseMes(MessageSource messageSource){
		String responseStr = null;
		JSONResponse jsonResponse = new JSONResponse();
		TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode("1002");
//		trans.setReplyText(messageSource.getMessage("error." + trans.getReplyCode(), null, Locale.CHINESE));
		trans.setReplyText("文件上传出错,文件类型不支持");
		jsonResponse.setTrans(trans);
		responseStr =  JSONObject.toJSONString(jsonResponse);
		return responseStr;
	}
	
	

}
