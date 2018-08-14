package com.moseeker.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

/**
 * @category 日期工具类
 */
public final class DateUtil {

	public static final Date currentTime() {
		return new Date();
	}

	/**
	 * Date类型转换成String
	 *
	 * @param date
	 * @param pattern
	 * @return 日期文字描述
	 */
	public static final String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		DateFormat formatter = getDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * Calendar类型转换成String
	 *
	 * @param date
	 * @param pattern
	 * @return 日期文字描述
	 */
	public static final String format(Calendar date, String pattern) {
		return format(date.getTime(), pattern);
	}

	/**
	 * String类型转换成Date
	 *
	 * @param text
	 * @param pattern
	 * @return 日期文字描述
	 * @throws Exception
	 */
	public static final Date parseDate(String text, String pattern) throws Exception {
		if (StringUtils.isEmpty(text)) {
			return null;
		}
		DateFormat formatter = getDateFormat(pattern);
		try {
			return formatter.parse(text);
		} catch (ParseException e) {
			throw new Exception("ParseDate failed. [" + text + "]");
		}
	}

	/**
	 * String类型转换成Calendar
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static final Calendar parseCalendar(String text, String pattern) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(text, pattern));
		return calendar;
	}

	public static final boolean validDate(String str, String pattern) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 获取年月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public static final Date getFirstDayOfMonth(int year, int month) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}


	/**
	 * 根据出生日期计算出年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public static final Integer calculateAgeByBirthday(Date birthday) throws NumberFormatException {
		if (birthday == null) {
			return 0;
		}
		int birthdayYear = Integer.parseInt(format(birthday, C.FORMAT_YYYYMMDD).split("-")[0]);
		int birthdayMonth = Integer.parseInt(format(birthday, C.FORMAT_YYYYMMDD).split("-")[1]);
		int nowYear = Integer.parseInt(format(currentTime(), C.FORMAT_YYYYMMDD).split("-")[0]);
		int nowMonth = Integer.parseInt(format(currentTime(), C.FORMAT_YYYYMMDD).split("-")[1]);
		int age = nowYear - birthdayYear;
		if (nowMonth < birthdayMonth) {
			age--;
		}
		return age;
	}

	/**
	 * 时间加减
	 * 
	 * @param source
	 * @param year
	 * @param month
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static final Date dateAdd(Date source, int year, int month, int date, int hour, int minute, int second) {
		if (source == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(source);
		c.add(Calendar.YEAR, year);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DATE, date);
		c.add(Calendar.HOUR, hour);
		c.add(Calendar.MINUTE, minute);
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}

	/**
	 * 获取正确的DateFormat对象
	 *
	 * @param pattern
	 * @return
	 */
	private static final DateFormat getDateFormat(String pattern) {
		DateFormat df = new SimpleDateFormat(pattern, Locale.CHINA);
		return df;
	}

    public static final String SHOT_DATE = "yy-MM-dd";
    public static final String NORMAL_DATE = "yyyy-MM-dd";
    public static final String MINUTE_TIME = "yyyy-MM-dd HH:mm";
    public static final String SHOT_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_TIME = "yyyy-MM-dd HH:mm:ss sss";

    private static final SimpleDateFormat SHOT_DATE_SDF = new SimpleDateFormat(SHOT_DATE);
    private static final SimpleDateFormat normalDateSDF = new SimpleDateFormat(NORMAL_DATE);
    private static final SimpleDateFormat minuteTimeSDF = new SimpleDateFormat(SHOT_DATE);
    private static final SimpleDateFormat shotTimeSDF = new SimpleDateFormat(SHOT_TIME);
    private static final SimpleDateFormat longTimeSDF = new SimpleDateFormat(LONG_TIME);

    public static String dateToPattern(Date date, String pattern) {
        DateTime dt = new DateTime(date);
        return dt.toString(pattern);
    }

    public static String dateToShortDate(Date date) {
        synchronized(SHOT_DATE_SDF) {
            return SHOT_DATE_SDF.format(date);
        }
    }

    public static String dateToMinuteDate(Date date) {
        synchronized(minuteTimeSDF) {
            return minuteTimeSDF.format(date);
        }
    }

    public static String dateToNormalDate(Date date) {
        synchronized(normalDateSDF) {
            return normalDateSDF.format(date);
        }
    }

    public static String dateToShortTime(Date date) {
        synchronized (shotTimeSDF) {
            return shotTimeSDF.format(date);
        }
    }

    public static String dateToLongTime(Date date) {
        synchronized (longTimeSDF) {
            return longTimeSDF.format(date);
        }
    }

    public static Date shortDateToDate(String shortDate) throws ParseException {
        synchronized (SHOT_DATE_SDF) {
            Date date = SHOT_DATE_SDF.parse(shortDate);
            return date;
        }
    }

    public static Date nomalDateToDate(String normalDate) throws ParseException {
        synchronized (normalDateSDF) {
            Date date = normalDateSDF.parse(normalDate);
            return date;
        }
    }

    public static Date shortTimeToDate(String shortTime) throws ParseException {
        synchronized (shotTimeSDF) {
            Date date = shotTimeSDF.parse(shortTime);
            return date;
        }
    }

    public static Date longTimeToDate(String longTime) throws ParseException {
        synchronized (longTimeSDF) {
            Date date = longTimeSDF.parse(longTime);
            return date;
        }
    }

    /**
     * 得到本月最后一天的日期
     *
     * @return Date
     * @Methods Name getLastDayOfMonth
     */
    public static long getLastDayOfMonth() {
        Calendar cDay = Calendar.getInstance();
        cDay.set(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                23,
                59,
                59
        );
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cDay.getTimeInMillis();
    }

    /**
     * 计算本月剩余秒数, 月末-当前
     *
     * @return
     */
    public static long calcCurrMonthSurplusSeconds() {
        return (getLastDayOfMonth() - System.currentTimeMillis()) / 1000;
    }

    public static int getLastDayOfMonth(int year, int month) {
        LocalDate localDate = LocalDate.of(year, month, 1);
        LocalDate lastData = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return lastData.getDayOfMonth();
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     *
     * @return
     */
    public static LocalDateTime getCurrentTwoMonthStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        LocalDateTime now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 2)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 3 && currentMonth <= 4)
                c.set(Calendar.MONTH, 2);
            else if (currentMonth >= 5 && currentMonth <= 6)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 7 && currentMonth <= 8)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 9 && currentMonth <= 10)
                c.set(Calendar.MONTH, 8);
            else if (currentMonth >= 11 && currentMonth <= 12)
                c.set(Calendar.MONTH, 10);
            c.set(Calendar.DATE, 1);
            now = LocalDateTime.parse(normalDateSDF.format(c.getTime()) + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public static LocalDateTime getCurrentTwoMonthEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        LocalDateTime now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 2) {
                c.set(Calendar.MONTH, 1);
            } else if (currentMonth >= 3 && currentMonth <= 4) {
                c.set(Calendar.MONTH, 3);
            } else if (currentMonth >= 5 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
            } else if (currentMonth >= 7 && currentMonth <= 8) {
                c.set(Calendar.MONTH, 7);
            } else if (currentMonth >= 9 && currentMonth <= 10) {
                c.set(Calendar.MONTH, 9);
            } else if (currentMonth >= 11 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
            }
            c.set(Calendar.DATE, getLastDayOfMonth(LocalDate.now().getYear(), c.get(Calendar.MONTH) + 1));
            now = LocalDateTime.parse(normalDateSDF.format(c.getTime()) + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     *
     * @return
     */
    public static LocalDateTime getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        LocalDateTime now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = LocalDateTime.parse(normalDateSDF.format(c.getTime()) + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public static LocalDateTime getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        LocalDateTime now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = LocalDateTime.parse(normalDateSDF.format(c.getTime()) + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /**
     * 取得日期：年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year;
    }


    /**
     * format date
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        String strDate = null;
        try {
            if (pattern == null) {
                pattern = "yyyy-MM-dd";
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            strDate = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {
        int season = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 取得日期：年
     *
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH) + 1;
        StringBuffer stringBuffer = new StringBuffer();
        if (month <= 9) {
            stringBuffer.append("0");
            stringBuffer.append(month);
        } else {
            stringBuffer.append(month);
        }
        return stringBuffer.toString();
    }

    /**
     * 日期补全：<br/>
     * 2017 -> 2017-01-01<br/>
     * 2017-05 ->  2017-05-01<br/>
     * @param date
     * @param splitRegex
     * @return
     */
    public static String dateRepair(String date, String splitRegex) throws ParseException {
        if (com.moseeker.util.StringUtils.isNullOrEmpty(date)) {
            return "";
        }
        date = date.replaceAll(splitRegex, "-");
        date += Stream.generate(() -> "-01").limit(3-date.split("-").length).collect(Collectors.joining(""));
        nomalDateToDate(date);
        return date;
    }

    /**
     * 截取一定长度的日期格式，年月日之间用.隔开
     * @param date
     * @param length
     * @return
     * @throws ParseException
     */
    public static String dateFormat(String date, int length){
        if (com.moseeker.util.StringUtils.isNullOrEmpty(date)) {
            return "";
        }
        date = date.substring(0,length);
        date = date.replace("-",".");
        return date;
    }

    public static String appendTime(Object startTime, Object endTime, Object endUntilNow ){
        String start = "";
        if (startTime != null) {
            start = ((String)startTime).substring(0, 7).replace("-", ".");
        }
        String end = "";
        if (endTime == null || 1 == (int) endUntilNow) {
            end = "至今";
        } else {
            end = ((String)endTime).substring(0, 7).replace("-", ".");
        }
        String time = start + " - " + end;
        return  time;
    }

}
