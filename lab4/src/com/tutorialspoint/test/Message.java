package com.tutorialspoint.test;

import javax.faces.bean.ManagedBean;


@ManagedBean (name = "message", eager = true)
public class Message {
	private String message = "Hello World!";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
