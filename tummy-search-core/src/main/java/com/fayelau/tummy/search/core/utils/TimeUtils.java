package com.fayelau.tummy.search.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * 
 * @author 3g7 2019-09-20 16:30:32
 * @version 0.0.1
 *
 */
public class TimeUtils {

    public static final String DEFAULT_FORMAT = "yyyyMMdd";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_FORMAT);

    public static Long dateStr2TimeStamp(String dateStr) throws ParseException {
        return simpleDateFormat.parse(dateStr).getTime();
    }

    /**
     * 判断是否是对应的格式的日期字符串
     * 
     * @param dateStr
     * @param datePattern
     * @return
     */
    public static boolean isRightDateStr(String dateStr, String datePattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        try {
            // 采用严格的解析方式，防止类似 “2017-05-35” 类型的字符串通过
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
            Date date = (Date) dateFormat.parse(dateStr);
            // 重复比对一下，防止类似 “2017-5-15” 类型的字符串通过
            String newDateStr = dateFormat.format(date);
            if (dateStr.equals(newDateStr)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

}
