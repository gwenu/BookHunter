package functional;

import java.util.List;

import models.Book;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class BooksPageTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("/mock/data/data.yml");
	}
	
	@Test
	public void retrieveBooksList(){
		// Retrieve first 5 books in natural order
		List<Book> booksList = Book.all().fetch(5);
		
		assertNotNull(booksList);
		assertEquals("Title1", booksList.get(0).getTitle());
		assertEquals("Title2", booksList.get(1).getTitle());
	}
}
