package com.cld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dong on 2017/4/14.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    /**
     * 上传图片，并且可以进行裁剪
     * @return
     */
    @RequestMapping(value = "/setAvatar")
    public String setAvatar(){
        return "setting-avatar";
    }

}
