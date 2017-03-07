package com.allen.guide.model.entities;

public class CommentBean {
	private int id;
	private String content;
	private String date;
	private String username;
	private String headshot;

	public CommentBean() {
		super();
	}

	public CommentBean(int id, String content, String date, String username,
			String headshot) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
		this.username = username;
		this.headshot = headshot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadshot() {
		return headshot;
	}

	public void setHeadshot(String headshot) {
		this.headshot = headshot;
	}
}
