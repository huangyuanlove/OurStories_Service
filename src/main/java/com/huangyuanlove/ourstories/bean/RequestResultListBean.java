package com.huangyuanlove.ourstories.bean;

import java.util.List;

public class RequestResultListBean<T> {
	
	private int status;
	private String errmsg;
	private List<T> data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	

}
