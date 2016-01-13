package com.xmomen.mos.module.account.web.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.mos.module.account.entity.SysUsers;
import com.xmomen.mos.module.account.entity.User;
import com.xmomen.mos.module.account.service.UserService;
import com.xmomen.mos.module.account.web.controller.dto.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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

    /**
     * 用户列表
     * @param limit
     * @param offset
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Resource<Map> createUser(@RequestParam(value = "limit") Integer limit,
                           @RequestParam(value = "offset") Integer offset){
        Page page = mybatisDao.selectPageByModel(new SysUsers(), limit, offset);
        Map map = new HashMap();
        map.put("data", page.getResult());
        Map pageInfo = new HashMap();
        pageInfo.put("pageSize", page.getPageSize());
        pageInfo.put("pageNum", page.getPageNum());
        pageInfo.put("startRow", page.getStartRow());
        pageInfo.put("endRow", page.getEndRow());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("total", page.getTotal());
        map.put("pageInfo", pageInfo);
        return new Resource<Map>(map);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public void createUser(@RequestParam(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(SysUsers.class, id);
    }

}
