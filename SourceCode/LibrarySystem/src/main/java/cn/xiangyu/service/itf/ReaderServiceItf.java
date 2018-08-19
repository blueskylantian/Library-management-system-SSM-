package cn.xiangyu.service.itf;

import java.util.List;

import cn.xiangyu.entity.BookPO;

public interface ReaderServiceItf {
	List<BookPO> querybook(String name);
	String[] tempQueryBook(String kw);
	BookPO queryBookById(String id);
}
