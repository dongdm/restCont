package com.cld.controller;

import com.alibaba.fastjson.JSONObject;
import com.cld.bean.BaCompchanData;
import com.cld.comp.JSONResponse;
import com.cld.comp.TransactionStatus;
import com.cld.services.BaCompChanLoadService;
import com.cld.services.BaCompchanDataService;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by dong on 2017/6/23.
 */
@Controller
@RequestMapping("/compChanData")
public class BaCompchanDataController {

    @Autowired
    private BaCompchanDataService baCompchanDataService;
    @Autowired
    private BaCompChanLoadService baCompChanLoadService;

    @RequestMapping(value = {"/", ""})
    public String compchanData(Model model, HttpServletRequest request){
        String compId = request.getParameter("compId");
        compId = null == compId ? "13" : compId;
        String compchanId = request.getParameter("compchanId");
        compchanId = null == compchanId ? "" : compchanId;
        model.addAttribute("compId", compId);
        model.addAttribute("compchanId", compchanId);
        return "compChanData";
    }

    @RequestMapping("/load")
    @ResponseBody
    public String load(HttpServletRequest request, HttpServletResponse response){
        String compchanId = request.getParameter("compchanId");
        compchanId = null == compchanId ? "" : compchanId;
        Map<String, Object> map = baCompChanLoadService.load(compchanId);
        //响应状态
        JSONResponse jsonResponse = new JSONResponse();
        TransactionStatus trans = new TransactionStatus();
        trans.setReplyCode("0000");
        trans.setReplyText("error." + trans.getReplyCode());
        jsonResponse.setTrans(trans);
        jsonResponse.setData(map);
        return JSONObject.toJSONString(jsonResponse);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response){
        String content = request.getParameter("content");
        HttpSession session = request.getSession();
        String compchanId = request.getParameter("compchanId");
        BaCompchanData record = baCompchanDataService.insertSelective(content, compchanId);
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
