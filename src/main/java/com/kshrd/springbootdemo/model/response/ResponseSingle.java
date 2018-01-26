package com.kshrd.springbootdemo.model.response;

public class ResponseSingle extends Response {

	private Object data;

	public ResponseSingle(String message, Object data) {
		super(message);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseSingle [data=" + data + "]";
	}
}
