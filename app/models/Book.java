package models;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CascadeType;
import org.hibernate.type.LobType;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Book extends Model {

	private String title;

	@ManyToOne
	private Author author;
	
	@Column(columnDefinition="TEXT")
	private String description;
	private String imageName;
	private String bookUrl;
	
	@ManyToMany(mappedBy="readBooks")
	private List<User> users;

	public Book() {

	}

	public Book(Author author, String title, String description, String bookUrl, String imageName) {
		this.author = author;
		this.title = title;
		this.description = description;
		this.bookUrl = bookUrl;
		this.imageName = imageName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}
}
