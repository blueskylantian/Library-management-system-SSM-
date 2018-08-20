package cn.xiangyu.tool.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTool {
	public static String dateCount(Date time,Integer year,Integer month,Integer day){
		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		  Calendar rightNow = Calendar.getInstance();
		  rightNow.setTime(time);
		  rightNow.add(Calendar.YEAR,year);//日期减1年
		  rightNow.add(Calendar.MONTH,month);//日期加3个月
		  rightNow.add(Calendar.DAY_OF_YEAR,day);//日期加10天
		  Date dt1=rightNow.getTime();
		  String reStr = sdf.format(dt1);
		 // System.out.println(reStr);
		  return reStr;
	}
	
	public static boolean dateCompare(Date time,String returntime) {
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
}
