package com.allen.guide.model.port;

import java.util.List;

public class JCategory {
	private int total;
	private List<String> rows;

	public JCategory(int total, List<String> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<String> getRows() {
		return rows;
	}

	public void setRows(List<String> rows) {
		this.rows = rows;
	}

}
