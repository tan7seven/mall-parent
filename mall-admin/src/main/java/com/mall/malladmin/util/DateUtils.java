package com.mall.malladmin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @Description:类说明：日期工具类
 * @author: gzh
 * @date: 2018年9月27日下午2:57:15
 */
public class DateUtils {

    public final static DateFormat YYYYMMDDMMHHSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 获取当月的 天数
     * */
    public static String[] getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        String[] days = new String[maxDate];
        for (int i = 1; i <maxDate ; i++) {
            days[i-1] = maxDate < 10 ? "0"+i+"号" : i+"号";
        }
        return days;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     * */
    public static List<Map<String,String>> getDaysByYearMonth(int year, int month) {
        List<Map<String,String>> days = new ArrayList<Map<String, String>>();
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);

        for (int i = 1; i <=maxDate ; i++) {
            Map<String,String> day = new HashMap<String, String>();
            day.put("name",i < 10 ? "0"+i+"号" : i+"号");
            day.put("value","");
            days.add(day);
        }
        return days;
    }


    /**
     * 字符串转日期
     * @param dateStr
     * @return yyyy-MM-dd HH:mm:ss Date
     */
    public static Date stringToDate(String dateStr){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        Date date = null;
        try {
            date  =sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     *
     * @Description:方法说明：Long型时间转化格式时间
     * @date: 2018年9月27日下午5:06:50
     * @param lo
     * @return String
     */
    public static String longToDate(long lo,String formatStr){
        if(formatStr==null || "".equals(formatStr)) {
            formatStr="yyyy-MM-dd HH:mm:ss";
        }
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat(formatStr);
        return sd.format(date);

    }
    /**
     * 日期转字符串
     * @param date
     * @return yyyy-MM-dd String
     */
    public static String dateToString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        String dateStr = null;
        dateStr  = sdf.format(date);
        return dateStr;
    }

    /**
     * 比较两个日期相差多少天()
     * @param sourceDate
     * @param targetDate
     * @return
     */
    public static Integer expireCompare(Date sourceDate,Date targetDate){
        if(sourceDate.getTime() < (new Date().getTime())){
            return 100000;
        }
        Long sourceTime = sourceDate.getTime();
        Long targetTime = targetDate.getTime();
        Long dayTime = sourceTime - targetTime;
        Integer day = (int)(dayTime / (1000*60*60*24));
        return day;
    }

    /**
     * 比较两个日期相差多少天(生日)
     * @param sourceDate
     * @param targetDate
     * @return
     */
    public static Integer birthdayCompare(Date sourceDate,Date targetDate){
        if(sourceDate.getTime() < (new Date().getTime() - 24*60*60*1000)){
            return 1000000;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");//小写的mm表示的是分钟
        String sourceDateStr = sdf.format(sourceDate);
        String targetDateStr = sdf.format(targetDate);
        try {
            sourceDate = sdf .parse(sourceDateStr);
            targetDate = sdf.parse(targetDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long sourceTime = sourceDate.getTime();
        Long targetTime = targetDate.getTime();
        Long dayTime = sourceTime - targetTime;
        Integer day = (int)(dayTime / (1000*60*60*24));
        return day;
    }

    public static String dateToStringDetail(Date date) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        String dateStr = null;
        dateStr  = sdf.format(date);
        return dateStr;
    }

    /**
     * 判断时间段是否有交集
     * @param leftStartDate
     * @param leftEndDate
     * @param rightStartDate
     * @param rightEndDate
     * @return true有交集
     */
    public static boolean isOverlap(Date leftStartDate, Date leftEndDate, Date rightStartDate, Date rightEndDate) {
        if(leftStartDate == null||leftEndDate == null||rightStartDate == null||rightEndDate == null) return false;
        return
                ((leftStartDate.getTime() >= rightStartDate.getTime())
                        && leftStartDate.getTime() < rightEndDate.getTime())
                        ||
                        ((leftStartDate.getTime() > rightStartDate.getTime())
                                && leftStartDate.getTime() <= rightEndDate.getTime())
                        ||
                        ((rightStartDate.getTime() >= leftStartDate.getTime())
                                && rightStartDate.getTime() < leftEndDate.getTime())
                        ||
                        ((rightStartDate.getTime() > leftStartDate.getTime())
                                && rightStartDate.getTime() <= leftEndDate.getTime());
    }

    /**
     * 当前时间-传入天数据，
     * @param enddays 30
     * @author add by gzh
     * @return String  返回yyyy-MM-dd 23:59:59 字符串
     */
    public static String addDays(Date beginDate,int enddays){
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(beginDate);
        calendar.add(Calendar.DAY_OF_WEEK, enddays-1);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;
        //凌晨00:00:00
        calendar.setTimeInMillis(calendar.getTimeInMillis()-millisecond);
        calendar.setTimeInMillis(calendar.getTimeInMillis()+23*60*60*1000 + 59*60*1000 + 59*1000);
        return dateToStringDetail(calendar.getTime());
    }

    /**
     * 当前时间生成文件名称，时间(YYYYMMDDMMHHSSSSS)+5位随机数据
     * @author add by gzh
     * @return String
     */
    public static String dateToStringFileName(){
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssss");//小写的mm表示的是分钟
        String fileName= sdf.format(new Date())+rannum;
        return fileName;
    }

    /**
     * 自定义格式化：当前 yyyy-MM-dd HH:mm:ss
     * @author add by gzh
     * @param yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String dateToStringNow(String formatStr){
        SimpleDateFormat sdf=new SimpleDateFormat(formatStr);//小写的mm表示的是分钟
        String fileName= sdf.format(new Date());
        return fileName;
    }

    /**
     * 当前时间生成文件名称，时间(YYYYMMDD)
     * @author add by gzh
     * @return String
     */
    public static String dateToString(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");//小写的mm表示的是分钟
        String fileName= sdf.format(new Date());
        return fileName;
    }

    /**
     * 自定义格式化：当前 yyyy-MM-dd HH:mm:ss
     * @author add by gzh
     * @return String
     */
    public static String dateToStringFromat(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        String fileName= sdf.format(new Date());
        return fileName;
    }

    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return true or false
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] arg){
        Date nowDate=new Date();
        String beginDate="2018-03-10 00:00:00";
        String endDate="2019-03-09 23:59:59";
        System.out.println(nowDate);
        System.out.println(stringToDate(beginDate) );
        System.out.println(stringToDate(endDate));
        System.out.println((DateUtils.belongCalendar(nowDate,DateUtils.stringToDate(beginDate) ,stringToDate(endDate))));
    }

    /**
     *
     * @Description:方法说明：
     * @date: 2018年9月27日上午9:30:14
     * @param startTime
     * @param string
     * @return
     */
    public static String format(Long startTime, String string) {
        SimpleDateFormat sdf=new SimpleDateFormat(string);//小写的mm表示的是分钟
        String fileName= sdf.format(startTime);
        return fileName;
    }


}
