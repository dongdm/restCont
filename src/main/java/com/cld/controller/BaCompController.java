package com.cld.controller;

import com.cld.bean.BaComp;
import com.cld.services.BaCompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by szb on 2017/6/20.
 */
@Controller
public class BaCompController {

    @Autowired
    private BaCompService baCompService;

    @RequestMapping(value = "/addComp",method = RequestMethod.GET)
    public String addComp(){
        return "company";
    }

    @RequestMapping(value = "/addComp",method = RequestMethod.POST)
    @ResponseBody
    public String addComp(@ModelAttribute BaComp baComp){
        baCompService.add(baComp);
        return "ok";
    }

    @RequestMapping(value = "/complist")
    public String compList(Model modeal){
        BaComp baComp = new BaComp();
       List<BaComp> baCompList = baCompService.find(baComp);
       modeal.addAttribute("baCompList",baCompList);
        return "complist";
    }
}
