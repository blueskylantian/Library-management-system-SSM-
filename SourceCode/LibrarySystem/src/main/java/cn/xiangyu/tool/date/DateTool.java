package cn.xiangyu.tool.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTool {
	public static String dateCount(Date time, Integer year, Integer month, Integer day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(time);
		rightNow.add(Calendar.YEAR, year);// 日期减1年
		rightNow.add(Calendar.MONTH, month);// 日期加3个月
		rightNow.add(Calendar.DAY_OF_YEAR, day);// 日期加10天
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		// System.out.println(reStr);
		return reStr;
	}
	//比较yyyy-MM-dd的时间格式的大小,returntime大的话则返回false
	public static boolean dateCompare(Date time, String returntime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date returndate;
		try {
			returndate = sdf.parse(returntime);
			int res = time.compareTo(returndate);
			if (res >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
			// System.out.println("相隔的天数="+day);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return day;
	}
}
