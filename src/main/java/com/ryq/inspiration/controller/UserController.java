package com.ryq.inspiration.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ryq.inspiration.pojo.User;
import com.ryq.inspiration.serviceImp.UserServiceImp;
import com.ryq.inspiration.util.RedisUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Api(value = "用户", description = "用户的相关操作", protocols="https,http")
@Controller
@RequestMapping("/users")
public class UserController {
    @Value("${com.ryq.UPLOADED_FOLDER}")
    private String defaultPath ;
    @Autowired
    @Qualifier("userService")
    private UserServiceImp userServiceImp;
    @Autowired
    private RedisUtil redisUtil;


    @ApiOperation(value = "登录",notes = "实现用户的登陆功能")
    @ResponseBody
    @PostMapping("/login")
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
    @ApiOperation(value = "上传", notes = "实现文件上传功能", produces = "multipart/form-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "UPP", value = "上传文件",required = true, dataType = "MultipartFile",paramType = "form"),
    })
    @ApiResponses({
            @ApiResponse(code = 100,message = "成功"),
            @ApiResponse(code = 101,message = "失败"),
    })
    @ResponseBody
    @PostMapping(value = "/upload")
    public String upload(@RequestParam(value = "UPP") MultipartFile file,HttpSession session) {
        String msg;
        User user = (User) session.getAttribute("userInfo");
        if (file.isEmpty()){
            msg = "上传失败,请检查文件是否存在";
        }
        String path = session.getServletContext().getRealPath("img/"+user.getId()+"/");
        msg = userServiceImp.upLoadUserImage(file, path, user);
        session.setAttribute("userInfo",userServiceImp.login(user));
        return msg;
    }
    @ApiOperation(value = "添加用户名称信息", notes = "以userId-userName形式经用户的名称信息添加到缓存中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的ID",required = true),
            @ApiImplicitParam(name = "userName", value = "用户的姓名",required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 100, message = "成功"),
            @ApiResponse(code = 101, message = "失败"),
    })
    @PostMapping(value = "/{id}")
    public String addUserName(@PathVariable(value = "id") String id,
                               @RequestParam(value = "userName",required = false) String userName){
        redisUtil.set("user-"+id, userName);
        return "index";
    }
    @ApiOperation(value = "查询用户名称", notes = "根据用户ID查询用户的名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "用户的ID",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 100, message = "成功"),
            @ApiResponse(code = 101, message = "失败"),
    })
    @GetMapping(value = "/{key}")
    @ResponseBody
    public String getUserName(@PathVariable(name = "key") String key){
        return JSON.toJSONString(redisUtil.get("user-"+key),true);
    }


}
