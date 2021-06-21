package pers.bc.utils.pub;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pers.bc.utils.cons.PubConsUtilbc;

/**
 * 提供一些常用的时间想法的方法 注意SimpleDateFormat不是线程安全的
 * @qualiFild nc.pub.itf.tools.pub.DateUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class DateUtil
{
    
    // 注意SimpleDateFormat不是线程安全的
    private static ThreadLocal<SimpleDateFormat> ThreadDateTime = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> ThreadDate = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> ThreadTime = new ThreadLocal<SimpleDateFormat>();
    
    /**
     * *********************************************************** <br>
     * 说明：获取指定的日期格式的时间 <br>
     * @see <br>
     * @param dateType
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.DateUtil#getSimpleDateFormat <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-27 <br>
     * @time 15:31:35 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getSimpleDateFormat(String dateType)
    {
        return getSimpleDateFormat(dateType, new Date());
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取指定的日期格式的时间<br>
     * @see <br>
     * @param dateType PubConsUtilbc.yyyy-MM-dd HH:mm:ss/yyyy/MM/dd HH:mm:ss等
     * @return 指定的格式 <br>
     * @SimpleDateFormat <br>
     * @methods pers.bc.utils.pub.DateUtil#getSimpleDateFormat <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-27 <br>
     * @time 15:17:15 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getSimpleDateFormat(String dateType, Date date)
    {
        return new SimpleDateFormat(dateType).format(date);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param strDate
     * @return <br>
     * @Date <br>
     * @methods pers.bc.utils.pub.DateUtil#dateZero <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-27 <br>
     * @time 15:20:37 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static Date dateZero(Date strDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
        return calendar.getTime();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：某天零点零分零秒 <br>
     * @see <br>
     * @param strDate
     * @return <br>
     * @Date <br>
     * @methods nc.pub.itf.tools.pub.DateUtil#beginDate <br>
     * @author licheng <br>
     * @date Created on 2019年11月2日 <br>
     * @time 下午1:25:59 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static Date beginDate(Date strDate)
    {
        
        long current = strDate.getTime();
        // 某天零点零分零秒的毫秒数
        long zero = current - (current + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);
        
        return new Timestamp(zero);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：某天23点59分59秒 <br>
     * @see <br>
     * @param strDate
     * @return <br>
     * @Date <br>
     * @methods nc.pub.itf.tools.pub.DateUtil#endDate <br>
     * @author licheng <br>
     * @date Created on 2019年11月2日 <br>
     * @time 下午1:25:53 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static Date endDate(Date strDate)
    {
        
        long current = strDate.getTime();
        // 某天零点零分零秒的毫秒数
        long zero = current - (current + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);
        // 某天23点59分59秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;
        
        return new Timestamp(twelve);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：日期时间类型格式yyyy-MM-dd HH:mm:ss <br>
     * @see <br>
     * @return <br>
     * @SimpleDateFormat <br>
     * @methods pers.bc.utils.pub.DateUtil#DateTimeInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-11 <br>
     * @time 下午8:59:43 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static SimpleDateFormat DateTimeInstance()
    {
        SimpleDateFormat df = ThreadDateTime.get();
        if (df == null)
        {
            df = new SimpleDateFormat(PubConsUtilbc.DATETIME_FORMAT);
            ThreadDateTime.set(df);
        }
        return df;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：日期类型格式yyyy-MM-dd <br>
     * @see <br>
     * @return <br>
     * @SimpleDateFormat <br>
     * @methods pers.bc.utils.pub.DateUtil#DateInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-11 <br>
     * @time 下午9:00:08 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static SimpleDateFormat DateInstance()
    {
        SimpleDateFormat df = ThreadDate.get();
        if (null == df)
        {
            df = new SimpleDateFormat(PubConsUtilbc.DATE_FORMAT);
            ThreadDate.set(df);
        }
        return df;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：时间类型的格式 HH:mm:ss <br>
     * @see <br>
     * @return 时间类型的格式<br>
     * @SimpleDateFormat <br>
     * @methods pers.bc.utils.pub.DateUtil#TimeInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-11 <br>
     * @time 下午9:01:49 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static SimpleDateFormat TimeInstance()
    {
        SimpleDateFormat df = ThreadTime.get();
        if (df == null)
        {
            df = new SimpleDateFormat(PubConsUtilbc.TIME_FORMAT);
            ThreadTime.set(df);
        }
        return df;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取当前日期时间 yyyy-MM-dd HH:mm:ss <br>
     * @see <br>
     * @return 返回当前日期时间类型格式 <br>
     * @String <br>
     * @methods pers.bc.utils.pub.DateUtil#currentDateTime <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-11 <br>
     * @time 下午9:00:49 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String currentDateTime()
    {
        return DateTimeInstance().format(new Date());
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取当前日期时间 yyyy-MM-dd HH:mm:ss <br>
     * @see <br>
     * @param date
     * @return 返回PRAM日期时间类型格式<br>
     * @String <br>
     * @methods pers.bc.utils.pub.DateUtil#dateTime <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-11 <br>
     * @time 下午9:01:11 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String dateTime(Date date)
    {
        return DateTimeInstance().format(date);
    }
    
    /**
     * 将指定的字符串解析为时间类型
     * 
     * @param datestr
     * @return
     * @throws ParseException
     */
    public static Date dateTime(String datestr) throws ParseException
    {
        return DateTimeInstance().parse(datestr);
    }
    
    /**
     * 获取当前的日期
     * 
     * @return
     */
    public static String currentDate()
    {
        return DateInstance().format(new Date());
    }
    
    /**
     * 将指定的时间格式化成出返回
     * 
     * @param date
     * @return
     */
    public static String date(Date date)
    {
        return DateInstance().format(date);
    }
    
    /**
     * 将指定的字符串解析为时间类型
     * 
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date date(String dateStr) throws ParseException
    {
        return DateInstance().parse(dateStr);
    }
    
    /**
     * 获取当前的时间
     * 
     * @return
     */
    public static String currentTime()
    {
        return TimeInstance().format(new Date());
    }
    
    /**
     * 讲指定的时间格式化成出返回
     * 
     * @param date
     * @return
     */
    public static String time(Date date)
    {
        return TimeInstance().format(date);
    }
    
    /**
     * 将指定的字符串解析为时间类型
     * 
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date time(String dateStr) throws ParseException
    {
        return TimeInstance().parse(dateStr);
    }
    
    /**
     * 在当前时间的基础上加或减去year年
     * 
     * @param year
     * @return
     */
    public static Date year(int year)
    {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(Calendar.YEAR, year);
        return Cal.getTime();
    }
    
    /**
     * 在指定的时间上加或减去几年
     * 
     * @param date
     * @param year
     * @return
     */
    public static Date year(Date date, int year)
    {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(Calendar.YEAR, year);
        return Cal.getTime();
    }
    
    /**
     * 在当前时间的基础上加或减去几月
     * 
     * @param month
     * @return
     */
    public static Date month(int month)
    {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(Calendar.MONTH, month);
        return Cal.getTime();
    }
    
    /**
     * 在指定的时间上加或减去几月
     * 
     * @param date
     * @param month
     * @return
     */
    public static Date month(Date date, int month)
    {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(Calendar.MONTH, month);
        return Cal.getTime();
    }
    
    /**
     * 在当前时间的基础上加或减去几天
     * 
     * @param day
     * @return
     */
    public static Date day(int day)
    {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(Calendar.DAY_OF_YEAR, day);
        return Cal.getTime();
    }
    
    /**
     * 在指定的时间上加或减去几天
     * 
     * @param date
     * @param day
     * @return
     */
    public static Date day(Date date, int day)
    {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(Calendar.DAY_OF_YEAR, day);
        return Cal.getTime();
    }
    
    /**
     * 在当前时间的基础上加或减去几小时-支持浮点数
     * 
     * @param hour
     * @return
     */
    public static Date hour(float hour)
    {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(Calendar.MINUTE, (int) (hour * 60));
        return Cal.getTime();
    }
    
    /**
     * 在制定的时间上加或减去几小时-支持浮点数
     * 
     * @param date
     * @param hour
     * @return
     */
    public static Date hour(Date date, float hour)
    {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(Calendar.MINUTE, (int) (hour * 60));
        return Cal.getTime();
    }
    
    /**
     * 在当前时间的基础上加或减去几分钟
     * 
     * @param minute
     * @return
     */
    public static Date minute(int minute)
    {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(Calendar.MINUTE, minute);
        return Cal.getTime();
    }
    
    /**
     * 在制定的时间上加或减去几分钟
     * 
     * @param date
     * @param minute
     * @return
     */
    public static Date minute(Date date, int minute)
    {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(Calendar.MINUTE, minute);
        return Cal.getTime();
    }
    
    /**
     * 判断字符串是否为日期字符串
     * 
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date)
    {
        try
        {
            DateTimeInstance().parse(date);
            return true;
        }
        catch (ParseException e)
        {
            LoggerUtilbc.getInstance("publogs").exception("isDateThw", e);
        }
        return false;
    }
    
    /**
     * 时间date1和date2的时间差-单位秒
     * 
     * @param date1
     * @param date2
     * @return 秒
     */
    public static long subtract(Date date1, Date date2)
    {
        long cha = (date2.getTime() - date1.getTime()) / 1000;
        return cha;
    }
    
    /**
     * 时间date1和date2的时间差-单位秒
     * 
     * @param date1
     * @param date2
     * @return 秒
     */
    public static long subtract(String date1, String date2)
    {
        long rs = 0;
        try
        {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = cha;
        }
        catch (ParseException e)
        {
            LoggerUtilbc.getInstance("publogs").exception("subtractThw", e);
        }
        return rs;
    }
    
    /**
     * 时间date1和date2的时间差 -单位分钟
     * 
     * @param date1
     * @param date2
     * @return 分钟
     */
    public static int subtractMinute(String date1, String date2)
    {
        int rs = 0;
        try
        {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = (int) cha / (60);
        }
        catch (ParseException e)
        {
            LoggerUtilbc.getInstance("publogs").exception("subtractMinuteThw", e);
        }
        return rs;
    }
    
    /**
     * 时间date1和date2的时间差-单位分钟
     * 
     * @param date1
     * @param date2
     * @return 分钟
     */
    public static int subtractMinute(Date date1, Date date2)
    {
        long cha = date2.getTime() - date1.getTime();
        return (int) cha / (1000 * 60);
    }
    
    /**
     * 时间date1和date2的时间差-单位小时
     * 
     * @param date1
     * @param date2
     * @return 小时
     */
    public static int subtractHour(Date date1, Date date2)
    {
        long cha = (date2.getTime() - date1.getTime()) / 1000;
        return (int) cha / (60 * 60);
    }
    
    /**
     * 时间date1和date2的时间差-单位小时
     * 
     * @param date1
     * @param date2
     * @return 小时
     */
    public static int subtractHour(String date1, String date2)
    {
        int rs = 0;
        try
        {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = (int) cha / (60 * 60);
        }
        catch (ParseException e)
        {
            
            LoggerUtilbc.getInstance("publogs").exception("subtractHourThw", e);
        }
        return rs;
    }
    
    /**
     * 时间date1和date2的时间差-单位天
     * 
     * @param date1
     * @param date2
     * @return 天
     */
    public static int subtractDay(String date1, String date2)
    {
        int rs = 0;
        try
        {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000;
            rs = (int) sss / (60 * 60 * 24);
        }
        catch (ParseException e)
        {
            LoggerUtilbc.getInstance("publogs").exception("subtractDayThw", e);
            ;
        }
        return rs;
    }
    
    /**
     * 时间date1和date2的时间差-单位天
     * 
     * @param date1
     * @param date2
     * @return 天
     */
    public static int subtractDay(Date date1, Date date2)
    {
        long cha = date2.getTime() - date1.getTime();
        return (int) cha / (1000 * 60 * 60 * 24);
    }
    
    /**
     * 时间date1和date2的时间差-单位月
     * 
     * @param date1
     * @param date2
     * @return 月
     */
    public static int subtractMonth(String date1, String date2)
    {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try
        {
            c1.setTime(DateInstance().parse(date1));
            c2.setTime(DateInstance().parse(date2));
            int year1 = c1.get(Calendar.YEAR);
            int month1 = c1.get(Calendar.MONTH);
            int year2 = c2.get(Calendar.YEAR);
            int month2 = c2.get(Calendar.MONTH);
            if (year1 == year2)
            {
                result = month2 - month1;
            }
            else
            {
                result = 12 * (year2 - year1) + month2 - month1;
            }
        }
        catch (ParseException e)
        {
            LoggerUtilbc.getInstance("publogs").exception("subtractMonthThw", e);
            result = -1;
        }
        return result;
    }
    
    /**
     * 时间date1和date2的时间差-单位月
     * 
     * @param date1
     * @param date2
     * @return 月
     */
    public static int subtractMonth(Date date1, Date date2)
    {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH);
        if (year1 == year2)
        {
            result = month2 - month1;
        }
        else
        {
            result = 12 * (year2 - year1) + month2 - month1;
        }
        return result;
    }
    
    /**
     * 时间date1和date2的时间差-单位年
     * 
     * @param date1
     * @param date2
     * @return 年
     */
    public static int subtractYear(String date1, String date2)
    {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try
        {
            c1.setTime(DateInstance().parse(date1));
            c2.setTime(DateInstance().parse(date2));
            int year1 = c1.get(Calendar.YEAR);
            int year2 = c2.get(Calendar.YEAR);
            result = year2 - year1;
        }
        catch (ParseException e)
        {
            
            LoggerUtilbc.getInstance("publogs").exception("subtractYearThw", e);
            result = -1;
        }
        return result;
    }
    
    /**
     * 时间date1和date2的时间差-单位年
     * 
     * @param date1
     * @param date2
     * @return 年
     */
    public static int subtractYear(Date date1, Date date2)
    {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        result = year2 - year1;
        return result;
    }
    
    /**
     * 获取俩个时间的查结果用时秒表示
     * 
     * @param date1
     * @param date2
     * @return 几小时:几分钟:几秒钟
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     */
    public static String subtractTime(String date1, String date2)
    {
        String result = "";
        try
        {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000;
            int hh = (int) sss / (60 * 60);
            int mm = (int) (sss - hh * 60 * 60) / (60);
            int ss = (int) (sss - hh * 60 * 60 - mm * 60);
            result = hh + ":" + mm + ":" + ss;
        }
        catch (ParseException e)
        {
            
            LoggerUtilbc.getInstance("publogs").exception("subtractTimeThw", e);
        }
        return result;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： * 获取俩个时间的查结果用时秒表示
     * 
     * @param date1
     * @param date2
     * @return 几天-几小时:几分钟:几秒钟
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     * @methods nc.pub.itf.tools.pub.DateUtil#subtractDate <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 下午9:00:32 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String subtractDate(String date1, String date2)
    {
        String result = "";
        try
        {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000;
            int dd = (int) sss / (60 * 60 * 24);
            int hh = (int) (sss - dd * 60 * 60 * 24) / (60 * 60);
            int mm = (int) (sss - dd * 60 * 60 * 24 - hh * 60 * 60) / (60);
            int ss = (int) (sss - dd * 60 * 60 * 24 - hh * 60 * 60 - mm * 60);
            result = dd + "-" + hh + ":" + mm + ":" + ss;
        }
        catch (ParseException e)
        {
            LoggerUtilbc.getInstance("publogs").exception("subtractDateThw", e);
        }
        return result;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取俩个时间之前的相隔的天数 <br>
     * @see <br>
     * @param startTime
     * @param endTime
     * @return <br>
     * @int <br>
     * @methods nc.pub.itf.tools.pub.DateUtil#subDay <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 下午9:00:18 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static int subDay(Date startTime, Date endTime)
    {
        int days = 0;
        Calendar can1 = Calendar.getInstance();
        can1.setTime(startTime);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(endTime);
        int year1 = can1.get(Calendar.YEAR);
        int year2 = can2.get(Calendar.YEAR);
        
        Calendar can = null;
        if (can1.before(can2))
        {
            days -= can1.get(Calendar.DAY_OF_YEAR);
            days += can2.get(Calendar.DAY_OF_YEAR);
            can = can1;
        }
        else
        {
            days -= can2.get(Calendar.DAY_OF_YEAR);
            days += can1.get(Calendar.DAY_OF_YEAR);
            can = can2;
        }
        for (int i = 0; i < Math.abs(year2 - year1); i++)
        {
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
            can.add(Calendar.YEAR, 1);
        }
        
        return days;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取俩个时间之前的相隔的天数 <br>
     * @see <br>
     * @param startTime
     * @param endTime
     * @return <br>
     * @int <br>
     * @methods nc.pub.itf.tools.pub.DateUtil#subDay <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 下午9:00:10 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static int subDay(String startTime, String endTime)
    {
        int days = 0;
        try
        {
            Date date1 = DateInstance().parse(DateInstance().format(DateTimeInstance().parse(startTime)));
            Date date2 = DateInstance().parse(DateInstance().format(DateTimeInstance().parse(endTime)));
            Calendar can1 = Calendar.getInstance();
            can1.setTime(date1);
            Calendar can2 = Calendar.getInstance();
            can2.setTime(date2);
            int year1 = can1.get(Calendar.YEAR);
            int year2 = can2.get(Calendar.YEAR);
            
            Calendar can = null;
            if (can1.before(can2))
            {
                days -= can1.get(Calendar.DAY_OF_YEAR);
                days += can2.get(Calendar.DAY_OF_YEAR);
                can = can1;
            }
            else
            {
                days -= can2.get(Calendar.DAY_OF_YEAR);
                days += can1.get(Calendar.DAY_OF_YEAR);
                can = can2;
            }
            for (int i = 0; i < Math.abs(year2 - year1); i++)
            {
                days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
                can.add(Calendar.YEAR, 1);
            }
            
        }
        catch (ParseException e)
        {
            LoggerUtilbc.getInstance("publogs").exception("subDayThw", e);
        }
        return days;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 返回俩个时间在时间段(例如每天的08:00-18:00)的时长-单位秒<br>
     * @see <br>
     * @param startDate
     * @param endDate
     * @param timeBurst
     * @return
     * @throws ParseException <br>
     * @long <br>
     * @methods nc.pub.itf.tools.pub.DateUtil#subtimeBurst <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 下午9:00:01 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static long subtimeBurst(String startDate, String endDate, String timeBurst) throws ParseException
    {
        Date start = DateTimeInstance().parse(startDate);
        Date end = DateTimeInstance().parse(endDate);
        return subtimeBurst(start, end, timeBurst);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 返回俩个时间在时间段(例如每天的08:00-18:00)的时长-单位秒<br>
     * @see <br>
     * @param startDate
     * @param endDate
     * @param timeBurst
     * @return
     * @throws ParseException <br>
     * @long <br>
     * @methods nc.pub.itf.tools.pub.DateUtil#subtimeBurst <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 下午8:59:53 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static long subtimeBurst(Date startDate, Date endDate, String timeBurst) throws ParseException
    {
        long second = 0;
        Pattern p = Pattern.compile("^\\d{2}:\\d{2}-\\d{2}:\\d{2}");
        Matcher m = p.matcher(timeBurst);
        boolean falg = false;
        if (startDate.after(endDate))
        {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
            falg = true;
        }
        if (m.matches())
        {
            String[] a = timeBurst.split("-");
            int day = subDay(startDate, endDate);
            if (day > 0)
            {
                long firstMintues = 0;
                long lastMintues = 0;
                long daySecond = 0;
                String strDayStart = DateInstance().format(startDate) + " " + a[0] + ":00";
                String strDayEnd = DateInstance().format(startDate) + " " + a[1] + ":00";
                Date dayStart = DateTimeInstance().parse(strDayStart);
                Date dayEnd = DateTimeInstance().parse(strDayEnd);
                daySecond = subtract(dayStart, dayEnd);
                if ((startDate.after(dayStart) || startDate.equals(dayStart)) && startDate.before(dayEnd))
                {
                    firstMintues = (dayEnd.getTime() - startDate.getTime()) / 1000;
                }
                else if (startDate.before(dayStart))
                {
                    firstMintues = (dayEnd.getTime() - dayStart.getTime()) / 1000;
                }
                dayStart = DateTimeInstance().parse(DateInstance().format(endDate) + " " + a[0] + ":00");
                dayEnd = DateTimeInstance().parse(DateInstance().format(endDate) + " " + a[1] + ":00");
                if (endDate.after(dayStart) && (endDate.before(dayEnd) || endDate.equals(dayEnd)))
                {
                    lastMintues = (endDate.getTime() - dayStart.getTime()) / 1000;
                }
                else if (endDate.after(dayEnd))
                {
                    lastMintues = (dayEnd.getTime() - dayStart.getTime()) / 1000;
                }
                // 第一天的秒数 + 最好一天的秒数 + 天数*全天的秒数
                second = firstMintues + lastMintues;
                second += (day - 1) * daySecond;
            }
            else
            {
                String strDayStart = DateInstance().format(startDate) + " " + a[0] + ":00";
                String strDayEnd = DateInstance().format(startDate) + " " + a[1] + ":00";
                Date dayStart = DateTimeInstance().parse(strDayStart);
                Date dayEnd = DateTimeInstance().parse(strDayEnd);
                if ((startDate.after(dayStart) || startDate.equals(dayStart)) && startDate.before(dayEnd) && endDate.after(dayStart)
                    && (endDate.before(dayEnd) || endDate.equals(dayEnd)))
                {
                    second = (endDate.getTime() - startDate.getTime()) / 1000;
                }
                else
                {
                    if (startDate.before(dayStart))
                    {
                        if (endDate.before(dayEnd))
                        {
                            second = (endDate.getTime() - dayStart.getTime()) / 1000;
                        }
                        else
                        {
                            second = (dayEnd.getTime() - dayStart.getTime()) / 1000;
                        }
                    }
                    if (startDate.after(dayStart))
                    {
                        if (endDate.before(dayEnd))
                        {
                            second = (endDate.getTime() - startDate.getTime()) / 1000;
                        }
                        else
                        {
                            second = (dayEnd.getTime() - startDate.getTime()) / 1000;
                        }
                    }
                }
                if ((startDate.before(dayStart) && endDate.before(dayStart)) || startDate.after(dayEnd) && endDate.after(dayEnd))
                {
                    second = 0;
                }
            }
        }
        else
        {
            second = (endDate.getTime() - startDate.getTime()) / 1000;
        }
        if (falg)
        {
            second = Long.parseLong("-" + second);
        }
        return second;
    }
    
    /**
     * 时间Date在时间段(例如每天的08:00-18:00)上增加或减去second秒
     * 
     * @param date
     * @param second
     * @param timeBurst
     * @return 计算后的时间
     * @Suumary 指定的格式错误后返回原数据
     */
    public static Date calculate(String date, int second, String timeBurst)
    {
        Date start = null;
        try
        {
            start = DateTimeInstance().parse(date);
            return calculate(start, second, timeBurst);
        }
        catch (ParseException e)
        {
            LoggerUtilbc.getInstance("publogs").exception("calculateThw", e);
        }
        return new Date();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：时间Date在时间段(例如每天的08:00-18:00)上增加或减去second秒 <br>
     * @see <br>
     * @param date
     * @param second
     * @param timeBurst
     * @return <br>
     * @Date <br>
     * @methods nc.pub.itf.tools.pub.DateUtil#calculate <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 下午8:59:39 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static Date calculate(Date date, int second, String timeBurst)
    {
        Pattern p = Pattern.compile("^\\d{2}:\\d{2}-\\d{2}:\\d{2}");
        Matcher m = p.matcher(timeBurst);
        Calendar cal = Calendar.getInstance();
        if (m.matches())
        {
            String[] a = timeBurst.split("-");
            try
            {
                Date dayStart = DateTimeInstance().parse(DateInstance().format(date) + " " + a[0] + ":00");
                Date dayEnd = DateTimeInstance().parse(DateInstance().format(date) + " " + a[1] + ":00");
                int DaySecond = (int) subtract(dayStart, dayEnd);
                int toDaySecond = (int) subtract(dayStart, dayEnd);
                if (second >= 0)
                {
                    if ((date.after(dayStart) || date.equals(dayStart)) && (date.before(dayEnd) || date.equals(dayEnd)))
                    {
                        cal.setTime(date);
                        toDaySecond = (int) subtract(date, dayEnd);
                    }
                    if (date.before(dayStart))
                    {
                        cal.setTime(dayStart);
                        toDaySecond = (int) subtract(dayStart, dayEnd);
                    }
                    if (date.after(dayEnd))
                    {
                        cal.setTime(day(dayStart, 1));
                        toDaySecond = 0;
                    }
                    
                    if (second > toDaySecond)
                    {
                        int day = (second - toDaySecond) / DaySecond;
                        int remainder = (second - toDaySecond) % DaySecond;
                        cal.setTime(day(dayStart, 1));
                        cal.add(Calendar.DAY_OF_YEAR, day);
                        cal.add(Calendar.SECOND, remainder);
                    }
                    else
                    {
                        cal.add(Calendar.SECOND, second);
                    }
                    
                }
                else
                {
                    if ((date.after(dayStart) || date.equals(dayStart)) && (date.before(dayEnd) || date.equals(dayEnd)))
                    {
                        cal.setTime(date);
                        toDaySecond = (int) subtract(date, dayStart);
                    }
                    if (date.before(dayStart))
                    {
                        cal.setTime(day(dayEnd, -1));
                        toDaySecond = 0;
                    }
                    if (date.after(dayEnd))
                    {
                        cal.setTime(dayEnd);
                        toDaySecond = (int) subtract(dayStart, dayEnd);
                    }
                    if (Math.abs(second) > Math.abs(toDaySecond))
                    {
                        int day = (Math.abs(second) - Math.abs(toDaySecond)) / DaySecond;
                        int remainder = (Math.abs(second) - Math.abs(toDaySecond)) % DaySecond;
                        cal.setTime(day(dayEnd, -1));
                        cal.add(Calendar.DAY_OF_YEAR, Integer.valueOf("-" + day));
                        cal.add(Calendar.SECOND, Integer.valueOf("-" + remainder));
                    }
                    else
                    {
                        cal.add(Calendar.SECOND, second);
                    }
                }
            }
            catch (ParseException e)
            {
                LoggerUtilbc.getInstance("publogs").exception("calculate", e);
            }
        }
        else
        {
            cal.setTime(date);
        }
        return cal.getTime();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 判断是否在某个时间段内 <br>
     * @see <br>
     * @param startTime
     * @param endTime
     * @param date
     * @return
     * @throws ParseException <br>
     * @boolean <br>
     * @methods nc.pub.itf.tools.pub.DateUtil#between <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 下午8:58:59 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static boolean between(String startTime, String endTime, Date date) throws ParseException
    {
        return between(dateTime(startTime), dateTime(endTime), date);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 判断在某个时间内 <br>
     * @see <br>
     * @param startTime
     * @param endTime
     * @param date
     * @return <br>
     * @boolean <br>
     * @methods nc.pub.itf.tools.pub.DateUtil#between <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 下午8:58:51 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static boolean between(Date startTime, Date endTime, Date date)
    {
        return date.after(startTime) && date.before(endTime);
    }
    
}
