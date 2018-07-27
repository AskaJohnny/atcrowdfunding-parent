package com.johnny.atcrowdfunding.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author johnny
 * @create 2018-07-20 上午9:47
 **/
public class DateUtil {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 根据时间类型 将其转化为 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return 字符串类型
     */
    public static String dateFormat(Date date) {
        return dateFormat.format(date);
    }
}