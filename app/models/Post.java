package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Post extends Model {

	
	private String postTitle;

	@ManyToOne
	private User user;
	private Book book;
	private Date date;

	@Lob
	private String content;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	public List<Comment> comments;

	public Post(Book book, User user, String postTitle, String content) {
		this.user = user;
		this.postTitle = postTitle;
		this.date = new Date();
		this.content = content;
		this.comments = new ArrayList<Comment>();
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public Date getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	public Post addComment(User user, String content) {
	    Comment newComment = new Comment(this, user, content).save();
	    this.comments.add(newComment);
	    this.save();
	    return this;
	}
}
