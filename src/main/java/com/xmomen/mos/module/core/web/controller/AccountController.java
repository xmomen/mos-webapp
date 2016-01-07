package com.xmomen.mos.module.core.web.controller;

import com.xmomen.mos.module.core.web.controller.dto.AccountSettingDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
public class AccountController {

    @RequestMapping(value = "/account/setting", method = RequestMethod.GET)
    public AccountSettingDto accountSetting(){
        AccountSettingDto accountSettingDto = new AccountSettingDto();
        Subject subject = SecurityUtils.getSubject();
        if(subject == null){
            return null;
        }
        accountSettingDto.setUsername(subject.getPrincipal().toString());
        List roles = new ArrayList<AccountSettingDto.Role>();
        Set set = subject.getPrincipals().getRealmNames();
        //roles.add(role);
        //accountSettingDto.setRoles(roles);
        return accountSettingDto;
    }

}
