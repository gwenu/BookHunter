package models;

import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	private String username;
	private String password;
	private String userInf;
	private String imageName;
	
	@ManyToMany
	private List<Book> readBooks;

	public User() {

	}

	public User(String username, String password, String userInf) {
		this.username = username;
		this.password = password;
		this.userInf = userInf;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserInf() {
		return userInf;
	}

	public void setUserInf(String userInf) {
		this.userInf = userInf;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public List<Book> getReadBooks() {
		return readBooks;
	}

	public void setReadBooks(List<Book> readBooks) {
		this.readBooks = readBooks;
	}
	
	public static User connect(String username, String password){
		return find("byUsernameAndPassword", username, password).first();
	}
}
