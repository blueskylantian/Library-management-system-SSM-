package cn.xiangyu.entity;


import java.io.Serializable;

/**
 * 描述：；<BR>
 * 
 * 类名：SettingPO.java<BR>
 * 
 * @author MC、ZL；<BR>
 * 
 *         说明：；<BR>
 * <BR>
 */
public class SettingPO implements Serializable {
	private static final long serialVersionUID = 170277303290397L;
	
	/**
	 * lend_num；
	 */
	private Integer lend_num;
	
	/**
	 * fine；
	 */
	private Double fine;
	
	/**
	 * lend_days；
	 */
	private Integer lend_days;
	
	/**
	 * teacher_num；
	 */
	private Integer teacher_num;
	
	/**
	 * remark；
	 */
	private String remark;
	
	/**
	 * student_num；
	 */
	private Integer student_num;
	
	/**
	 * setting_id；
	 */
	private Integer setting_id;
		
	public Integer getLend_num() {
		return this.lend_num;
	}

	public void setLend_num(Integer lend_num) {
		this.lend_num = lend_num;
	}
	
	public Double getFine() {
		return this.fine;
	}

	public void setFine(Double fine) {
		this.fine = fine;
	}
	
	public Integer getLend_days() {
		return this.lend_days;
	}

	public void setLend_days(Integer lend_days) {
		this.lend_days = lend_days;
	}
	
	public Integer getTeacher_num() {
		return this.teacher_num;
	}

	public void setTeacher_num(Integer teacher_num) {
		this.teacher_num = teacher_num;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getStudent_num() {
		return this.student_num;
	}

	public void setStudent_num(Integer student_num) {
		this.student_num = student_num;
	}
	
	public Integer getSetting_id() {
		return this.setting_id;
	}

	public void setSetting_id(Integer setting_id) {
		this.setting_id = setting_id;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else {
			if (obj instanceof SettingPO) {
				SettingPO s = (SettingPO) obj;
				if (s.lend_num == this.lend_num && s.fine == this.fine && s.lend_days == this.lend_days
						&& s.teacher_num == this.teacher_num && s.student_num == this.student_num
						&& s.remark == this.remark) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "SettingPO [lend_num=" + lend_num + ", fine=" + fine + ", lend_days=" + lend_days + ", teacher_num="
				+ teacher_num + ", remark=" + remark + ", student_num=" + student_num + ", setting_id=" + setting_id
				+ "]";
	}
	
	
	
}
