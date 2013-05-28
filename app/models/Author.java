package models;

import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Author extends Model {

	private String authorFullName;
	private String info;
	
	public Author(){
		
	}
	
	public Author(String authorFullName, String info){
		this.authorFullName = authorFullName;
		this.info = info;
	}

	public String getAuthorName() {
		return authorFullName;
	}

	public void setAuthorName(String authorName) {
		this.authorFullName = authorName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
