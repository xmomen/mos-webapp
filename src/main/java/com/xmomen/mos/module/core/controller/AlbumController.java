package com.xmomen.mos.module.core.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.NotFoundResourcesException;
import com.xmomen.mos.module.core.entity.TEmployee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AlbumController {

	@Autowired
	MybatisDao mybatisDao;

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	@ResponseBody
	public Page getAllAlbums() {
		Subject currentUser = SecurityUtils.getSubject();
		if(false){
			throw new NotFoundResourcesException("未找到需操作的资源");
		}
		return mybatisDao.selectPageByModel(new TEmployee(), 10, 1);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public Subject user() {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser;
	}
}
