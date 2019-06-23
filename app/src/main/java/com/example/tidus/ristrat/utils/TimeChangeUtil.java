package com.example.tidus.ristrat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeChangeUtil {
    //字符串转时间戳
    public static String getCurrentTime(String timeString) {
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date d;
        try {
            d = sdf.parse(timeString);
            long l = d.getTime();
            timeStamp = String.valueOf(l);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }

    //时间戳转字符串
    public static String getStrTime(String timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    /**
     * @param time    1541569323155
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return 2018-11-07 13:42:03
     */
    public static String getDate2String(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(date);
    }
}
