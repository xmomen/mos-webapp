package com.xmomen.mos.module.core.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.mos.module.core.entity.TEmployee;
import com.xmomen.mos.module.core.entity.TEmployeeExample;
import org.apache.ibatis.annotations.Param;

public interface TEmployeeMapper extends MybatisMapper {
    int countByExample(TEmployeeExample example);

    int deleteByExample(TEmployeeExample example);

    int insertSelective(TEmployee record);

    int updateByExampleSelective(@Param("record") TEmployee record, @Param("example") TEmployeeExample example);
}