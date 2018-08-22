package cn.xiangyu.dao.itf;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiangyu.entity.AccountPO;
import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.BooktypesPO;
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
}
