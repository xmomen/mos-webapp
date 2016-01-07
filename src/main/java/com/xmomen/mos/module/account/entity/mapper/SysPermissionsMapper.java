package com.xmomen.mos.module.account.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.mos.module.account.entity.SysPermissions;
import com.xmomen.mos.module.account.entity.SysPermissionsExample;
import org.apache.ibatis.annotations.Param;

public interface SysPermissionsMapper extends MybatisMapper {
    int countByExample(SysPermissionsExample example);

    int deleteByExample(SysPermissionsExample example);

    int insertSelective(SysPermissions record);

    int updateByExampleSelective(@Param("record") SysPermissions record, @Param("example") SysPermissionsExample example);
}