package com.jbwz.core.common.util;


import org.apache.commons.lang3.StringUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能描述：日期工具
 */
public final class DateUtil {

    public static final String TIMEZONE_GMT8 = "GMT+08:00";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd
     */
    public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";

    // add by junpingshi
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";

    /**
     * 当前时间Date
     *
     * @return
     */
    public static Date nowDate() {
        return new Date(nowTime());
    }

    /**
     * 当前时间long
     *
     * @return
     */
    public static long nowTime() {
        return System.currentTimeMillis();
    }


    /**
     * 执行型别转换:字符串表示的日期--->>日期型日期
     * <li>1 格式为 yyyy-MM-dd
     * <li>2 格式为 yyyy-MM-dd HH:mm:ss
     * <li>3 格式为 yyyy/MM/dd HH:mm:ss
     * <li>4 格式为 yy-MM-dd HH:mm:ss
     * <li>5 格式为 yyyy年MM月dd日 HH时mm分ss秒
     * <li>6 格式为 dd/MM/yyyy
     * <li>7 格式为 yyyy年MM月dd日 HH时mm分
     * <li>8 格式为 HH:mm:ss
     * <li>9 格式为 yyyy-MM-ddHH:mm
     * <li>10 格式为 yyyy-MM-dd HH:mm
     * <li>默认格式为 yy年MM月dd日 HH时mm分ss秒
     */
    public static Date stringToDate(final int style, final String strDate) {

        SimpleDateFormat format = null;

        if (null == strDate || "".equals(strDate) || "null".equals(strDate)) {

            return null;
        } else {

            switch (style) {
                case 1:
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    break;
                case 2:
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    break;
                case 3:
                    format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    break;
                case 4:
                    format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                    break;
                case 5:
                    format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                    break;
                case 6:
                    format = new SimpleDateFormat("dd/MM/yyyy");
                    break;
                case 7:
                    format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
                    break;
                case 8:
                    format = new SimpleDateFormat("HH:mm:ss");
                    break;
                case 9:
                    format = new SimpleDateFormat("yyyy-MM-ddHH:mm");
                    break;
                case 10:
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    break;
                case 11:
                    format = new SimpleDateFormat("MMddyy");
                    break;
                case 12:
                    format = new SimpleDateFormat("yyyy-MM-ddHHmm");
                    break;
                default:
                    format = new SimpleDateFormat("yy年MM月dd日 HH时mm分ss秒");
                    break;
            }
            try {
                return format.parse(strDate);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * 执行型别转换:日期型日期--->>字符串表示的日期
     * <li>1 格式为 yyyy-MM-dd
     * <li>2 格式为 yyyy-MM-dd HH:mm:ss
     * <li>3 格式为 yyyy/MM/dd HH:mm:ss
     * <li>4 格式为 yy-MM-dd HH:mm:ss
     * <li>5 格式为 yyyy年MM月dd日 HH时mm分ss秒
     * <li>6 格式为 yyyy年MM月dd日
     * <li>7 格式为 yy-MM-dd
     * <li>8 格式为 HH:mm:ss
     * <li>9 格式为 yyyy年MM月dd日 HH时mm分
     * <li>10 格式为 yyyyMMdd HH:mm
     * <li>11 格式为 HH:mm
     * <li>默认格式为 yy年MM月dd日 HH时mm分ss秒
     */
    public static String dateToString(final int style, final Date date) {

        SimpleDateFormat format = null;

        if (null == date || "null".equals(date) || "".equals(date)) {

            return "";
        } else {

            switch (style) {
                case 1:
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    break;
                case 2:
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    break;
                case 3:
                    format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    break;
                case 4:
                    format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                    break;
                case 5:
                    format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                    break;
                case 6:
                    format = new SimpleDateFormat("yyyy年MM月dd日");
                    break;
                case 7:
                    format = new SimpleDateFormat("yy-MM-dd");
                    break;
                case 8:
                    format = new SimpleDateFormat("HH:mm:ss");
                    break;
                case 9:
                    format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
                    break;
                case 10:
                    format = new SimpleDateFormat("yyyyMMdd HH:mm");
                    break;
                case 11:
                    format = new SimpleDateFormat("HH:mm");
                    break;
                case 12:
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    break;
                case 13:
                    format = new SimpleDateFormat("yyyy/MM/dd");
                    break;
                default:
                    format = new SimpleDateFormat("yy年MM月dd日 HH时mm分ss秒");
                    break;
            }
            try {
                return format.format(date);
            } catch (NumberFormatException e) {
                return "";
            }
        }
    }

    /**
     * 执行型别转换:字符串表示的日期--->>日期型日期
     */
    public static Date stringToDate(final String format, final String strDate) {
        SimpleDateFormat dateFormat = null;
        if (null == strDate || "".equals(strDate) || "null".equals(strDate)) {
            return null;
        } else {
            dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            try {
                return dateFormat.parse(strDate);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * 执行型别转换:日期型日期--->>字符串表示的日期
     */
    public static String dateToString(final String format, final Date date) {
        SimpleDateFormat dateFormat = null;
        if (null == date || "".equals(date) || "null".equals(date)) {
            return "";
        } else {
            dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            try {
                return dateFormat.format(date);
            } catch (NumberFormatException nfe) {
                return "";
            }
        }
    }

    /**
     * 获取当前日期的long值对应的字符串
     */
    public synchronized static String getTimeStamp() {
        Date d = new Date();
        return String.valueOf(d.getTime());
    }

    /**
     * 获取当前年份
     */
    public static final int getCurrentYear() {
        return new GregorianCalendar().get(GregorianCalendar.YEAR);
    }

    public static String getCurrentDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(new Date());
        return strDate.substring(4, 8);
    }

    public static String getCurrentWeek() {
        int week = new GregorianCalendar().get(GregorianCalendar.DAY_OF_WEEK);
        if (week == 1) {
            return "日";
        } else if (week == 2) {
            return "一";
        } else if (week == 3) {
            return "二";
        } else if (week == 4) {
            return "三";
        } else if (week == 5) {
            return "四";
        } else if (week == 6) {
            return "五";
        } else {
            return "六";
        }
    }

    public static String getWeek(final Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int week = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        if (week == 1) {
            return "星期日";
        } else if (week == 2) {
            return "星期一";
        } else if (week == 3) {
            return "星期二";
        } else if (week == 4) {
            return "星期三";
        } else if (week == 5) {
            return "星期四";
        } else if (week == 6) {
            return "星期五";
        } else {
            return "星期六";
        }
    }

    /**
     * 得到指定时间在指定年数之后的时间(传入负整数表示之前)
     */
    public static Date getDateAfterYesr(final Date date, final int afterYear) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, afterYear);
        return calendar.getTime();
    }

    /**
     * 得到指定时间在指定月数之后的时间(传入负整数表示之前)
     */
    public static Date getDateAfterMonth(final Date date, final int afterMonth) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, afterMonth);
        return calendar.getTime();
    }

    /**
     * 得到指定时间在指定天数之后的时间(传入负整数表示之前)
     */
    public static Date getDateAfter(final Date date, final int afterDay) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, afterDay);
        return calendar.getTime();
    }

    /**
     * 得到指定时间在指定小时数之后的时间(传入负整数表示之前)
     */
    public static Date getDateAfterHour(final Date date, final int afterHour) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, afterHour);
        return calendar.getTime();
    }

    /**
     * 得到指定时间在指定分钟之后的时间(传入负整数表示之前) String版本 for计算最早到店时间
     *
     * @throws ParseException
     */
    public static String getDateAfterMin(String dateStr, final int afterMin) throws ParseException {
        if (dateStr.contains(":")) {
            dateStr = dateStr.replace(":", "");
        }

        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        Date Datesource = format.parse(dateStr);
        Date Datetarget = getDateAfterMinute(Datesource, afterMin);
        return format.format(Datetarget);
    }

    /**
     * 得到指定时间在指定分钟数之后的时间(传入负整数表示之前)
     */
    public static Date getDateAfterMinute(final Date date, final int afterMinute) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, afterMinute);
        return calendar.getTime();
    }

    /**
     * 得到指定时间在指定秒数之后的时间(传入负整数表示之前)
     */
    public static Date getDateAfterSecond(final Date date, final int afterSecond) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, afterSecond);
        return calendar.getTime();
    }

    /**
     * 获得一个时间(Date对象)在当天的起始时刻的时间(Date对象)
     */
    public static Date getDayStart(Date date) {

        String dateStr = dateToString("yyyy-MM-dd", date);
        date = stringToDate("yyyy-MM-dd", dateStr);
        return date;
    }

    /**
     * 判断两个时间(Date对象)精确到天数以后是否相等
     */
    public static boolean dateEquals(final Date date1, final Date date2) {
        String d1 = dateToString("yyyy-MM-dd", date1);
        String d2 = dateToString("yyyy-MM-dd", date2);
        if (d1.equalsIgnoreCase(d2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 格式化字符串到指定格式的字符串
     */
    public static String formatDateString(final String strDate, final String orgFormat, final String desFormat) {
        return DateUtil.dateToString(desFormat, DateUtil.stringToDate(orgFormat, strDate));
    }

    public static String getFormatDateInTimeZone(final Date date, final String timeZoneId, final String format) {
        //
        String timeZone = timeZoneId;
        if (timeZoneId == null || timeZoneId.trim().equals("")) {
            timeZone = TIMEZONE_GMT8;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        //
        fmt.setTimeZone(TimeZone.getTimeZone(timeZone));
        return fmt.format(date);
    }

    public static Date formatDateInTimeZone(final String date, final String timeZoneId, final String format)
            throws Exception {
        if (date == null || date.trim() == "") {
            return null;
        }

        String timeZone = timeZoneId;
        if (timeZoneId == null || timeZoneId.trim().equals("")) {
            timeZone = TIMEZONE_GMT8;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        //
        fmt.setTimeZone(TimeZone.getTimeZone(timeZone));
        return fmt.parse(date);
    }

    /**
     * 将Date类转换为XMLGregorianCalendar
     *
     * @param date
     * @return
     */
    public static XMLGregorianCalendar dateToXmlDate(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DatatypeFactory dtf = null;
        try {
            dtf = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
        }
        XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
        dateType.setYear(cal.get(Calendar.YEAR));
        // 由于Calendar.MONTH取值范围为0~11,需要加1
        dateType.setMonth(cal.get(Calendar.MONTH) + 1);
        dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
        dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
        dateType.setMinute(cal.get(Calendar.MINUTE));
        dateType.setSecond(cal.get(Calendar.SECOND));
        return dateType;
    }

    /**
     * 将XMLGregorianCalendar转换为Date
     *
     * @param cal
     * @return
     */
    public static Date xmlDate2Date(final XMLGregorianCalendar cal) {
        return cal.toGregorianCalendar().getTime();
    }

    /**
     * 转换成字符串（年月日）
     *
     * @param date 日期
     * @return 转换成字符串（年月日）
     */
    public static String formatDateGB(final Date date) {
        String value = "";
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            value = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DATE) + "日";
        }
        return value;

    }

    /**
     * 转换成字符串，UTC格式
     *
     * @param date 日期
     * @return 转换成字符串，UTC格式
     */
    public static String formatDateUTC(final Date date) {
        String value = "";
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            switch (cal.get(Calendar.MONTH)) {
                case 0:
                    value = "Jan";
                    break;
                case 1:
                    value = "Feb";
                    break;
                case 2:
                    value = "Mar";
                    break;
                case 3:
                    value = "Apr";
                    break;
                case 4:
                    value = "May";
                    break;
                case 5:
                    value = "Jun";
                    break;
                case 6:
                    value = "Jul";
                    break;
                case 7:
                    value = "Aug";
                    break;
                case 8:
                    value = "Sep";
                    break;
                case 9:
                    value = "Oct";
                    break;
                case 10:
                    value = "Nov";
                    break;
                case 11:
                    value = "Dec";
                    break;
            }
            dateFormat.applyPattern("dd, yyyy");
            value = value + " " + dateFormat.format(date);
        }
        return value;
    }

    /**
     * 转换成字符串
     *
     * @param date 日期
     * @return 转换成字符串，格式为 October 25,2011(Tuesday);
     */
    public static String formatDateEn(final Date date) {
        String value = "";
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            switch (cal.get(Calendar.MONTH)) {
                case 0:
                    value = "Jan";
                    break;
                case 1:
                    value = "Feb";
                    break;
                case 2:
                    value = "Mar";
                    break;
                case 3:
                    value = "Apr";
                    break;
                case 4:
                    value = "May";
                    break;
                case 5:
                    value = "Jun";
                    break;
                case 6:
                    value = "Jul";
                    break;
                case 7:
                    value = "Aug";
                    break;
                case 8:
                    value = "Sep";
                    break;
                case 9:
                    value = "Oct";
                    break;
                case 10:
                    value = "Nov";
                    break;
                case 11:
                    value = "Dec";
                    break;
            }
            dateFormat.applyPattern("dd,yyyy");
            value = value + " " + dateFormat.format(date) + "(" + getDayOfWeekEN(date) + ")";
        }
        return value;
    }

    /**
     * 取得一个日期是星期几
     *
     * @param date 日期
     * @return 星期
     */
    public static String getDayOfWeekEN(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return "SUN";
            case 2:
                return "MON";
            case 3:
                return "TUE";
            case 4:
                return "WED";
            case 5:
                return "THU";
            case 6:
                return "FRI";
            case 7:
                return "SAT";
            default:
                return "";
        }
    }

    /**
     * 带指定小数位数的String
     *
     * @param dValue double数值
     * @param iScale 保留的小数位数
     * @return 带指定小数位数的String
     */
    public static String formatNumber(final double dValue, final int iScale) {
        if (Double.isNaN(dValue)) {
            return "";
        }

        DecimalFormat df = null;
        StringBuffer sPattern = new StringBuffer("##0");
        if (iScale > 0) {
            sPattern.append(".");
            for (int i = 0; i < iScale; i++) {
                sPattern.append("0");
            }
        }
        df = new DecimalFormat(sPattern.toString());
        return df.format(dValue);
    }

    /**
     * 比较日期
     * <p>
     * dt1-dt2
     *
     * @param dt1
     * @param dt2
     * @return 1 大于 -1 小于 0 等于
     */
    public static int compare_date(final Date dt1, final Date dt2) {
        try {

            if (dt1.getTime() > dt2.getTime()) {// dt1 在dt2前
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {// dt1在dt2后
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static String dateDiff(final String startTime, final String endTime, final String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        // 获得两个时间的毫秒时间差异
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            hour = diff % nd / nh + day * 24;// 计算差多少小时
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println(
                    "时间相差：" + day + "天" + (hour - day * 24) + "小时" + (min - day * 24 * 60) + "分钟" + sec + "秒。");
            System.out.println("hour=" + hour + ",min=" + min);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hour + "h" + min + "m";
    }

    /**
     * 将时间串转化为UTC 通用标准时间 （此时间与时区有关 相差8小时）
     *
     * @param time
     * @return
     * @throws Exception
     */
    public static Date timeConvertUTCDate(String time) throws Exception {

        if (StringUtils.isNotBlank(time)) {
            time = time.replace("Z", " UTC");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date date = sdf.parse(time);
            return date;
        }
        return null;

    }

    /**
     * 处理时间 日+月（转移成英文月）
     *
     * @param ddmmDate DDMM
     * @return DD英文MONTH
     */
    public static String transfromDateDDMMM(final String ddmmDate) {
        String beginDT = ddmmDate.substring(0, 2);
        String endDT = null;
        if (ddmmDate.length() == 4) {
            endDT = ddmmDate.substring(2, 4);
        } else if (ddmmDate.length() == 3) {
            endDT = ddmmDate.substring(2, 3);
        }
        switch (endDT) {
            case "01":
                endDT = "Jan";
                break;
            case "1":
                endDT = "Jan";
                break;
            case "02":
                endDT = "Feb";
                break;
            case "2":
                endDT = "Feb";
                break;
            case "03":
                endDT = "Mar";
                break;
            case "3":
                endDT = "Mar";
                break;
            case "04":
                endDT = "Apr";
                break;
            case "4":
                endDT = "Apr";
                break;
            case "05":
                endDT = "May";
                break;
            case "5":
                endDT = "May";
                break;
            case "06":
                endDT = "Jun";
                break;
            case "6":
                endDT = "Jun";
                break;
            case "07":
                endDT = "Jul";
                break;
            case "7":
                endDT = "Jul";
                break;
            case "08":
                endDT = "Aug";
                break;
            case "8":
                endDT = "Aug";
                break;
            case "09":
                endDT = "Sep";
                break;
            case "9":
                endDT = "Sep";
                break;
            case "10":
                endDT = "Oct";
                break;
            case "11":
                endDT = "Nov";
                break;
            case "12":
                endDT = "Dec";
                break;
            default:
                endDT = "00";
        }
        return beginDT + endDT;
    }

    /**
     * {Date转XMLGregorianCalendar}.
     *
     * @param date
     * @return
     */
    public static XMLGregorianCalendar dateToGregorian(final Date date) {
        if (date == null) {
            return null;
        }
        DatatypeFactory dataTypeFactory;
        try {
            dataTypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(date.getTime());
        return dataTypeFactory.newXMLGregorianCalendar(gc);
    }

    /**
     * {XMLGregorianCalendar转Date}.
     *
     * @param cal
     * @return
     * @throws Exception
     */
    public static Date convertToDate(final XMLGregorianCalendar cal) throws Exception {
        if (cal == null) {
            return null;
        }
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    /**
     * 返回当前日期，格式可以自定义
     *
     * @param pattern
     * @return
     */

    public static String getCurrentDate(String pattern) {
        String result;
        /*
         * DateTime now = new DateTime(); result =
		 * DateTimeFormat.forPattern(pattern).print(now);
		 */
        Date date = new Date();
        result = dateToString(pattern, date);
        return result;
    }

}
