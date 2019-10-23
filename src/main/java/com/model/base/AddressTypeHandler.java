package com.model.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.model.entity.Address;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author lty
 * @Date 2019/8/29 9:24
 */
public class AddressTypeHandler extends BaseTypeHandler<Address> {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * address 对象就是需要自定义转换的对象，转换成自己想要的存在ps的i位置上
     * 从实体 -> 数据库
     *
     * @param ps
     * @param i
     * @param address
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Address address, JdbcType jdbcType) throws SQLException {
        if(address == null){
            logger.info("address为空。。");
            return ;
        }

        String addressJson = JSON.toJSONString(address);

        ps.setString(i, addressJson);
    }

    /**
     * 从结果集中取出 映射到实体上 (下边的3个都是)
     * 从数据库 -> 实体
     *
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public Address getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);

        Address address = null;

        try {
            address = JSONObject.parseObject(string, Address.class);
        }catch (Exception e){
            logger.info("转换失败--{}", string);
        }

        return address;
    }

    @Override
    public Address getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);

        Address address = null;

        try {
            address = JSONObject.parseObject(string, Address.class);
        }catch (Exception e){
            logger.info("转换失败--{}", string);
        }

        return address;
    }

    @Override
    public Address getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        Address address = null;

        try {
            address = JSONObject.parseObject(string, Address.class);
        }catch (Exception e){
            logger.info("转换失败--{}", string);
        }

        return address;
    }
}
