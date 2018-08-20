package cn.xiangyu.service.itf;

import java.util.List;

import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.BorrowPO;
import cn.xiangyu.entity.SettingPO;

public interface ReaderServiceItf {
	List<BookPO> querybook(String name);
	String[] tempQueryBook(String kw);
	BookPO queryBookById(String id);
	int verifyaccount(String username,String password);
	String verifyReader(int readerId,SettingPO setting);
	void finshlend(String bookId,String readerId,SettingPO setting);
	List<BorrowPO> queryReaderbooks(int raderId);
}
