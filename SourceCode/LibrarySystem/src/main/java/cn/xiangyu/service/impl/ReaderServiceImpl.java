package cn.xiangyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiangyu.dao.itf.ReaderDao;
import cn.xiangyu.entity.BookPO;
import cn.xiangyu.service.itf.ReaderServiceItf;
@Service
public class ReaderServiceImpl implements ReaderServiceItf {
	@Autowired
	ReaderDao dao;
	
	
	@Override
	public List<BookPO> querybook(String name) {
		List<BookPO> list= dao.queryBook(name);
		return list;
	}

	@Override
	public String[] tempQueryBook(String kw) {
		String[] strings = dao.tempQueryBook(kw);
		return strings;
	}

	@Override
	public BookPO queryBookById(String id) {
		BookPO po = dao.queryBookById(id);
		return po;
	}

}
