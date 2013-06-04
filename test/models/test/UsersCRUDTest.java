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
		new User("TestUser1", "pass1", "userInf").save();

		List<User> createdUser = User.find("byUsername", "TestUser1").fetch();

		assertNotNull(createdUser);
		assertEquals(1, createdUser.size());
		assertEquals("TestUser1", createdUser.get(0).getUsername());
		assertEquals("pass1", createdUser.get(0).getPassword());
		assertEquals("userInf", createdUser.get(0).getUserInf());
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
