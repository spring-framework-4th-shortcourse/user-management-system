package com.kshrd.springbootdemo.model.response;

import com.kshrd.springbootdemo.utility.Paging;

public class ResponseList extends Response{
	
	public ResponseList(String message, Object data, Paging paging) {
		super(message);
		this.data = data;
		this.paging = paging;
	}

	private Object data;
	
	private Paging paging;

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	@Override
	public String toString() {
		return "Response [data=" + data + ", paging=" + paging + "]";
	}
	
}
