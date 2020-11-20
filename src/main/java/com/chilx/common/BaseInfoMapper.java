package com.chilx.common;//package com.gpos.dao;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author chilx
 * @date 2020年11月03日
 */
public interface BaseInfoMapper<T> extends Mapper<T> ,InsertListMapper<T>,IdsMapper<T>,ConditionMapper<T>{
    // 特别注意，该接口不能被扫描到，否则会出错
}