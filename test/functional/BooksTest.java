package functional;

import java.util.Iterator;
import java.util.List;

import models.Book;

import org.junit.*;

import play.modules.paginate.ValuePaginator;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;
import play.test.UnitTest;

public class BooksTest extends FunctionalTest {

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("/mock/data/data.yml");
	}

	@Test
	public void retrieveBooksList() {
		Response response = GET("/books");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);

		ValuePaginator booksPaginator = (ValuePaginator) renderArgs("paginatorBooks");
		assertNotNull(booksPaginator);
		assertEquals(8, booksPaginator.size());
		assertEquals(2, booksPaginator.getPageCount());
		assertEquals(4, booksPaginator.getPageSize());
	}
	
	@Test
	public void searchExistingBook(){
		Response response = GET("/books?searchKey=search");
		
		ValuePaginator searchedBooksPaginator = (ValuePaginator) renderArgs("paginatorBooks");
		assertNotNull(searchedBooksPaginator);
		assertEquals(1, searchedBooksPaginator.size());
		assertEquals(1, searchedBooksPaginator.getPageCount());
		
		Book book = (Book) searchedBooksPaginator.get(0);
		assertEquals("SearchTitle1", book.getTitle());
		assertEquals("Description2", book.getDescription());
		assertEquals("Fifth", book.getAuthor().getFullName());
	}
	
	@Test
	public void searchNotExistingBook(){
		Response response = GET("/books?searchKey=qwer");
		
		ValuePaginator searchedBooksPaginator = (ValuePaginator) renderArgs("paginatorBooks");
		assertNotNull(searchedBooksPaginator);
		assertEquals(0, searchedBooksPaginator.size());
	}
}
