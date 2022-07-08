package com.xulei.cn.service.impl;

import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.target.UserTarget;
import com.xulei.cn.entities.target.UserTargetC;
import com.xulei.cn.entities.target.UserTargetP;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.UserTargetService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserTargetServiceImpl extends BaseService implements UserTargetService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;


    @Transactional
    @Override
    public int add(UserTarget userTarget) {
        UserTargetP userTargetP = userTarget.getUserTargetP();
        userTargetP.setId(idWorker.nextId()+"");
        if(daoSupport.save("UserTargetMapper.addUserTargetP",userTargetP)>0) {
            List<UserTargetC> userTargetCs = userTarget.getUserTargetCs();
            for (UserTargetC userTargetC : userTargetCs) {
                userTargetC.setId(idWorker.nextId() + "");
                userTargetC.setUtpId(userTargetP.getId());
            }
            return daoSupport.save("UserTargetMapper.addUserTargetC", userTargetCs);
        }
        return -1;
    }

    @Transactional
    @Override
    public int edit(UserTarget userTarget) {
        UserTargetP userTargetP = userTarget.getUserTargetP();
        UserTarget userTargetDb = this.getDeatilById(userTargetP.getId());
        if(userTargetDb==null)
            return -1;
        UserTargetP userTargetPDb = userTargetDb.getUserTargetP();
        if(userTargetPDb==null)
            return -1;
        userTargetPDb.setYear(userTargetP.getYear());
        userTargetPDb.setTargetId(userTargetP.getTargetId());
        userTargetPDb.setType(userTargetP.getType());
        userTargetPDb.setShopsId(userTargetP.getShopsId());
        if(daoSupport.update("UserTargetMapper.updateUserTargetDb",userTargetPDb)<0)
            return -1;
        String id = userTargetP.getId();
        //根据id删除所有的子类
        if(daoSupport.delete("UserTargetMapper.deleteUserTargetC",id)>0){
            List<UserTargetC> userTargetCs = userTarget.getUserTargetCs();
            for (UserTargetC userTargetC : userTargetCs) {
                userTargetC.setId(idWorker.nextId() + "");
                userTargetC.setUtpId(id);
            }
            return daoSupport.save("UserTargetMapper.addUserTargetC", userTargetCs);
        }
        return -1;
    }

    @Override
    public UserTarget getDeatilById(String id) {
        UserTargetP userTargetP= (UserTargetP) daoSupport.findForObject("UserTargetMapper.getDeatilById",id);
        List<UserTargetC> userTargetCS= (List<UserTargetC>) daoSupport.findForList("UserTargetMapper.getChildByParentId",id);
        UserTarget userTarget=new UserTarget();
        userTarget.setUserTargetP(userTargetP);
        userTarget.setUserTargetCs(userTargetCS);
        return userTarget;
    }

    @Override
    public List<Map> findAll(Map map) {
        return (List<Map>) daoSupport.findForList("UserTargetMapper.findAll",map);
    }

    @Transactional
    @Override
    public int delete(String id) {
        if(daoSupport.delete("UserTargetMapper.deleteUserTargetP",id)>0){
            return daoSupport.delete("UserTargetMapper.deleteUserTargetC",id);
        }
        return -1;
    }
}
