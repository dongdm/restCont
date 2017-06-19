/**
 * 
 */
package com.cld.utils;

import javax.validation.constraints.NotNull;

/** 
 * @className Img.java
 * @author dongdm
 * @createTime 2015年4月7日 下午11:06:58
 * @classDesc 客户头像剪切
 */
public class ImgCut {
	
	private String imgContentType;

	@NotNull(message="头像不能为空")
	private String srcImgPath;
	
	private String tarImgPath;
	@NotNull(message="裁剪x坐标不能为空")
	private Integer x1;
	@NotNull(message="裁剪y坐标不能为空")
	private Integer y1;
	
	private Integer x2;
	
	private Integer y2;
	@NotNull(message="裁剪宽度不能为空")
	private Integer width;
	@NotNull(message="裁剪高度不能为空")
	private Integer height;

	/**
	 * @return the x1
	 */
	public Integer getX1() {
		return x1;
	}

	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(Integer x1) {
		this.x1 = x1;
	}

	/**
	 * @return the y1
	 */
	public Integer getY1() {
		return y1;
	}

	/**
	 * @param y1 the y1 to set
	 */
	public void setY1(Integer y1) {
		this.y1 = y1;
	}

	/**
	 * @return the x2
	 */
	public Integer getX2() {
		return x2;
	}

	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(Integer x2) {
		this.x2 = x2;
	}

	/**
	 * @return the y2
	 */
	public Integer getY2() {
		return y2;
	}

	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(Integer y2) {
		this.y2 = y2;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	
	
	

	

	/**
	 * @return the imgContentType
	 */
	public String getImgContentType() {
		return imgContentType;
	}

	/**
	 * @param imgContentType the imgContentType to set
	 */
	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	/**
	 * @return the srcImgPath
	 */
	public String getSrcImgPath() {
		return srcImgPath;
	}

	/**
	 * @param srcImgPath the srcImgPath to set
	 */
	public void setSrcImgPath(String srcImgPath) {
		this.srcImgPath = srcImgPath;
		this.imgContentType = ImgUtils.getExt(srcImgPath);
		this.tarImgPath =
				ImgUtils.getFileLongName(srcImgPath) + "_cut." + this.imgContentType;
	}

	/**
	 * @return the tarImgPath
	 */
	public String getTarImgPath() {
		return tarImgPath;
	}

	/**
	 * @param tarImgPath the tarImgPath to set
	 */
	public void setTarImgPath(String tarImgPath) {
		this.tarImgPath = tarImgPath;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Img [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2
				+ ", width=" + width + ", height=" + height + "]";
	}

}
