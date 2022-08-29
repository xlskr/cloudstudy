package com.xulei.cn.service.impl;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.wechath5.Customer;
import com.xulei.cn.entities.wechath5.CustomerVo;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.CustomerChildService;
import com.xulei.cn.service.CustomerService;
import com.xulei.cn.utils.HttpUtils;
import com.xulei.cn.utils.IdWorker;
import com.xulei.cn.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CustomerChildService customerChildService;

    @Autowired
    private RedisUtil redisUtil;




    @Override
    public void saveCustomer(Map map) {
        String openid = map.get("openid").toString();
        String token = map.get("token").toString();
        //根据openId查询是否有此customer
        Customer customerDb = selectCustomerByOpenId(openid);
        if(customerDb==null){
            map.remove("token");
            String customerString = HttpUtil.get("https://api.weixin.qq.com/sns/userinfo", map);
            JSONObject cusJsonObject=new JSONObject(customerString);
            customerDb=new Customer();
            customerDb.setOpenId(cusJsonObject.getStr("openid"));
            customerDb.setCity(cusJsonObject.getStr("city"));
            customerDb.setProvince(cusJsonObject.getStr("province"));
            customerDb.setNickName(cusJsonObject.getStr("nickname"));
            customerDb.setId(idWorker.nextId()+"");
            customerDb.setSex(cusJsonObject.getInt("sex"));
            customerDb.setHeadImage(cusJsonObject.getStr("headimgurl"));
            daoSupport.save("CustomerMapper.save",customerDb);
        }
        redisUtil.set(token,customerDb,7200);
    }

    @Override
    public Customer selectCustomerByOpenId(String openId) {
        return (Customer) daoSupport.findForObject("CustomerMapper.selectCustomerByOpenId",openId);
    }

    /**
     * 用户提交
     * @param customerVo
     * @return
     */
    @Transactional
    @Override
    public int submit(CustomerVo customerVo) {
        String token = customerVo.getToken();
        Object o = redisUtil.get(token);
        if(o==null||!(o instanceof Customer))
            return -99;
        Customer customer=(Customer) o;
        customer.setPhone(customerVo.getPhone());
        daoSupport.update("CustomerMapper.update",customer);
        List<String> childIds = customerVo.getChildIds();
        final String id = customer.getId();
        final CustomerChildService customerChildService1=customerChildService;
        childIds.forEach((childId)->{
            Map map=new HashMap();
            map.put("id",idWorker.nextId());
            map.put("childId",childId);
            map.put("customerId",id);
            map.put("phone",customerVo.getPhone());
            customerChildService1.save(map);
        });
        return 1;
    }

    @Override
    public Map<String, Object> findIsGet(String token,String id) {
        Object o = redisUtil.get(token);
        if(o==null||!(o instanceof Customer))
            return null;
        Customer customer=(Customer) o;
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("id",customer.getId());
        params.put("prizeId",id);
        Map<String,Object> forObject = (Map<String, Object>) daoSupport.findForObject("CustomerChildMapper.findIsGet", params);
        if(forObject!=null){
            map.put("customerId",customer.getId());
            map.put("phone",forObject.get("phone"));
            map.put("isPost",true);
        }else {
            map.put("isPost",false);
        }
        return map;
    }


}
