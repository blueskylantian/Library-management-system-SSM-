package cn.xiangyu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiangyu.dao.itf.ReaderDao;
import cn.xiangyu.entity.AccountPO;
import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.BorrowPO;
import cn.xiangyu.entity.ReaderPO;
import cn.xiangyu.entity.SettingPO;
import cn.xiangyu.service.itf.ReaderServiceItf;
import cn.xiangyu.tool.date.DateTool;
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

	
	/**
	 * 验证账户
	 */
	@Override
	public int verifyaccount(String username, String password) {
		/**
		 * 查询账户是否存在，如果存在返回读者id,
		 */
		AccountPO po = dao.verifyAccount(username, password);
		if(po == null) {
			return -1;
		}else {
			return po.getReader_id();
		}
	}
	/**
	 * 验证读者的信息
	 */
	@Override
	public String verifyReader(int readId,SettingPO setting){
	         /**
			 * 1.查询读者状态
			 * 2.查询读者是否还可以借书
			 */
			ReaderPO reader = dao.queryReaderById(readId);
			Integer reader_type = reader.getReader_type();
			String reader_name = reader.getReader_name();
			if(reader.getReader_credit() == 0) {
				return "有违规记录，请处理后再进行借阅";
			}else if (reader_type == 0) {
				if(reader.getReader_lendnum() >= setting.getStudent_num()) {
					return "借阅书籍数量已达上限";
				}else {
					return reader_name+":验证成功";
				}
			}else if(reader_type == 1){
				if(reader.getReader_lendnum() >= setting.getTeacher_num()) {
					return "借阅书籍数量已达上限";
				}else {
					return reader_name+":验证成功";
				}
			}
			return "验证失败";
		}
	@Override
	public void finshlend(String bookId,String readerId) {
		//修改读者的图书借阅数量
		dao.finshlend(readerId);
		//扣除图书的库藏数量
		dao.decBookAmount(bookId);
		//生成借阅记录
		Date time = Calendar.getInstance().getTime();
		String date=new SimpleDateFormat("yyyy-MM-dd").format(time);
		
		String returnDate = DateTool.dateCount(time, 0, 0, 30);
		
		BorrowPO borrow = new BorrowPO();
		borrow.setBook_id(Integer.valueOf(bookId));
		borrow.setReader_id(Integer.valueOf(readerId));
		borrow.setBorrow_type(0);
		borrow.setBorrow_return(returnDate);
		borrow.setBorrow_date(date);
		borrow.setBorrow_num(0);
		dao.insertBorrow(borrow);
	}

	@Override
	public List<BorrowPO> queryReaderbooks(int readerId) {
		List<BorrowPO> list = dao.queryBorrowByReaderId(readerId);
		return list;
	}

	
}
