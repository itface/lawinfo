package com.lawinfo.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangrongtao on 15/10/14.
 */
public class DateUtils {

    public static String formatDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }
    public static String formatDatetime(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }
    public static String formatDatetime(long datetime) {
        Date date = new Date(datetime);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    public static Date convertToDate(long time) {
        return new Date(time);
    }
}
