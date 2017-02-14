package com.vds.app.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * 时间工具类
 * 
 * @author Cay
 *
 */
public class DateUtil
{
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong = "yyyyMMddHHmmss";

    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple = "yyyy-MM-dd HH:mm:ss";

    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort = "yyyy-MM-dd";

    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * 
     * @return 以yyyyMMddHHmmss为格式的当前系统时间
     */
    public static String getOrderNum()
    {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtLong);
        return df.format(date);
    }

    /**
     * 根据day获取日期开始时间，1为明天，-1为昨天，以此类推(0为今天)
     * 
     * @param day
     * @return
     */
    @SuppressWarnings("static-access")
	public static Date getTodayStartTime(int day)
    {
        Calendar currentDate = new GregorianCalendar();
        currentDate.add(currentDate.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 根据date获取日期开始时间，1为明天，-1为昨天，以此类推(0为今天)
     * 
     * @param date
     * @param day
     * @return
     */
    public static Date getDateStartTime(Date date, int day)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DATE, day);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }

    /**
     * 根据date获取日期结束时间，1为明天，-1为昨天，以此类推(0为今天)
     * 
     * @param date
     * @param day
     * @return
     */
    public static Date getDateEndStartTime(Date date, int day)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DATE, day);
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        return now.getTime();
    }

    /**
     * 根据day获取日期结束时间，1为明天，-1为昨天，以此类推(0为今天)
     * 
     * @param day
     * @return
     */
    @SuppressWarnings("static-access")
	public static Date getTodayEndTime(int day)
    {
        Calendar currentDate = new GregorianCalendar();
        currentDate.add(currentDate.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 获取系统某个日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getOtherDateFormatter(Date date)
    {
        DateFormat df = new SimpleDateFormat(simple);
        return df.format(date);
    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getDateFormatter()
    {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simple);
        return df.format(date);
    }

    /**
     * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
     * 
     * @return
     */
    public static String getDate()
    {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtShort);
        return df.format(date);
    }

    /**
     * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
     * 
     * @return
     */
    public static String getAfterDate(Date date)
    {

        DateFormat df = new SimpleDateFormat(dtShort);
        return df.format(date);
    }

    /**
     * 产生随机的三位数
     * 
     * @return
     */
    public static String getThree()
    {
        Random rad = new Random();
        return rad.nextInt(100000000) + "";
    }

    /**
     * 得到几天前的时间
     * 
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     * 
     * @param d
     * @param day
     * @return
     */
    public static String getDateAfter(Date d, int day)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return getAfterDate(now.getTime());
    }

    /**
     * 得到传入时间所在周的周几
     * 
     * @param d
     * @param weekDay
     *            1周日 7周六
     * @return
     */
    public static Date getDayOfWeek(Date d, int weekDay)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DAY_OF_WEEK, weekDay);
        return now.getTime();
    }

    /**
     * 得到几月前的时间
     * 
     * @param d
     * @param month
     * @return
     */
    public static Date getDateBeforeMonth(Date d, int month)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) - month);
        return now.getTime();
    }

    /**
     * 得到几月后的时间
     * 
     * @param d
     * @param month
     * @return
     */
    public static Date getDateAfterMonth(Date d, int month)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + month);
        return now.getTime();
    }

    /**
     * 得到虚岁
     * 
     * @param birthday
     *            yyyy-MM-dd
     * @return
     */
    public static int getBirthdayByAge(String birthdayStr)
    {
        boolean valid = ValidateUtil.isValid(birthdayStr);
        if (!valid)
        {
            return -1;
        }
        Calendar now = Calendar.getInstance();
        int birthday = Integer.parseInt(birthdayStr.substring(0,
                birthdayStr.indexOf("-")));
        return (now.getWeekYear() - birthday) + 1;
    }

    /**
     * 将Date格式日期转换为String格式日期
     * 
     * @param date
     *            类型
     * @return
     */
    public static String getDateToString(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = sdf.format(date); // 转换成字符串类型
        return d;
    }

    /**
     * 将String格式日期转换为Date格式日期
     * 
     * @param date
     *            类型
     * @return
     */
    public static Date getStringToDate(String d)
    {
        if (d == null || d == "")
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try
        {
            date = sdf.parse(d);// 转回Date类型
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getStringToDate(String d, String regex)
    {
        if (d == null || d == "")
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(regex);
        Date date = new Date();
        try
        {
            date = sdf.parse(d);// 转回Date类型
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 转换格式(2016/06/06) --》 (2016:06:06 00:00:00)
     * 
     * @param date
     * @return
     */
    public static String convertFormatDate(String date, boolean flag)
    {
        boolean valid = ValidateUtil.isValid(date);
        if (valid)
        {
            String date4 = null;
            try
            {
                String date2 = null;
                if (flag)
                {
                    date2 = date + " 00:00:00";
                }
                else
                {
                    date2 = date + " 23:59:59";
                }
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "MM/dd/yyyy HH:mm:ss");

                Date date3 = sdf.parse(date2);
                date4 = getDateToString(date3);

            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            return date4;
        }
        else
        {
            return null;
        }

    }
    /**
     * 判断某个日期是不是今天
     */
    public static boolean isToday(Date date){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        if(null!=date&&sdf.format(date).equals(sdf.format(new Date()))){
            return true;
        }
        return false;
    }
    
    public static long containDays(Date startTime,Date endTime){
    	long diffDays = (endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24);
		return diffDays;
    }
    
    public static void main(String[] args) {
		System.out.println(containDays(new Date(), new Date()));
	}
    
}
