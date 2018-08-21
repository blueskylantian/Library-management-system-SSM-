package cn.xiangyu.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiangyu.entity.SettingPO;
import cn.xiangyu.service.itf.AdminServiceItf;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin")
public class AmdminController {
	@Autowired
	private AdminServiceItf service;
	
	@ResponseBody
	@RequestMapping("/saveSetting")
	public JSONObject savesrtting(@RequestBody Map<String,String> map,HttpServletRequest request){
			
			SettingPO setting =new SettingPO();
			setting.setSetting_id(Integer.valueOf(map.get("setting_id")));
			setting.setLend_days(Integer.valueOf(map.get("lend_days")));
			setting.setLend_num(Integer.valueOf(map.get("lend_num")));
			setting.setStudent_num(Integer.valueOf(map.get("student_num")));
			setting.setTeacher_num(Integer.valueOf(map.get("teacher_num")));
			setting.setFine(Double.valueOf(map.get("fine")));
			setting.setRemark(map.get("remark"));
			
			//更新之前先获得当前配置，并保存
			SettingPO po = service.showSetting();
			po.setSetting_id(2);
			service.updateSetting(po);
			//更新
			service.updateSetting(setting);
			setting(request);
			
		return null;
	}
	
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
