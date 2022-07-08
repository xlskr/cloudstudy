package com.xulei.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.system.Role;
import com.xulei.cn.entities.target.Target;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.TargetService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TargetServiceImpl extends BaseService implements TargetService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;


    @Override
    public int add(Target target) {
        target.setId(idWorker.nextId()+"");
        target.setCreateTime(new Date());
        return daoSupport.save("TargetMapper.add",target);
    }

    @Override
    public int edit(Target target) {
        Target targetDb = getDeatilById(target.getId());
        if (targetDb==null)
            return -1;
        targetDb.setType(target.getType());
        targetDb.setName(target.getName());
        return daoSupport.update("TargetMapper.edit",target);
    }

    @Override
    public Target getDeatilById(String id) {
        return (Target) daoSupport.findForObject("TargetMapper.getDeatilById",id);
    }

    @Override
    public PageInfo findAll(Map map) {
        Map pageParms = this.getPageParms(map);
        List<Target> targets = (List<Target>) daoSupport.findForList("TargetMapper.findAll", map);
        PageInfo<Target> pageInfo = new PageInfo<Target>(targets);
        return pageInfo;
    }

    @Override
    public int delete(String id) {
        return daoSupport.delete("TargetMapper.delete",id);
    }

    @Override
    public List<Target> selectCheckedTargets(String roleId) {
        Map map=new HashMap();
        map.put("roleId",roleId);
        return (List<Target>) daoSupport.findForList("TargetMapper.findAll", map);
    }

    @Override
    public List<Target> selectUnCheckedTargets(String hotelId) {
        return (List<Target>) daoSupport.findForList("TargetMapper.selectUnCheckedTargets", hotelId);
    }

    @Override
    public List<Target> selectCheckedTargetsByShopsId(String shopsId) {
        return (List<Target>) daoSupport.findForList("TargetMapper.selectCheckedTargetsByShopsId", shopsId);
    }
}
