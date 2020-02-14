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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public Utillist upload(@RequestParam(value = "file") MultipartFile file) {
        System.out.println(1234);
        if (file.isEmpty()) {
            String msg = "上传失败，请检查文件是否存在";
            return Utillist.CreatUtillist(msg,null,101);
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            // UPLOADED_FOLDER 文件本地存储地址
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            String msg = "You successfully uploaded '" + file.getOriginalFilename() + "'";
            return Utillist.CreatUtillist(msg,null,102);
        } catch (IOException e) {
            return Utillist.CreatUtillist(e.getMessage(),null,103);
        }

    }
//    @RequestMapping("/upload")
//    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest,HttpSession httpSession) {
//        System.out.println(1234454);
//        if (file.isEmpty()){
//            httpServletRequest.setAttribute("message","uploadFail,please check" +
//                    "if the file exist");
//            return "uploadStatus";
//        }
//        try {
//            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//            User userInfo = (User) httpSession.getAttribute("userInfo");
//            // UPLOADED_FOLDER 文件本地存储地址
//            Path path = Paths.get(UPLOADED_FOLDER + userInfo.getId() + file.getOriginalFilename());
//            Files.write(path, bytes);
//            httpServletRequest.setAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "uploadStatus";
//    }
}
