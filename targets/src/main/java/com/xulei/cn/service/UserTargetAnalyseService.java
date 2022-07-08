package com.xulei.cn.service;

import java.util.List;
import java.util.Map;

public interface UserTargetAnalyseService {
    List<Map> getDb(Map map);

    List<Map> getWdb(Map map);

    List<Map> getDbDetail(Map map);

    List<Map> getWdbDetail(Map map);

    List<Map> getFwzbtj(Map map);

    Object getFwzbtjOneShopsDeatil(Map map);

    Object getFwzbtjMoreShopsDeatil(Map map);

    Map getsdqsByWeek(Map map);

    Map getsdqsByMonth(Map map);

    List<Map> getSdzbWeekDetail(Map map);

    Map getSdzbMonthDetail(Map map);

    List<Map> getMoreDb(Map map);

    List<Map> getMoreWdb(Map map);

    List<Map> mdyjtj(Map map);

    List<Map> mdyjmbtj(Map map);

    List<Map> ygyjtj(Map map);

    List<Map> ygyjmbtj(Map map);
}
