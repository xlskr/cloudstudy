package com.xulei.cn.entities.hotel;

import lombok.Data;

import java.util.Date;

@Data
public class Hotel {

    private String id;

    private String name;

    //冗余字段  联系人
    private String linkName;

    //冗余字段 联系电话
    private String linkphone;

    private Date createTime;

    private String userId;

    private String roleId;






}
