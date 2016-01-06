package com.xmomen.mos.module.core.controller;

import com.github.zhangkaitao.shiro.chapter12.entity.User;
import com.github.zhangkaitao.shiro.chapter12.service.UserService;
import com.xmomen.mos.module.core.controller.dto.RegisterDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Jeng on 2016/1/5.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "redirect:/src/index.html";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Model model){
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        Subject subject = SecurityUtils.getSubject();
        if(SecurityUtils.getSubject().isAuthenticated()){
            return "redirect:/views/src/index.html";
        }
        return "login";
    }

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register")
    public String register(@ModelAttribute @Valid RegisterDto registerDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }else{
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setPassword(registerDto.getPassword());
            user.setSalt(UUID.randomUUID().toString().toUpperCase());
            user = userService.createUser(user);
            if(user.getId() != null && user.getId() > 0 ){
                return "login";
            }else{
                return "register";
            }
        }
    }
}
