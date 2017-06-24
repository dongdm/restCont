package com.cld.controller;

import com.alibaba.fastjson.JSONObject;
import com.cld.comp.JSONResponse;
import com.cld.comp.TransactionStatus;
import com.cld.services.BaCompchanFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dong on 2017/6/24.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private BaCompchanFileService baCompchanFileService;

    @RequestMapping("/del")
    @ResponseBody
    public String del(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        baCompchanFileService.deleteByPrimaryKey(id);
        JSONResponse jsonResponse = new JSONResponse();
        //响应状态
        TransactionStatus trans = new TransactionStatus();
        trans.setReplyCode("0000");
        trans.setReplyText("error." + trans.getReplyCode());
        jsonResponse.setTrans(trans);
        //文件地址列表
        return JSONObject.toJSONString(jsonResponse);
    }

}
