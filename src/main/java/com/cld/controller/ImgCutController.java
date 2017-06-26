package com.cld.controller;

import com.alibaba.fastjson.JSONObject;
import com.cld.comp.JSONResponse;
import com.cld.comp.TransactionStatus;
import com.cld.services.BaCompchanFileService;
import com.cld.utils.ImgCut;
import com.cld.utils.ImgUtils;
import com.cld.utils.ResponseMes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dong on 2017/6/19.
 */
@Controller
public class ImgCutController {

    private static Log log = LogFactory.getLog(ImgCutController.class);

    @Autowired
    private BaCompchanFileService baCompchanFileService;

    /**
     *
     * @author Dongdm
     * @createTime 2015年4月7日 下午11:19:53
     * @desc   图像剪切上传
     * @return
     */
    @RequestMapping("/imgCutUpload")
    @ResponseBody
    public String imgCutUpload(@Valid ImgCut imgCut, BindingResult bindingResult, HttpServletRequest request){
        log.info("图片剪切上传开始...");
        String responseStr = "";
        if(bindingResult.hasErrors()){
            return responseStr;
        }else{
            //裁剪图片
            String rootPath = request.getServletContext().getRealPath("/");
            String srcPath = rootPath + imgCut.getSrcImgPath();
            imgCut.setSrcImgPath(srcPath);
            boolean flag = ImgUtils.save(imgCut);
            if(flag){
                //上传图片到OSS
                String realPath =  imgCut.getTarImgPath();
                /*
                File tempFile = new File(realPath);
                Map<String,Object> fileMap = ossFileService.uploadAndGetFromOSS(tempFile,
                        aliyun_oss_img_open_bucket, aliyun_oss_img_open_folder);
                */
                HttpSession session = request.getSession();
                String chanDataId = (String)session.getAttribute("chanDataId");
                chanDataId = (chanDataId == null ? "1" : chanDataId);
                File tempFile = new File(imgCut.getTarImgPath());
                String path = imgCut.getTarImgPath().replace(rootPath, "");
                int id = baCompchanFileService.insertSelective(path, imgCut.getSrcImgName(),chanDataId);
                Map<String,Object> fileMap = new HashMap<String,Object>();
                fileMap.put("fileName", "");
                fileMap.put("filePath", path);
                fileMap.put("id", id);
                //删除图像
                realPath = imgCut.getSrcImgPath();
                File srcFile = new File(realPath);
                srcFile.deleteOnExit();

                if(fileMap != null){
                    //图像剪切上传成功
                    JSONResponse jsonResponse = new JSONResponse();
                    //响应状态
                    TransactionStatus trans = new TransactionStatus();
                    trans.setReplyCode("0000");
                    trans.setReplyText("error." + trans.getReplyCode());
                    jsonResponse.setTrans(trans);
                    //文件地址列表
                    jsonResponse.setData(fileMap);
                    responseStr = JSONObject.toJSONString(jsonResponse);
                }else{
                    //文件上传到远端服务器失败
                    responseStr = ResponseMes.getUploadErrorResponseMes(null);
                }
            }else{
                //裁剪图片失败
                String realPath = rootPath + "/" + imgCut.getTarImgPath();
                File tempFile = new File(realPath);
                tempFile.deleteOnExit();
                //文件上传到远端服务器失败
                responseStr = ResponseMes.getUploadErrorResponseMes(null);
            }
        }
        log.info("图片剪切上传结束...");
        return responseStr;
    }

}
