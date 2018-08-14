package cn.xiangyu.entity;


import java.io.Serializable;

/**
 * 描述：；<BR>
 * 
 * 类名：ReaderPO.java<BR>
 * 
 * @author MC、ZL；<BR>
 * 
 *         说明：；<BR>
 * <BR>
 */
public class ReaderPO implements Serializable {
	private static final long serialVersionUID = 228998427751429L;
	
	/**
	 * reader_id；
	 */
	private Integer reader_id;
	
	/**
	 * reader_type；
	 */
	private Integer reader_type;
	
	/**
	 * reader_name；
	 */
	private String reader_name;
	
	/**
	 * reader_credit；
	 */
	private Integer reader_credit;
	
	/**
	 * remark；
	 */
	private String remark;
	
	/**
	 * reader_lendnum；
	 */
	private Integer reader_lendnum;
		
	public Integer getReader_id() {
		return this.reader_id;
	}

	public void setReader_id(Integer reader_id) {
		this.reader_id = reader_id;
	}
	
	public Integer getReader_type() {
		return this.reader_type;
	}

	public void setReader_type(Integer reader_type) {
		this.reader_type = reader_type;
	}
	
	public String getReader_name() {
		return this.reader_name;
	}

	public void setReader_name(String reader_name) {
		this.reader_name = reader_name;
	}
	
	public Integer getReader_credit() {
		return this.reader_credit;
	}

	public void setReader_credit(Integer reader_credit) {
		this.reader_credit = reader_credit;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getReader_lendnum() {
		return this.reader_lendnum;
	}

	public void setReader_lendnum(Integer reader_lendnum) {
		this.reader_lendnum = reader_lendnum;
	}
	
}
