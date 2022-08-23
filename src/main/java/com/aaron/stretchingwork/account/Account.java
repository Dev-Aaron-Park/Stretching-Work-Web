package com.aaron.stretchingwork.account;

import java.util.Date;

public class Account {
	private String id;
	private String pw;
	private String name;
	private Date birthday;
	private String addr;
	private String photo;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String id, String pw, String name, Date birthday, String addr, String photo) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birthday = birthday;
		this.addr = addr;
		this.photo = photo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
