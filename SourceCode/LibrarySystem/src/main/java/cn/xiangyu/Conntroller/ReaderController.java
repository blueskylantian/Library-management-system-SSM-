package cn.xiangyu.Conntroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiangyu.entity.BookPO;

@Controller
@RequestMapping("reader")
public class ReaderController {
	@RequestMapping("query")
	@ResponseBody
	public List<BookPO> querybook() {
		
		return null;
		
	}
}
