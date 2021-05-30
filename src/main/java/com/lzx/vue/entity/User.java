package com.lzx.vue.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author: lzx
 * @Data: 2021/05/30/2:13
 * @Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {
    private String id;
    private String username;
    private Integer age;
    private Double salary;
    private String phoneCode;

}
