package cn.xiangyu.entity;

import java.util.List;

public class JsonEO {
	private List<?> rows;
	private int total;
	public JsonEO() {
		
	}
	
	public JsonEO(int totle,List<?> rows) {
		super();
		this.rows = rows;
		this.total = totle;
	}

	
	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
