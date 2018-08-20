package cn.xiangyu.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import cn.xiangyu.tool.date.DateTool;

public class MainTest {

	@Test
	public void test() {
		Date time = Calendar.getInstance().getTime();
		
			String dateCount = DateTool.dateCount(time, 0, 0, 30);
			System.out.println(dateCount);
		}
	}

