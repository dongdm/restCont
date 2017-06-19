package com.cld.controller;

import com.cld.bean.BaChannel;
import com.cld.services.BaChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by szb on 2017/4/14.
 */
@Controller
public class BaChannelController {

    @Autowired
    private BaChannelService baChannelService;

    @RequestMapping("/tree")
    public String tree(Model modeal){
        return"/baseTree";
    }

    @RequestMapping("/addTree")
    public String addTree( @ModelAttribute BaChannel baChannel){
        baChannelService.add(baChannel);
        return "redirect:/tree";
    }

    @RequestMapping("/mergeTree")
    public String mergeTree( @ModelAttribute BaChannel baChannel){
        baChannelService.merge(baChannel);
        return "redirect:/tree";
    }


    @RequestMapping(value="/treeList",method = RequestMethod.POST)
    @ResponseBody
    public  ArrayList<Map<String,Object>> treeList(){
        BaChannel baChannel = new BaChannel();
        baChannel.setType("book");
        List<BaChannel> list = baChannelService.searchAll(baChannel);
        ArrayList<Map<String,Object>> stringList = new ArrayList<Map<String, Object>>();
        for (int i = 0 ;i<list.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",list.get(i).getId());
            map.put("pId",list.get(i).getParentid());
            map.put("name",list.get(i).getChannelname());
            map.put("link",list.get(i).getUrl());
            map.put("mark",list.get(i).getDescription()==null?"":list.get(i).getDescription());
            stringList.add(map);
        }
        return stringList;
    }

}
