package com.xulei.cn.service;

import java.util.Map;

public interface MainService {


    Object getDb(String shopsId, String time, String roleId, String type, String targetId);

    Object getWdb(String shopsId, String time, String roleId, String type, String targetId);

    Object getDbDeatil(String shopsId, String time, String roleId, String type, String targetId, String userId);

    Object getWdbDeatil(String shopsId, String time, String roleId, String type, String targetId, String userId);

    Object fwzbtj(Map map);

    Object fwzbtjDeatil(Map map);

    Object getsdqsByWeek(Map map);

    Object getsdqsByMonth(Map map);

    Object getSdzbWeekDetail(Map map);

    Object getSdzbMonthDetail(Map map);

    Object getMoreDb(String shopsId, String time, String roleId, String type, String targetId);

    Object getMoreWdb(String shopsId, String time, String roleId, String type, String targetId);
}
