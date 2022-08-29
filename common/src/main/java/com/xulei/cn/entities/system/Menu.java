package com.xulei.cn.entities.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable {

    private String id;
    private String name;
    private String code;
    private String description;
    //序号
    private Integer menu_order;
    private String pid;
    //图标
    private String icon;
    private String createTime;
}
