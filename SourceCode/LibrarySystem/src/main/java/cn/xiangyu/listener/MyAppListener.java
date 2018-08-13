package cn.xiangyu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyAppListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String path = sce.getServletContext().getContextPath();
		System.out.println(path);
		sce.getServletContext().setAttribute("lpath", path);
		sce.getServletContext().setAttribute("ares", path+"/res");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}
