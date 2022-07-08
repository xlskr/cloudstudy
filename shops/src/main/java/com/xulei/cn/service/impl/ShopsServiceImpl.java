package com.xulei.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.shops.Shops;
import com.xulei.cn.entities.shops.UserAndShops;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.ShopsService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopsServiceImpl extends BaseService implements ShopsService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    @Override
    public int add(Shops shops) {
        shops.setId(idWorker.nextId()+"");
        shops.setCreateTime(new Date());
        shops.setUpdateTime(new Date());
        return daoSupport.save("ShopsMapper.save",shops);
    }

    @Override
    public int edit(Shops shops) {
        Shops shopsDb = this.getDeatilById(shops.getId());
        if(shopsDb==null)
            return -1;
        shopsDb.setUpdateTime(new Date());
        shopsDb.setName(shops.getName());
        return daoSupport.update("ShopsMapper.update",shopsDb);
    }

    @Override
    public Shops getDeatilById(String id) {
        return (Shops) daoSupport.findForObject("ShopsMapper.getDeatilById",id);
    }

    @Override
    public PageInfo<Shops> findAll(Map map) {
        Map pageParms = this.getPageParms(map);
        List<Shops> shops = (List<Shops>) daoSupport.findForList("ShopsMapper.findAll", map);
        return new PageInfo<>(shops);
    }

    @Override
    public int delete(String id) {
        return daoSupport.delete("ShopsMapper.delete",id);
    }

    @Override
    public int assginShopsUser(UserAndShops userAndShops) {
        daoSupport.delete("ShopsMapper.deleteUserAndShops",userAndShops);
        userAndShops.setId(idWorker.nextId()+"");
        //
        return daoSupport.save("ShopsMapper.saveUserAndShops",userAndShops);
    }

    @Override
    public List<Shops> selectCheckedShops(String roleId) {
        return (List<Shops>) daoSupport.findForList("ShopsMapper.selectCheckedShops",roleId);
    }

    @Override
    public List<Shops> selectUnCheckedShops(String hotelId) {
        Map<String,Object> map=new HashMap();
        map.put("hotelId",hotelId);
        return  (List<Shops>) daoSupport.findForList("ShopsMapper.findAll", map);
    }
}
