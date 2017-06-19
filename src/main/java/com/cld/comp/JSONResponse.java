package com.cld.comp;

import java.util.ArrayList;

/** 
 * @className JsonResponse.java
 * @author TuJun
 * @createTime 2015年2月3日 下午3:58:21
 * @classDesc 返回JSON格式传递给页面
 */
public class JSONResponse {
	
	private TransactionStatus trans;
	
	private Object data;
	
	//spring validation界面校验错误信息
	private ArrayList<JSONError> validation;
	
	public TransactionStatus getTrans() {
		return trans;
	}

	public void setTrans(TransactionStatus trans) {
		this.trans = trans;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ArrayList<JSONError> getValidation() {
		return validation;
	}

	public void setValidation(ArrayList<JSONError> validation) {
		this.validation = validation;
	}

	public JSONResponse() {
		super();
	}

	public JSONResponse(TransactionStatus trans, Object data,
			ArrayList<JSONError> validation) {
		super();
		this.trans = trans;
		this.data = data;
		this.validation = validation;
	}

}
