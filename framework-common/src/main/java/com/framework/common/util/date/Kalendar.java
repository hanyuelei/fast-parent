package com.framework.common.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * 
 * @version 1.0 
 */
public class Kalendar {
	
	// 获得当前系统日期
	public static String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public static String getDate(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	// 获得当前系统时间
	public static String getTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	// 获得当前系统时间的星期日期
	public static String getWeekday() {
		return new SimpleDateFormat("E").format(new Date());
	}

	public static String getWeekday(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("E").format(calendar.getTime());
	}

	// 获得当前系统时间的年月
	public static String getYearAndMonth() {
		return new SimpleDateFormat("yyyy-MM").format(new Date());
	}

	public static String getYearAndMonth(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
	}

	// 获得当前系统时间是一年中的第几天
	public static String getDateInYear() {
		return new SimpleDateFormat("DDD").format(new Date());
	}

	public static String getDateInYear(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("DDD").format(calendar.getTime());
	}

	// 获得当前系统时间是一年中的第几周
	public static String getWeekInYear() {
		return new SimpleDateFormat("ww").format(new Date());
	}

	public static String getWeekInYear(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("ww").format(calendar.getTime());
	}

	// 获得当前系统时间是一个月中的第几周
	public static String getWeekInMonth() {
		return new SimpleDateFormat("WW").format(new Date());
	}

	public static String getWeekInMonth(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("WW").format(calendar.getTime());
	}

	// 获得n天以前的日期
	public static String getDateByBefore(int beforeNum) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, -1 * beforeNum);
		return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
	}

	public static String getDateByBefore(int year, int month, int day,
			int beforeNum) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, -1 * beforeNum);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	/**
	 * 得到指定日期前指定天数的日期
	 * 
	 * @param dateString
	 * @param beforeNum
	 * @return
	 */
	public static String getDateByBefore(String dateString, int beforeNum) {

		Calendar calendar = Calendar.getInstance();

		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

			calendar.setTime(date);
		} catch (Exception e) {
			return "";
		}

		calendar.add(GregorianCalendar.DAY_OF_YEAR, -1 * beforeNum);

		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	/**
	 * 得到指定日期后指定天数的日期
	 * 
	 * @param dateString
	 * @param beforeNum
	 * @return
	 */
	public static String getDateByAfter(String dateString, int afterNum) {

		Calendar calendar = Calendar.getInstance();

		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

			calendar.setTime(date);
		} catch (Exception e) {
			return "";
		}

		calendar.add(GregorianCalendar.DAY_OF_YEAR, afterNum);

		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	// 获得n天以前的日期的星期日期
	public static String getWeekdayByBefore(int beforeNum) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, -1 * beforeNum);
		return new SimpleDateFormat("E").format(now.getTime());
	}

	public static String getWeekdayByBefore(int year, int month, int day,
			int beforeNum) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, -1 * beforeNum);
		return new SimpleDateFormat("E").format(calendar.getTime());
	}

	// 获得n天以后的日期
	public static String getDateByAfter(int afterNum) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, afterNum);
		return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
	}

	public static String getDateByAfter(int year, int month, int day,
			int afterNum) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, afterNum);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	// 获得n天以后的日期的星期日期
	public static String getWeekdayByAfter(int afterNum) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, afterNum);
		return new SimpleDateFormat("E").format(now.getTime());
	}

	public static String getWeekdayByAfter(int year, int month, int day,
			int afterNum) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, afterNum);
		return new SimpleDateFormat("E").format(calendar.getTime());
	}

	// 获得当前系统日期所处周的周末日期（周六）
	public static String getDateOfWeekend() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, 7 - now.get(Calendar.DAY_OF_WEEK));
		return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
	}

	public static String getDateOfWeekend(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, 7 - calendar
				.get(GregorianCalendar.DAY_OF_WEEK));
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	// 获得当前系统日期所处周的周起始日期（周日）
	public static String getDateOfWeekstart() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, 1 - now.get(Calendar.DAY_OF_WEEK));
		return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
	}

	public static String getDateOfWeekstart(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, 1 - calendar
				.get(GregorianCalendar.DAY_OF_WEEK));
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	// 获得当前系统日期所处月的天数
	public static int getDateOfMonthend() {
		Calendar now = Calendar.getInstance();
		return now.getActualMaximum(Calendar.DATE);
	}

	public static int getDateOfMonthend(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return calendar.getActualMaximum(GregorianCalendar.DATE);
	}

	/**
	 * 判断传入的日期是否大于当前系统时间额定的时间
	 * 
	 * @param dateString：要查询的日期，格式为"yyyy-MM-dd
	 *            HH:mm:ss"
	 * @return：boolean
	 */
	public static boolean IsOverTime(String timeString, int rating) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(timeString);
			Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date()));

			long min = date.getTime();
			long nowmin = now.getTime();

            return (nowmin - min) > rating * 60 * 1000;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 得到指定的日期是星期几
	 * 
	 * @param dateString：要查询的日期，格式为"yyyy-MM-dd"
	 * @return：日期的星期
	 */
	public static String getWeekdayByDateString(String dateString) {
		String weekday = "";

		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

			weekday = new SimpleDateFormat("E").format(date);
		} catch (Exception e) {

		}

		return weekday;
	}

	/**
	 * 得到指定的日期是星期几
	 * 
	 * @param date：要查询的日期
	 * @return：日期的星期
	 */
	public static String getWeekdayByDate(Date date) {
		return new SimpleDateFormat("E").format(date);
	}

	/**
	 * 将当前系统时间转换成直至Millisecond的样式
	 * 
	 * @return
	 */
	public static String getDateTimeZone() {
		return new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
	}
	
	public static String getDateTimeZones() {
		return new SimpleDateFormat("MM-dd HH:mm").format(new Date());
	}
	/**
	 * 系统当前时间后2天的时间
	 * @return
	 */
	public static String getDateTimetwo() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 可以方便地修改日期格式
		String datetime = dateFormat.format(now);
		Date afterDate = new Date(now.getTime() + 7200000);
		String format = dateFormat.format(afterDate);
		return format;
	}
	/**
	 * 系统当前时间后2天的时间（小时）
	 * @return
	 */
	public static String getDateTimetwoh() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH");// 可以方便地修改日期格式
		String datetime = dateFormat.format(now);
		Date afterDate = new Date(now.getTime() + 7200000);
		String format = dateFormat.format(afterDate);
		return format;
	}
	public static String  nowTimeString(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String timeStemp = df.format(new Date());
		return timeStemp;
	}
	// 获得当前系统日期+时间
	public static String getDateTime() {
		return getDate()+" "+getTime();
	}
	public static String getWeek(){
		Calendar c = Calendar.getInstance();
		String resultString ="";
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 1:
		resultString = "0"; // sunday
		break;
		case 2:
		resultString = "1"; // monday
		break;
		case 3:
		resultString = "2"; // tuesday
		break;
		case 4:
		resultString = "3"; // wednesday
		break;
		case 5:
		resultString = "4"; // thursday
		break;
		case 6:
		resultString = "5"; // friday
		break;
		case 7:
		resultString = "6"; // saturday
		break;
		default:
		resultString = "Error Date";
		break;
		}
		System.out.println( "今天:" + resultString );
		return resultString;
		}
}
