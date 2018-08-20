package cn.xiangyu.entity;


import java.io.Serializable;

/**
 * 描述：；<BR>
 * 
 * 类名：ReceiptPO.java<BR>
 * 
 * @author MC、ZL；<BR>
 * 
 *         说明：；<BR>
 * <BR>
 */
public class ReceiptPO implements Serializable {
	private static final long serialVersionUID = 176169902464975L;
	
	/**
	 * borrow_id；
	 */
	private Integer borrow_id;
	
	/**
	 * reader_id；
	 */
	private Integer reader_id;
	
	/**
	 * receipt_type；
	 */
	private Integer receipt_type;
	
	/**
	 * receipt_date；
	 */
	private String receipt_date;
	
	/**
	 * receipt_id；
	 */
	private Integer receipt_id;
	
	/**
	 * receipt_money；
	 */
	private Double receipt_money;
		
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
	
	public Integer getReceipt_type() {
		return this.receipt_type;
	}

	public void setReceipt_type(Integer receipt_type) {
		this.receipt_type = receipt_type;
	}
	
	public String getReceipt_date() {
		return this.receipt_date;
	}

	public void setReceipt_date(String receipt_date) {
		this.receipt_date = receipt_date;
	}
	
	public Integer getReceipt_id() {
		return this.receipt_id;
	}

	public void setReceipt_id(Integer receipt_id) {
		this.receipt_id = receipt_id;
	}
	
	public Double getReceipt_money() {
		return this.receipt_money;
	}

	public void setReceipt_money(Double receipt_money) {
		this.receipt_money = receipt_money;
	}
	
}
