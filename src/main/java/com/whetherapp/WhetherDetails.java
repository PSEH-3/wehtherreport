package com.whetherapp;

import java.util.List;

public class WhetherDetails {

	List<Whether> whetherList;

	public List<Whether> getWhetherList() {
		return whetherList;
	}

	public void setWhetherList(List<Whether> whetherList) {
		this.whetherList = whetherList;
	}

	public WhetherDetails() {
		super();
	}

	public WhetherDetails(List<Whether> whetherList) {
		super();
		this.whetherList = whetherList;
	}
	
	
}
