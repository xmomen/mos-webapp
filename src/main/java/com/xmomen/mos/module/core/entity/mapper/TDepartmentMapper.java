package com.xmomen.mos.module.core.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.mos.module.core.entity.TDepartment;
import com.xmomen.mos.module.core.entity.TDepartmentExample;
import org.apache.ibatis.annotations.Param;

public interface TDepartmentMapper extends MybatisMapper {
    int countByExample(TDepartmentExample example);

    int deleteByExample(TDepartmentExample example);

    int insertSelective(TDepartment record);

    int updateByExampleSelective(@Param("record") TDepartment record, @Param("example") TDepartmentExample example);
}