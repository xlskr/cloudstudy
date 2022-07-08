package com.xulei.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.hotel.Hotel;
import com.xulei.cn.entities.system.Role;
import com.xulei.cn.entities.system.SysUser;
import com.xulei.cn.entities.system.UserAndRole;
import com.xulei.cn.entities.target.Target;
import com.xulei.cn.feginclient.system.SystemService;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.HotelService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelServiceImpl extends BaseService implements HotelService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SystemService systemService;


    @Override
    public int addHotel(Hotel hotel) {

        hotel.setId(idWorker.nextId()+"");
        //去重以后做
        hotel.setCreateTime(new Date());
        if(daoSupport.save("HotelMapper.addHotel",hotel)>0){
            SysUser sysUser=new SysUser();
            sysUser.setName(hotel.getLinkName());
            sysUser.setPhone(hotel.getLinkphone());
            sysUser.setIsSuper(0);
            sysUser.setHotelId(hotel.getId());
            //检查phone有无重复
            Result result1 = systemService.addUser(sysUser);
            if (result1.isSuccess()){
                Role r=new Role();
                r.setHotelId(hotel.getId());
                r.setDescription("酒店超级管理员");
                r.setName("酒店超级管理员");
                //保存酒店角色
                Result result = systemService.addRole(r);
                if(result.isSuccess()){
                    //为用户绑定角色
                    UserAndRole userAndRole=new UserAndRole();
                    userAndRole.setRoleId((String) ((LinkedHashMap)result.getData()).get("id"));
                    userAndRole.setUserId((String) ((LinkedHashMap)result1.getData()).get("id"));
                    //远程调用绑定
                    systemService.assignRoles(userAndRole);
                    hotel.setUserId((String) ((LinkedHashMap)result1.getData()).get("id"));
                    hotel.setRoleId((String) ((LinkedHashMap)result.getData()).get("id"));
                    daoSupport.update("HotelMapper.update",hotel);
                }else {
                    return -1;
                }
            }else{
                return -1;
            }
            return 1;
        }
        return -1;
    }

    @Override
    public int editHotel(Hotel hotel) {
        //
        Hotel hoteldb = this.getDeatilById(hotel.getId());
        hoteldb.setName(hotel.getName());
        hoteldb.setLinkName(hotel.getLinkName());
        hoteldb.setLinkphone(hotel.getLinkphone());
        daoSupport.update("HotelMapper.update",hoteldb);
        SysUser sysUser=new SysUser();
        sysUser.setId(hoteldb.getUserId());
        sysUser.setName(hoteldb.getLinkName());
        sysUser.setPhone(hoteldb.getLinkphone());
        systemService.editUser(sysUser);
        return 1;
    }

    @Override
    public Hotel getDeatilById(String id) {
        return (Hotel) daoSupport.findForObject("HotelMapper.getDeatilById",id);
    }

    @Override
    public PageInfo<Hotel> findAll(Map map) {
        Map pageParms = this.getPageParms(map);
        PageInfo<Hotel> pageInfo=new PageInfo((List<Hotel>) daoSupport.findForList("HotelMapper.findAll",pageParms));
        return pageInfo;
    }

    @Override
    public int delete(String id) {
        return daoSupport.delete("HotelMapper.delete",id);
    }
}
