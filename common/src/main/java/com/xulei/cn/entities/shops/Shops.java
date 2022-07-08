package com.xulei.cn.entities.shops;

import lombok.Data;

import java.util.Date;

@Data
public class Shops {

    private String id;
    private String address;
    private String name;
    private String hotelId;
    private Date createTime;
    private Date updateTime;


}
