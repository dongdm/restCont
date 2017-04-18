package com.cld.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cld.bean.BaChannel;
import com.cld.services.BaChannelService;
import org.codehaus.groovy.runtime.metaclass.ConcurrentReaderHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by szb on 2017/4/14.
 */
@Controller
public class TemplateController {

    @Autowired
    private BaChannelService baChannelService;
    /**

     * 返回html模板.

     */

    @RequestMapping("/helloHtml")
    public String helloHtml(Map<String,Object> map){
        map.put("hello","HELLO WORD!!!");
        return"/hello";
    }

    @RequestMapping("/tree1")
    public String tree(Model modeal){
        BaChannel baChannel = new BaChannel();
        baChannel.setType("book");
        List<BaChannel> list = baChannelService.searchAll(baChannel);
        ArrayList<Map<String,Object>> stringList = new ArrayList<Map<String, Object>>();
        for (int i = 0 ;i<list.size();i++){
            Map<String,Object> map = new ConcurrentReaderHashMap();
            map.put("id",list.get(i).getId());
            map.put("pId",list.get(i).getParentid());
            map.put("name",list.get(i).getChannelname());
            stringList.add(map);
        }
        modeal.addAttribute("list", JSONArray.toJSONStringWithDateFormat(stringList, JSON.VERSION));
       // map.put("list", JSONArray.toJSONStringWithDateFormat(list, JSON.VERSION));
        return"/baseTree";
    }
}
