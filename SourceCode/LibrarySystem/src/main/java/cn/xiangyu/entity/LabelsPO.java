package cn.xiangyu.entity;


import java.io.Serializable;

/**
 * 描述：；<BR>
 * 
 * 类名：LabelsPO.java<BR>
 * 
 * @author MC、ZL；<BR>
 * 
 *         说明：；<BR>
 * <BR>
 */
public class LabelsPO implements Serializable {
	private static final long serialVersionUID = 229054420151439L;
	
	/**
	 * label_num；
	 */
	private String label_num;
	
	/**
	 * remark；
	 */
	private String remark;
	
	/**
	 * label_name；
	 */
	private String label_name;
	
	/**
	 * label_id；
	 */
	private Integer label_id;
		
	public String getLabel_num() {
		return this.label_num;
	}

	public void setLabel_num(String label_num) {
		this.label_num = label_num;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getLabel_name() {
		return this.label_name;
	}

	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}
	
	public Integer getLabel_id() {
		return this.label_id;
	}

	public void setLabel_id(Integer label_id) {
		this.label_id = label_id;
	}
	
}
