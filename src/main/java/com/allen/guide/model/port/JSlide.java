package com.allen.guide.model.port;

import com.allen.guide.model.entities.SlideBean;

import java.util.List;

public class JSlide {
	private int total;
	private List<SlideBean> rows;

	public JSlide(int total, List<SlideBean> rows) {
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

	public List<SlideBean> getRows() {
		return rows;
	}

	public void setRows(List<SlideBean> rows) {
		this.rows = rows;
	}

}
