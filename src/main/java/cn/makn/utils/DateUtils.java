package cn.makn.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 时间工具类
 * @author makn
 * @date 2021/1/7 11:06
 * @param
 * @return
 */
public class DateUtils {
    // https://blog.csdn.net/Evankaka/article/details/72614601
    private static SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat ymdhmsFormat2 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    private static SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat ymdFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat hmsFormat = new SimpleDateFormat("HHmmss");
    private static SimpleDateFormat ymFormat = new SimpleDateFormat("yyyyMM");
    private static Calendar c = Calendar.getInstance();

    public static Date dateOnly(Date date) {
        return yyyyMMddToDate(parseToyyyyMMdd(date));
    }

    /**
     * 转换为 yyyyMMddHHmmss格式
     */
    public static String parseToyyyyMMddHHmmss(Date date) {

        if (date == null) {
            return null;
        }
        return ymdhmsFormat.format(date);

    }

    /**
     * 转换为 yyyyMMdd HH:mm:ss格式
     */
    public static String parseToyyyyMMddHHmmss2(Date date) {

        if (date == null) {
            return null;
        }
        return ymdhmsFormat2.format(date);

    }

    /**
     * 转换为HHmmss格式
     */
    public static String parseToHHmmss(Date date) {
        if (date == null) {
            return null;
        }
        return hmsFormat.format(date);
    }

    /**
     * 转换为yyyyMMdd格式
     */
    public static String parseToyyyyMMdd(Date date) {
        if (date == null) {
            return null;
        }

        return ymdFormat.format(date);
    }

    /**
     * 转换为yyyyMM格式
     */
    public static int parseToyyyyMM(Date date) {
        if (date == null) {
            return 0;
        }

        return Integer.valueOf(ymFormat.format(date));
    }

    /**
     * @Description: yyyyMMddHHmmss转化为类型DATE时间
     * @author makn
     * @date 2021/1/7 11:05
     * @param yyyyMMddHHmmss
     * @return
     */
    public static Date yyyyMMddHHmmssToDate(String yyyyMMddHHmmss) {
        try {

            return ymdhmsFormat.parse(yyyyMMddHHmmss);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @Description: yyyyMMdd转化为类型DATE时间
     * @author makn
     * @date 2021/1/7 11:05
     * @param yyyyMMdd
     * @return
     */
    public static Date yyyyMMddToDate(String yyyyMMdd) {
        try {

            return ymdFormat.parse(yyyyMMdd);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @Description: yyyyMM转化为类型DATE时间
     * @author makn
     * @date 2021/1/7 11:05
     * @param yyyyMM
     * @return
     */
    public static Date yyyyMMToDate(String yyyyMM) {
        try {

            return ymFormat.parse(yyyyMM);
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * @Description: yyyy-MM-dd转换成date
     * @author makn
     * @date 2021/1/7 11:05
     * @param yyyyMMdd2
     * @return
     */
    public static Date yyyyMMddToDate2(String yyyyMMdd2) {
        try {

            return ymdFormat2.parse(yyyyMMdd2);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @Description: HHmmss转换成date
     * @author makn
     * @date 2021/1/7 11:05
     * @param HHmmss
     * @return
     */
    public static Date HHmmssToDate(String HHmmss) {
        try {

            return hmsFormat.parse(HHmmss);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @Description: 时间加daysToAdd天数
     * @author makn
     * @date 2021/1/7 11:08
     * @param srcDate 时间
     * @param daysToAdd  天数
     * @return
     */
    public static Date getDate(Date srcDate, Integer daysToAdd) {

        c.setTime(srcDate);
        c.add(Calendar.DATE, daysToAdd); // number of days to add

        return c.getTime();
    }

    /**
     * @Description: yyyyMMddHHmmss转为类型date 的yyyyMMdd HH:mm:ss
     * @author makn
     * @date 2021/1/7 11:10
     * @param yyyyMMddHHmmss
     * @return
     */
    public static Date yyyyMMddHHmmssToDate2(String yyyyMMddHHmmss) {
        try {
            return ymdhmsFormat2.parse(yyyyMMddHHmmss);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @Description: 两个日期之间的天数
     * @author makn
     * @date 2021/1/7 11:10
     * @param early
     * @param late
     * @return
     */
    public static final int daysBetween(Date early, Date late) {

        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        // 得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    /**
     * @Description: 获取下一天星期
     * @author makn
     * @date 2021/1/7 11:11
     * @param date
     * @param dayOfWeek
     * @return
     */
    public static Date getNextDayOfWeek(Date date, int dayOfWeek) {
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        if (dayOfWeek > 7 || dayOfWeek < 1) {
            throw new RuntimeException("星期：" + dayOfWeek + "不存在");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        while (true) {
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (preWeekDay(day) == dayOfWeek) {
                return cal.getTime();
            }
            cal.add(Calendar.DATE, 1);
        }
    }

    /**
     * @Description:
     * @author makn
     * @date 2021/1/7 11:11
     * @param date
     * @param nextMonthDate
     * @return
     */
    public static Date getNextMonthDate(Date date, int nextMonthDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int day = cal.get(Calendar.DATE);
        if (day <= nextMonthDate) {
            cal.set(Calendar.DATE, nextMonthDate);
        } else {
            cal.set(Calendar.DATE, 1);
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DATE, nextMonthDate);
        }
        return cal.getTime();
    }

    /**
     * @Description:
     * @author makn
     * @date 2021/1/7 11:12
     * @param day
     * @return
     */
    private static int nextWeekDay(int day) {
        if (day == 7) {
            return 1;
        } else {
            return day++;
        }
    }

    /**
     * @Description:
     * @author makn
     * @date 2021/1/7 11:13
     * @param day
     * @return
     */
    private static int preWeekDay(int day) {
        if (day == 1) {
            return 7;
        } else {
            return day - 1;
        }
    }

    /**
     * 计算两个日期相差的天数
     *
     * @param beginDate 【YYYYMMDD】
     * @param endDate   【YYYYMMDD】
     * @return Integer
     * @author
     * @since
     */
    public static long diffDate(Date beginDate, Date endDate) {
        Calendar theCa1 = Calendar.getInstance();
        Calendar theCa2 = Calendar.getInstance();
        theCa1.setTime(beginDate);
        theCa2.setTime(endDate);
        long between_days = (theCa2.getTimeInMillis() - theCa1.getTimeInMillis()) / (1000 * 3600 * 24);
        return between_days;
    }

    /**
     * 分钟差
     *
     * @param @param     beginDate
     * @param @param     endDate
     * @param @return    设定文件 
     * @return long    返回类型 
     * @Title: diffMinute 
     * @Description: TODO
     * @author :
     * @throws 
     */
    public static long diffMinute(Date beginDate, Date endDate) {
        Calendar theCa1 = Calendar.getInstance();
        Calendar theCa2 = Calendar.getInstance();
        theCa1.setTime(beginDate);
        theCa2.setTime(endDate);
        long between_minutes = (theCa2.getTimeInMillis() - theCa1.getTimeInMillis()) / (1000 * 60);
        return between_minutes;
    }

    /**
     * 获取月份差第一天
     *
     * @param @param     date
     * @param @param     monthToAdd
     * @param @param     minOrMax 月初还是月末
     * @param @return    设定文件 
     * @return Date    返回类型 
     * @Title: getMonthFirstDate 
     * @Description: TODO
     * @author :
     * @throws 
     */
    public static Date getMonthFirstDate(Date date, int monthToAdd, String minOrMax) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, monthToAdd);
        if (minOrMax.equals("min")) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        } else if (minOrMax.equals("max")) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }

        return calendar.getTime();
    }

    /**
     * @param date
     * @return
     * @Description: 获取date的上一个月
     * @author makn
     * @date 2021/1/7 11:04
     */
    public static long getLastMonth(Date date) {
        Date lastDate = getMonthFirstDate(date, -1, "min");
        long lastMonth = parseToyyyyMM(lastDate);

        return lastMonth;
    }


    public static void main(String[] args) throws InterruptedException {
        Calendar cal = Calendar.getInstance();
        System.out.println(" cal.get(Calendar.DAY_OF_WEEK);:" + cal.get(Calendar.DAY_OF_WEEK));
        System.out.println(" cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);:" + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
//
        System.out.println(getNextDayOfWeek(cal.getTime(), 0));
        System.out.println(getNextDayOfWeek(cal.getTime(), 7));
        System.out.println(getNextDayOfWeek(cal.getTime(), 1));
        System.out.println(getNextDayOfWeek(cal.getTime(), 2));
//
        System.out.println(getNextMonthDate(cal.getTime(), 0));
        System.out.println(parseToyyyyMMdd(getNextMonthDate(cal.getTime(), 15)));

        System.out.println(parseToyyyyMMdd(getMonthFirstDate(yyyyMMddToDate("20160618"), -1, "max")));

        System.out.println(yyyyMMddToDate2("2012-09-01"));
//
        Date start = new Date();
        System.out.println(start);
//        Thread.sleep(60*1000*5+1000);
        Date end = new Date();


        System.out.println(getLastMonth(end));

        System.out.println(parseToyyyyMMddHHmmss(new Date()));

        System.out.println(end);
        System.out.println(diffMinute(start, end));
    }

}
