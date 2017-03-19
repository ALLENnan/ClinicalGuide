package com.allen.guide.model.entities;

//广告栏信息实体
public class SlideBean {
	private String pic;// 图片
	private GuideBean guideBean;// 对应的指南

	public SlideBean(String pic, GuideBean guideBean) {
		super();
		this.pic = pic;
		this.guideBean = guideBean;
	}

	public String getPicUrl() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public GuideBean getGuideBean() {
		return guideBean;
	}

	public void setGuideBean(GuideBean guideBean) {
		this.guideBean = guideBean;
	}
}
