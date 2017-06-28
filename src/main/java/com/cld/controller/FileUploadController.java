/**
 * 
 */
package com.cld.controller;

import com.alibaba.fastjson.JSONObject;
import com.cld.comp.JSONResponse;
import com.cld.comp.TransactionStatus;
import com.cld.services.BaCompchanFileService;
import com.cld.utils.ImgUtils;
import com.cld.utils.ResponseMes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @className FileUploadController.java
 * @author Dongdm
 * @date 2015年2月3日 下午3:31:30
 * @classDesc 文件上传
 */
@Controller
public class FileUploadController {
	
	private static Log log = LogFactory.getLog(FileUploadController.class);
	
	@Autowired
	private MessageSource messageSource;

	@Value("${portal.fileSize}")
	private String upFileSize;
	@Value("${portal.uploadPath}")
	private String serverPath;

	@Autowired
	private BaCompchanFileService baCompchanFileService;

	@RequestMapping(value="/uploadFiles", method=RequestMethod.POST)
	@ResponseBody
	public String uploadFiles(@RequestParam MultipartFile[] files , HttpServletRequest request){  
        //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解   
        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解   
        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是，否则参数里的无法获取到所有上传的文件

		long start = System.currentTimeMillis();
		//判断文件立即上传到OSS（1：表示立即上传，0：表示不立即上传）
    	String isUpload2OSS = request.getParameter("flag");
    	//判断上传文件类型(图片或非图片)
    	String fileType = request.getParameter("type");
		String compchanId = request.getParameter("compchanId");
    	if(null == isUpload2OSS || null == fileType || null == compchanId){
			return ResponseMes.getUploadTypeErrorResponseMes(messageSource);
    	}
		JSONResponse jsonResponse = new JSONResponse();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        for(MultipartFile myfile : files){  
            if(myfile.isEmpty()){  
            	log.info("文件未上传");
            }else{  
            	log.info("文件长度: " + myfile.getSize() + ";" + "文件类型: " + myfile.getContentType() + ";" +
            			"文件名称: " + myfile.getName() + ";" + "文件原名: " + myfile.getOriginalFilename());
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中   
            	//上传文件的后缀名
            	String originalFileName = myfile.getOriginalFilename();
            	String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
            	//判断上传文件是否可以上传
        		if(null == upFileSize){
        			log.info("文件上传出错，缺少参数配置" + myfile.getName() );
        			return ResponseMes.getUploadSizeErrorResponseMes(0);
        		}
        		/*
				int rightSize = Integer.parseInt(upFileSize);
	      		int rightSize = 5242880;
            	if(!isExtAllowUpload(fileType,ext)){
					log.info("文件上传出错" + myfile.getName() );
					return ResponseMes.getUploadTypeErrorResponseMes(messageSource);
            	}else if(!isSizeAllowUpload(myfile.getSize(),rightSize)){
            		log.info("文件上传出错" + myfile.getName() );
            		return ResponseMes.getUploadSizeErrorResponseMes(rightSize);
            	}
            	*/
            	//上传文件路径
            	String realPath = request.getServletContext().getRealPath("/");
            	realPath += serverPath;
                File dir = new File(realPath);
                if(!dir.exists()){
                	dir.mkdirs();
                }
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的   
                //FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename())); 
                String uploadFileName = ImgUtils.getFileName(ext);
                File tempFile = new File(realPath + "/" + uploadFileName);
                try {
					myfile.transferTo(tempFile);
					log.info(tempFile.getAbsoluteFile());

	            	/*
	            	//判断文件立即上传到OSS（1：表示立即上传，0：表示不立即上传）
	            	if("1".equals(isUpload2OSS)){
	            		Map<String,Object> fileMap = ossFileService.uploadAndGetFromOSS(tempFile, 
	            				aliyun_oss_img_open_bucket, aliyun_oss_img_open_folder);
	            		if(null != fileMap){
	            			fileMap = generateResult(fileType,tempFile,fileMap);
		            		resultList.add(fileMap);
		            		tempFile.deleteOnExit();
	            		}else{
	            			//文件上传到远端服务器失败
		            		tempFile.deleteOnExit();
							log.info("文件上传出错" + myfile.getName() );
							return ResponseMes.getUploadTypeErrorResponseMes(messageSource);
	            		}
	            	}else{
	            	*/
					Map<String,Object> fileMap = new HashMap<String, Object>();
					fileMap = generateResult(fileType,tempFile,fileMap);
					fileMap.put("filePath", serverPath + "/" + uploadFileName);
					fileMap.put("fileName", originalFileName);
					fileMap.put("fileSize", myfile.getSize());

	            	if(!"image".equals(fileType)){
	            		//文件数据入库
						int id = baCompchanFileService.insertSelective(serverPath + uploadFileName, originalFileName,
								myfile.getSize(), compchanId);
						fileMap.put("id", id);
					}
	            		resultList.add(fileMap);
	            	//}
	            	
				} catch (IllegalStateException e) {
					log.info("文件上传出错" + myfile.getName() + "," + e.getMessage());
					return ResponseMes.getUploadErrorResponseMes(messageSource);
				} catch (IOException e) {
					log.info("文件上传出错" + myfile.getName() + "," + e.getMessage());
					return ResponseMes.getUploadErrorResponseMes(messageSource);
				}
            }  
        }
        //响应状态
        TransactionStatus trans = new TransactionStatus();
		trans.setReplyCode("0000");
		trans.setReplyText("error." + trans.getReplyCode());
		jsonResponse.setTrans(trans);
		//文件地址列表
		jsonResponse.setData(resultList);
		long end = System.currentTimeMillis();
		log.info("上传用时：" + (end - start));
        return JSONObject.toJSONString(jsonResponse);  
	}
	
	
	public String uploadImg2Oss(){
		return "";
	}
	
	
	
	/**
	 * 
	 * @author dongdm
	 * @createTime 2015年7月2日 上午10:15:04
	 * @desc   判断文件类型是否可以上传
	 * @param fileType 文件类型（image 图片 other 代表其他）
	 * @param ext  文件后缀名
	 * @return 
	 */
	private boolean isExtAllowUpload(String fileType,String ext){
		if("image".equals(fileType)){
			return ImgUtils.isAllowed(ext);
		}else{
			return true;
		}
	}
	
	
	/**
	 * 判断文件大小是否可以上传（手动捕获异常）
	 * @author Dongdm
	 * @createTime 2015年7月8日 上午11:19:39
	 * @desc 
	 * @param fileSize
	 * @return
	 */
	private boolean isSizeAllowUpload(long fileSize,long rightSize){
		if(rightSize < fileSize){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @author dongdm
	 * @createTime 2015年7月2日 上午10:26:10
	 * @desc   根据文件类型判断返回的数据
	 * @param fileType  上传文件类型（image 图片 other 其他）
	 * @param tempFile  文件
	 * @param param     上传后的信息
	 * @return
	 * @throws IOException 
	 */
	private Map<String,Object> generateResult(String fileType, File tempFile , Map<String,Object> param) throws IOException{
		if(null == tempFile || null == param){
			return null;
		}
		long fileSize = tempFile.length();
		if("image".equals(fileType)){
			BufferedImage img = ImageIO.read(tempFile);
			param.put("imgWidth", img.getWidth());
			param.put("imgHeight", img.getHeight());
			param.put("imgHeight", img.getHeight());
		}
		param.put("fileSize", fileSize);
		param.put("fileType", fileType);
		return param;
	}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
    public String handleException(Exception ex,HttpServletRequest request) {       
         if (ex instanceof MaxUploadSizeExceededException){  
             return "文件应不大于 "+  getFileKB(((MaxUploadSizeExceededException)ex).getMaxUploadSize());  
         } else{  
            return "上传错误";
        }  
                  
    }
	
	private String getFileKB(long byteFile){  
        if(byteFile==0)  
           return "0KB";  
        long kb=1024;  
        return ""+byteFile/kb+"KB";  
    }  
	

}
