package cn.xiangyu.entity;


import java.io.Serializable;

/**
 * 描述：；<BR>
 * 
 * 类名：BorrowPO.java<BR>
 * 
 * @author MC、ZL；<BR>
 * 
 *         说明：；<BR>
 * <BR>
 */
public class BorrowPO implements Serializable {
	private static final long serialVersionUID = 176157260928212L;
	
	/**
	 * borrow_id；
	 */
	private Integer borrow_id;
	
	/**
	 * reader_id；
	 */
	private Integer reader_id;
	
	/**
	 * borrow_num；
	 */
	private Integer borrow_num;
	
	/**
	 * borrow_date；
	 */
	private String borrow_date;
	
	/**
	 * remark；
	 */
	private String remark;
	
	/**
	 * book_id；
	 */
	private Integer book_id;
	
	/**
	 * borrow_return；
	 */
	private String borrow_return;
	
	/**
	 * borrow_type；
	 */
	private Integer borrow_type;
	
	
	private BookPO book;
	private ReaderPO reader;
		
	public ReaderPO getReader() {
		return reader;
	}

	public void setReader(ReaderPO reader) {
		this.reader = reader;
	}

	public BookPO getBook() {
		return book;
	}

	public void setBook(BookPO book) {
		this.book = book;
	}

	public Integer getBorrow_id() {
		return this.borrow_id;
	}

	public void setBorrow_id(Integer borrow_id) {
		this.borrow_id = borrow_id;
	}
	
	public Integer getReader_id() {
		return this.reader_id;
	}

	public void setReader_id(Integer reader_id) {
		this.reader_id = reader_id;
	}
	
	public Integer getBorrow_num() {
		return this.borrow_num;
	}

	public void setBorrow_num(Integer borrow_num) {
		this.borrow_num = borrow_num;
	}
	
	public String getBorrow_date() {
		return this.borrow_date;
	}

	public void setBorrow_date(String borrow_date) {
		this.borrow_date = borrow_date;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getBook_id() {
		return this.book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	
	public String getBorrow_return() {
		return this.borrow_return;
	}

	public void setBorrow_return(String borrow_return) {
		this.borrow_return = borrow_return;
	}
	
	public Integer getBorrow_type() {
		return this.borrow_type;
	}

	public void setBorrow_type(Integer borrow_type) {
		this.borrow_type = borrow_type;
	}
	
}
