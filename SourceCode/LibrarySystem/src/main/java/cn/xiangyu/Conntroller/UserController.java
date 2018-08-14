package cn.xiangyu.Conntroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.xiangyu.entity.User;
import cn.xiangyu.service.itf.UserServiceItf;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserServiceItf service;
	
	@RequestMapping("/login")
    public String view() {
        return "/admin/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "/admin/desktop";
    }

    @RequestMapping(value = "/logins", method = RequestMethod.POST)
    public ModelAndView login(User model, HttpSession session) {
        User user = service.findByUsername(model.getUsername());

        if (user == null || !user.getPassword().equals(model.getPassword())) {
        	
            return  new ModelAndView("redirect:/login");
        } else {
        	ModelAndView mav = new ModelAndView();
            session.setAttribute("user", user);
            mav.setViewName("/index");
            return mav;
        }
    }
}
