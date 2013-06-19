package models;

import java.util.Date;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Comment extends Model {

	@ManyToOne
	private Book book;
	
	@ManyToOne
	private User user;
	private Date date;
	
	@Lob
	private String content;
	
	public Comment() {

	}
	
	public Comment(Book book, User user, String content) {
		this.book = book;
		this.user = user;
		this.date = new Date();
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
