package com.example.demo.units;

import javax.print.DocFlavor;
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

        long ts = date.getTime()/1000;


        return ts;
    }

    public static void main(String[] args) throws ParseException {
        long times=System.currentTimeMillis()/1000;
        System.out.println(times);
        long time1= dateToStamp("2020-08-01 00:00:00");
        System.out.println(time1);
    }
}
