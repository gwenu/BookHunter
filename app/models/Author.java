package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Author extends Model {

	private String fullName;
	private String info;
	
	public Author(){
		
	}
	
	public Author(String fullName, String info){
		this.fullName = fullName;
		this.info = info;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
