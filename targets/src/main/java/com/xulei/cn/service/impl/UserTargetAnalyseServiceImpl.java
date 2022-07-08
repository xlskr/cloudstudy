package com.xulei.cn.service.impl;

import com.xulei.cn.dao.DAO;
import com.xulei.cn.service.UserTargetAnalyseService;
import com.xulei.cn.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserTargetAnalyseServiceImpl implements UserTargetAnalyseService {

    @Autowired
    private DAO daoSupport;

   private String month[]={"targetOne","targetTwo","targetThree","targetFour","targetFive","targetSix"
           ,"targetSeven","targetEight","targetNine","targetTen","targetEleven","targetTwelve"};

    @Override
    public List<Map> getDb(Map map) {

        try {
            String time=map.get("time").toString();
            Integer month1 = Integer.valueOf(getMonthOrYear(time,0));
            map.put(month[month1-1],1);
            map.put("year",getMonthOrYear(time,1));
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.getDb",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map> getWdb(Map map) {
        try {
            String time=map.get("time").toString();
            Integer month1 = Integer.valueOf(getMonthOrYear(time,0));
            map.put(month[month1-1],1);
            map.put("year",getMonthOrYear(time,1));
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.getWdb",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map> getMoreDb(Map map) {
        try {
            String time=map.get("time").toString();
            Integer month1 = Integer.valueOf(getMonthOrYear(time,0));
            map.put(month[month1-1],1);
            map.put("year",getMonthOrYear(time,1));
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.getMoreDb",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map> getMoreWdb(Map map) {
        try {
            String time=map.get("time").toString();
            Integer month1 = Integer.valueOf(getMonthOrYear(time,0));
            map.put(month[month1-1],1);
            map.put("year",getMonthOrYear(time,1));
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.getMoreWdb",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map> getDbDetail(Map map) {
        try {
            String time=map.get("time").toString();
            Integer month1 = Integer.valueOf(getMonthOrYear(time,0));
            map.put(month[month1-1],1);
            map.put("year",getMonthOrYear(time,1));
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.getDbDetail",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map> getWdbDetail(Map map) {
        try {
            String time=map.get("time").toString();
            Integer month1 = Integer.valueOf(getMonthOrYear(time,0));
            map.put(month[month1-1],1);
            map.put("year",getMonthOrYear(time,1));
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.getWdbDetail",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 服务指标统计
     *
     * 参数 timeStart 2022-05  2024-07  1 2 3 4
     * @param map key值 timeStart timeEnd shopsId(有值代表单个门店 ""就是所有门店) roleId
     * @return
     */
    @Override
    public List<Map> getFwzbtj(Map map) {
        try {
            String timeStart=map.get("timeStart").toString();
            String timeEnd=map.get("timeEnd").toString();
            String yearStart = getMonthOrYear(timeStart, 1);
            String yearEnd = getMonthOrYear(timeEnd, 1);
            map.put("yearStart", yearStart);
            map.put("yearEnd",yearEnd);
            int monthStart = Integer.valueOf(getMonthOrYear(timeStart, 0));
            int monthEnd = Integer.valueOf(getMonthOrYear(timeEnd, 0));
            // 5月
            for(int i=0;i<monthStart-1;i++){
                map.put(i+1+"s1",1);
            }
            for(int i=monthEnd;i<12;i++){
                map.put(i+1+"s2",1);
            }
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.getFwzbtj",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查看一个门店的详情  指标类型不能为空  指标内容可以为空  求目标数量和完成数量
     * @param map
     * @return
     */
    @Override
    public Object getFwzbtjOneShopsDeatil(Map map) {
        try {
            String timeStart=map.get("timeStart").toString();
            String timeEnd=map.get("timeEnd").toString();
            String yearStart = getMonthOrYear(timeStart, 1);
            String yearEnd = getMonthOrYear(timeEnd, 1);
            map.put("yearStart", yearStart);
            map.put("yearEnd",yearEnd);
            int monthStart = Integer.valueOf(getMonthOrYear(timeStart, 0));
            int monthEnd = Integer.valueOf(getMonthOrYear(timeEnd, 0));
            // 5月
            for(int i=0;i<monthStart-1;i++){
                map.put(i+1+"s1",1);
            }
            for(int i=monthEnd;i<12;i++){
                map.put(i+1+"s2",1);
            }
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.getFwzbtjOneShopsDeatil",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查看多个门店的详情  求店铺的 目标数量 完成数量
     * @param map
     * @return
     */
    @Override
    public Object getFwzbtjMoreShopsDeatil(Map map) {
        try {
            String timeStart=map.get("timeStart").toString();
            String timeEnd=map.get("timeEnd").toString();
            String yearStart = getMonthOrYear(timeStart, 1);
            String yearEnd = getMonthOrYear(timeEnd, 1);
            map.put("yearStart", yearStart);
            map.put("yearEnd",yearEnd);
            int monthStart = Integer.valueOf(getMonthOrYear(timeStart, 0));
            int monthEnd = Integer.valueOf(getMonthOrYear(timeEnd, 0));
            // 5月
            for(int i=0;i<monthStart-1;i++){
                map.put(i+1+"s1",1);
            }
            for(int i=monthEnd;i<12;i++){
                map.put(i+1+"s2",1);
            }
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.getFwzbtjMoreShopsDeatil",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map getsdqsByWeek(Map map) {
        Map map1=new HashMap();
        try {
        //获取本周趋势
        String timeStart = TimeUtils.getCurrentWeekEnd();
        String timeEnd = TimeUtils.getCurrentWeekFirst();
        List<Map> yData=(List<Map>) daoSupport.findForList("UserStatisticsMapper.getsdqsByWeek",map);
        List<String> listx=new ArrayList<>();
        List<String> xData=new ArrayList<>();
        xData.add("周一");
        xData.add("周二");
        xData.add("周三");
        xData.add("周四");
        xData.add("周五");
        xData.add("周六");
        xData.add("周日");
        //nexDay
        for(int i=0;i<7;i++){
            listx.add(TimeUtils.nexDay(timeStart,i));
        }
        yData=convertData(yData,listx);
        map1.put("xData",xData);
        map1.put("yData",yData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map1;
    }

    @Override
    public Map getsdqsByMonth(Map map) {
        Map map1=new HashMap();
        try {
            //获取本周趋势
            String timeStart = map.get("time").toString()+"-01";
            String timeEnd = map.get("time").toString()+"-12";
            map.put("timeEnd", timeEnd);
            map.put("timeStart",timeStart);
            List<Map> yData=(List<Map>) daoSupport.findForList("UserStatisticsMapper.getsdqsByMonth",map);
            List<String> listx=new ArrayList<>();
            List<String> xData=new ArrayList<>();
            for(int i=1;i<13;i++){
                xData.add(i+"月");
            }
            //nexDay
            for(int i=0;i<12;i++){
                listx.add(TimeUtils.nexMonth(timeStart,i));
            }
            yData=convertData(yData,listx);
            map1.put("xData",xData);
            map1.put("yData",yData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map1;
    }

    @Override
    public List<Map> getSdzbWeekDetail(Map map) {
        return (List<Map>) daoSupport.findForList("UserStatisticsMapper.getSdzbWeekDetail",map);
    }

    @Override
    public Map getSdzbMonthDetail(Map map) {
        Map map1=new HashMap();
        try {
            String timeStart = map.get("time").toString()+"-01";
            String timeEnd = TimeUtils.getTheMonthLastDay(timeStart);
            List<String> xData=new ArrayList<>();
            int times=Integer.valueOf(TimeUtils.getSpecialTime(timeEnd,"dd"));
            //nexDay
            for(int i=0;i<times;i++){
                xData.add(TimeUtils.getSpecialTime(TimeUtils.nexDay(timeStart,i),"M.d"));
            }
            map.put("timeEnd", timeEnd);
            map.put("timeStart",timeStart);
            List<Map> yData=(List<Map>) daoSupport.findForList("UserStatisticsMapper.getSdzbMonthDetail",map);
            map1.put("xData",xData);
            map1.put("yData",yData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map1;
    }

    @Override
    public List<Map> mdyjtj(Map map) {
        return (List<Map>) daoSupport.findForList("UserStatisticsMapper.mdyjtj",map);
    }

    //shopsId
    @Override
    public List<Map> mdyjmbtj(Map map) {
        try {
            //2018-05
            String timeStart=map.get("timeStart").toString();
            //2020-08
            String timeEnd=map.get("timeEnd").toString();
            String yearStart = getMonthOrYear(timeStart, 1);
            String yearEnd = getMonthOrYear(timeEnd, 1);
            map.put("yearStart", yearStart);
            map.put("yearEnd",yearEnd);
            int monthStart = Integer.valueOf(getMonthOrYear(timeStart, 0));
            int monthEnd = Integer.valueOf(getMonthOrYear(timeEnd, 0));
            // 5月
            for(int i=0;i<monthStart-1;i++){
                map.put(i+1+"s1",1);
            }
            for(int i=monthEnd;i<12;i++){
                map.put(i+1+"s2",1);
            }
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.mdyjmbtj",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map> ygyjtj(Map map) {
        try {
            //2018-05
            String timeStart=map.get("timeStart").toString()+"-01";
            //2020-08
            String timeEnd=TimeUtils.getTheMonthLastDay(map.get("timeEnd").toString()+"-01");
            map.put("timeStart", timeStart);
            map.put("timeEnd",timeEnd);
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.mdyjmbtj",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Map> ygyjmbtj(Map map) {
        try {
            //2018-05
            String time=map.get("time").toString();
            Integer month1 = Integer.valueOf(getMonthOrYear(time,0));
            map.put(month[month1-1],1);
            map.put("year",getMonthOrYear(time,1));
            return (List<Map>) daoSupport.findForList("UserStatisticsMapper.ygyjmbtj",map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Map> convertData(List<Map> yData, List<String> listx) {
        final List<Map> list=new ArrayList<>();
        for(int i=0;i< listx.size();i++){
            final String time=listx.get(i);
            yData.forEach((map)->{
                if(time.equals(map.get("createTime").toString())){
                    list.add(map);
                }
            });
            if(list.size()!=(i+1)){
                Map map=new HashMap();
                map.put("num",0);
                map.put("targetId",0);
                map.put("targetName","");
                map.put("createTime",time);
                list.add(map);
            }
        }
        return list;
    }


    //获取月 0是月  1是年
    private static String getMonthOrYear(String time,int i) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM");
        Date date = simpleDateFormat.parse(time);
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat(i==0?"MM":"yyyy");
        return simpleDateFormat1.format(date);
    }

}
