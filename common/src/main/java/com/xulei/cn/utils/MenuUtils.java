package com.xulei.cn.utils;

import com.xulei.cn.entities.system.Menu;
import com.xulei.cn.entities.system.MenuLevel;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuUtils {


    public static List<MenuLevel> getChildren(String id,List<Menu> menuList){

        List<MenuLevel> list=new ArrayList<>();
        for(Menu menu:menuList){
            if("0".equals(id)&&StringUtils.isEmpty(menu.getPid())){
                list.add(addMenuLevel(menu,menuList));
            }else {
                if(id.equals(menu.getPid())){
                    list.add(addMenuLevel(menu,menuList));
                }
            }
        }
        Collections.sort(list);
        return list;
    }

    private static MenuLevel addMenuLevel(Menu menu,List<Menu> menuList) {
        MenuLevel m=new MenuLevel();
        m.setCode(menu.getCode());
        m.setId(menu.getId());
        m.setMenu_order(menu.getMenu_order());
        m.setDescription(menu.getDescription());
        m.setName(menu.getName());
        m.setIcon(menu.getIcon());
//        m.setMenuApi(MenuApiSelectUtils.selectApi(menu.getId()));
        m.setChidrens(getChildren(menu.getId(),menuList));
        return m;
    }


}
