package cn.xiangyu.entity;


import java.io.Serializable;

/**
 * 描述：；<BR>
 * 
 * 类名：BookPO.java<BR>
 * 
 * @author XY；<BR>
 * 
 *         说明：；<BR>
 * <BR>
 */
public class BookPO implements Serializable {
	private static final long serialVersionUID = 228933856591746L;
	
	/**
	 * book_introduction；
	 */
	private String book_introduction;
	
	/**
	 * book_num；
	 */
	private String book_num;
	
	/**
	 * book_price；
	 */
	private Double book_price;
	
	/**
	 * type_id；
	 */
	private Integer type_id;
	
	/**
	 * book_amount；
	 */
	private Integer book_amount;
	
	/**
	 * book_author；
	 */
	private String book_author;
	
	/**
	 * remark；
	 */
	private String remark;
	
	/**
	 * book_id；
	 */
	private Integer book_id;
	
	/**
	 * book_name；
	 */
	private String book_name;
	
	/**
	 * book_publish；
	 */
	
	private String book_publish;
	/**
	 * book_labels	
	 * 
	 */
	private String book_labels;
	
	public String getBook_labels() {
		return book_labels;
	}

	public void setBook_labels(String book_labels) {
		this.book_labels = book_labels;
	}

	public String getBook_introduction() {
		return this.book_introduction;
	}

	public void setBook_introduction(String book_introduction) {
		this.book_introduction = book_introduction;
	}
	
	public String getBook_num() {
		return this.book_num;
	}

	public void setBook_num(String book_num) {
		this.book_num = book_num;
	}
	
	public Double getBook_price() {
		return this.book_price;
	}

	public void setBook_price(Double book_price) {
		this.book_price = book_price;
	}
	
	public Integer getType_id() {
		return this.type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	
	public Integer getBook_amount() {
		return this.book_amount;
	}

	public void setBook_amount(Integer book_amount) {
		this.book_amount = book_amount;
	}
	
	public String getBook_author() {
		return this.book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
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
	
	public String getBook_name() {
		return this.book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	
	public String getBook_publish() {
		return this.book_publish;
	}

	public void setBook_publish(String book_publish) {
		this.book_publish = book_publish;
	}
	
}
