package cn.xiangyu.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.JsonEO;
import cn.xiangyu.service.itf.ReaderServiceItf;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("reader")
public class ReaderController {
	@Autowired
	private ReaderServiceItf service;
	
	@RequestMapping("/query")
	@ResponseBody
	public JsonEO querybook(String name) {
		System.out.println(name);
		List<BookPO> list = service.querybook(name);
		int totle = list.size();
		System.out.println("查询为"+list.get(0).getBook_name());
		JSONArray fromObject = JSONArray.fromObject(list);
		System.out.println(fromObject);
		JsonEO eo = new JsonEO(totle,fromObject);
		return  eo;
		
	}
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
	@ResponseBody
	@RequestMapping("/finshlend")
	public JSONArray finshlend(@RequestBody Map<String,String> map) {
		System.out.println(map.get("username"));
		
		return null;
	}
}
