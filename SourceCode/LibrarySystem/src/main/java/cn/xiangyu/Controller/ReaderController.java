package cn.xiangyu.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.BorrowPO;
import cn.xiangyu.entity.JsonEO;
import cn.xiangyu.entity.SettingPO;
import cn.xiangyu.service.itf.ReaderServiceItf;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("reader")
public class ReaderController {
	@Autowired
	private ReaderServiceItf service;
	/**
	 * 搜索
	 * @param name
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public JsonEO querybook(String name) {
		System.out.println(name);
		List<BookPO> list = service.querybook(name);
		int totle = list.size();
		JSONArray fromObject = JSONArray.fromObject(list);
		System.out.println(fromObject);
		JsonEO eo = new JsonEO(totle,fromObject);
		return  eo;
	}
	/**
	 * 搜索框的提示
	 * @param kw
	 * @return
	 */
	@RequestMapping("/tempquery")
	@ResponseBody
	public JSONArray tempquery(String kw){
		
		System.out.println("即时查询,关键词为"+kw);
		String[] tempQueryBook = service.tempQueryBook(kw);
		for (String string : tempQueryBook) {
			System.out.println(string);
		}
		JSONArray jsons = JSONArray.fromObject(tempQueryBook);
		System.out.println(jsons);
		return jsons;
	}
	@RequestMapping("/lendbook")
	public String lendbook(HttpServletRequest request,String id) {	
		BookPO po = service.queryBookById(id);
		request.setAttribute("data",po);
		return ("/reader/reader_lend");
	}
	/**
	 * 借阅操作
	 * @param map
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/finshlend")
	public JSONObject finshlend(@RequestBody Map<String,String> map,HttpServletRequest request) {
		String username= map.get("username");
		String password = map.get("password");
		String book_id = map.get("bookid");
		//获取配置信息
		SettingPO setting = (SettingPO)request.getSession().getAttribute("setting");
		String jsonMsg;
		/**
		 * 验证
		 */
		int accountMsg = service.verifyaccount(username, password);
		if(accountMsg == -1) {
			jsonMsg = "{msg:\"账号或密码错误\"}";
			return JSONObject.fromObject(jsonMsg);
		}else {
			int readId = accountMsg;
			String msg = service.verifyReader(readId,setting);
			if(!(msg.indexOf("验证成功")!=-1)){
				jsonMsg = "{msg:\""+msg+"\"}";
				//System.out.println(JSONObject.fromObject(msg));
				return JSONObject.fromObject(jsonMsg);
			}else {
				//写入读者信息，扣除库藏数量，生成借阅记录
				String[] split = msg.split(":");
				String reader_name = split[0];
				service.finshlend(book_id,String.valueOf(readId));
				jsonMsg = "{msg:\"借阅成功\",name:\""+reader_name+"\"}";
				return JSONObject.fromObject(jsonMsg);
			}
		}
	}
	/**
	 * 续借及查询操作
	 */
	@ResponseBody
	@RequestMapping("/queryReaderbooks")
	public Object queryReaderBooks(@RequestBody Map<String,String> map,HttpServletRequest request) {
		String username= map.get("username");
		String password = map.get("password");
		String jsonMsg;
		int accountMsg = service.verifyaccount(username, password);
		if(accountMsg == -1) {
			jsonMsg = "{msg:\"账号或密码错误\"}";
			return JSONObject.fromObject(jsonMsg);
		}else{
			int readerid = accountMsg;
			System.out.println("读者id为"+readerid);
			List<BorrowPO> list = service.queryReaderbooks(readerid);
			int totle = list.size();
			JSONArray fromObject = JSONArray.fromObject(list);
			System.out.println(fromObject);
			JsonEO json = new JsonEO(totle,fromObject);
			return json;
		}
	}
}
