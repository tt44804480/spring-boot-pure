package com.model.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 公共mapper
 *
 * @Author lty
 * @Date 2019/8/26 14:49
 */
public interface BaseMappers <T> extends Mapper<T>, IInsertNoGeneratedListMapper<T> {
}
