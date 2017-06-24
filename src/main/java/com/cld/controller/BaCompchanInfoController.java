package com.cld.controller;

import com.alibaba.fastjson.JSONObject;
import com.cld.bean.BaCompchanInfo;
import com.cld.comp.JSONResponse;
import com.cld.comp.TransactionStatus;
import com.cld.services.BaCompchanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dong on 2017/6/23.
 */
@Controller
@RequestMapping("/compChaninfo")
public class BaCompchanInfoController {

    @Autowired
    private BaCompchanInfoService baCompchanDataService;

    @RequestMapping(value = {"/save"})
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response){
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        HttpSession session = request.getSession();
        String chanDataId = (String)session.getAttribute("chanDataId");
        chanDataId = (chanDataId == null ? "1" : chanDataId);
        BaCompchanInfo record = baCompchanDataService.insertSelective(key, value, chanDataId);
        JSONResponse jsonResponse = new JSONResponse();
        //响应状态
        TransactionStatus trans = new TransactionStatus();
        trans.setReplyCode("0000");
        trans.setReplyText("error." + trans.getReplyCode());
        jsonResponse.setTrans(trans);
        //文件地址列表
        jsonResponse.setData(record);
        String responseStr = JSONObject.toJSONString(jsonResponse);
        return responseStr;
    }

    @RequestMapping(value = {"/edit"})
    @ResponseBody
    public String edit(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        BaCompchanInfo record = baCompchanDataService.updateByPrimaryKeySelective(key, value, id);
        JSONResponse jsonResponse = new JSONResponse();
        //响应状态
        TransactionStatus trans = new TransactionStatus();
        trans.setReplyCode("0000");
        trans.setReplyText("error." + trans.getReplyCode());
        jsonResponse.setTrans(trans);
        jsonResponse.setData(record);
        String responseStr = JSONObject.toJSONString(jsonResponse);
        return responseStr;
    }

    @RequestMapping(value = {"/del"})
    @ResponseBody
    public String delete(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        int i = baCompchanDataService.deleteByPrimaryKey(new Integer(id));
        JSONResponse jsonResponse = new JSONResponse();
        //响应状态
        TransactionStatus trans = new TransactionStatus();
        trans.setReplyCode("0000");
        trans.setReplyText("error." + trans.getReplyCode());
        jsonResponse.setTrans(trans);
        String responseStr = JSONObject.toJSONString(jsonResponse);
        return responseStr;
    }


}
