package cn.xiangyu.dao.itf;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiangyu.entity.AccountPO;
import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.BooktypesPO;
import cn.xiangyu.entity.BorrowPO;
import cn.xiangyu.entity.ReaderPO;
import cn.xiangyu.entity.ReceiptPO;
import cn.xiangyu.entity.SettingPO;

public interface AdminDao {
	AccountPO login(@Param("username")String username,@Param("password")String password);
	SettingPO querySettingById(int settingId);
	int updateSetting(SettingPO po);
	List<BookPO> showBooks();
	String countbook();
	List<BooktypesPO> getAllBooktypes();
	BookPO queryBookByID(@Param("bookId")String bookId);
	void updateBook(BookPO po);
	void insertBook(BookPO po);
	void delbook(int bookId);
	List<BorrowPO> queryBorrowsByBookidAndReaderid(@Param("bookid")String bookid,@Param("readerid")String readerid);
	ReaderPO queryreaderidByname(@Param("username")String username);
	BookPO queryBookByNum(@Param("num")String num);
	void readerreturn(@Param("readerId")String raderid);
	void addBookAmount(@Param("bookId")String bookid);
	BorrowPO queryBorrowByid(@Param("borrowid")String borrowid);
	void insertReceipt(ReceiptPO po);
}
