package com.cld.controller;

import com.cld.bean.BaChannel;
import com.cld.bean.User;
import com.cld.services.BaChannelService;
import com.cld.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dong on 2017/4/13.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BaChannelService baChannelService;

    @RequestMapping(value = "hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "getUser")
    public User getUser(String name){
        User user = new User();
        user.setName(name);
        user.setSex("man");
        return user;
    }

    @RequestMapping(value = "findAll")
    public List<User> findAll(){
        return userService.findAll();
    }
    @RequestMapping(value = "findById")
    public List<BaChannel> search(){
        BaChannel baChannel = new BaChannel();
        baChannel.setType("book");
        return baChannelService.searchAll(baChannel);
    }

    @RequestMapping("/searchByid")
    public  BaChannel  searchById(@RequestParam int id){
        BaChannel baChannel= baChannelService.findById(id);
        return baChannel;
    }
}
