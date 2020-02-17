package com.ryq.inspiration.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ryq.inspiration.pojo.User;
import com.ryq.inspiration.serviceImp.UserServiceImp;
import com.ryq.inspiration.util.Utillist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String UPLOADED_FOLDER = "E://temp//";
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
    @ResponseBody
    @PostMapping(value = "/upload")
    public String upload(@RequestParam(value = "UPP") MultipartFile file,HttpSession session) {
        String msg;
        User user = (User) session.getAttribute("userInfo");
        if (file.isEmpty()){
            msg = "上传失败,请检查文件是否存在";
        }
        String path = UPLOADED_FOLDER+user.getId()+"_"+file.getOriginalFilename();
        msg = userServiceImp.upLoadUserImage(file,path);
        return msg;
    }

}
