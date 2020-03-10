package com.liuguodong.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @ClassName: DateUtil 
 * @Description: 时间工具类
 * @author: Lenovo
 * @date: 2020年1月2日 下午8:30:42
 */
public class DateUtil {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
//	时间格式化  
	public static String format(Date theDate) {
		return dateFormat.format(theDate);
	}
	
//	 根据出生日期计算年龄
	public static int getAge(String birthDateStr) {
		Date birthDate = null;
		try {
			//解析日期字符串为Date对象
			birthDate = dateFormat.parse(birthDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//调用日期计算方法
		return getAgeByBirthday(birthDate);
	}
	
	/**
	 * @Title: parse   
	 * @Description: 解析日期   
	 * @param: @param theDateStr
	 * @param: @param format
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date parse(String theDateStr,String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(theDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Title: getRandomDate   
	 * @Description: 获取随机时间 
	 * @param: @param date1
	 * @param: @param date2
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date getRandomDate(Date date1,Date date2) {
		Long randomLong = Math.abs(date1.getTime()-date2.getTime());
		long random = (long) (randomLong*Math.random());
		long newDateLong = compare(date1, date2)==1?date2.getTime()+random:date1.getTime()+random;
		return new Date(newDateLong);
	}
	
	/**
	 * @Title: compare   
	 * @Description: 0-相等
					1- date1大于date2
					-1 date1小于date2   
	 * @param: @param date1
	 * @param: @param date2
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static int compare(Date date1,Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		if(time1==time2) {
			return 0;
		}
		if(time1>time2) {
			return 1;
		}
		return -1;
	}
	
//	获取开始日期和结束日期之间有多少天   
	public static int getDayNum(Date date1,Date date2) {
		//一天有多少毫秒
		Long dayTime = 1000*60*60*24L;
		Long startTime = date1.getTime();
		Long endTime = date2.getTime();
//		System.out.println(startTime);
//		System.out.println(endTime);
		Double dayNum = Math.abs(((endTime-startTime)/dayTime*1.0));
//		dayNum = Math.ceil(dayNum);
//		System.out.println(dayNum);
		return dayNum.intValue()+1;
	}
	
//	验证指定日期是否为今天   
	public static boolean isToday(Date theDate) {
		Date nowDate = new Date();
		String nowDateStr = dateFormat.format(nowDate);
		String theDateStr = dateFormat.format(theDate);
		return nowDateStr.equals(theDateStr);
	}
	
//	验证指定日期是否为今天    
	public static boolean isToday(String theDateStr) {
		try {
			Date theDate = dateFormat.parse(theDateStr);
			return isToday(theDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Title: compareTime   
	 * @Description: TODO(描述这个方法的作用)   
	 * @param: @param date1
	 * @param: @param date2
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int compareTime(Date date1,Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		if(time1==time2) {
			return 0;
		}
		if(time1>time2) {
			return 1;
		}
		return -1;
		
	}
	
//	判断指定日期是否在本周   
	public static boolean isInWeek(Date theDate) {
		Date nowDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(nowDate);
		//本周的第几天
		int dayofweek = c.get(Calendar.DAY_OF_WEEK);
		//设置本周第一天的时间
		c.add(Calendar.DAY_OF_YEAR, 1-dayofweek);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date firstDate = c.getTime();
		System.out.println(dateTimeFormat.format(firstDate));
		//设置本周最后一天的时间
		c.add(Calendar.DAY_OF_YEAR, 6);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date lastDate = c.getTime();
		System.out.println(dateTimeFormat.format(theDate));
		System.out.println(dateTimeFormat.format(lastDate));
		return compareTime(theDate,firstDate)>=0 && compareTime(theDate,lastDate)<=0;
	}
	
//	计算指定日期距离今天，过去了多少天或还有多少天   
	public static int getDayNum(Date date) {
		Date date2 = new Date();
		return getDayNum(date,date2);
	}
	
//		把传入的日期前推24个小时。即一天
		public static Date subDate(Date date) {
//			用当前系统时间去实例化一个日期类
			Calendar c = Calendar.getInstance();
//			用传入的日期示例化日历类
			c.setTime(date);
//			借助日历类，减去1天
			c.add(Calendar.DATE, -1);
			return c.getTime();
		}
	
//      获取一个月的月初 如 ：2020-01-01 00:00:00
		public static Date initMonth(Date date) {
		   //获取一个日期类
		   Calendar c = Calendar.getInstance();
		   //用传入的日期初始日历类
		   c.setTime(date);
		   c.set(Calendar.DAY_OF_MONTH, 1);//设置为当前日期的第一天
		   c.set(Calendar.HOUR_OF_DAY, 0);//设置小时
		   c.set(Calendar.MINUTE, 0);//分钟
		   c.set(Calendar.SECOND, 0);//秒
			return c.getTime();
			
		}
		
		//返回一个月月末
		//思路： 让月加1 ，再变成月初，最后减去1秒
		public static Date endMonth(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			//让月份+1
			c.add(Calendar.MONTH, 1);
			//让时间变成月初
			Date initMonth = initMonth(c.getTime());
			c.setTime(initMonth);
			//让日期减去1
			c.add(Calendar.SECOND, -1);
			return c.getTime();	
		}

//		使用日历类 计算     ：根据出生日期算年龄
		public static int getAgeByBirthday(Date date) {
			//用系统时间获取日历类
			Calendar c = Calendar.getInstance();
			//获取系统的年
			int s_year = c.get(Calendar.YEAR);
			//获取系统的月
			int s_month = c.get(Calendar.MONTH);
			//获取系统的日
			int s_day = c.get(Calendar.DAY_OF_MONTH);
			//用传入的日期初始化一个日历类
			c.setTime(date);
			//获取出生日期的年
			int b_year = c.get(Calendar.YEAR);
			//获取出生日期的月
			int b_month = c.get(Calendar.MONTH);
			//获取出生日期的日
			int b_day = c.get(Calendar.DAY_OF_MONTH);
			int age = s_year - b_year;//用系统年 -出生年 
			if(s_month < b_month)//如果系统月小于出生月年龄减一
				age --;
			if(s_month == b_month && s_day < b_day )//如果系统月和初始月一致并且系统日小于出生日 年龄减一
			   age --;
			return age;	
		}
		
//	         随机返回一个在start--end 之间的日期
		public static Date randomDate(Date start,Date end) {
			//获取开始日期的毫秒数
			long t1 = start.getTime();
			//获取结束日期的毫秒数
			long t2 = end.getTime();
			long t =(long) ((Math.random() * (t2-t1)+1) +t1);
			return new Date(t);
		}
}
