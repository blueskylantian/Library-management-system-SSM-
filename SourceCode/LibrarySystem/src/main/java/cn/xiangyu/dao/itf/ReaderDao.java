package cn.xiangyu.dao.itf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiangyu.entity.BookPO;

public interface ReaderDao {
	String[] tempQueryBook(@Param("kw")String kw);
	List<BookPO> queryBook(@Param("name")String name);
	BookPO queryBookById(@Param("id")String id);
}
