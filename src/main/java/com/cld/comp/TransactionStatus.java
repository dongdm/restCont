package com.cld.comp;

import java.io.Serializable;

public class TransactionStatus implements Serializable {

	private static final long serialVersionUID = 728948318902420728L;

	private String userReferenceNumber;
	private String externalReferenceNo;
	private String globaReferenceNumber;
	private String replyCode;
	private String replyText;
	private String memo;

	public String getUserReferenceNumber() {
		return userReferenceNumber;
	}

	public void setUserReferenceNumber(String userReferenceNumber) {
		this.userReferenceNumber = userReferenceNumber;
	}

	public String getExternalReferenceNo() {
		return externalReferenceNo;
	}

	public void setExternalReferenceNo(String externalReferenceNo) {
		this.externalReferenceNo = externalReferenceNo;
	}

	public String getGlobaReferenceNumber() {
		return globaReferenceNumber;
	}

	public void setGlobaReferenceNumber(String globaReferenceNumber) {
		this.globaReferenceNumber = globaReferenceNumber;
	}

	public String getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public boolean isSuccess(){
		if("0000".equals(this.replyCode)){
			return true;
		}else{
			return false;
		}
	}

}
