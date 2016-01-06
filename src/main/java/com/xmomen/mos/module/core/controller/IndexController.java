package com.xmomen.mos.module.core.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jeng on 2016/1/5.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "redirect:/views/src/index.html";
    }

    @RequestMapping(value = "/login")
    public String login(){
        if(SecurityUtils.getSubject().isAuthenticated()){
            return "redirect:/views/src/index.html";
        }
        return "login";
    }

    @RequestMapping(value = "/register")
    public String register(){
        if(SecurityUtils.getSubject().isAuthenticated()){
            return "redirect:/views/src/index.html";
        }
        return "register";
    }
}
