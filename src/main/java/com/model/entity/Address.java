package com.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lty
 * @Date 2019/8/28 21:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String province;

    private String city;

    private String name;
}
