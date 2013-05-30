package functional;

import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("/mock/data/data.yml");
	}

	@Test
	public void createAndRetrieveAuthor() {

		assertEquals(4, Author.count());
		// Retrive information about author 'Author1'
		// Author author = Author.find("byFullName", "Fifth").first();
		List<Author> authors = Author.findAll();

		// Test
		assertNotNull(authors);
		// assertEquals("First", author.getAuthorName());
		assertEquals("InfAboutAuthor2", authors.get(0).getInfo());

	}

	@Test
	public void createAndRetrieveBook() {

		// Retrieve the book by title
		Book book = Book.find("byTitle", "Title1").first();

		// Test
		assertNotNull(book);
		assertEquals("Title1", book.getTitle());
		assertEquals("Description1", book.getDescription());
	}

	@Test
	public void createAndRetrieveComment() {

		// Retrieve all comments
		List<Comment> userComments = Comment.find("byUser",
				User.find("byUsername", "User1").first()).fetch();

		// Tests
		assertEquals(4, userComments.size());

		Comment firstComment = userComments.get(0);
		assertNotNull(firstComment);
		assertEquals("comment 1 book 2", firstComment.getComment());

		Comment secondComment = userComments.get(1);
		assertNotNull(secondComment);
		assertEquals("comment 2 book 2", secondComment.getComment());
	}

	@Test
	public void useTheCommentsRelation() {

		// Count things
		assertEquals(1, User.count());
		assertEquals(4, Comment.count());

		List<Comment> userComments = Comment.find("byUser",
				User.find("byUsername", "User1").first()).fetch();
		assertNotNull(userComments);

		userComments.get(0).delete();

		// Check that all comments have been deleted
		assertEquals(1, User.count());
		assertEquals(3, Comment.count());
	}
}
