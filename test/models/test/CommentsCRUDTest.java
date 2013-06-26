package models.test;

import java.util.List;

import models.*;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class CommentsCRUDTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("/mock/data/data.yml");
	}

	@Test
	public void createAndRetrieveUser() {
		Author author = new Author("TestAuthor1","info1").save();
		Book book = new Book(author, "BookTitle", "BookDescription", "", "").save();
		User user = new User("TestUser1", "pass1", "FirstName1","LastName1","userInf", "").save();
		new Comment(book, user, "content1").save();

		List<Comment> createdCommnet = Comment.find("byUser", user).fetch();

		assertNotNull(createdCommnet);
		assertEquals(1, createdCommnet.size());
		assertEquals(book, createdCommnet.get(0).getBook());
		assertEquals("content1", createdCommnet.get(0).getContent());
	}

	@Test
	public void updateComment() {
		// Retrieve Comment by User
		User user = User.find("byUsername", "User1").first();
		Comment comment = Comment.find("byUser", user).first();
		assertNotNull(comment);
		assertEquals("comment 1 book 2", comment.getContent());

		// Update comment's info and save
		comment.setContent("update comment");
		comment.save();

		// Retrieve Comment by User and check that obj was updated
		Comment updatedComment = Comment.find("byUser", user).first();
		assertNotNull(updatedComment);
		assertEquals("update comment", updatedComment.getContent());
	}

	@Test
	public void deleteComment() {
		Comment comment = Comment.find("byContent", "comment 4 book 2").first();
		assertNotNull(comment);

		comment.delete();

		Comment deletedUser = Comment.find("byContent", "comment 4 book 2").first();
		assertNull(deletedUser);
	}
}
