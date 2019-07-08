package org.chenkui.spring.batch.sample.support;

/**
 * Reader对象
 * @author chenkui
 *
 */
public class Record {
	private int id;
	private String msg;

	public int getId() {
		return id;
	}

	public Record setId(int id) {
		this.id = id;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Record setMsg(String msg) {
		this.msg = msg;
		return this;
	}
}
