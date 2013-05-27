package models;

import java.util.Date;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Comment extends Model {

	@ManyToOne
	private User user;
	private Date date;
	
	@Lob
	private String comment;

	@ManyToOne
	private Post post;

	public Comment() {

	}
	
	public Comment(Post post, User user, String comment) {
		this.user = user;
		this.date = new Date();
		this.comment = comment;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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
