package com.xulei.cn.entities.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

@Data
public class MenuLevel implements Comparable<MenuLevel>, Serializable {

    private String id;
    private String name;
    private String code;
    private String description;
    //序号
    private Integer menu_order;
    private String pid;
    private List<MenuLevel> chidrens;


    @Override
    public int compareTo(MenuLevel o) {
        return this.getMenu_order()-o.getMenu_order();
    }
}
