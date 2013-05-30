package models;

import java.util.Date;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Comment extends Model {

	private Book book;
	
	@ManyToOne
	private User user;
	private Date date;
	
	@Lob
	private String comment;
	
	public Comment() {

	}
	
	public Comment(Book book, User user, String comment) {
		this.book = book;
		this.user = user;
		this.date = new Date();
		this.comment = comment;
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

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
