package com.model.model;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Iterator;
import java.util.Set;

/**
 * @Author lty
 * @Date 2019/8/28 16:12
 */
public class SpecialNoGenerateProvider extends MapperTemplate {

    public SpecialNoGenerateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String insertList(MappedStatement ms) {
        Class<?> entityClass = super.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnList.iterator();

        while(var5.hasNext()) {
            EntityColumn column = (EntityColumn)var5.next();

            sql.append(column.getColumnHolder("record") + ",");

        }

        sql.append("</trim>");
        sql.append("</foreach>");

        return sql.toString();
    }

    public String updateSelectiveList(MappedStatement ms){
        Class<?> entityClass = super.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append("<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"\" close=\"\" separator=\";\">");
        sql.append(SqlHelper.updateTable(entityClass,super.tableName(entityClass)));
        sql.append(" SET ");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnList.iterator();
        String idWhere  = "";
        while(var5.hasNext()) {
            EntityColumn column = (EntityColumn)var5.next();
            if (!column.isId() && column.isUpdatable()) {

                sql.append(column.getColumn() + " = " + column.getColumnHolder("item") + ",");
            }else{
                idWhere = column.getColumn() + " = " + column.getColumnHolder("item");
            }
        }
        sql.replace(sql.length()-1,sql.length(),"");
        sql.append(" WHERE ");
        sql.append(idWhere);
        sql.append("</foreach>");
        return sql.toString();
    }

}
