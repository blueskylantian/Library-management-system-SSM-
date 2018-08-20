package cn.xiangyu.tool.date;

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
}
