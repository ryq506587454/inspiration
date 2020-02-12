package com.ryq.inspiration.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ryq.inspiration.pojo.User;
import com.ryq.inspiration.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired(required = false)
    private UserServiceImp userServiceImp;
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody JSONObject data, HttpSession session){
        User user = JSON.toJavaObject(data,User.class);
        User record = userServiceImp.login(user);
        if (record == null){
            return "0";
        }else{
            session.setAttribute("userInfo",record);
            return "1";
        }

    }
}
