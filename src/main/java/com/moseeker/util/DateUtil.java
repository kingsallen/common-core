package com.moseeker.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.util.StringUtils;

/**
 * @category 日期工具类
 */
public final class DateUtil {

	public static final String SHOT_DATE = "yy-MM-dd";
	public static final String NORMAL_DATE = "yyyy-MM-dd";
	public static final String SHOT_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String LONG_TIME = "yyyy-MM-dd HH:mm:ss sss";

	private static final SimpleDateFormat SHOT_DATE_SDF = new SimpleDateFormat(SHOT_DATE);
	private static final SimpleDateFormat normalDateSDF = new SimpleDateFormat(NORMAL_DATE);
	private static final SimpleDateFormat shotTimeSDF = new SimpleDateFormat(SHOT_TIME);
	private static final SimpleDateFormat longTimeSDF = new SimpleDateFormat(LONG_TIME);

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
	 * 获取年月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public static final Date getLastDayOfMonth(int year, int month) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MILLISECOND, -1);
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
}
