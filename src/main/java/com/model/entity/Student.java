package com.model.entity;

import com.model.base.AddressTypeHandler;
import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 对类的描述
 *
 * @author liutianyang
 * @since 1.0
 */
@Data
@Table(name = "student")
public class Student implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    @ColumnType(typeHandler = AddressTypeHandler.class)
    public Address address;

}
