package com.cld.controller;

import com.cld.bean.BaChannelContent;
import com.cld.services.BaChannelContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by szb on 2017/6/20.
 */
@Controller
public class BaChannelContentController {

    @Autowired
    private BaChannelContentService baChannelContentService;

    @RequestMapping("/ctree")
    public String tree(Model modeal){
        return"/baseTree";
    }

    @RequestMapping("/caddTree")
    public String addTree( @ModelAttribute BaChannelContent baChannelContent){
        baChannelContentService.add(baChannelContent);
        return "redirect:/tree";
    }

    @RequestMapping("/cmergeTree")
    public String mergeTree( @ModelAttribute BaChannelContent baChannelContent){
        baChannelContentService.merge(baChannelContent);
        return "redirect:/tree";
    }

    @RequestMapping(value = "/getContent",method = RequestMethod.POST)
    public  BaChannelContent getContentById(@RequestParam Integer id){
        return baChannelContentService.findById(id);
    }

}
