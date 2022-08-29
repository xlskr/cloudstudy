package com.xulei.cn.entities.system;

import lombok.Data;

@Data
public class MenuApi {

    private String id;
    private String menuId;
    //请求方式
    private String requestMethod;
    //api路径
    private String url;
    private String name;

    private String menuName;
    private Integer isChecked;

}
