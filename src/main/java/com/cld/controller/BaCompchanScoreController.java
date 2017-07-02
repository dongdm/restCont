package com.cld.controller;

import com.alibaba.fastjson.JSONObject;
import com.cld.bean.BaComp;
import com.cld.bean.BaCompChanAuto;
import com.cld.bean.BaCompchanScore;
import com.cld.cawl.Goods;
import com.cld.cawl.GoodsBreadthCrawler;
import com.cld.cawl.GoodsDB;
import com.cld.comp.JSONResponse;
import com.cld.comp.TransactionStatus;
import com.cld.services.BaCompChanAutoService;
import com.cld.services.BaCompService;
import com.cld.services.BaCompchanScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private static final Logger LOG = LoggerFactory.getLogger(BaCompchanScoreController.class);

    @Autowired
    private BaCompChanAutoService baCompChanAutoService;

    @Autowired
    private BaCompchanScoreService baCompchanScoreService;

    @RequestMapping(value = {"/", ""})
    public String score(Model model, HttpServletRequest request){
        String compId = request.getParameter("compId");
        compId = null == compId ? "13" : compId;
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

    @Autowired
    private BaCompService baCompService;
    @Qualifier("crawler")
    @Autowired
    private GoodsBreadthCrawler crawler;

    @RequestMapping("/goods")
    public String goods(HttpServletRequest request, Model model){
        String compId = request.getParameter("compId");
        compId = null == compId ? "13" : compId;
        //爬取物品信息
        BaComp baComp = new BaComp();
        try{
            baComp.setId(new Integer(compId));
            baComp = baCompService.search(baComp);
            List<Goods> goodsList = GoodsDB.get(baComp.getProduct());
            if(null == goodsList){
                crawler.jdService(baComp.getProduct());
                goodsList = GoodsDB.get(baComp.getProduct());
            }
            model.addAttribute("goodsList", goodsList);
        }catch (Exception e){
            //爬取信息失败
            LOG.info("爬取信息失败%s", baComp.getProduct());
        }
        return "goods";
    }


}
