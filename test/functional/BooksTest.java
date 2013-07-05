package functional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import models.Book;

import org.apache.commons.httpclient.HttpException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import play.modules.paginate.ModelPaginator;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

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
		assertEquals("url2", book.getBookUrl());
		assertEquals("2book_front_page.jpeg", book.getImageName());
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
		assertEquals("url_test", book.getBookUrl());
		assertEquals("1book_front_page.jpeg", book.getImageName());
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
	
	@Test
	@Ignore
	public void saveAmazonBookIntoDB(){
		Response response = POST("/saveBook?preview_title=TestTitle&preview_author=Testauthor&preview_description=TestDescription&preview_url=TestUrl&preview_img_src=");
		assertStatus(302, response);
	}
	
	@Test
	public void renderHtmlByUrl() {	
		Response response = GET("/proxy?resourceLink=test");
		assertIsOk(response);
	}
	

	@Test
	public void testAddBookToUserList(){
		Fixtures.loadModels("/mock/data/data.yml");
		
		Request request = newRequest();
		request.url = "/addBookToUserList";
		
		Map paramsMap = new HashMap();
		paramsMap.put("id", "1");
		paramsMap.put("username", "User1");
		paramsMap.put("select_user_list", "Already Read");
		
		Response response = POST("/addBookToUserList", paramsMap);
		assertStatus(302, response);	
		
		GET("/book/1");

		Book book = (Book) renderArgs("bookItem");
		assertNotNull(book);
		assertEquals("User1", book.getUsers().get(3).getUsername());
		assertEquals("pass1", book.getUsers().get(0).getPassword());
		assertEquals("Inf1", book.getUsers().get(0).getUserInf());
		assertEquals("userpic1.jpg", book.getUsers().get(0).getImageName());
	}
	
	@Test
	@Ignore
	public void sortBooksByUserCount(){
		Fixtures.loadModels("/mock/data/sortBooks.yml");
		
		GET("/books");
		
		ModelPaginator sortedBooksPaginator = (ModelPaginator) renderArgs("paginatorBooks");
		assertNotNull(sortedBooksPaginator);
		assertEquals(3, sortedBooksPaginator.size());
		
		Book book1 = (Book) sortedBooksPaginator.get(0);
		Book book2 = (Book) sortedBooksPaginator.get(1);
		Book book3 = (Book) sortedBooksPaginator.get(2);
		
		assertEquals("Book_3users", book1.getTitle());
		assertEquals("Book_2users", book2.getTitle());
		assertEquals("Book_1users", book3.getTitle());
	}
}
