package com.mall.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/26
 */
@Slf4j
public class DateUtil {

    private static final TimeZone zone = TimeZone.getTimeZone("GMT+8");
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";
    public final static String YYYY_MM_DD_HH_MM_SS_SSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public final static String YYYY_MM_DD_HH_MM_SSXXX = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public final static String MM_DD = "MM-dd";
    public static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public final static String YYYY_MM_DD_TIGHT = "yyyyMMdd";
    public final static String YYYY_MM_DD_HH_TIGHT = "yyyyMMddHH";
    public final static String YYYY_MM_DD_HH_MM_SS_TIGHT = "yyyyMMddHHmmss";
    public final static String YYYY_MM_TIGHT = "yyyyMM";
    public final static String YYYY_MM = "yyyy-MM";
    public final static String YYYY = "yyyy";
    public final static String CN_YYYY_MM_DD = "yyyy年MM月dd日";

    public static String getCurrentDateTime(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.CHINA);
        String time = df.format(date);
        return time;
    }

    /**
     * 获取当前时间 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS,Locale.CHINA);
        String time = df.format(date);
        return time;
    }

    /**
     * 获取当前时间 yyyy-MM-dd
     * @return
     */
    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
        String time = df.format(date);
        return time;
    }

    /**
     * 获取当前时间 yyyyMMdd
     * @return
     */
    public static String getCurrentTightDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_TIGHT);
        String time = df.format(date);
        return time;
    }

    /**
     * 获取当前时间 yyyyMMddHH
     * @return
     */
    public static String getCurrentTightHour() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_TIGHT);
        String time = df.format(date);
        return time;
    }

    /**
     * 获取当前时间 yyyyMM
     * @return
     */
    public static String getCurrentMonth() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_TIGHT);
        String time = df.format(date);
        return time;
    }

    /**
     * 获取当前时间 fmt
     * @return
     */
    public static String getCurrentMonth(String fmt) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(fmt);
        String time = df.format(date);
        return time;
    }

    /**
     * 获取指定时间 yyyyMMdd
     * @return
     */
    public static String getTightDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_TIGHT,Locale.CHINA);
        String time = df.format(calendar.getTime());
        return time;
    }

    /**
     * 获取一个简单的日期格式化对象
     *
     * @return 一个简单的日期格式化对象
     */
    private static SimpleDateFormat getFormatter(String parttern) {
        return new SimpleDateFormat(parttern,Locale.CHINA);
    }
    /**
     * 获取一个简单的日期格式化对象
     *
     * @return 一个简单的日期格式化对象
     */
    private static SimpleDateFormat getFormatter(String parttern,Locale locale) {
        return new SimpleDateFormat(parttern,locale);
    }

    /**
     * 默认把日期格式化成yyyy-mm-dd格式
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (date == null){
            return null;
        }else{
            return getFormatter(YYYY_MM_DD).format(date);
        }

    }
    /**
     * 把字符串日期转换为f指定格式的Data对象
     *
     * @param strDate
     *            ,f
     * @return
     */
    public static Date format(String strDate, String f) {
        Date d = null;
        if ("".equals(strDate)) {
            return null;
        } else {
            try {
                d = getFormatter(f).parse(strDate);
            } catch (ParseException pex) {
                return null;
            }
        }
        return d;
    }

    /**
     * 把字符串日期转换为f指定格式的Data对象
     *
     * @param strDate
     *            ,f
     * @param locale 时区
     * @return
     */
    public static Date format(String strDate,String f,Locale locale){
        Date d = null;
        if ("".equals(strDate)) {
            return null;
        } else {
            try {
                d = getFormatter(f,locale).parse(strDate);
            } catch (ParseException pex) {
                return null;
            }
        }
        return d;
    }

    /**
     * 日期格式转换
     * */
    public static String formatToString(String strDate, String f) {
        String d = null;
        if ("".equals(strDate)) {
            return null;
        } else {
            try {
                d = format(getFormatter(YYYY_MM_DD).parse(strDate),f);
            } catch (ParseException pex) {
                return null;
            }
        }
        return d;
    }
    /**
     * 默认把日期格式化成yyyy-MM-dd格式
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        return format(date);
    }

    /**
     *
     * @title:获取和本月相差的月份
     * @return
     * @return:String
     * @throws:
     * @description:
     */

    public static String getMonthAfterNum(Integer Num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, Num);
        return DateUtil.format(calendar.getTime(), DateUtil.YYYY_MM_TIGHT);
    }


    /**
     * 默认把日期格式化成yyyy-MM-dd HH:mm:ss格式
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date){
        if (date == null){
            return null;
        }else{
            return getFormatter(YYYY_MM_DD_HH_MM_SS).format(date);
        }
    }

    /**
     * 默认把日期格式化成yyyy-mm-dd 格式
     *
     * @param date
     * @return
     */



    /**
     * 日期解析－将<code>String</code>类型的日期解析为<code>Date</code>型
     *
     * 待格式化的日期
     *
     * @param pattern
     *            日期样式
     * @exception ParseException
     *                如果所给的字符串不能被解析成一个日期
     * @return 一个被格式化了的<code>Date</code>日期
     */
    public static Date parse(String strDate, String pattern) throws ParseException {
        try {
            return getFormatter(pattern).parse(strDate);
        } catch (ParseException pe) {
            throw new ParseException("Method parse in Class DateUtil err: parse strDate fail.", pe.getErrorOffset());
        }
    }

    /**
     * 日期格式化－将<code>Date</code>类型的日期格式化为<code>String</code>型
     *
     * @param date
     *            待格式化的日期
     *@param pattern
     *            时间样式
     *@return 一个被格式化了的<code>String</code>日期
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        } else {
            return getFormatter(pattern).format(date);
        }
    }

    /**
     * 设定开始时间对日进行加减
     *
     *            开始时间
     *@param day
     *           日加减
     *@param pattern
     *            时间样式
     *@return 一个被格式化了的String日期
     */
    public static String getDateDay(String date ,int day,String pattern) {
        try {
            Calendar ca = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = sdf.parse(date);
            ca.setTime(date2);
            ca.add(Calendar.DATE, day);
            Date dateTo = ca.getTime();
            return DateUtil.format(dateTo, pattern);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 比较时间大小
     * @param firstTime
     * @param lastTime
     * @return firstTime>lastTime 返回true
     * @throws ParseException
     */
    public static boolean compareTime(String firstTime, String lastTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);

        Date d1 = df.parse(firstTime);
        Date d2 = df.parse(lastTime);
        if( d1.getTime() - d2.getTime() >0){
            return true;
        }
        return false;
    }

    /**
     * 比较时间大小
     * @param firstTime
     * @param lastTime
     * @param pattern   格式
     * @return  firstTime>lastTime 返回true
     * @throws ParseException
     */
    public static boolean compareTime(String firstTime, String lastTime, String pattern) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);

        Date d1 = df.parse(firstTime);
        Date d2 = df.parse(lastTime);
        if( d1.getTime() - d2.getTime() >0){
            return true;
        }
        return false;
    }

    /**
     * @description 获得间隔第几天的date日期
     * @author huangqm
     * @time:2014年1月18日 下午4:23:04
     * @param date
     * @param space(正则计算今天之后的，负则计算今天之前的)
     * @return
     */
    public static Date getAddDate(Date date,Integer space){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        if(space!=null && space !=0 ){
            calendar.add(Calendar.DATE,space);
        }
        return calendar.getTime();
    }

    /**
     * 获得间隔第几年的date日期
     * @param date
     * @param space
     * @return
     */
    public static Date getAddYear(Date date, Integer space) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (space != null && space != 0) {
            calendar.add(Calendar.YEAR, space);
        }
        return calendar.getTime();
    }

    /**
     * 获取给定日前或者后intevalDay天的日期，前传入负数，后传入正数
     *
     * @param refenceDate
     *            Date 给定日期
     * @param intevalDays
     *            int 间隔天数
     * @return String 计算后的日期 YYYY_MM_DD_HH_MM_SS格式
     */
    public static String getNextDate(Date refenceDate, int intevalDays) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)
                    + intevalDays);
            return format(calendar.getTime(), YYYY_MM_DD_HH_MM_SS);
        } catch (Exception ee) {
            return "";
        }
    }
    /**
     * 获取当前日期
     *
     * @return 一个包含月日的<code>String</code>型日期，但不包含时分秒。mm-dd
     */
    public static String getCurrDateByMonthDay() {
        return format(new Date(), MM_DD);
    }
    /**
     * 获取给定日前或者后intevalDay天的日期，前传入负数，后传入正数
     *
     * @param refenceDate
     *            Date 给定日期
     * @param intevalDays
     *            int 间隔天数
     * @return String 计算后的日期 MM_DD格式
     */
    public static String getNextDateByMonthDay(Date refenceDate, int intevalDays) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)
                    + intevalDays);
            return format(calendar.getTime(), MM_DD);
        } catch (Exception ee) {
            return "";
        }
    }
    public static Date getDate(String string) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern(YYYY_MM_DD_HH_MM_SS);
        dateFormat.setTimeZone(zone);
        try {
            Date date = dateFormat.parse(string);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException("时间转换错误!", e);
        }
    }

    /**
     * 计算时间差
     *
     * @param time
     *            指定的时间，格式为：yyyy-MM-dd HH:mm:ss
     * @return 当前时间和指定时间的时间差（秒）
     * @throws ParseException
     */
    public static long getTimeDifference(String time) throws ParseException {
        long between = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String systemTime = sdf.format(new Date()).toString();

        Date end = null;
        Date begin = null;
        // 将截取到的时间字符串转化为时间格式的字符串
        end = sdf.parse(time);
        begin = sdf.parse(systemTime);

        between = Math.abs(end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

        return between;
    }

    /**
     * 判断前面的时候是否大于后面的时间
     * @param before Date
     * @param after Date
     * @return boolean true是 false否
     */
    public static boolean beforeMoreAfter(Date before,Date after){
        if(before==null || after==null) {
            return false;
        }
        return before.getTime()-after.getTime()>0;
    }

    /**
     * 某个参考时间点往前推N天N小时N分钟N秒
     *
     * @param date
     *            参考时间(如果参考时间为空,则默认为默认时区和语言环境的当前时间)
     * @param day
     *            (-day往前推day天,+day往后推day天)
     * @param hours
     *            (-hours往前推hours小时,+day往后推hours小时)
     * @param minute
     *            (-minute往前推minute分钟,+minute往后推minute分钟)
     * @param second
     *            (-second往前推second秒,+second往后推second秒)
     * @return 返回 yyyy-MM-dd 格式的时间字符串
     */
    @SuppressWarnings("static-access")
    public static Date dateAddToTime(Date date, int day, int hours, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        try {
            if (date != null) {
                calendar.setTime(date);
            }
            calendar.add(calendar.DATE, day);
            calendar.add(calendar.HOUR, hours);
            calendar.add(calendar.MINUTE, minute);
            calendar.add(calendar.SECOND, second);
            return calendar.getTime();
        } catch (Exception e) {
            return date;
        }
    }
    /**
     * 通过格式获取年、月、日
     * @param date Date 日期，如果为空则设为当前
     * @param format String 格式
     * @return String
     */
    public static int getYMD(Date date,String format){
        if(date==null) {
            date=new Date();
        }
        return Integer.valueOf(format(date, format));
    }

    /**
     * 判断一个生日是不是在一个比较时间的N天内有效(只比月跟天)
     * @param birth Date 生日
     * @param now Date 比较的时间
     * @return 如果是N天内有效，则返回0~(N-1)为正确结果，返回负数或者其他为错误结果
     * @throws ParseException
     */
    public static long calMonthAndDay(Date birth,Date now) throws ParseException{
        if(birth==null || now==null) {
            return -1;
        }
        Calendar birthCal=Calendar.getInstance();//生日
        birthCal.setTime(birth);
        int month = birthCal.get(Calendar.MONTH)+1;
        Calendar nowCal=Calendar.getInstance();//比较时间
        nowCal.setTime(now);
        int nowMonth = nowCal.get(Calendar.MONTH)+1;
        if(nowMonth==12&&month==1){
            String a = "2015-"+"12-"+ nowCal.get(Calendar.DATE);
            String b = "2016-"+"01-"+ birthCal.get(Calendar.DATE);
            Date aDate = format(a,DateUtil.YYYY_MM_DD);
            Date bDate = format(b,DateUtil.YYYY_MM_DD);
            return (bDate.getTime()-aDate.getTime())/(24*60*60*1000);
        }
        //判断生日当年是2月的特殊情况
        if(birthCal.get(Calendar.MONTH)==1){
            Calendar nowCalBack=Calendar.getInstance();//比较时间设置为2月
            nowCalBack.setTime(now);
            nowCalBack.set(Calendar.MONTH, 1);// 把日期设置为2月
            nowCalBack.set(Calendar.DATE, 1);// 把日期设置为当月第一天
            nowCalBack.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
            if(nowCalBack.get(Calendar.DATE)<birthCal.get(Calendar.DATE))
            {
                return -1;//比较的月份总天数小于生日时的天，就是不是每年都能过生日的衰人
            }
        }

        birthCal.set(Calendar.YEAR, nowCal.get(Calendar.YEAR));
        return (birthCal.getTimeInMillis()-nowCal.getTimeInMillis())/(24*60*60*1000);
    }
    /**
     * 获取给定日前或者后intevalDay天的日期并按时间格式格式化，前传入负数，后传入正数
     *
     * @param refenceDate
     *            Date 给定日期
     * @param intevalDays
     *            int 间隔天数
     * @param format
     *              String 时间格式
     * @return String 计算后的日期格式
     */
    public static String getNextDate(Date refenceDate, int intevalDays, String format) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)
                    + intevalDays);
            if (StringUtils.isEmpty(format)) {
                format = YYYY_MM_DD_HH_MM_SS;
            }
            return format(calendar.getTime(), format);
        } catch (Exception ee) {
            return "";
        }
    }
    /**
     * 获取当前时间 yyyy
     * @return
     */
    public static String getCurrentTightYear() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(YYYY);
        String time = df.format(date);
        return time;
    }
    /**
     *
     * @title:获取两个日期间的所有时间，以天为单位，如果开始时间大于结束时间，返回为空
     * @param before 开始日期 格式yyyyMMdd
     * @param after 结束日期 格式yyyyMMdd
     * @return
     * @return:List<Integer> 两个日期间的所有时间，数字类型，如果开始时间大于结束时间，返回为空
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static List<Integer> getTightDays(String before, String after){
        List<Integer> list = new ArrayList<Integer>();
        DateFormat dfTight = new SimpleDateFormat(YYYY_MM_DD_TIGHT);
        try {
            Date beforeDate = dfTight.parse(before);
            Date afterDate = dfTight.parse(after);
            Calendar beforeCal = Calendar.getInstance();
            beforeCal.setTime(beforeDate);
            if (afterDate.before(beforeCal.getTime())) {//开始日期不能大于结束日期
                return null;
            }
            list.add(Integer.parseInt(dfTight.format(beforeCal.getTime())));
            while (afterDate.after(beforeCal.getTime())) {
                beforeCal.add(Calendar.DAY_OF_YEAR, 1);
                list.add(Integer.parseInt(dfTight.format(beforeCal.getTime())));
            }
        } catch (ParseException e) {
            log.error("--------------获取两个日期间的所有时间转换时间时出错-------------------");
            log.error(e.getMessage(), e);
        }
        return list;
    }
    /**
     *
     * @title:将yyyy-MM-dd 类型的时间转换成yyyyMMdd的String字符串
     * @param refenceDate 要转换的日期 yyyy-MM-dd 格式
     * @return
     * @return:String 转换后的yyyyMMdd日期
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static String getTightDate(String refenceDate){
        DateFormat df = new SimpleDateFormat(YYYY_MM_DD);
        DateFormat dfTight = new SimpleDateFormat(YYYY_MM_DD_TIGHT);
        String time = "";
        try {
            Date refence = df.parse(refenceDate);
            time = dfTight.format(refence);
        } catch (ParseException e) {
            log.error("--------------获取两个日期间的所有时间转换时间时出错-------------------");
            log.error(e.getMessage(), e);
        }
        return time;
    }

    /**
     *
     * @title:求当前日期和指定字符串日期的相差天数
     * @param startDate
     * @return
     * @return:long
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static long getTodayIntevalDays(Date startDate) {
        try {
            // 当前时间
            Date currentDate = new Date();

            // 两个时间之间的天数
            long days = (startDate.getTime() - currentDate.getTime() )
                    / ( 24 * 60 * 60 * 1000 );
            return days;
        } catch (Exception ee) {
            log.error(ee.getMessage(), ee);
            return 0L;
        }
    }
    /**
     *
     * @title:求当前日期和指定字符串日期的相差天数(绝对值)
     * @param startDate
     * @return
     * @return:long
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static long getTodayIntevalDaysAbs(Date startDate){
        long days = getTodayIntevalDays(startDate);
        return Math.abs(days);
    }
    /**
     *
     * @title:获得两个时间的时间差，yyyy-MM-dd格式，开始时间晚于结束时间返回负数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @return:long 相距天数
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static long getIntevalDays(String startDate, String endDate) {
        try {
            return getIntevalDays(parse(startDate, YYYY_MM_DD), parse(endDate,
                    YYYY_MM_DD));
        } catch (Exception ee) {
            return 0L;
        }
    }
    /**
     *
     * @title:获得两个时间的时间差，开始时间晚于结束时间返回负数
     * @param startDate
     * @param endDate
     * @return
     * @return:long
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static long getIntevalDays(Date startDate, Date endDate) {
        try {
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();

            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
            long diff = endCalendar.getTimeInMillis()-startCalendar.getTimeInMillis();

            return (diff / (1000 * 60 * 60 * 24));
        } catch (Exception ee) {
            return 0L;
        }
    }
    /**
     * 获取给定日前或者后intevalMonths天的日期并按时间格式格式化，前传入负数，后传入正数
     *
     * @param refenceDate
     *            Date 给定日期
     * @param intevalMonths
     *            int 间隔月数
     * @param format
     *              String 时间格式
     * @return String 计算后的日期格式
     */
    public static String getNextMonth(Date refenceDate, int intevalMonths, String format) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)
                    + intevalMonths);
            if (StringUtils.isEmpty(format)) {
                format = YYYY_MM_DD_HH_MM_SS;
            }
            return format(calendar.getTime(), format);
        } catch (Exception ee) {
            return "";
        }
    }
    /**
     * 获取给定日前或者后intevalMonths天的日期，前传入负数，后传入正数
     *
     * @param refenceDate
     *            Date 给定日期
     * @param intevalMonths
     *            int 间隔月数
     * @return String 计算后的日期 YYYY_MM_DD_HH_MM_SS格式
     */
    public static String getNextMonth(Date refenceDate, int intevalMonths) {
        return getNextMonth(refenceDate, intevalMonths,"");
    }
    /**
     *
     * @title:返回两个时间的月份差，结束时间大于开始时间返回正数，反之返回负数,不足一个月以一个月记
     * @param before 开始时间
     * @param after 结束时间
     * @return
     * @return:int
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static int getIntevalMonths(String before, String after){
        DateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        int diff = 0;
        try {
            Date beforeDate = df.parse(before);
            Date afterDate = df.parse(after);
            Calendar beforeCal = Calendar.getInstance();
            beforeCal.setTime(beforeDate);
            Calendar afterCal = Calendar.getInstance();
            afterCal.setTime(afterDate);
            if (afterCal.after(beforeCal)) {
                while (afterCal.after(beforeCal)) {
                    afterCal.add(Calendar.MONTH, -1);
                    diff ++ ;
                }
            }else {
                while (beforeCal.after(afterCal)) {
                    beforeCal.add(Calendar.MONTH, -1);
                    diff -- ;
                }
            }
        } catch (ParseException e) {
            log.error("--------------获取两个日期间的月份差转换时间时出错-------------------");
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * 判断当前时间是否在两个时间内firstTime必须大于lastTime,否则结果不可预料
     * @param firstTime
     * @param lastTime
     * @return 在first和lastTime之间则返回true
     * @throws ParseException
     */
    public static boolean isBetween(String firstTime, String lastTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);

        Date statrTime = df.parse(firstTime);
        Date endTime = df.parse(lastTime);
        Date now = new Date();
        if(now.after(statrTime) && now.before(endTime)){
            return true;
        }
        return false;
    }

    /**
     * 获取传入时间的最大值
     *
     * @param dates
     * @return
     */
    public static Date getMaxDate(Date... dates) {
        Date maxDate = null;
        for (Date date : dates) {
            if (date == null) {
                continue;
            }
            if (maxDate == null) {
                maxDate = date;
                continue;
            }
            if (date.after(maxDate)) {
                maxDate = date;
            }
        }
        return maxDate;
    }

    /**
     *
     * @title:返回date相差diff天的结束时间  相差diff天的23：59：59，正数为date之后的日期，负为date之前的日期
     * @param date 传入时间
     * @param diff 间隔时间，正为date之后的日期，负为date之前的日期
     * @return
     * @return:date 返回 当天的23：59：59
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static Date getDiffEndTime(Date date, int diff){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, diff);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }


    /**
     *
     * @title:返回date相差diff天的开始时间  相差diff天的00:00:00，正数为date之后的日期，负为date之前的日期
     * @param date 传入时间
     * @param diff 间隔时间，正为date之后的日期，负为date之前的日期
     * @return
     * @return:date 返回 当天的00：00：00
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static Date getDiffStartTime(Date date, int diff){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, diff);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取给定日前或者后intevalMinute分钟的日期并按时间格式格式化，前传入负数，后传入正数
     *
     * @param refenceDate
     *            Date 给定日期
     * @param intevalMinute
     *            int 间隔分钟数
     * @param format
     *              String 时间格式
     * @return String 计算后的日期格式
     */
    public static String getNextMinute(Date refenceDate, int intevalMinute, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refenceDate);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)
                + intevalMinute);
        if (StringUtils.isEmpty(format)) {
            format = YYYY_MM_DD_HH_MM_SS;
        }
        return format(calendar.getTime(), format);
    }

    /**
     * @description 获得间隔第几天的date日期
     * @author 林解元
     * @param date 年月日
     * @param space(正则计算今天之后的，负则计算今天之前的)
     * @return
     */
    public static String getAddDate(String date,Integer space){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(format(date, YYYY_MM_DD));
        if(space!=null && space !=0 ){
            calendar.add(Calendar.DATE,space);
        }
        return format(calendar.getTime());
    }

    /**
     *
     * @title:获取date当天的结束时间
     * @param date yyyy-MM-dd
     * @return
     * @return:String 返回带时区的yyyy-MM-dd'T'HH:mm:ss.SSSZ 格式
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static String getDateEndTime(String date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(format(date, YYYY_MM_DD));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return format(cal.getTime(), YYYY_MM_DD_HH_MM_SS_SSSZ);
    }

    /**
     *
     * @title:获取当前数字月份
     * @return
     * @return:int
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static int getDigitMonth() {
        return getDigitMonth(new Date());
    }

    /**
     *
     * @title:获取传入时间的月份，1-12
     * @param date
     * @return
     * @return:int
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static int getDigitMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     *
     * @title:获取date的数字年份
     * @param date
     * @return
     * @return:int
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static int getDigitYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     *
     * @title:获取今年month月份的最后一天
     * @param month 自然月 1-12月
     * @return
     * @return:String
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static String getMonthLastDay(int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format(cal.getTime());
    }

    /**
     *
     * @title:获取当前年份，space整数时间往后，负数时间往前
     * @param space
     * @return
     * @return:String
     * @throws:
     * @description:
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static String getCurrentYear(Integer space) {
        return format(getAddYear(new Date(), space), YYYY);
    }
}