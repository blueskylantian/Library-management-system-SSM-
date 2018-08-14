package cn.xiangyu.entity;


import java.io.Serializable;

/**
 * 描述：；<BR>
 * 
 * 类名：AccountPO.java<BR>
 * 
 * @author MC、ZL；<BR>
 * 
 *         说明：；<BR>
 * <BR>
 */
public class AccountPO implements Serializable {
	private static final long serialVersionUID = 229015926798319L;
	
	/**
	 * reader_id；
	 */
	private Integer reader_id;
	
	/**
	 * account_password；
	 */
	private String account_password;
	
	/**
	 * account_id；
	 */
	private Integer account_id;
	
	/**
	 * account_name；
	 */
	private String account_name;
	
	/**
	 * remark；
	 */
	private String remark;
		
	public Integer getReader_id() {
		return this.reader_id;
	}

	public void setReader_id(Integer reader_id) {
		this.reader_id = reader_id;
	}
	
	public String getAccount_password() {
		return this.account_password;
	}

	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}
	
	public Integer getAccount_id() {
		return this.account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	
	public String getAccount_name() {
		return this.account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
