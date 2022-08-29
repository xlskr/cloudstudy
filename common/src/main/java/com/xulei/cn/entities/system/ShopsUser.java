package com.xulei.cn.entities.system;

import lombok.Data;

import java.util.Date;

@Data
public class ShopsUser {
    private String id;
    private String name;
    private String phone;
    private String hotelId;

    private Integer isSuper;

    private Date createTime;

    private Integer sex;

    private Integer isQuit;

    private String userNum;

    private String aiNum;

    private String remark;

    private String shopsId;
    private String shopsName;

    private String roleId;
    private String roleName;

}
