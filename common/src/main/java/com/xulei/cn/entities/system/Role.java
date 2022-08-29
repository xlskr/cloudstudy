package com.xulei.cn.entities.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Role implements Serializable {

    private String id;
    //角色名称
    private String name;

    private String description;

    //酒店id
    private String clubId;

    private Date createTime;


}
