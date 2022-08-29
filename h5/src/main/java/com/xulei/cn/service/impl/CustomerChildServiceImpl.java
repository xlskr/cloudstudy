package com.xulei.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.wechath5.Customer;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.CustomerChildService;
import com.xulei.cn.utils.IdWorker;
import com.xulei.cn.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerChildServiceImpl extends BaseService implements CustomerChildService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void save(Map map) {
        daoSupport.save("CustomerChildMapper.save",map);
    }

    @Override
    public PageInfo<Map> selectCustomerList(Map guessId) {
        Map pageParms = this.getPageParms(guessId);
        return new PageInfo((List<Map>) daoSupport.findForList("CustomerChildMapper.selectCustomerList",pageParms));
    }

    @Override
    public int changeGet(String id) {
        String[] idarray = id.split(",");
        List<String> ids = Arrays.asList(idarray);
        return daoSupport.update("CustomerChildMapper.changeGet",
                ids);
    }

    @Override
    public List<Map> getMyguess(String token,String id) {
        Object o = redisUtil.get(token);
        if(o==null||!(o instanceof Customer))
            return null;
        Customer customer= (Customer) o;
        Map params=new HashMap<>();
        params.put("id",customer.getId());
        params.put("pqId",id);
        return (List<Map>) daoSupport.findForList("CustomerChildMapper.getMyguess",params);
    }

    @Override
    public List<Map> getCustomerListByGuessIdNoPage(Map map) {
        return (List<Map>) daoSupport.findForList("CustomerChildMapper.selectCustomerList",map);
    }

    @Override
    public List<Map> getMyguessHistory(String token,String id) {
        Object o = redisUtil.get(token);
        if(o==null||!(o instanceof Customer))
            return null;
        Customer customer= (Customer) o;
        Map params=new HashMap<>();
        params.put("id",customer.getId());
        params.put("pqId",id);
        return (List<Map>) daoSupport.findForList("CustomerChildMapper.getMyguessHistory",params);
    }


}
