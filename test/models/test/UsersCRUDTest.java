package models.test;

import java.util.List;

import models.Author;
import models.Book;
import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class UsersCRUDTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("/mock/data/data.yml");
	}

	@Test
	public void createAndRetrieveUser() {
		new User("TestUser1", "pass1", "FirstName1","LastName1","userInf", "").save();

		User createdUser = User.find("byUsername", "TestUser1").first();

		assertNotNull(createdUser);
		assertEquals("TestUser1", createdUser.getUsername());
		assertEquals("pass1", createdUser.getPassword());
		assertEquals("FirstName1", createdUser.getFirstName());
		assertEquals("LastName1", createdUser.getLastName());
		assertEquals("userInf", createdUser.getUserInf());
		assertEquals("", createdUser.getImageName());
	}

	@Test
	public void updateUser() {
		// Retrieve User by Username
		User user = User.find("byUsername", "User1").first();
		assertNotNull(user);
		assertEquals("Inf1", user.getUserInf());

		// Update user's info and save
		user.setUserInf("update userInf");
		user.save();

		// Retrieve User by Username and check that obj was updated
		User updatedUser = User.find("byUsername", "User1").first();
		assertNotNull(updatedUser);
		assertEquals("update userInf", updatedUser.getUserInf());
	}

	@Test
	public void deleteUser() {
		User user = User.find("byUsername", "User3").first();
		assertNotNull(user);

		user.delete();

		Book deletedUser = User.find("byUsername", "User3").first();
		assertNull(deletedUser);
	}
}
