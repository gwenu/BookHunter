package models;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Book extends Model {

//	@Id
//	private Long id;
	private String title;
	private String author;
	private String description;

	public Book() {

	}

	public Book(String title, String author, String description) {
		this.title = title;
		this.author = author;
		this.description = description;
	}

//	 public Long getId() {
//	 return id;
//	 }
//	
//	 public void setId(Long id) {
//	 this.id = id;
//	 }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
