package com.allen.guide.model.entities;

public class JRegister {
	private boolean isRegistered;

	public JRegister(Boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
}
