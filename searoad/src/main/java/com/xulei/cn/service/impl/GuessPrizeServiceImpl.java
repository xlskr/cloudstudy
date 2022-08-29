package com.xulei.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.prizesquiz.GuessInfo;
import com.xulei.cn.entities.prizesquiz.PrizeChild;
import com.xulei.cn.fegin.H5Service;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.GuessPrizeService;
import com.xulei.cn.service.PrizeChildService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GuessPrizeServiceImpl extends BaseService implements GuessPrizeService {
    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;
    
    @Autowired
    private PrizeChildService prizeChildService;

    @Autowired
    private H5Service h5Service;

    @Override
    public int add(GuessInfo guessInfo) {
        guessInfo.setId(idWorker.nextId()+"");
        guessInfo.setCreateTime(new Date());
        return daoSupport.save("GuessPrizeMapper.add",guessInfo);
    }

    @Override
    public int edit(GuessInfo guessInfo) {
        GuessInfo guessInfoDb = this.getDeatilById(guessInfo.getId());
        guessInfoDb.setAwardsName(guessInfo.getAwardsName());
        guessInfoDb.setName(guessInfo.getName());
        guessInfoDb.setNum(guessInfo.getNum());
        return daoSupport.update("GuessPrizeMapper.edit",guessInfo);
    }

    @Override
    public GuessInfo getDeatilById(String id) {
        return (GuessInfo) daoSupport.findForObject("GuessPrizeMapper.getDeatilById",id);
    }

    @Override
    public List<GuessInfo> findAll(Map map) {
//        Map pageParms = this.getPageParms(map);
//        PageInfo<GuessInfo> pageInfo=new PageInfo((List<GuessInfo>) daoSupport.findForList("GuessPrizeMapper.findAll",pageParms));
        return (List<GuessInfo>) daoSupport.findForList("GuessPrizeMapper.findAll",map);
    }

    @Override
    public int delete(String id) {
        return daoSupport.delete("GuessPrizeMapper.delete",id);
    }

    @Override
    public int selectResult(String childId) {
        //根据childId找出child
        PrizeChild prizeChildDb = prizeChildService.getDeatilById(childId);
        if(prizeChildDb==null)
            return -1;
        GuessInfo guessInfoDb = this.getDeatilById(prizeChildDb.getGuessId());
        guessInfoDb.setChildName(prizeChildDb.getName());
        guessInfoDb.setChildId(childId);
        return daoSupport.update("GuessPrizeMapper.edit",guessInfoDb);
    }

    @Override
    public Object selectCustomerList(Map map) {
        if(map.containsKey("pageSize"))
             return h5Service.getCustomerListByGuessId(map);
        return h5Service.getCustomerListByGuessIdNoPage(map);
    }

    @Override
    public int changeGet(String id) {
        return h5Service.changeGet(id);
    }
}
