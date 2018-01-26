package com.kshrd.springbootdemo.model.response;

public class ResponseSingle<T> extends Response {

	private T data;

	public ResponseSingle(String message, T data) {
		super(message);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseSingle [data=" + data + "]";
	}
}
