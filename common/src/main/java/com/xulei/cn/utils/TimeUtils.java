package com.xulei.cn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {


    //根据月份求月末
    public static String isLastDayOfMonth(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date dateP = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateP);
            calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            if (calendar.get(Calendar.DAY_OF_MONTH) != 1) {
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                return sdf1.format(calendar.getTime());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    //获取本周一
    public static String getCurrentWeekFirst() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return sdf.format(calendar.getTime());
    }

    //获取本周日
    public static String getCurrentWeekEnd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        return sdf.format(calendar.getTime());
    }

    //求下一天
    public static String nexDay(String currentDay,int day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(currentDay));
        calendar.add(Calendar.DAY_OF_WEEK, day);
        return sdf.format(calendar.getTime());
    }

    public static String nexMonth(String currentDay,int month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(currentDay));
        calendar.add(Calendar.MONTH, month);
        return sdf.format(calendar.getTime());
    }


    public static String getSpecialTime(String date,String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateP = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateP);
            SimpleDateFormat sdf1 = new SimpleDateFormat(format);
            return sdf1.format(calendar.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) throws ParseException {
        System.out.println(getTheMonthLastDay("2022-05"));
    }

    public static String getTheMonthLastDay(String timeStart) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(timeStart));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
        return sdf.format(calendar.getTime());
    }
}
