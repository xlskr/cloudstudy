package com.xulei.cn.entities.system;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuApis {

    private String menuName;

    private List<MenuApi> menuApiList=new ArrayList<>();
}
