package functional;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import models.Book;

import org.junit.*;

import play.modules.paginate.ModelPaginator;
import play.modules.paginate.ValuePaginator;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;
import play.test.UnitTest;

public class BooksTest extends FunctionalTest {

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
		Fixtures.executeSQL("ALTER TABLE Book ALTER COLUMN id RESTART WITH 1");
	}

	@Test
	public void retrieveBooksList() {
		Fixtures.loadModels("/mock/data/data.yml");

		Response response = GET("/books");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);

		ModelPaginator booksPaginator = (ModelPaginator) renderArgs("paginatorBooks");
		assertNotNull(booksPaginator);
		assertEquals(8, booksPaginator.size());
		assertEquals(2, booksPaginator.getPageCount());
		assertEquals(4, booksPaginator.getPageSize());
	}

	@Test
	public void searchExistingBook() {
		Fixtures.loadModels("/mock/data/data.yml");

		GET("/books?searchKey=search");

		ModelPaginator searchedBooksPaginator = (ModelPaginator) renderArgs("paginatorBooks");
		assertNotNull(searchedBooksPaginator);
		assertEquals(1, searchedBooksPaginator.size());
		assertEquals(1, searchedBooksPaginator.getPageCount());

		Book book = (Book) searchedBooksPaginator.get(0);
		assertEquals("SearchTitle1", book.getTitle());
		assertEquals("Description_search", book.getDescription());
		assertEquals("Fifth", book.getAuthor().getFullName());
	}

	@Test
	public void searchNotExistingBook() {
		Fixtures.loadModels("/mock/data/data.yml");

		GET("/books?searchKey=qwer");

		ModelPaginator searchedBooksPaginator = (ModelPaginator) renderArgs("paginatorBooks");
		assertNotNull(searchedBooksPaginator);
		assertEquals(0, searchedBooksPaginator.size());
	}

	@Test
	public void renderBookPage() {
		Fixtures.loadModels("/mock/data/book.yml");

		GET("/book/1");

		Book book = (Book) renderArgs("bookItem");
		assertNotNull(book);
		assertEquals("Title_book", book.getTitle());
		assertEquals("Description_book", book.getDescription());
		assertEquals("Fifth", book.getAuthor().getFullName());
		assertEquals("book1_front_page.jpeg", book.getImageName());
		assertNotNull(book.getUsers());
		assertEquals(2, book.getUsers().size());
	}

	@Test
	public void retrieveBookUsersList() {
		Fixtures.loadModels("/mock/data/book.yml");

		GET("/book/1");

		Book book = (Book) renderArgs("bookItem");
		assertNotNull(book);
		assertNotNull(book.getUsers());
		assertEquals(2, book.getUsers().size());
		assertEquals("User1", book.getUsers().get(0).getUsername());
		assertEquals("pass1", book.getUsers().get(0).getPassword());
		assertEquals("Inf1", book.getUsers().get(0).getUserInf());
		assertEquals("userpic1.jpg", book.getUsers().get(0).getImageName());
		assertEquals("User2", book.getUsers().get(1).getUsername());
		assertEquals("pass2", book.getUsers().get(1).getPassword());
		assertEquals("Inf2", book.getUsers().get(1).getUserInf());
		assertEquals("userpic2.jpg", book.getUsers().get(1).getImageName());
	}
}
