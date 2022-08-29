package com.xulei.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.system.MenuApi;
import com.xulei.cn.entities.system.MenuApis;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.MenuApiService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class MenuApiServiceImpl extends BaseService implements MenuApiService {
    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    @Override
    public int addMenuApi(MenuApi menu) {
        menu.setId(idWorker.nextId()+"");
        return daoSupport.save("MenuApiMapper.addMenuApi",menu);
    }

    @Override
    public MenuApi getMenuApi(String id) {
        return (MenuApi) daoSupport.findForObject("MenuApiMapper.getMenuApi",id);
    }

    @Override
    public PageInfo<MenuApi> findAll(Map map) {
        Map pageParms = this.getPageParms(map);
        List<MenuApi> forList = (List<MenuApi>)daoSupport.findForList("MenuApiMapper.findAll", pageParms);
        return new PageInfo<>(forList);
    }

    @Override
    public int editMenuApi(MenuApi menu) {
        MenuApi menuApiDb = this.getMenuApi(menu.getId());
        if(menuApiDb==null)
                return -1;
        menuApiDb.setName(menu.getName());
        menuApiDb.setUrl(menu.getUrl());
        menuApiDb.setRequestMethod(menu.getRequestMethod());
        return daoSupport.update("MenuApiMapper.editMenuApi",menu);
    }

    @Override
    public int deleteMenuApi(String id) {
        return daoSupport.delete("MenuApiMapper.deleteMenuApi",id);
    }

    @Override
    public List<MenuApis> selectAllMenuApis(String roleId) {
        List<MenuApi> list=(List<MenuApi>) daoSupport.findForList("MenuApiMapper.selectAllMenuApis",roleId);
        List<MenuApis> lists=new ArrayList<>();
        //把菜单id作为map
        Map<String,MenuApis> map=new HashMap();
        list.forEach(menuApi -> {
            if(!map.containsKey(menuApi.getMenuId())){
                MenuApis menuApis=new MenuApis();
                menuApis.setMenuName(menuApi.getMenuName());
                menuApis.getMenuApiList().add(menuApi);
                map.put(menuApi.getMenuId(),menuApis);
            }else{
                MenuApis menuApis=map.get(menuApi.getMenuId());
                menuApis.getMenuApiList().add(menuApi);
            }
        });
        Iterator<Map.Entry<String, MenuApis>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, MenuApis> e = iterator.next();
            lists.add(e.getValue());
        }
        return lists;
    }
}
