package models;

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
	private String description;
	private String imageName;

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
}
