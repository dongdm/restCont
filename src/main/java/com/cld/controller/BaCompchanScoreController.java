package com.cld.controller;

import com.alibaba.fastjson.JSONObject;
import com.cld.bean.BaCompChanAuto;
import com.cld.bean.BaCompchanScore;
import com.cld.comp.JSONResponse;
import com.cld.comp.TransactionStatus;
import com.cld.services.BaCompChanAutoService;
import com.cld.services.BaCompchanScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dong on 2017/6/26.
 */
@Controller
@RequestMapping("/score")
public class BaCompchanScoreController {

    @Autowired
    private BaCompChanAutoService baCompChanAutoService;

    @Autowired
    private BaCompchanScoreService baCompchanScoreService;

    @RequestMapping(value = {"/", ""})
    public String score(Model model, HttpServletRequest request){
        String compId = request.getParameter("compId");
        compId = "13";
        model.addAttribute("compId", compId);
        return "score";
    }

    /**
     * 根据公司ID获取公司节点数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/chanData")
    @ResponseBody
    public ArrayList<Map<String,Object>> chanData(HttpServletRequest request, HttpServletResponse response){
        String compId = request.getParameter("compId");
        List<BaCompChanAuto> list = baCompChanAutoService.queryByCompId(compId);
        ArrayList<Map<String,Object>> stringList = new ArrayList<Map<String, Object>>();
        for (int i = 0 ;i<list.size();i++){
            BaCompChanAuto baCompChanAuto = list.get(i);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",baCompChanAuto.getBaChannel().getId());
            map.put("pId",baCompChanAuto.getBaChannel().getParentid());
            map.put("name",baCompChanAuto.getBaChannel().getChannelname());
            map.put("link",baCompChanAuto.getBaChannel().getUrl());
            map.put("pkId",baCompChanAuto.getId());
            stringList.add(map);
        }
        return stringList;
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response){
        String compChanId = request.getParameter("compChanId");
        String score = request.getParameter("score");
        String desc = request.getParameter("desc");
        BaCompchanScore record = baCompchanScoreService.insertSelective(compChanId, score, desc);
        //响应状态
        JSONResponse jsonResponse = new JSONResponse();
        TransactionStatus trans = new TransactionStatus();
        trans.setReplyCode("0000");
        trans.setReplyText("error." + trans.getReplyCode());
        jsonResponse.setTrans(trans);
        jsonResponse.setData(record);
        String responseStr = JSONObject.toJSONString(jsonResponse);
        return responseStr;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String score = request.getParameter("score");
        String desc = request.getParameter("desc");
        BaCompchanScore record = baCompchanScoreService.updateByPrimaryKeySelective(id, score, desc);
        //响应状态
        JSONResponse jsonResponse = new JSONResponse();
        TransactionStatus trans = new TransactionStatus();
        trans.setReplyCode("0000");
        trans.setReplyText("error." + trans.getReplyCode());
        jsonResponse.setTrans(trans);
        jsonResponse.setData(record);
        String responseStr = JSONObject.toJSONString(jsonResponse);
        return responseStr;
    }

    @RequestMapping("/view")
    public String view(Model model, HttpServletRequest request){
        String compId = request.getParameter("compId");
        compId = "13";
        model.addAttribute("compId", compId);
        return "score";
    }


}
