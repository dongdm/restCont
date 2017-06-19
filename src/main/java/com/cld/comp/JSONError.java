package com.cld.comp;
/** 
 * @className JSONError.java
 * @author TuJun
 * @createTime 2015年2月3日 下午4:00:59
 * @classDesc 
 */
public class JSONError {
	//元素名，与页面元素名一致  
    private String element;  
    //错误信息  
    private String message;
    
	public JSONError(String element, String message) {
		super();
		this.element = element;
		this.message = message;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}  
      
}
