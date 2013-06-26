package functional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import models.Book;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import play.modules.paginate.ModelPaginator;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;
import controllers.utils.Constants;

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
	@Ignore
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
	public void renderHtmlByUrl(){
		Response response = GET("/proxy?resourceLink=http://localhost:9000/about");
	
		String inputStreamString = null;
		
		try {
			FileInputStream fis = new FileInputStream("/test/mock/data/proxyControllerTest.txt");
			inputStreamString = new Scanner(fis,"UTF-8").useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
       
		inputStreamString.equals(response.toString());
	}
	
	@Test
	@Ignore
	public void saveAmazonBookIntoDB(){
		Response response = GET("/saveBook?preview_title=TestTitle&preview_author=Testauthor&preview_description=TestDescription&preview_url=TestUrl&preview_img_src=http://localhost:9000/public/images/logo.jpg");
		assertStatus(302, response);
		
		File bookImageFile = new File(Constants.IMAGE_PATH + "1book_front_page.jpg");
		assertTrue(bookImageFile.exists());
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
