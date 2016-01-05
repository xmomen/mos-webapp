package com.xmomen.mos.module.core.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.mos.module.core.entity.TEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AlbumController {

	@Autowired
	MybatisDao mybatisDao;

	@RequestMapping(value = "/demo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page getAllAlbums() {
		return mybatisDao.selectPageByModel(new TEmployee(), 10, 1);
		
	}
}
