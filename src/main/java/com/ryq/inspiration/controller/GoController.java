package com.ryq.inspiration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoController {
    @RequestMapping(value = "index")
    public String index(){return "index";}
    @RequestMapping(value = "hello")
    public String hello(){return "hello";}
    @RequestMapping(value = "login")
    public String login(){return "login";}

}

