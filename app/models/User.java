package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String userInf;
	private String imageName;

	@ManyToMany
	@JoinTable(name = "ReadBooks", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "readBook_id"))
	private List<Book> readBooks;
	@ManyToMany
	@JoinTable(name = "GoingToReadBooks", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "going_readBook_id"))
	private List<Book> goingToReadBooks;

	public User() {

	}

	public User(String username, String password, String firstName,
			String lastName, String userInf, String imageName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userInf = userInf;
		this.imageName = imageName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public List<Book> getGoingToReadBooks() {
		return goingToReadBooks;
	}

	public void setGoingToReadBooks(List<Book> goingToReadBooks) {
		this.goingToReadBooks = goingToReadBooks;
	}

	public static User connect(String username, String password) {
		return find("byUsernameAndPassword", username, password).first();
	}
}
