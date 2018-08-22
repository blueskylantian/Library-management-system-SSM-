package cn.xiangyu.dao.itf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiangyu.entity.AccountPO;
import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.BorrowPO;
import cn.xiangyu.entity.ReaderPO;

public interface ReaderDao {
	String[] tempQueryBook(@Param("kw")String kw);
	List<BookPO> queryBook(@Param("name")String name);
	AccountPO verifyAccount(@Param("username")String username,@Param("password")String password);
	ReaderPO queryReaderById(@Param("id")int id);	
	void finshlend(@Param("readerId")String readerId);
	int  insertBorrow(BorrowPO borrow);
	int decBookAmount(@Param("bookId")String bookId);
	List<BorrowPO> queryBorrowByReaderId(@Param("readerId")int readerId);
	BookPO selectbookById(int book_id);
	void updateBorrow(BorrowPO borrow);
	void updateReader(ReaderPO reader);
	void selectbooktypeById(int type_id);
}
