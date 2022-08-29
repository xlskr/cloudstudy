package com.xulei.cn.service;

import com.xulei.cn.entities.wechath5.Customer;
import com.xulei.cn.entities.wechath5.CustomerVo;

import java.util.Map;

public interface CustomerService {

    void saveCustomer(Map token);


    Customer selectCustomerByOpenId(String openId);

    int submit(CustomerVo customerVo);

    Map<String, Object> findIsGet(String token,String id);
}
