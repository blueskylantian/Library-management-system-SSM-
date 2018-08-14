package cn.xiangyu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyAppListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String path = sce.getServletContext().getContextPath();
		sce.getServletContext().setAttribute("lpath", path);
		sce.getServletContext().setAttribute("ares", path+"/res");
		sce.getServletContext().setAttribute("jsp", path+"/WEB-INF/view");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}
