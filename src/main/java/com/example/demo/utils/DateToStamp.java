package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStamp {
    /*

     * 将时间转换为时间戳

     */
    public static long dateToStamp(String s) throws ParseException {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = simpleDateFormat.parse(s);

        long ts = date.getTime() / 1000;


        return ts;
    }

    /*
     *获取现在长度为10的时间戳
     * */
    public static int getTimeStap() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static void main(String[] args) throws ParseException {
        long times = System.currentTimeMillis() / 1000;
        System.out.println(times);
        long time1 = dateToStamp("2020-08-01 00:00:00");
        System.out.println(time1);
    }
}
