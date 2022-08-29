package com.xulei.cn.entities.wechath5;

import lombok.Data;

import java.util.List;

@Data
public class CustomerVo {
    private String token;

    private List<String> childIds;

    private String phone;

}
