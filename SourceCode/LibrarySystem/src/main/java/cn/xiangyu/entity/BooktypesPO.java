package cn.xiangyu.entity;


import java.io.Serializable;

/**
 * 描述：；<BR>
 * 
 * 类名：BooktypesPO.java<BR>
 * 
 * @author MC、ZL；<BR>
 * 
 *         说明：；<BR>
 * <BR>
 */
public class BooktypesPO implements Serializable {
	private static final long serialVersionUID = 229039996893781L;
	
	/**
	 * type_name；
	 */
	private String type_name;
	
	/**
	 * type_num；
	 */
	private String type_num;
	
	/**
	 * type_id；
	 */
	private Integer type_id;
	
	/**
	 * remark；
	 */
	private String remark;
		
	public String getType_name() {
		return this.type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	public String getType_num() {
		return this.type_num;
	}

	public void setType_num(String type_num) {
		this.type_num = type_num;
	}
	
	public Integer getType_id() {
		return this.type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
