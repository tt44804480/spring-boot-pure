package com.model.entity;

import com.model.base.AddressTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author lty
 * @Date 2019/8/28 21:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "my_class")
public class MyClass {

    @Id
    private Integer id;

    private String name;

    private Integer age;

    @ColumnType(typeHandler = AddressTypeHandler.class)
    @Column
    private Address address;

}
