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
	public void createAndRetrieveBook() {
		// Create a new book and save it
		new Book("Title1", "Author1", "Description1").save();

		// Retrieve the book with author Author1
		Book book1 = Book.find("byAuthor", "Author1").first();

		// Test
		assertNotNull(book1);
		assertEquals("Title1", book1.getTitle());
	}

	@Test
	public void createPost() {
		// Create a new user and book and save it
		User user = new User("username", "password", "inf").save();
		Book book = new Book("Title2", "Author2", "Description2").save();

		// Create a new post
		new Post(book, user, "PostTitle", "Content").save();

		// Test that the post has been created
		assertEquals(1, Post.count());

		// Retrieve all posts created by User
		List<Post> userPosts = Post.find("byUser", user).fetch();

		// Tests
		assertEquals(1, userPosts.size());
		Post firstPost = userPosts.get(0);
		assertNotNull(firstPost);
		assertEquals(user, firstPost.getUser());
		// assertEquals(book, firstPost.getBook());
		assertEquals("Content", firstPost.getContent());
		assertNotNull(firstPost.getDate());
	}

	@Test
	public void postComments() {
		// Create a new user and book and save it
		User user = new User("username_c", "password_c", "inf_c").save();
		User user_p = new User("username_p", "password_p", "inf_p").save();
		Book book = new Book("Title_c", "Author_c", "Description_c").save();

		// Create a new post
		Post post = new Post(book, user_p, "PostTitle_c", "Content_c").save();

		// Post a first comment
		new Comment(post, user, "Content_comment").save();
		new Comment(post, user, "Content_comment2").save();

		// Retrieve all comments
		List<Comment> userPostComments = Comment.find("byUser", user).fetch();

		// Tests
		assertEquals(2, userPostComments.size());

		Comment firstComment = userPostComments.get(0);
		assertNotNull(firstComment);
		assertEquals("Content_comment", firstComment.getComment());

		Comment secondComment = userPostComments.get(1);
		assertNotNull(secondComment);
		assertEquals("Content_comment2", secondComment.getComment());
	}

	@Test
	public void useTheCommentsRelation() {
		// Create a new user and book and save it
		User user = new User("username_c", "password_c", "inf_c").save();
		User user_p = new User("username_p", "password_p", "inf_p").save();
		Book book = new Book("Title_c", "Author_c", "Description_c").save();

		// Create a new post
		Post post = new Post(book, user_p, "PostTitle_c", "Content_c").save();

		// Post a first comment
		post.addComment(user, "Content1");
		post.addComment(user, "Content2");

		// Count things
		assertEquals(2, User.count());
		assertEquals(1, Post.count());
		assertEquals(2, Comment.count());

		// Retrieve Bob's post
		post = Post.find("byUser", user_p).first();
		assertNotNull(post);

		// Navigate to comments
		assertEquals(2, post.comments.size());
		assertEquals("Content1", post.comments.get(0).getComment());

		// Delete the post
		post.delete();

		// Check that all comments have been deleted
		assertEquals(2, User.count());
		assertEquals(0, Post.count());
		assertEquals(0, Comment.count());
	}
}
