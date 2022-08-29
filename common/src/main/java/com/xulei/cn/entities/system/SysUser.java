package com.xulei.cn.entities.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {

    private String id;
    private String name;
    private String phone;
//    private String hotelId;

    private Integer isSuper;

    private Date createTime;

    private Integer sex;

    private Integer isQuit;

    private String userNum;

    private String aiNum;

    private String remark;

}
