package com.cld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dong on 2017/4/13.
 */
@RestController
public class UserController {

    @RequestMapping(value = "hello")
    public String hello(){
        return "hello";
    }

}
