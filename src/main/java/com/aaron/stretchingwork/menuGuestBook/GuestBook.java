package com.aaron.stretchingwork.menuGuestBook;

public class GuestBook {
	private String no;
	private String id;
	private String photo;
	private String title;
	private String contents;
	private String date;
	
	public GuestBook() {
		// TODO Auto-generated constructor stub
	}

	public GuestBook(String no, String id, String photo, String title, String contents, String date) {
		super();
		this.no = no;
		this.id = id;
		this.photo = photo;
		this.title = title;
		this.contents = contents;
		this.date = date;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
