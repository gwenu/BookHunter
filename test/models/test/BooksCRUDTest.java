package models.test;

import java.util.List;

import models.Author;
import models.Book;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class BooksCRUDTest extends UnitTest {
	
	@Before
	public void setup(){
		Fixtures.deleteAllModels();
		Fixtures.loadModels("/mock/data/data.yml");
	}
	
	@Test
	public void createAndReadBook(){
		Author author = new Author("TestAuthor1","info1").save();
		new Book(author, "BookTitle", "BookDescription").save();
		
		List<Book> createdBook = Book.find("byTitle", "BookTitle").fetch();
		
		assertNotNull(createdBook);
		assertEquals(1, createdBook.size());
		assertEquals(author, createdBook.get(0).getAuthor());
		assertEquals("BookTitle", createdBook.get(0).getTitle());
		assertEquals("BookDescription", createdBook.get(0).getDescription());
	}
	
	@Test
	public void updateBook(){
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
	
	@Test
	public void deleteBook(){
		Book bookForDelete = Book.find("byTitle", "Title8").first();
		assertNotNull(bookForDelete);
		
		bookForDelete.delete();
		
		Book deletedBook = Book.find("byTitle", "Title8").first();
		assertNull(deletedBook);
	}
}
