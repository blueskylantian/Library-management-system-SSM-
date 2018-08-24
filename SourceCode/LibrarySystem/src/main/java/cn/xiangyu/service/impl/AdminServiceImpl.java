package cn.xiangyu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiangyu.dao.itf.AdminDao;
import cn.xiangyu.dao.itf.IndexDao;
import cn.xiangyu.dao.itf.ReaderDao;
import cn.xiangyu.entity.AccountPO;
import cn.xiangyu.entity.BookPO;
import cn.xiangyu.entity.BooktypesPO;
import cn.xiangyu.entity.BorrowPO;
import cn.xiangyu.entity.ReaderPO;
import cn.xiangyu.entity.ReceiptPO;
import cn.xiangyu.entity.SettingPO;
import cn.xiangyu.service.itf.AdminServiceItf;
import cn.xiangyu.tool.date.DateTool;
@Service
public class AdminServiceImpl implements AdminServiceItf {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ReaderDao readerDao;
	@Autowired
	private IndexDao indeaDao;

	@Override
	public String login(String username, String password) {
		AccountPO po = adminDao.login(username, password);
		if(po == null) {
			return "账号或密码错误";
		}else{
			int readerid= po.getReader_id();
			ReaderPO readerPO = readerDao.queryReaderById(readerid);
			String adminname = readerPO.getReader_name();
			if(readerPO.getReader_type() != -1) {
				return "权限不足，无法登陆";
			}else {
				return adminname+":成功";
			}
		}
	}

	@Override
	public SettingPO showSetting() {
		SettingPO po = adminDao.querySettingById(3);
		return po;
	}

	@Override
	public void updateSetting(SettingPO po) {
		adminDao.updateSetting(po);
	}

	@Override
	public List<BookPO> showBooks() {
		List<BookPO> list = adminDao.showBooks();
		return list;
	}

	@Override
	public String countbook() {
		String countbook = adminDao.countbook();
		return countbook;
	}

	@Override
	public BookPO querybookById(String bookid) {
		BookPO po = adminDao.queryBookByID(bookid);
		return po;
	}

	@Override
	public List<BooktypesPO> getAllbooktypes() {
		List<BooktypesPO> list = adminDao.getAllBooktypes();
		return list;
	}

	@Override
	public String saveBook(BookPO po) {
		if(po.getBook_id() != null) {
			adminDao.updateBook(po);
			return "更新成功";
		}else {
			adminDao.insertBook(po);
			return "新增成功";
		}
	}

	@Override
	public String delBook(String bookId) {
		adminDao.delbook(Integer.valueOf(bookId));
		return "删除成功";
	}

	@Override
	public String verifyReaderBooks(String username, String book_num) {
		//获得readerid
		int readerid;
		int bookid;
		//后台检验账号是否为空
		if(username == "" || username == null) {
			return "读者账号不可为空";
		}
		
		ReaderPO readerpo = adminDao.queryreaderidByname(username);
		if(readerpo != null) {
			 readerid = readerpo.getReader_id();
			 if(book_num != null && book_num != "") {
				 BookPO bookpo = adminDao.queryBookByNum(book_num);
				 if(bookpo != null) {
					 bookid = bookpo.getBook_id();
					//校验读者的借阅账户
						ReaderPO reader = readerDao.queryReaderById(readerid);
						if(!checkDate(readerid)) {
							//如果有书本逾期，将读者状态置为0违规
							reader.setReader_type(0);
							readerDao.updateReader(reader);
						}
					 return readerid+":"+bookid;
				 }else {
					 return "图书编号错误";
				 }
			 }else {
				//校验读者的借阅账户
					ReaderPO reader = readerDao.queryReaderById(readerid);
					if(!checkDate(readerid)) {
						//如果有书本逾期，将读者状态置为0违规
						reader.setReader_type(0);
						readerDao.updateReader(reader);
					}
				 return readerid+":";
			 }
			 //获得bookid
		}else {
			return "读者账号错误";
		}	
	}

	@Override
	public List<BorrowPO> showReaderBooks(String bookid, String raderid) {
		List<BorrowPO> list = adminDao.queryBorrowsByBookidAndReaderid(bookid, raderid);
		return list;
	}
	
	public  boolean checkDate(int readerId){
		List<BorrowPO> list = readerDao.queryBorrowByReaderId(readerId);
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
						readerDao.updateBorrow(po);
						flag = false;
					}
				}else if(po.getBorrow_type() == -1) {
					flag = false;
				}
			}
		}
		return flag;
	}

	@Override
	public String returnBook(String borrowid) {
		BorrowPO borrow = adminDao.queryBorrowByid(borrowid);
		int readerid = borrow.getReader().getReader_id();
		int bookid = borrow.getBook().getBook_id();
		//图书馆库存加1
		adminDao.addBookAmount(String.valueOf(bookid));
		//读者借书减一
		adminDao.readerreturn(String.valueOf(readerid));
		//将图书借阅状态置为1
		borrow.setBorrow_type(1);
		readerDao.updateBorrow(borrow);
		return "成功";
	}

	@Override
	public Double getFine(String borrowid) {
		BorrowPO borrow = adminDao.queryBorrowByid(borrowid);
		//获取当前时间
		Date time = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String returndate = sdf.format(time);
		if(DateTool.dateCompare(time,returndate)) {
			
			//获取最后的还书时间
			String borrowreturn = borrow.getBorrow_return();
			//获得天数
			
			long daySub = DateTool.getDaySub(borrowreturn, returndate);
			//获得每逾期一天的罚款
			
			SettingPO setting = indeaDao.getSetting().get(0);
			Double fine = setting.getFine();
			
			//获得罚款总费用
			Double Fine = daySub*fine;
			return Fine;
		}else {
			return 0D;
		}
	}
	//查询读者是否还有逾期的图书没有处理，如果没有将读者的状态职位正常
	@Override
	public String checkReader(String borrowid) {
		BorrowPO borrow = adminDao.queryBorrowByid(borrowid);
		int readerid = borrow.getReader().getReader_id();
		List<BorrowPO> list = readerDao.queryBorrowByReaderId(readerid);
		boolean flag = true;
		for (BorrowPO po : list) {
			if(po.getBorrow_type() == -1) {
				flag = false;
			}
		}
		if(flag) {
			//修改读者的状态
			ReaderPO reader = new ReaderPO();
			reader.setReader_id(readerid);
			reader.setReader_credit(1);
			readerDao.updateReader(reader);
		}
			
		return null;
	}

	@Override
	public String returnLostBook(String borrowid) {
		BorrowPO borrow = adminDao.queryBorrowByid(borrowid);
		int readerid = borrow.getReader().getReader_id();
		//读者借书减一
		adminDao.readerreturn(String.valueOf(readerid));
		//将图书借阅状态置为1
		borrow.setBorrow_type(1);
		readerDao.updateBorrow(borrow);
		return "成功";
	}

	@Override
	public String insertReceipt(Double fine, String borrowid ,int type) {
		
		Date time = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(time);
		
		BorrowPO borrow = adminDao.queryBorrowByid(borrowid);
		ReceiptPO po = new ReceiptPO();
		po.setBorrow_id(borrow.getBorrow_id());
		po.setReader_id(borrow.getReader().getReader_id());
		po.setReceipt_date(format);
		po.setReceipt_type(type);
		if(type == 0) {
			po.setReceipt_money(fine);
		}else {
			po.setReceipt_money(fine+borrow.getBook().getBook_price());
		}
		adminDao.insertReceipt(po);
		return null;
	}

	@Override
	public String defaultSetting() {
		//获得默认配置
		SettingPO defaultSetting = adminDao.querySettingById(1);
		defaultSetting.setSetting_id(3);
		adminDao.updateSetting(defaultSetting);
		return "已更新为默认配置";
	}

	@Override
	public String backSetting() {
		SettingPO defaultSetting = adminDao.querySettingById(2);
		defaultSetting.setSetting_id(3);
		adminDao.updateSetting(defaultSetting);
		return "已经还原为上一次的配置";
	}

}
