package com.allen.guide.model.entities;

import java.util.List;

public class Jcomment {
	private int total;
	private List<CommentBean> rows;

	public Jcomment(int total, List<CommentBean> rows) {
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

	public List<CommentBean> getRows() {
		return rows;
	}

	public void setRows(List<CommentBean> rows) {
		this.rows = rows;
	}

}
