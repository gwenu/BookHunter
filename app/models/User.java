package models;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class User extends Model {
	
//	@Id
//	private Long id;
	private String username;
	private String password;
	private String userInf;

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
}
