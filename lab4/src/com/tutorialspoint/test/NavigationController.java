package com.tutorialspoint.test;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {
	
	private static final long serialVersionUID = 7344758255174272404L;

	public String moveToPage1() {
		return "page1";
	}
	
	public String moveToPage2() {
		return "page2";
	}
	
	public String backToIndex() {
		return "index1";
	}
	
}