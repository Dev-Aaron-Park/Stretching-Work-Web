package com.aaron.stretchingwork.menuWorking;

public class Working {
	private String no;
	private String id;
	private String date;
	private String todo;
	private String memo;
	private String imp;
	
	public Working() {
		// TODO Auto-generated constructor stub
	}

	public Working(String no, String id, String date, String todo, String memo, String imp) {
		super();
		this.no = no;
		this.id = id;
		this.date = date;
		this.todo = todo;
		this.memo = memo;
		if (imp.equals("1")) {
			this.imp = "High";
		} else if (imp.equals("2")) {
			this.imp = "Middle";
		} else {
			this.imp = "Low";
		}
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImp() {
		return imp;
	}

	public void setImp(String imp) {
		this.imp = imp;
	}
}
