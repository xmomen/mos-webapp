package com.xmomen.mos.module.account.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.mos.module.account.entity.SysRoles;
import com.xmomen.mos.module.account.entity.SysRolesExample;
import org.apache.ibatis.annotations.Param;

public interface SysRolesMapper extends MybatisMapper {
    int countByExample(SysRolesExample example);

    int deleteByExample(SysRolesExample example);

    int insertSelective(SysRoles record);

    int updateByExampleSelective(@Param("record") SysRoles record, @Param("example") SysRolesExample example);
}