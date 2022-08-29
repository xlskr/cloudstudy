package com.xulei.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.prizesquiz.PrizeChild;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.PrizeChildService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PrizeChidServiceImpl implements PrizeChildService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    @Override
    public int add(PrizeChild prizeChild) {
        prizeChild.setId(idWorker.nextId()+"");
        if(this.checkRepatName(prizeChild)<0)
            return -2;
        return daoSupport.save("PrizeChildMapper.add",prizeChild);
    }

    @Override
    public int edit(PrizeChild prizeChild) {
        if(this.checkRepatName(prizeChild)<0)
            return -2;
        PrizeChild prizeChildDb = this.getDeatilById(prizeChild.getId());
        if(prizeChildDb==null)
            return -1;
        prizeChildDb.setName(prizeChild.getName());
        return daoSupport.update("PrizeChildMapper.edit",prizeChildDb);
    }

    @Override
    public PrizeChild getDeatilById(String id) {
        return (PrizeChild) daoSupport.findForObject("PrizeChildMapper.getDeatilById",id);
    }

    @Override
    public List<PrizeChild> findAll(Map map) {
//        Map pageParms = this.getPageParms(map);
//        PageInfo<PrizeChild> pageInfo=new PageInfo((List<PrizeChild>) daoSupport.findForList("PrizeChildMapper.findAll",pageParms));
        return (List<PrizeChild>) daoSupport.findForList("PrizeChildMapper.findAll",map);
    }

    @Override
    public int delete(String id) {
        return daoSupport.delete("PrizeChildMapper.delete",id);
    }

    @Override
    public int checkRepatName(PrizeChild prizeChild) {
        Object forObject = daoSupport.findForObject("PrizeChildMapper.checkRepatName", prizeChild);
        return forObject==null?1:-1;
    }
}
