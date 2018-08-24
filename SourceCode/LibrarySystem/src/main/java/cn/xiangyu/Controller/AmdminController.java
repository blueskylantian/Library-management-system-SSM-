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

import cn.xiangyu.dao.itf.ReaderDao;
import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.BooktypesPO;
import cn.xiangyu.entity.BorrowPO;
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
	
	@ResponseBody
	@RequestMapping("backSetting")
	public JSONObject backSetting() {
		String jsonMsg;
		String msg = service.backSetting();
		jsonMsg = "{msg:\""+msg+"\"}";
		return JSONObject.fromObject(jsonMsg);
	}
	/**
	 * 还原为默认操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping("defaultSetting")
	public JSONObject defaultSetting() {
		String jsonMsg;
		String msg = service.defaultSetting();
		jsonMsg = "{msg:\""+msg+"\"}";
		return JSONObject.fromObject(jsonMsg);
	}
	/**
	 * 丢失图书后的操作
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("lost")
	public JSONObject lostBook(@RequestBody Map<String, String> map) {
		String jsonMsg;
		String borrowid= map.get("id");
		String msg = service.returnLostBook(borrowid);
		//如果产生费用则生成收款单
		Double fine = service.getFine(borrowid);
		if(fine > 0) {
			service.insertReceipt(fine, borrowid,1);
		}
		//如无逾期的图书，将读者状态设置成正常
		service.checkReader(borrowid);
		if(msg.indexOf("成功") != -1) {
			jsonMsg = "{msg:\"还书成功\"}";
			return JSONObject.fromObject(jsonMsg);
		}else {
			jsonMsg = "{msg:\"失败\"}";
			return JSONObject.fromObject(jsonMsg);
		}
	}
	/**
	 * 获得罚款金额
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("topay")
	public JSONObject toPayFine(@RequestBody Map<String, String> map) {
		String Fine;
		String borrowid= map.get("id");
		Double fine = service.getFine(borrowid);
		Fine = "{fine:\""+fine+"\"}";
		return JSONObject.fromObject(Fine);
	}
	/**
	 * 归还图书
	 * @return
	 */
	@ResponseBody
	@RequestMapping("returnbook")
	public JSONObject returnbook(@RequestBody Map<String, String> map) {
		String jsonMsg;
		String borrowid= map.get("id");
		String msg = service.returnBook(borrowid);
		//如果产生费用，生成收款单
		
		Double fine = service.getFine(borrowid);
		if(fine > 0) {
			service.insertReceipt(fine, borrowid, 0);
		}
		//如无逾期的图书，将读者状态设置成正常
		service.checkReader(borrowid);
		
		if(msg.indexOf("成功") != -1) {
			jsonMsg = "{msg:\"还书成功\"}";
			return JSONObject.fromObject(jsonMsg);
		}else {
			jsonMsg = "{msg:\"失败\"}";
			return JSONObject.fromObject(jsonMsg);
		}
	}
	/**
	 * 查询读者的图书
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/showReaderBooks")
	public JSONObject showReaderBooks(@RequestBody Map<String,String> map) {
		String username = map.get("username");
		String book_num = map.get("book_num");
		String jsonMsg;
		String  readerAndBook = service.verifyReaderBooks(username, book_num);
		if(readerAndBook.indexOf(":") != -1) {
			String[] split = readerAndBook.split(":");
			String readerid;
			String bookid = "";
			if(split.length == 1) {
				readerid = split[0];
			}else {
				readerid = split[0];
				bookid = split[1];
			}
			List<BorrowPO> list = service.showReaderBooks(bookid, readerid);
			int totle = list.size();
			System.out.println(JSONArray.fromObject(list));
			return JSONObject.fromObject(new JsonEO(totle,list));
		}else {
			System.out.println("找不到");
			jsonMsg = "{msg:\""+readerAndBook+"\"}";
			return JSONObject.fromObject(jsonMsg);
		}
	}
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
	@RequestMapping("/showbooks")
	public JsonEO showBooks(HttpServletRequest request) {
		//String countbook = service.countbook();
		List<BookPO> list = service.showBooks();
		int totle = list.size(); 
		JSONArray fromObject = JSONArray.fromObject(list);
		System.out.println(fromObject);
		JsonEO eo = new JsonEO(totle,fromObject);
		return  eo;
	}
	/**
	 * 保存设置
	 * @param setting
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveSetting")
	public JSONObject savesrtting(@RequestBody SettingPO setting,HttpServletRequest request){
			String jsonMsg;	
			jsonMsg = "{msg:\"修改成功\"}";		
			//更新之前先获得当前配置，并保存
			SettingPO po = service.showSetting();
			po.setSetting_id(2);
			service.updateSetting(po);
			//更新
			service.updateSetting(setting);
			setting(request);
			
			
		return JSONObject.fromObject(jsonMsg);
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
