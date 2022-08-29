package com.xulei.cn.entities.wechath5;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {

    private String id;
    private String nickName;
    private Integer sex;
    private String province;
    private String city;
    private String phone;
    private String openId;
    private String headImage;
}
