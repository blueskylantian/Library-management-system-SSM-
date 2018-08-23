package cn.xiangyu.service.impl;

import java.text.ParseException;
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
		BookPO po = dao.selectbookById(Integer.valueOf(id));
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
			if(!checkDate(readId)) {
				//如果有书本逾期，将读者状态置为0违规
				reader.setReader_type(0);
				dao.updateReader(reader);
			}
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
	public void finshlend(String bookId,String readerId,SettingPO setting) {
		//获取续借可以增加的天数
		int lenddays= setting.getLend_days();
		
		List<BorrowPO> list = dao.queryBorrowByReaderId(Integer.valueOf(readerId));
		//查询读者是否已经借阅过这本书，有的话，只要更新一下就可以了
		
		for (BorrowPO po : list) {
			if(po.getBook().getBook_id() == Integer.valueOf(bookId)) {	
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date time = sdf.parse(po.getBorrow_return());
					String returnDate = DateTool.dateCount(time, 0, 0, lenddays);
					//更新借阅表的归还日期
					po.setBorrow_return(returnDate);
					po.setBorrow_num(po.getBorrow_num()+1);
					dao.updateBorrow(po);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}
		//没有的话，就新增
		//修改读者的图书借阅数量
		dao.finshlend(readerId);
		//扣除图书的库藏数量
		dao.decBookAmount(bookId);
		//生成借阅记录
		Date time = Calendar.getInstance().getTime();
		String date=new SimpleDateFormat("yyyy-MM-dd").format(time);
		String returnDate = DateTool.dateCount(time, 0, 0, lenddays);
		
		BorrowPO borrow = new BorrowPO();
		borrow.setBook_id(Integer.valueOf(bookId));
		borrow.setReader_id(Integer.valueOf(readerId));
		borrow.setBorrow_type(0);
		borrow.setBorrow_return(returnDate);
		borrow.setBorrow_date(date);
		borrow.setBorrow_num(0);
		dao.insertBorrow(borrow);
	}
	/**
	 * 读者的续借和查询操作
	 */
	@Override
	public List<BorrowPO> queryReaderbooks(int readerId) {
		ReaderPO reader = dao.queryReaderById(readerId);
		if(!checkDate(readerId)) {
			//如果有书本逾期，将读者状态置为0违规
			System.out.println("有书本逾期");
			reader.setReader_credit(0);
			dao.updateReader(reader);
		}
		List<BorrowPO> list = dao.queryBorrowByReaderId(readerId);
		//日期的校验
		return list;
	}

	/**
	 * 校验当前读者的图书是否有逾期,如果有就修改读者状态，以及借阅图书的状态
	 * 
	 */
	public  boolean checkDate(int readerId){
		List<BorrowPO> list = dao.queryBorrowByReaderId(readerId);
		boolean flag = true;
		if(list != null) {
			for (BorrowPO po : list) {
				//获得该读者的所有正常状态的借阅书本
				if(po.getBorrow_type() == 0){
					//获得应该归还的日期
					String return_day = po.getBorrow_return();
					//获得当前日期
					Date time = Calendar.getInstance().getTime();
					System.out.println(DateTool.dateCompare(time, return_day));
					//比较，逾期是true
					if(DateTool.dateCompare(time, return_day)) {
						//有书本逾期，改变书本状态
						po.setBorrow_type(-1);
						dao.updateBorrow(po);
						flag = false;
					}
				}else if(po.getBorrow_type() == -1) {
					flag = false;
				}
			}
		}
		return flag;
	}
}
