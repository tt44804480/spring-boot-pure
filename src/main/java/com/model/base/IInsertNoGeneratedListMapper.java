package com.model.base;

import com.model.model.SpecialNoGenerateProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * 拓展通用mapper接口
 *
 * @Author lty
 * @Date 2019/8/28 16:10
 */
public interface IInsertNoGeneratedListMapper<T> {

    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    @InsertProvider(
            type = SpecialNoGenerateProvider.class,
            method = "dynamicSQL"
    )
    int insertList(List<T> list);
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    @UpdateProvider(
            type = SpecialNoGenerateProvider.class,
            method = "dynamicSQL"
    )
    int updateSelectiveList(List<T> list);

}
