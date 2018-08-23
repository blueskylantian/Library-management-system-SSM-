package cn.xiangyu.service.itf;

import java.util.List;

import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.BooktypesPO;
import cn.xiangyu.entity.BorrowPO;
import cn.xiangyu.entity.SettingPO;

public interface AdminServiceItf {
	String login(String userrname,String password);
	SettingPO showSetting();
	void updateSetting(SettingPO po);
	List<BookPO> showBooks();
	String countbook();
	BookPO querybookById(String bookid);
	List<BooktypesPO> getAllbooktypes();
	String saveBook(BookPO po);
	String delBook(String bookId);
	String verifyReaderBooks(String username,String book_num);
	List<BorrowPO> showReaderBooks(String bookid,String raderid);
	String returnBook(String borrowid);
	Double getFine(String borrowid);
	String checkReader(String borrowid);
	String returnLostBook(String borrowid);
	String insertReceipt(Double fine,String borrowid,int type);
}
