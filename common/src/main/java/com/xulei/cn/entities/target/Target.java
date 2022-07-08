package com.xulei.cn.entities.target;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Target implements Serializable {
    private String id;
    private Integer type;
    private String name;
    private Date createTime;
}
