/**
 * 
 */
package com.cld.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




/** 
 * @className ImgUtils.java
 * @author dongdm
 * @createTime 2015年4月7日 上午11:03:02
 * @classDesc 
 */
/**
 * @author Administrator
 *
 */
public class ImgUtils {
	
	private static Log log = LogFactory.getLog(ImgUtils.class);
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年4月16日 下午3:31:54
	 * @desc  获得文件后缀
	 * @param fileName
	 * @return
	 */
	public static String getExt(String fileName){
		String ext = null;
		int index = 0;
		if(null != fileName){
			index = fileName.lastIndexOf(".");
		}else{
			return ext;
		}
		if(index > 0){
			ext = fileName.substring(index + 1);
		}
		return ext;
	}
	
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年4月16日 下午3:31:54
	 * @desc  获得长文件名称
	 * @param fileName
	 * @return
	 */
	public static String getFileLongName(String fileName){
		String longFileName = null;
		int index = 0;
		if(null != fileName){
			index = fileName.lastIndexOf(".");
		}else{
			return longFileName;
		}
		if(index > 0){
			longFileName = fileName.substring(0,index-1);
		}
		return longFileName;
	}
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月14日 下午5:49:13
	 * @desc 生成上传文件的名称
	 * @param ext 文件后缀名
	 * @return
	 */
	public static String getFileName(String ext){
		return System.currentTimeMillis() + RandomUtil.getRandomChar(4) + ext;
	}
	
	
	/**
	 * 
	 * @author Dongdm
	 * @createTime 2015年3月28日 上午11:29:15
	 * @desc   判断该类型文件是否可以上传
	 * @param ext
	 * @return 
	 */
	public static boolean isAllowed(String ext){
		boolean flag = false;
		if(null != ext){
			ext = ext.toLowerCase();
			if(ext.endsWith("jpg") || ext.endsWith("jpeg") || ext.endsWith("png")){
				flag = true;
			}
		}
		return flag;
	}
	
	
	 /**      
	 * 截图      
	 * @param srcPath      
	 * @param startX      
	 * @param startY      
	 * @param width      
	 * @param height      
	 */    
	 private static BufferedImage cut(String srcPath,int startX,int startY,int width,int height){
		 BufferedImage subImg = null;
		 try {
			 BufferedImage bufImg = ImageIO.read(new File(srcPath));
			 subImg = bufImg.getSubimage(startX, startY, width, height);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }     
		 return subImg;
	 }     
	 /**
	  * 
	  * @author Dongdm
	  * @createTime 2015年4月7日 上午11:18:16
	  * @desc  根据传入的图片类型地址尺寸进行截图并保存
	  * @param imgCut 图片信息
	  * @return 保存结果(true或false)
	  */
	 public static boolean save(ImgCut imgCut){
		 boolean result = true;
		 try {
			 String imgType = imgCut.getImgContentType();
			 String tarPath = imgCut.getTarImgPath();
			 String srcPath = imgCut.getSrcImgPath();
			 int startX = imgCut.getX1().intValue();
			 int startY = imgCut.getY1().intValue();
			 int width = imgCut.getWidth().intValue();
			 int height = imgCut.getHeight().intValue();
			 /**裁剪*/
			 BufferedImage subImg = cut(srcPath, startX, startY, width, height);
			 if(subImg != null){
				 /**压缩图片为指定尺寸*/
				 if(subImg.getWidth() != width || subImg.getHeight() != height){
					 BufferedImage tempImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
					 tempImg.getGraphics().drawImage(subImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0,null);
					 ImageIO.write(tempImg, imgType, new File(tarPath));
				 }else{
					 ImageIO.write(subImg,imgType,new File(tarPath));
			 	 } 
			 }
			         
		 } catch (IOException e) {
			 log.info("error:" + e.getMessage());
			 result = false;
		 }    
		 return result;
	 } 

}
