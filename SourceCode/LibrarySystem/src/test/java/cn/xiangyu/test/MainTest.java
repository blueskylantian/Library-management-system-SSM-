package cn.xiangyu.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import cn.xiangyu.tool.date.DateTool;

public class MainTest {

	@Test
	public void test() {
		Date time = Calendar.getInstance().getTime();
		String returntime = "2018-10-01";
			boolean dateCompare = DateTool.dateCompare(time, returntime);
			System.out.println(dateCompare);
			//false
		}
	}

