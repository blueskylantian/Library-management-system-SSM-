package cn.xiangyu.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xiangyu.entity.SettingPO;
import cn.xiangyu.service.itf.IndexServiceItf;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private IndexServiceItf service;
	
	@RequestMapping("/readerindex")
    public String view(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<SettingPO> list = service.getSetting();
		SettingPO setting = list.get(0);
		session.setAttribute("setting", setting);
        return "/reader/index";
    }
	
	@RequestMapping("/admin/desktop")
	public String admindesktop() {
		return "/admin/desktop";
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "/admin/index";
	}
		
	@RequestMapping("/login")
	public String login(){
		return "/admin/login";
	}
}
