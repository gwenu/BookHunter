package models.test;

import java.util.List;

import models.Author;
import models.Book;
import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class BooksCRUDTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
		Fixtures.executeSQL("ALTER TABLE Book ALTER COLUMN id RESTART WITH 1");
	}

	@Test
	public void createAndReadBook() {
		Fixtures.loadModels("/mock/data/data.yml");
		Author author = new Author("TestAuthor1", "info1").save();
		new Book(author, "BookTitle", "BookDescription", "Bookurl", "ImageName").save();

		List<Book> createdBook = Book.find("byTitle", "BookTitle").fetch();

		assertNotNull(createdBook);
		assertEquals(1, createdBook.size());
		assertEquals(author, createdBook.get(0).getAuthor());
		assertEquals("BookTitle", createdBook.get(0).getTitle());
		assertEquals("BookDescription", createdBook.get(0).getDescription());
		assertEquals("Bookurl", createdBook.get(0).getBookUrl());
		assertEquals("ImageName", createdBook.get(0).getImageName());
	}

	@Test
	public void updateBook() {
		Fixtures.loadModels("/mock/data/data.yml");
		// Retrieve Book by Title
		Book book = Book.find("byTitle", "Title1").first();
		assertNotNull(book);
		assertEquals("Description1", book.getDescription());

		// Update author's info and save
		book.setDescription("update description");
		book.save();

		// Retrieve Book by Title and check that obj was updated
		Book updatedBook = Book.find("byTitle", "Title1").first();
		assertNotNull(updatedBook);
		assertEquals("update description", updatedBook.getDescription());
	}
}
