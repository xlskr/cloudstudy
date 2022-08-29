package com.xulei.cn.service.impl;

import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.system.Menu;
import com.xulei.cn.service.MenuService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    @Override
    public int addMenu(Menu menu) {
        menu.setId(idWorker.nextId()+"");
        if(StringUtils.isEmpty(menu.getMenu_order())){
            menu.setMenu_order(0);
        }
        //去重以后做
        return daoSupport.save("MenuMapper.addMenu",menu);
    }

    @Override
    public Menu getMenu(String id) {
        return (Menu) daoSupport.findForObject("MenuMapper.getgetMenuDetail",id);
    }

    @Override
    public List<Menu> findAll(Map map) {
        List<Menu> menus= (List<Menu>) daoSupport.findForList("MenuMapper.findAll",map);
        return menus;
    }

    @Override
    public int editMenu(Menu menu) {
        Menu menuDb = this.getMenu(menu.getId());
        if(menuDb!=null) {
            menuDb.setMenu_order(menu.getMenu_order());
            menuDb.setCode(menu.getCode());
            menuDb.setDescription(menu.getDescription());
            menuDb.setName(menu.getName());
            if(StringUtils.isEmpty(menu.getMenu_order())){
                menuDb.setMenu_order(0);
            }
            return daoSupport.update("MenuMapper.editMenu", menuDb);
        }
        return -1;
    }

    @Override
    public int deleteMenu(String id) {
        return daoSupport.delete("MenuMapper.deleteMenu",id);
    }

    @Override
    public List<Menu> selectCheckedMenus(String roleId) {
        List<Menu> menus= (List<Menu>) daoSupport.findForList("MenuMapper.selectCheckedMenus",roleId);
        return menus;
    }

}
