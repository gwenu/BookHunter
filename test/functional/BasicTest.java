package functional;

import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
	}

	@Test
	public void createAndRetrieveAuthor() {
		// Create a new author and save to DB
		new Author("Author1", "InfAboutAuthor").save();

		// Retrive information about author 'Author1'
		Author author = Author.find("byAuthorFullName", "Author1").first();

		// Test
		assertNotNull(author);
		assertEquals("Author1", author.getAuthorName());
		assertEquals("InfAboutAuthor", author.getInfo());
	}

	@Test
	public void createAndRetrieveBook() {
		// Create a new author and save to DB
		Author author = new Author("Author_for_book", "InfAboutAuthor").save();

		// Create a new book and save it
		new Book(author, "Title1", "Description1").save();

		// Retrieve the book by title
		Book book1 = Book.find("byTitle", "Title1").first();

		// Test
		assertNotNull(book1);
		assertEquals(author, book1.getAuthor());
		assertEquals("Title1", book1.getTitle());
		assertEquals("Description1", book1.getDescription());

	}

	@Test
	public void createAndRetrieveComment() {
		// Create a new author and save to DB
		Author author = new Author("Author_for_comment", "InfAboutAuthor").save();

		// Create a new user and book and save it
		User user = new User("username", "password", "inf").save();
		Book book = new Book(author, "Title_c", "Description_c").save();

		// Post a first comment
		new Comment(book, user, "Content_comment").save();
		new Comment(book, user, "Content_comment2").save();

		// Retrieve all comments
		List<Comment> userComments = Comment.find("byUser", user).fetch();

		// Tests
		assertEquals(2, userComments.size());

		Comment firstComment = userComments.get(0);
		assertNotNull(firstComment);
		assertEquals("Content_comment", firstComment.getComment());

		Comment secondComment = userComments.get(1);
		assertNotNull(secondComment);
		assertEquals("Content_comment2", secondComment.getComment());
	}

	@Test
	public void useTheCommentsRelation() {
		// Create a new user, author and book and save it
		Author author = new Author("Author_commnet_ralation", "InfAboutAuthor").save();
		User user = new User("username_cr", "password_cr", "inf_cr").save();
		Book book = new Book(author, "Title_c", "Description_c").save();
		
		new Comment(book, user, "Content_comment3").save();
		new Comment(book, user, "Content_comment4").save();

		// Count things
		assertEquals(1, User.count());
		assertEquals(2, Comment.count());

		List<Comment> userComments = Comment.find("byUser", user).fetch();
		assertNotNull(userComments);
		
		userComments.get(0).delete();
		
		// Check that all comments have been deleted
		assertEquals(1, User.count());
		assertEquals(1, Comment.count());
	}
}
