package com.ryq.inspiration.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class GoController {
    @RequestMapping(value = "index")
    public String index(){return "index";}
    @RequestMapping(value = "hello")
    public String hello(){return "hello";}
    @RequestMapping(value = "login")
    public String login(){return "login";}
    @RequestMapping(value = "upload")
    public String upload(){return "upload";}


}

