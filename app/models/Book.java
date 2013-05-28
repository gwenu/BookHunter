package models;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Book extends Model {
	
	private String title;
	
	@ManyToOne
	private Author author;
	private String description;

	public Book() {

	}

	public Book(Author author, String title, String description) {
		this.author = author;
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
}
