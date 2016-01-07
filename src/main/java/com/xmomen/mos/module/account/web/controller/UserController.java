package com.xmomen.mos.module.account.web.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.mos.module.account.entity.SysUsers;
import com.xmomen.mos.module.account.entity.User;
import com.xmomen.mos.module.account.service.UserService;
import com.xmomen.mos.module.account.web.controller.dto.CreateUser;
import com.xmomen.mos.module.core.web.controller.dto.AccountSettingDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增用户
     * @param createUser
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public SysUsers createUser(@RequestBody @Valid CreateUser createUser, BindingResult bindingResult){
        User user = new User();
        user.setUsername(createUser.getUsername());
        user.setPassword(createUser.getPassword());
        user.setLocked(createUser.getLocked());
        return userService.createUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public void createUser(@RequestParam(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(SysUsers.class, id);
    }

}
