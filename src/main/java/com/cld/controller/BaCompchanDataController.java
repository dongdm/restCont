package com.cld.controller;

import com.alibaba.fastjson.JSONObject;
import com.cld.bean.BaCompchanData;
import com.cld.comp.JSONResponse;
import com.cld.comp.TransactionStatus;
import com.cld.services.BaCompchanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dong on 2017/6/23.
 */
@Controller
@RequestMapping("/compChanData")
public class BaCompchanDataController {

    @Autowired
    private BaCompchanDataService baCompchanDataService;

    @RequestMapping(value = {"/", ""})
    public String compchanData(){
        return "compChanData";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response){
        String content = request.getParameter("content");
        HttpSession session = request.getSession();
        String chanDataId = (String)session.getAttribute("chanDataId");
        chanDataId = (chanDataId == null ? "1" : chanDataId);
        BaCompchanData record = baCompchanDataService.insertSelective(content, chanDataId);
        //响应状态
        JSONResponse jsonResponse = new JSONResponse();
        TransactionStatus trans = new TransactionStatus();
        trans.setReplyCode("0000");
        trans.setReplyText("error." + trans.getReplyCode());
        jsonResponse.setTrans(trans);
        jsonResponse.setData(record);
        return JSONObject.toJSONString(jsonResponse);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpServletRequest request, HttpServletResponse response){
        String content = request.getParameter("content");
        String id = request.getParameter("id");
        BaCompchanData record = baCompchanDataService.updateByPrimaryKeySelective(content, id);
        //响应状态
        JSONResponse jsonResponse = new JSONResponse();
        TransactionStatus trans = new TransactionStatus();
        trans.setReplyCode("0000");
        trans.setReplyText("error." + trans.getReplyCode());
        jsonResponse.setTrans(trans);
        jsonResponse.setData(record);
        return JSONObject.toJSONString(jsonResponse);
    }

}
