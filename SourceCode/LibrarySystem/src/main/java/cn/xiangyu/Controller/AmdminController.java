package cn.xiangyu.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.BooktypesPO;
import cn.xiangyu.entity.JsonEO;
import cn.xiangyu.entity.SettingPO;
import cn.xiangyu.service.itf.AdminServiceItf;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin")
public class AmdminController {
	@Autowired
	private AdminServiceItf service;
	/**
	 * 新增图书和修改图书的跳转
	 * @return
	 */
	@RequestMapping("/editbook")
	public String editbook(HttpServletRequest request) {
		String bookid = request.getParameter("bookid");
		//System.out.println(bookid);
		if(bookid != "") {
			BookPO bookpo = service.querybookById(bookid);
			//System.out.println(bookpo);
			request.setAttribute("data", bookpo);
			//查询所有的图书类型
			List<BooktypesPO> types = service.getAllbooktypes();
			//System.out.println(types.get(0).getType_name());
			request.setAttribute("types", types);
			return "/admin/editBook";
		}else {
			List<BooktypesPO> types = service.getAllbooktypes();
			request.setAttribute("types", types);
			return "/admin/editBook";
		}
	}
	/**
	 * 删除图书
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delBook")
	public JSONObject delbook(@RequestBody Map<String,String> map){
		String jsonMsg;
		String bookid = map.get("id");
		//删除图书
		String msg = service.delBook(bookid);
		jsonMsg = "{msg:\""+msg+"\"}";
		
		return JSONObject.fromObject(jsonMsg);
	}
	/**
	 * 新增图书和修改图书的保存
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/savebook")
	public JSONObject savebook(@RequestBody BookPO book,HttpServletRequest request) {
		String jsonMsg;
		//新建或更新
		String msg = service.saveBook(book);
		
		jsonMsg = "{msg:\""+msg+"\"}";
		if(msg.indexOf("更新") != -1) {
			return JSONObject.fromObject(jsonMsg);
		}else if(msg.indexOf("新增") != -1) {
			return JSONObject.fromObject(jsonMsg);
		}
		return null;
	}
	/**
	 * 跳转到图书管理页面
	 * @return
	 */
	@RequestMapping("/book")
	public String showBook() {
		return "/admin/bookConfig";
	}
	
	/**
	 * 为管理员展示所有图书
	 * @return
	 */
	@ResponseBody
	@RequestMapping("showbooks")
	public JsonEO showBooks(HttpServletRequest request) {
		//String countbook = service.countbook();
		List<BookPO> list = service.showBooks();
		int totle = list.size(); 
		JSONArray fromObject = JSONArray.fromObject(list);
		System.out.println(fromObject);
		JsonEO eo = new JsonEO(totle,fromObject);
		return  eo;
	}
	
	@ResponseBody
	@RequestMapping("/saveSetting")
	public JSONObject savesrtting(@RequestBody SettingPO setting,HttpServletRequest request){
			
//			SettingPO setting =new SettingPO();
//			setting.setSetting_id(Integer.valueOf(map.get("setting_id")));
//			setting.setLend_days(Integer.valueOf(map.get("lend_days")));
//			setting.setLend_num(Integer.valueOf(map.get("lend_num")));
//			setting.setStudent_num(Integer.valueOf(map.get("student_num")));
//			setting.setTeacher_num(Integer.valueOf(map.get("teacher_num")));
//			setting.setFine(Double.valueOf(map.get("fine")));
//			setting.setRemark(map.get("remark"));
			
			//更新之前先获得当前配置，并保存
			SettingPO po = service.showSetting();
			po.setSetting_id(2);
			service.updateSetting(po);
			//更新
			service.updateSetting(setting);
			setting(request);
			
		return null;
	}
	/**
	 * 展示图书
	 * @param request
	 * @return
	 */
	@RequestMapping("/setting")
	public String setting(HttpServletRequest request){
		SettingPO po = service.showSetting();
		request.setAttribute("data", po);
		return "/admin/setting";
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public JSONObject login(@RequestBody Map<String,String> map,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = map.get("username");
		String password = map.get("password");
		String jsonMsg;
		String msg = service.login(username, password);
		if(msg.indexOf("成功") != -1) {
			String adminname = msg.split(":")[0];
			session.setAttribute("name", adminname);
			jsonMsg = "{msg:\""+msg+"\"}";
			return JSONObject.fromObject(jsonMsg);
		}else {
			jsonMsg = "{msg:\""+msg+"\"}";
			return JSONObject.fromObject(jsonMsg);
		}
	}
	
	
}
