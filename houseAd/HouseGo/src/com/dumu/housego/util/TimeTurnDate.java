package com.dumu.housego.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeTurnDate {
    public static String getStandardTime(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        Date date = new Date(string);
        sdf.format(date);
        return sdf.format(date);
    }

    public static String getStringDate(String ab) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        long times = Long.valueOf(ab);
        Date date = new Date(times * 1000);
        String tim = sdf.format(date);
        return tim;
    }

    public static String getStringShortDate(String ab) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        long times = Long.valueOf(ab);
        Date date = new Date(times * 1000);
        String tim = sdf.format(date);
        return tim;
    }

    public static String getStringDate(long times) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ", Locale.CHINA);
        Date date = new Date(times * 1000);
        String tim = sdf.format(date);
        return tim;
    }

    public static String getStringDateMore(long times) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        Date date = new Date(times * 1000);
        String tim = sdf.format(date);
        return tim;
    }

    public static String getStringDateMoreMORE(long times) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date = new Date(times * 1000);
        String tim = sdf.format(date);
        return tim;
    }

    public static long getLongByGMT(String gmtTime) throws ParseException {
        SimpleDateFormat Gmt = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return Gmt.parse(gmtTime).getTime();
    }

}